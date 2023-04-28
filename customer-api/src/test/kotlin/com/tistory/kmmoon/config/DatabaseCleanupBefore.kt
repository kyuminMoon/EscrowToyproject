package com.tistory.kmmoon.config

import com.google.common.base.CaseFormat
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import jakarta.persistence.metamodel.EntityType
import org.springframework.beans.factory.InitializingBean
import org.springframework.transaction.annotation.Transactional
import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors


open class DatabaseCleanupBefore: InitializingBean {
  @PersistenceContext
  private lateinit var entityManager: EntityManager

  private lateinit var tableNames: MutableList<String>

  override fun afterPropertiesSet() {
    val entities: Set<EntityType<*>> = entityManager!!.metamodel.entities
    tableNames = entities.stream()
      .filter(Predicate<EntityType<*>> { e: EntityType<*> -> isEntity(e) && hasTableAnnotation(e) })
      .map(Function<EntityType<*>, String> { e: EntityType<*> ->
        val tableName: String = e.getJavaType().getAnnotation(Table::class.java).name
        if (tableName.isBlank()) CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.name) else tableName
      })
      .collect(Collectors.toList())
    val entityNames: List<String> = entities.stream()
      .filter(Predicate<EntityType<*>> { e: EntityType<*> -> isEntity(e) && !hasTableAnnotation(e) })
      .map(Function<EntityType<*>, String> { e: EntityType<*> -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.name) })
      .toList()
    tableNames.addAll(entityNames)
  }

  private fun isEntity(e: EntityType<*>): Boolean {
    return null != e.getJavaType().getAnnotation(Entity::class.java)
  }

  private fun hasTableAnnotation(e: EntityType<*>): Boolean {
    return null != e.getJavaType().getAnnotation(Table::class.java)
  }

  @Transactional
  open fun execute() {
    entityManager.flush()
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()
    for (tableName in tableNames) {
      entityManager.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
      entityManager.createNativeQuery("ALTER TABLE $tableName ALTER COLUMN ID RESTART WITH 1").executeUpdate()
    }
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
  }
}