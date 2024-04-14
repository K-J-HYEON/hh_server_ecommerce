package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("상품목록 조회 성공")
    void readProductInfoSuccess() throws Exception {

        // given
        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");

        given(productService.readProductInfo()).willReturn(
                List.of(sample1, sample2));

        // when && // then
        mockMvc.perform(get("/ecommerce/api/product"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.product[0].name").value("신발"),
                        jsonPath("$.product[0].price").value(90000),
                        jsonPath("$.product[1].name").value("바지"),
                        jsonPath("$.product[1].price").value(10000));

    }

    @Test
    @DisplayName("상품정보 상세조회 성공")
    void readProductInfoDetailSuccess() throws Exception {

        // given
        Long productId = 1L;

        Product product = TestFixtures.product("바지");

        given(productService.readProductInfoDetail(productId)).willReturn(product);

        // when && then
        mockMvc.perform(get("/ecommerce/api/product/" + productId))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("와이드 팬츠"),
                        jsonPath(".price").value(50000)
                );
    }

    @Test
    @DisplayName("인기 상품 조회 성공")
    void readPopularProductsInfoSuccess() throws Exception {
        // Given
        Product product1 = TestFixtures.product("신발");
        Product product2 = TestFixtures.product("바지");

        given(productService.readPopularProducts()).willReturn(
                List.of(product1, product2)
        );

        // When && Then
        mockMvc.perform(get("/ecommerce/api/product"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.products[0].name").value("나이키 에어포스"),
                        jsonPath("$.products[1].name").value("맨투맨")
                );
    }
}