package com.tistory.kmmoon.product.adaptor.`in`

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.kmmoon.common.DatabaseCleanupBefore
import com.tistory.kmmoon.common.WithMockCustomUser
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*

import java.math.BigDecimal
import java.util.*

@AutoConfigureMockMvc
@WithMockUser(roles = ["USER"], username = "test")
class ProductControllerTest(
    @Autowired
    val mvc: MockMvc,
    @Autowired
    val objectMapper: ObjectMapper,
) : DatabaseCleanupBefore() {

    val createProductRequest = ProductCreateRequest(
        name = "상품명_1",
        description = "상품설명_1",
        price = BigDecimal(5000),
        quantity = 5
    )
//    LocalDateTime.of(2032,4,30,10,10,19),

    /**
     * 상품 생성 시, 재고 테이블도 생성
     * */
    @WithMockCustomUser
    @Test
    @DisplayName("상품 생성")
    fun create() {
        val content: String = objectMapper.writeValueAsString(createProductRequest)

        val perform = mvc.perform(
            post("/products")//.with(csrf())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
//                .param("page", "0")
//                .param("size", "20")
        ).andDo(print())




    }

    /**
     * 재고 수정 시,
     *
     * */
    @DisplayName("상품 재고 수정")
    fun modify() {
    }

    /**
     * Soft delete
     * */
    @Test
    @DisplayName("상품 삭제")
    fun delete() {
    }
}