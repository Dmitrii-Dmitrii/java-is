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
    implementation("org.hibernate.orm:hibernate-platform:6.4.4.Final")
    implementation("org.hibernate.orm:hibernate-core")

    testImplementation("org.mockito:mockito-core:5.11.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    implementation("org.springframework.boot:spring-boot-starter-security")


    implementation("junit:junit:4.13.1")

    implementation(project(":DAO"))
    implementation(project(":Service"))

    testImplementation("com.h2database:h2:1.4.200")
    implementation("org.postgresql:postgresql:42.2.16")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}