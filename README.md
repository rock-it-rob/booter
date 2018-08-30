# Booter

This project is a simple [spring-boot][1] application running an embedded
[jetty][2] server on port 8080.


## Building

The project is managed by [maven][3] so building is achieved by running:

`mvn package`


## Running

The application can be run by using its jar file:

`java -jar booter-<version>.jar`

If you need to run the server on a different port than 8080 that can
be done by specifying the <em>server.port</em> system property:

`java -Dserver.port=9000 -jar booter-<version>.jar`


## Docker Image(s)

This project provides configuration files for running this application
inside a [Docker][4] container. A production-ready image can be built
using the supplied `Dockerfile`:

`docker build -t booter:latest .`

The above command will build the container image, name it *booter* and
tag it with the tag *latest*.


### Docker Development

Development and testing of the docker image can be achieved by using
[Docker Compose][5]. The supplied `docker-compose.yml` file provides definitions
for building and running a [Docker][4] container and exposing its port, *8080*,
to port *80* on the localhost.

The default `Dockerfile` builds the application using maven inside the
container. This can be a lengthy process. To speed up development the
`docker-compose.yml` includes instructions to mount a volume from the
[maven][3] build directory, *./target*, to the docker image such that
the application can be rebuilt directly using maven without having to
rebuild the entire docker image.


### Environment Variables

The following environment variables are defined and used in the [Docker][4]
image:

* **APPDIR** - Absolute path to the directory where the application
files reside.
* **APPNAME** - Name of the application jar file.


[1]: [https://spring.io/projects/spring-boot]
[2]: https://www.eclipse.org/jetty/
[3]: https://maven.apache.org/
[4]: https://www.docker.com/
[5]: https://docs.docker.com/compose/