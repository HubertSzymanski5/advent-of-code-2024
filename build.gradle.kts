plugins {
    kotlin("jvm") version "2.0.21"
    groovy
}

group = "pl.szymanski.hubert"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.apache.groovy:groovy-all:4.0.22")
    testImplementation("org.spockframework:spock-core:2.3-groovy-4.0")
    testImplementation("org.spockframework:spock-junit4:2.3-groovy-4.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}