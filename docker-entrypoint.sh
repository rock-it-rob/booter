#!/bin/sh

APP=${BINDIR}/${APPNAME}

function run()
{
    java -jar $APP
}

if [ $# -eq 0 ]; then
    run
else
    eval "$@"
fi
