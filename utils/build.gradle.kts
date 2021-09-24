plugins {
    kotlin("jvm")
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "by.ese.components"
version = "1.3-SNAPSHOT"
description = "Utility classes"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

