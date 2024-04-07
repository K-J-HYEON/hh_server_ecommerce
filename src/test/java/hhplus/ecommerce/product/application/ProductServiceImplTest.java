package hhplus.ecommerce.product.application;

import hhplus.ecommerce.product.TestFixtures;
import hhplus.ecommerce.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;
    private ProductRetrieve productRetrieve;

    @BeforeEach
    void setUp() {
        productRetrieve = mock(ProductRetrieve.class);

        productService = new ProductServiceImpl(productRetrieve);
    }

    @Test
    @DisplayName("상품 목록 조회")
    void readProductInfo() {

        // given
        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");
        given(productRetrieve.readAll()).willReturn(List.of(sample1, sample2));

        // when
        List<Product> products = productService.readProductInfo();


        // then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).name()).isEqualTo("나이키 에어포스");
        assertThat(products.get(0).price()).isEqualTo(90000);
        assertThat(products.get(1).name()).isEqualTo("와이드 팬츠");
        assertThat(products.get(1).price()).isEqualTo(10000);
    }

    @Test
    @DisplayName("상품 상세 정보 조회")
    void readProductInfoDetails() {

        // given
        Long productId = 1L;

        Product product = TestFixtures.product("신발");

        given(productRetrieve.readById(any())).willReturn(product);

        // when
        Product productDetail = productService.readProductInfoDetail(productId);

        // then
        assertThat(productDetail.name()).isEqualTo("나이키 에어포스");
        assertThat(productDetail.price()).isEqualTo(90000);
        assertThat(productDetail.stock()).isEqualTo(10);
        assertThat(productDetail.size()).isEqualTo("270");
        assertThat(productDetail.color()).isEqualTo("화이트");
    }
}