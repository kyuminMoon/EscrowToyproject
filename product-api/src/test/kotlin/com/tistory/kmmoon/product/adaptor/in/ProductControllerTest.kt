package com.tistory.kmmoon.product.adaptor.`in`

import com.fasterxml.jackson.databind.ObjectMapper
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.tistory.kmmoon.common.DatabaseCleanupBefore
import com.tistory.kmmoon.common.UserRole
import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest
import net.jqwik.api.Arbitraries
import org.assertj.core.api.BDDAssertions.then
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.time.Instant
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
    val sut: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .build()
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


    /**
     * 상품 생성 시, 재고 테이블도 생성
     * */
    @Test
    @DisplayName("상품 생성")
    fun create() {
        val actual: ProductCreateRequest = sut.giveMeBuilder(ProductCreateRequest::class.java)
            .set("name", Arbitraries.strings().ofMinLength(2).ofMaxLength(50).ascii())
            .set("description", Arbitraries.strings().ofMinLength(2).ofMaxLength(1000).ascii())
            .set("price", Arbitraries.integers().between(100, 100_000_000))
            .set("quantity", Arbitraries.integers().between(1, 10_000))
            .sample()

        val content: String = objectMapper.writeValueAsString(actual)

        mvc.perform(
            post("/user/products").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print())
            .andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
                jsonPath("success").value(Matchers.`is`(true)),
                jsonPath("data").exists()
            )
    }

    /**
     * 상품 정보 및 재고 수정
     *
     * */
    @Test
    @DisplayName("상품 정보 및 재고 수정")
    fun modify() {
        val actual: ProductModifyRequest = sut.giveMeBuilder(ProductModifyRequest::class.java)
            .set("name", Arbitraries.strings().ofMinLength(2).ofMaxLength(50).ascii())
            .set("description", Arbitraries.strings().ofMinLength(2).ofMaxLength(1000).ascii())
            .set("price", Arbitraries.integers().between(100, 100_000_000))
            .set("quantity", Arbitraries.integers().between(1, 10_000))
            .sample()

        val content: String = objectMapper.writeValueAsString(actual)

        mvc.perform(
            put("/user/products/{productId}", 1).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
        ).andDo(print())
            .andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
                jsonPath("success").value(Matchers.`is`(true)),
                jsonPath("data").exists()
            )
    }

    /**
     * Soft delete
     * */
    @Test
    @DisplayName("상품 삭제")
    fun delete() {
        mvc.perform(
            delete("/user/products/{productId}", 1).with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
                jsonPath("success").value(Matchers.`is`(true)),
            )
    }
}