import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
  implementation(project(":customer-rds-entity"))
  implementation(Dependencies.KAFKA)
  implementation(Dependencies.KAFKA_STREAM)
  implementation(Dependencies.CORE)
  implementation(Dependencies.JPA)
  implementation(Dependencies.JPA_KAPT)
  testImplementation(Dependencies.TEST)
}
