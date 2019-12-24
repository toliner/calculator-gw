plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "dev.toliner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jfoenix:jfoenix:9.0.9")
    implementation("org.jetbrains:annotations:18.0.0")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks {
    withType<JavaCompile> {
        options.encoding = "utf-8"
    }
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClassName = "dev.toliner.calcgw.CalculatorApplication"
}