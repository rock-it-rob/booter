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

# References

[1]: https://spring.io/projects/spring-boot
[2]: https://www.eclipse.org/jetty/
[3]: https://maven.apache.org/
