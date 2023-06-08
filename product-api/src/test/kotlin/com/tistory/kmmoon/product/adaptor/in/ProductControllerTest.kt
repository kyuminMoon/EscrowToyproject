package com.tistory.kmmoon.product.adaptor.`in`

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.kmmoon.common.DatabaseCleanupBefore
import com.tistory.kmmoon.common.UserRole
import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import java.math.BigDecimal
import java.util.*

/**
* XXX : kotlin 코드로 mockmvc를 사용할 경우 Security에 버그가 있어 일부 기능이 사용 불가능하다고 한다.
* https://shanepark.tistory.com/417
* 해당 annotation이 적용되지 않아 일단 주석처리하고 setup으로 대체하여 진행
* @WithMockUser, @WithMockCustomUser
*/
@AutoConfigureMockMvc
class ProductControllerTest(
    @Autowired
    val mvc: MockMvc,
    @Autowired
    val objectMapper: ObjectMapper,
) : DatabaseCleanupBefore() {
    @BeforeEach
    override fun setUp() {
        val loginUser = UserSecurity(
            1,
            "email1",
            "password",
            listOf(SimpleGrantedAuthority(UserRole.ROLE_USER.name))
        )

        val authenticationToken = UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities())
        SecurityContextHolder.getContext().authentication = authenticationToken
    }

    val createProductRequest = ProductCreateRequest(
        name = "상품명_1",
        description = "상품설명_1",
        price = BigDecimal(5000),
        quantity = 5
    )

    /**
     * 상품 생성 시, 재고 테이블도 생성
     * */
    @Test
    @DisplayName("상품 생성")
    fun create() {
        val content: String = objectMapper.writeValueAsString(createProductRequest)

        val perform = mvc.perform(
            post("/user/products").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print())


    }

    /**
     * 상품 정보 및 재고 수정
     *
     * */
    @Test
    @DisplayName("상품 정보 및 재고 수정")
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