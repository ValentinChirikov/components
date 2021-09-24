plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    `maven-publish`
}

group = "by.ese.components"
version = "1.3-SNAPSHOT"
description = "Domains"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }

    js {
        browser()
    }

    val queryDslVersion = "4.4.0"
    sourceSets {

        val jvmMain by getting {
            dependencies {
                implementation("jakarta.validation:jakarta.validation-api:2.0.2")
                implementation("jakarta.annotation:jakarta.annotation-api:1.3.5")
                implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                implementation("com.querydsl:querydsl-apt:$queryDslVersion")
                implementation(project(":utils"))

                configurations.getByName("kapt")
                    .dependencies
                    .add(project.dependencies.create("com.querydsl:querydsl-apt:$queryDslVersion:jpa"))
            }

            kotlin.srcDir("$buildDir/generated/source/kapt/main")
        }
    }
}
