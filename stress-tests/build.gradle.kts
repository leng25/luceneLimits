plugins {
    java
    id("io.gatling.gradle") version "3.15.0.2"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
