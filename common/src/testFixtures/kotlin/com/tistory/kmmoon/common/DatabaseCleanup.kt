package com.tistory.kmmoon.common

import com.google.common.base.CaseFormat
import jakarta.persistence.metamodel.EntityType
import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Table
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Component
class DatabaseCleanup: InitializingBean {

  @PersistenceContext
  private lateinit var entityManager: EntityManager;

  private lateinit var tableNames: MutableList<String>

  override fun afterPropertiesSet() {

    val entities = entityManager.metamodel.entities
    tableNames = entities.stream()
      .filter{e -> isEntity(e) && hasTableAnnotation(e)}
      .map{e -> e.getJavaType().getAnnotation(Table::class.java).name}
      .collect(Collectors.toList());

    val entityNames = entities.stream()
      .filter{e -> isEntity(e) && !hasTableAnnotation(e)}
    .map{e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName())}
    .toList();

    tableNames.addAll(entityNames);
  }

  private fun isEntity(e: EntityType<*>): Boolean {
    return e.javaType.getAnnotation(Entity::class.java) != null
  }


  private fun hasTableAnnotation(e : EntityType<*>): Boolean {
    return e.javaType.getAnnotation(Table::class.java) != null
  }


  @Transactional
  fun execute () {
    entityManager.flush()
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()

    for(tableName in tableNames) {
      if(tableName != "authority") {
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate()
        entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate()
      }
    }

      entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
  }

}