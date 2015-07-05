# cljs-lambda

A Lein plugin, template and small Clojurescript library for exposing functions
via [AWS Lambda](http://aws.amazon.com/documentation/lambda/).

```sh
$ lein new cljs-lambda my-lambda-project
$ cd my-lambda-project
$ lein cljs-lambda default-iam-role
$ lein cljs-lambda deploy
$ lein cljs-lambda invoke work-magic '{"variety": "black"}'
```

The above requires a recent [Node](https://nodejs.org/) runtime, and a properly-configured (`aws configure`) [AWS CLI](https://github.com/aws/aws-cli) installation.

Or, put:

[![Clojars
Project](http://clojars.org/io.nervous/lein-cljs-lambda/latest-version.svg)](http://clojars.org/io.nervous/lein-cljs-lambda)

In your Clojurescript project's `:plugins` vector.

# The Plugin

## project.clj Excerpt
From [the example project](https://github.com/nervous-systems/cljs-lambda/blob/master/example/project.clj):

```clojure
{:cljs-lambda
 {:cljs-build-id "cljs-lambda-example"
  :defaults
  {:role   "arn:aws:iam::151963828411:role/lambda_basic_execution"}
 :functions
 [{:name   "dog-bark"
   :invoke cljs-lambda-example.dog/bark}
  {:name   "cat-meow"
   :invoke cljs-lambda-example.cat/meow}]}}
```

## Function Configuration

The following keys are valid for entries in `[:cljs-lambda :functions]`.  The optional keys are listed at the end, alongside their defaults:

```clojure
{:name "the-lambda-function-name"
 :invoke my-cljs-namespace.module/fn
 :role "arn:..."
 [:description "I don't think this field sees much action"
  :create true
  :timeout 3 ;; seconds
  :memory-size 128]} ;; MB
```

Values in `[:cljs-lambda :defaults]` will be merged into each function map.  These values are written to Lambda on  `deploy`.  Alternatively:

```sh
$ lein cljs-lambda update-config
```

Will update the remote (Lambda) configuration of all of the functions listed in the project file.  The `:create` key, defaulting to `true`, determines whether a `create-function` Lambda command will be issued if an attempt is made to `deploy` a not-yet-existing Lambda function.

## cljsbuild

The plugin depends on `cljsbuild`, and assumes there is a `:cljsbuild` section
in your `project.clj`.  A deployment or build via `cljs-lambda` invokes
`cljsbuild` - it'll run either the first build in the `:builds` vector, or the
one identified by `[:cljs-lambda :cljs-build-id]`. 

Source map support will be enabled if the `:source-map` key of the active build
is `true`.

## Limitations

 - `deploy` means "deploy all functions in this project".  It could be changed pretty easily.
 - I guess we ought to use the docstring for `:description`, if none is supplied.
 - PR's welcome.

# The Library

[![Clojars Project](http://clojars.org/io.nervous/cljs-lambda/latest-version.svg)](http://clojars.org/io.nervous/cljs-lambda)

It's pretty tiny - please see the example project, or the project generated by `lein new cljs-lambda`.
