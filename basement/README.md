# basement

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Developing

You can start your ring webserver inside your IDEs REPL with:
  (require 'basement.handler)
  (use 'ring.adapter.jetty)
  (defonce server (run-jetty #'basement.handler/basement {:port 8080 :join? false}))

## EJDB

ejdb is a dependency of this project which can't be found in a common maven repository at the moment.
It comes with a native lib, therefore the easiest way is to build it for yourself.
Get it from https://github.com/Softmotions/ejdb-java

After that, create a local maven repository and add it to the project with:
1) mkdir maven_repository
2) mvn deploy:deploy-file -Dfile=jejdb-1.0.2.jar -DartifactId=jejdb -Dversion=1.0.2 -DgroupId=jejdb -Dpackaging=jar -Durl=file:maven_repository
3) Add to your project.clj:
    :repositories {"local" "file:maven_repository"}
4) lein deps

The native library file should be in your java.library.path, check it with:
    (System/getProperty "java.library.path")

After that you should be able to instantiate EJDB with:
    (org.ejdb.driver.EJDB.)

## License

Copyright © 2015 FIXME
