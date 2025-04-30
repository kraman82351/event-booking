# Located at ./apps/irctc_discovery/Dockerfile

# Step 1: Builder image
FROM eclipse-temurin:21-jdk AS builder

ARG REVISION=0.0.1-SNAPSHOT

WORKDIR /build

# Copy only necessary files first for better layer caching
COPY ./mvnw .
COPY ./.mvn ./.mvn
COPY ./pom.xml .

RUN chmod +x ./mvnw

# Download parent POM dependencies
RUN ./mvnw dependency:go-offline -N -Drevision=${REVISION}

# Copy the rest of the source code
COPY ./libs ./libs
COPY ./apps ./apps

# Grant execute permission
RUN chmod +x ./mvnw

# Build the specific application module (and its dependencies)
# Maven automatically builds dependencies like irctc_common first.
RUN ./mvnw clean package -pl apps/irctc_discovery -am -Drevision=${REVISION} -DskipTests
# '-am' also builds required dependencies

# Step 2: Runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the executable JAR created by spring-boot-maven-plugin
COPY --from=builder /build/apps/irctc_discovery/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]