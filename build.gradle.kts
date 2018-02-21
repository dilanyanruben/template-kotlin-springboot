import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "1.0.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/milestone")
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.RC1")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.2")
    }
}

apply {
    plugin("java")
    plugin("org.springframework.boot")
    plugin("org.junit.platform.gradle.plugin")
}

plugins {
    val kotlinVersion = "1.2.21"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.4.RELEASE"
}

repositories {
    mavenCentral()
    maven("http://repo.spring.io/milestone")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.0.0.RC1")
    }
}

dependencies {
    // Compile dependencies
    compile("org.springframework.boot:spring-boot-starter-jersey")
    compile("org.springframework.boot:spring-boot-starter-jetty")
    compile("org.springframework.boot:spring-boot-starter-logging")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("org.hsqldb:hsqldb")
    compile("org.apache.commons:commons-lang3")
    // Test dependencies
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

