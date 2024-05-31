import org.gradle.api.credentials.PasswordCredentials

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.7/samples
 */

plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    `kotlin-dsl`
    `maven-publish`
}

val releaseTag = System.getenv("RELEASE_TAG")
val pluginVersion = if (releaseTag != null) {
    val pluginVersion = releaseTag.substring(1)
    println("Detected Release Version: $pluginVersion")
    pluginVersion
} else {
    val plugin_version: String by project
    println("Detected Local Version: $plugin_version")
    plugin_version
}

if (pluginVersion.isEmpty()) {
    throw IllegalStateException("Failed to detect version")
}

version = pluginVersion
group = "com.kneelawk"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("versioningPlugin") {
            id = "com.kneelawk.versioning"
            implementationClass = "com.kneelawk.versioning.VersioningPlugin"
        }
    }
}

java {
    withSourcesJar()
}

publishing {
    repositories {
        val publishRepo = System.getenv("PUBLISH_REPO")
        if (publishRepo != null) {
            maven {
                name = "publishRepo"
                url = uri(rootProject.file(publishRepo))
            }
        }
        if (project.hasProperty("kneelawkUsername") && project.hasProperty("kneelawkPassword")) {
            maven {
                name = "kneelawk"
                url = project.uri("https://maven.kneelawk.com/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}
