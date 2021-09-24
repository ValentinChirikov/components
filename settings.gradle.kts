pluginManagement {
    val kotlinVersion = "1.5.21"

    plugins {
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("jvm") version kotlinVersion apply false
        kotlin("kapt") version kotlinVersion apply false
    }

    repositories {
        gradlePluginPortal()
    }

}

rootProject.name = "components"
include("utils", "domains", "microservices")