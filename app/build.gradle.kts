plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.0"

    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") 
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")

    mavenLocal()
}

dependencies {
    library(kotlin("stdlib"))
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    library("com.google.code.gson", "gson", "2.10.1")
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
    version = "1.1.3"
    author = "Woomymy"
    main = "be.woomy.chatplugin.Plugin"
    apiVersion = "1.20"
}

tasks.jar {
    archiveFileName.set("${bukkit.name}-v${bukkit.version}.jar")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
