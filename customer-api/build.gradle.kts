import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = true
bootJar.mainClass.set("com.tistory.kmmoon.CustomerApiApplicationKt")
bootJar.manifest {
    attributes(
        mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        )
    )
}

jar.enabled = true

ext {
    set("mainClassName", "com.tistory.kmmoon.CustomerApiApplicationKt")
}

apply<JibConfigPlugin>()

dependencies {
    implementation(project(":customer-rds-entity"))
    implementation(project(":common"))
    implementation(project(":core"))

    implementation(Dependencies.API)
    implementation(Dependencies.JPA)
    implementation(Dependencies.KAFKA)
    implementation(Dependencies.KAFKA_STREAM)
    implementation(Dependencies.FEIGN)
    implementation(Dependencies.REDIS)
//    testImplementation(project(mapOf("path" to ":common")))
    testImplementation(Dependencies.TEST)
    testRuntimeOnly("com.h2database:h2")

    testImplementation(testFixtures(project(":common")))
    kapt(Dependencies.JPA_KAPT)
}
