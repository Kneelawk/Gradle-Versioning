# Gradle-Versioning
Versioning plugin I use in all my gradle build scripts

## Using This Plugin

Add the plugin to your project by adding the following to your `settings.gradle.kts` file:

```kotlin
pluginManagement { 
    repositories { 
        maven("https://maven.kneelawk.com/releases/") { name = "Kneelawk" }
    }
    plugins {
        id("com.kneelawk.versioning") version "<version>"
    }
}
```

Then include the following in your `build.gradle.kts` file:

```kotlin
id {
    id("com.kneelawk.versioning")
}
```

Finally, add the following to your `gradle.properties` file:

```properties
project_version = <your-project-version>
```
