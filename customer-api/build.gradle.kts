import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = true
bootJar.mainClass.set("com.tistory.kmmoon.PaymentApiApplicationKt")
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
    set("mainClassName", "com.tistory.kmmoon.PaymentApiApplicationKt")
}

apply<JibConfigPlugin>()

dependencies {
    implementation(project(":customer-rds-entity"))
    implementation(project(":common"))

    implementation(Dependencies.API)
    implementation(Dependencies.JPA)
    implementation(Dependencies.KAFKA)
    implementation(Dependencies.KAFKA_STREAM)
    implementation(Dependencies.FEIGN)
    implementation(Dependencies.REDIS)
    kapt(Dependencies.JPA_KAPT)
}
