package com.tistory.kmmoon.product.adaptor.`in`.web

import com.tistory.kmmoon.core.exception.CommonResponse
import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.product.application.port.`in`.ProductCreateUseCase
import com.tistory.kmmoon.product.application.port.`in`.ProductDeleteUseCase
import com.tistory.kmmoon.product.application.port.`in`.ProductModifyUseCase
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
  var productQueryUseCase: ProductQueryUseCase,
  var productCreateUseCase: ProductCreateUseCase,
  var productModifyUseCase: ProductModifyUseCase,
  var productDeleteUseCase: ProductDeleteUseCase
) {

  @GetMapping("/all")
  fun findAll(): ResponseEntity<List<Product>> {
    return ResponseEntity
      .ok()
      .body(productQueryUseCase.findAll())
  }

  @PostMapping
  fun create(@AuthenticationPrincipal userSecurity: UserSecurity, @RequestBody productCreateRequest: ProductCreateRequest): ResponseEntity<CommonResponse<Product>> {
    return ResponseEntity
      .ok()
      .body(productCreateUseCase.create(userSecurity.getId(), productCreateRequest))
  }

  @PutMapping("/{productId}")
  fun modify(@AuthenticationPrincipal userSecurity: UserSecurity, @PathVariable productId: Long, @RequestBody productModifyRequest: ProductModifyRequest): ResponseEntity<Product> {
    productModifyRequest.id = productId
    return ResponseEntity
      .ok()
      .body(productModifyUseCase.modify(userSecurity.getId(), productModifyRequest))
  }

  @DeleteMapping("/{productId}")
  fun delete(@AuthenticationPrincipal userSecurity: UserSecurity, @PathVariable productId: Long): ResponseEntity<Void> {
    productDeleteUseCase.delete(userSecurity.getId(), productId)
    return ResponseEntity
      .ok().build()
  }
}