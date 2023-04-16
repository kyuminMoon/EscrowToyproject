package com.tistory.kmmoon.web.inbound

import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

  @Autowired
  private lateinit var productService: ProductService

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<ProductEntity>> {
    return ResponseEntity
      .ok()
      .body(productService.findAll())
  }
}