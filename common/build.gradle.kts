import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    id("java-test-fixtures")
    id ("java-library")
    id ("maven-publish")
}

dependencies {
    testImplementation(Dependencies.COMMON)
    testImplementation(Dependencies.JPA_KAPT)


    testFixturesImplementation("org.springframework:spring-tx")
    testFixturesImplementation("com.google.guava:guava:31.1-jre")

    testFixturesImplementation("com.querydsl:querydsl-jpa:${Versions.querydslVersion}:jakarta")
    testFixturesImplementation("com.querydsl:querydsl-apt:${Versions.querydslVersion}:jakarta")
    testFixturesImplementation("jakarta.persistence:jakarta.persistence-api")
    testFixturesImplementation("jakarta.annotation:jakarta.annotation-api")//    testFixtures(Dependencies.COMMON)
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-web")//    testFixtures(Dependencies.COMMON)

    //testFixture
//    testFixtures("org.springframework.boot:spring-boot-starter-test")
}
