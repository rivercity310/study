plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.reflections:reflections:0.10.2")
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("ch.qos.logback:logback-classic:1.2.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}