package com.tistory.kmmoon.product.adaptor.`in`

import com.tistory.kmmoon.product.application.port.`in`.ProductQueryUseCase
import com.tistory.kmmoon.product.domain.Product
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
  var productQueryUseCase: ProductQueryUseCase
) {

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<Product>> {
    return ResponseEntity
      .ok()
      .body(productQueryUseCase.findAll())
  }
}