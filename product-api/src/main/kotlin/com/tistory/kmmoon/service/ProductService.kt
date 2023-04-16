package com.tistory.kmmoon.service

import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.product.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductService {

  @Autowired
  lateinit var productRepository: ProductRepository;

  fun findAll(): List<ProductEntity>? {
    return productRepository.findAllBy();
  }

}