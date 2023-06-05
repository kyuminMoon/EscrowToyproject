package com.tistory.kmmoon.product.adaptor.`in`

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.product.application.port.`in`.ProductQueryUseCase
import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/products")
class ProductController(
  var productQueryUseCase: ProductQueryUseCase
) {

  @GetMapping("/all")
  fun findAll(): ResponseEntity<List<Product>> {
    return ResponseEntity
      .ok()
      .body(productQueryUseCase.findAll())
  }

  @PostMapping
  fun create(@AuthenticationPrincipal userSecurity: UserSecurity, @RequestBody productCreateRequest: ProductCreateRequest): ResponseEntity<Product> {
    return ResponseEntity
      .ok()
      .body(productQueryUseCase.create(userSecurity.getId(), productCreateRequest))
  }

  @PutMapping("/{productId}")
  fun modify(@AuthenticationPrincipal userSecurity: UserSecurity, @PathVariable productId: Long, @RequestBody productModifyRequest: ProductModifyRequest): ResponseEntity<Product> {
    productModifyRequest.id = productId
    productModifyRequest.userId = userSecurity.getId()
    return ResponseEntity
      .ok()
      .body(productQueryUseCase.modify(productModifyRequest))
  }

  @DeleteMapping("/{productId}")
  fun delete(@AuthenticationPrincipal userSecurity: UserSecurity, @PathVariable productId: Long): ResponseEntity<Void> {
    productQueryUseCase.delete(productId, userSecurity.getId())
    return ResponseEntity
      .ok().build()

  }
}