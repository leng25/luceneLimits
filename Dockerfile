# Stage 1: build
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /build
COPY . .
RUN ./gradlew :app:installDist --no-daemon

# Stage 2: run
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /build/app/build/install/app .
ENTRYPOINT ["bin/app"]
