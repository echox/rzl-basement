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

## License

Copyright © 2015 FIXME
