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

    testFixturesImplementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    testFixturesImplementation("com.querydsl:querydsl-apt:5.0.0:jakarta")
    testFixturesImplementation("jakarta.persistence:jakarta.persistence-api")
    testFixturesImplementation("jakarta.annotation:jakarta.annotation-api")//    testFixtures(Dependencies.COMMON)
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-web")//    testFixtures(Dependencies.COMMON)

}
