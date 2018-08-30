FROM openjdk:8-alpine

ENV APPDIR=/app

# The application name and location are used in the entrypoint shell script.
ENV BINDIR=/bin
ENV APPNAME=booter.jar

# Expose the 8080 port which is where spring boot runs by default.
EXPOSE 8080

# Set the entrypoint.
COPY docker-entrypoint.sh .
RUN chmod 755 docker-entrypoint.sh
ENTRYPOINT [ "/docker-entrypoint.sh" ]

# Set the healthcheck.
COPY healthcheck.sh ./
RUN chmod 755 healthcheck.sh
HEALTHCHECK --timeout=2s CMD /healthcheck.sh

# Add the necessary build tools.
RUN apk add --no-cache \
    curl \
    maven

WORKDIR $APPDIR

# Copy the source of the project into the image.
COPY src ./src/
COPY pom.xml ./

# Build the application. Since maven downloads a LOT of files we will delete them after the build
# is complete in order to keep the image size down. This isn't strictly necessary.
RUN mvn -Doutput.dir=$BINDIR package
RUN rm -r ~/.m2
