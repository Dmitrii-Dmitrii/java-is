plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

group = "ru.Onshin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    implementation("jakarta.transaction:jakarta.transaction-api")

    implementation("org.hibernate.orm:hibernate-platform:6.4.4.Final")
    implementation("org.hibernate.orm:hibernate-core")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.kafka:spring-kafka:3.0.10")

    implementation(project(":owners:owners-models"))
    implementation(project(":cats:cats-client"))

    implementation("org.postgresql:postgresql:42.7.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}