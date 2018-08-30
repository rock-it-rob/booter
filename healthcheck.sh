#!/bin/sh

SCHEME=http
HOST=localhost
PORT=8080
URI=/health

url=${SCHEME}://${HOST}:${PORT}/${URI}

status=`curl $url --stderr /dev/null -o /dev/null -w %{http_code}`

if [ $status -ne 200 ]; then exit 1; fi
