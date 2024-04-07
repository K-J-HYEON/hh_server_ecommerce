package hhplus.ecommerce.product.presentation;

import hhplus.ecommerce.product.TestFixtures;
import hhplus.ecommerce.product.application.ProductServiceImpl;
import hhplus.ecommerce.product.domain.Product;
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
    private ProductServiceImpl productsService;

    @Test
    @DisplayName("상품 목록 조회 성공")
    void readProductInfo_success() throws Exception {

        // given
        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");

        given(productsService.readProductInfo()).willReturn(
                List.of(sample1, sample2));

        // when && // then
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.product[0].name").value("신발"),
                        jsonPath("$.product[0].price").value(90000),
                        jsonPath("$.product[1].name").value("바지"),
                        jsonPath("$.product[1].price").value(10000));

    }

    @Test
    @DisplayName("상품 상세 조회 성공")
    void readProductInfoDetails_success() throws Exception {

        // given
        Long productId = 1L;

        Product product = TestFixtures.product("바지");

        given(productsService.readProductInfoDetail(productId)).willReturn(product);

        // when && then
        mockMvc.perform(get("/product/" + productId))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("와이드 팬츠"),
                        jsonPath(".price").value(50000)
                );
    }
}