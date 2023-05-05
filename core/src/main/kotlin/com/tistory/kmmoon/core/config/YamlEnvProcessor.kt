//package com.tistory.kmmoon.core.config
//
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.env.EnvironmentPostProcessor
//import org.springframework.core.env.ConfigurableEnvironment
//import org.springframework.core.env.PropertiesPropertySource
//import org.springframework.core.io.ClassPathResource
//import org.springframework.core.io.support.PropertiesLoaderUtils
//import java.io.IOException
//import java.util.Properties
//
//
//class CustomEnvironmentPostProcessor : EnvironmentPostProcessor {
//  override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
//    val activeProfile = if (environment.activeProfiles.size > 0) environment.activeProfiles[0] else "default"
//    val customConfigFileName = CUSTOM_CONFIG_FILE_PREFIX + activeProfile + CUSTOM_CONFIG_FILE_SUFFIX
//    try {
//      val resource = ClassPathResource(customConfigFileName)
//      if (resource.exists()) {
//        val properties: Properties = PropertiesLoaderUtils.loadProperties(resource)
//        val propertySource = PropertiesPropertySource("yml", properties)
//        environment.propertySources.addLast(propertySource)
//      }
//    } catch (e: IOException) {
//      throw RuntimeException("Failed to load $customConfigFileName file.", e)
//    }
//  }
//
//  companion object {
//    private const val CUSTOM_CONFIG_FILE_PREFIX = "application-"
//    private const val CUSTOM_CONFIG_FILE_SUFFIX = ".yml"
//  }
//}