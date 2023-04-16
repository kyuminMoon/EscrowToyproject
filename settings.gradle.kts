rootProject.name = "escro-payment-toy"

include("payment-rds-entity")
include("payment-api")
include("product-rds-entity")
include("product-api")
include("order-rds-entity")
include("order-api")
include("customer-rds-entity")
include("customer-api")
include("common")
include("escro-payment-front:payment-front-api")
findProject(":escro-payment-front:payment-front-api")?.name = "payment-front-api"

pluginManagement {
    val kotlinVersion = "1.7.22"
    val springBootVersion = "3.0.5"
    val springDependencyManagementVersion = "1.1.0"
    val jibVersion = "3.3.1"
    val koverVersion = "0.6.1"

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "com.google.cloud.tools.jib" -> useVersion(jibVersion)
                "org.jetbrains.kotlinx.kover" -> useVersion(koverVersion)
            }
        }
    }
}
