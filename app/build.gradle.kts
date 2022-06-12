plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.31"

    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")

    mavenLocal()
}

dependencies {
    library(kotlin("stdlib"))
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")
    compileOnly("org.spigotmc:spigot-api:1.18-R0.1-SNAPSHOT") // The Spigot API with no shadowing. Requires the OSS repo.
    library("com.google.code.gson", "gson", "2.8.7")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest()
        }
    }
}

application {
    // Define the main class for the application.
    mainClass.set("ChatPlugin.Plugin")
}

bukkit {
    name = "ChatDatePlugin"
    version = "1.1.0"
    author = "Woomymy"
    main = "ovh.woomy.chatplugin.Plugin"
    apiVersion = "1.18"
}

