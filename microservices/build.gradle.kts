plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "by.ese.components"
version = "1.3-SNAPSHOT"
description = "Microservices"

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
