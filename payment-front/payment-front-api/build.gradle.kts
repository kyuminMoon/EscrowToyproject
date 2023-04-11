import com.github.gradle.node.npm.task.NpxTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("com.github.node-gradle.node") version "3.3.0"
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

jar.enabled = true
bootJar.enabled = true
bootJar.mainClass.set("com.tistory.kmmoon.PaymentFrontApiApplicationKt")
bootJar.manifest {
    attributes(
        mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        )
    )
}

var frontUiDir = "$rootDir/payment-front/payment-front-ui"

ext {
    set("mainClassName", "com.tistory.kmmoon.PaymentFrontApiApplicationKt")
}

dependencies {
    implementation(project(":payment-support"))
    implementation(Dependencies.API)
    implementation(Dependencies.FEIGN)
}

apply<JibConfigPlugin>()

tasks.npmSetup {
    doFirst {
        // doSomething
    }
}

node {
    version.set("16.14.2")
    nodeProjectDir.set(file(frontUiDir))
    download.set(true)
}

tasks.npmInstall {
    nodeModulesOutputFilter {
        exclude("notExistingFile")
    }
}

tasks.processResources {
    from("$frontUiDir/dist/") {
        into("static")
    }
    dependsOn(buildFrontend)
}

val buildFrontend by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command.set("npm")
    args.set(listOf("run", "build"))
}
