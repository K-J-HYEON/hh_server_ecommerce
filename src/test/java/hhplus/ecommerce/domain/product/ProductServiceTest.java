package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;

class ProductServiceTest {

    private ProductReader productReader;

    private ProductUpdater productUpdater;

    private ProductValidator productValidator;

    private ProductService productService;

    @BeforeEach
    void setUp() {

        productReader = mock(ProductReader.class);
        productUpdater = mock(ProductUpdater.class);
        productService = new ProductService(productReader, productUpdater, productValidator);
    }

    @Test
    @DisplayName("상품 목록 조회")
    void read_product_info() {

        // given
        Product sample1 = TestFixtures.product("신발");
        Product sample2 = TestFixtures.product("바지");
        given(productReader.retrieveAll()).willReturn(List.of(sample1, sample2));

        // when
        List<Product> products = productService.readProductInfo();


        // then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).name()).isEqualTo("신발");
        assertThat(products.get(0).price()).isEqualTo(90000);
        assertThat(products.get(1).name()).isEqualTo("바지");
        assertThat(products.get(1).price()).isEqualTo(10000);
    }

    @Test
    @DisplayName("상품 상세 정보 조회")
    void read_product_info_detail() {

        // given
        Long productId = 1L;

        Product product = TestFixtures.product("신발");

        given(productReader.retrieveById(any())).willReturn(product);

        // when
        Product productDetail = productService.readProductInfoDetail(productId);

        // then
        assertThat(productDetail.name()).isEqualTo("신발");
        assertThat(productDetail.price()).isEqualTo(90000);
        assertThat(productDetail.stockCount()).isEqualTo(10);
        assertThat(productDetail.size()).isEqualTo("270");
        assertThat(productDetail.color()).isEqualTo("화이트");
    }


    @Test
    @DisplayName("인기 상품 정보 조회")
    void read_popular_products() {
        // Given
        Product product1 = TestFixtures.product("신발");
        Product product2 = TestFixtures.product("바지");

        given(productReader.retrievePopularProducts()).willReturn(
                List.of(product1, product2)
        );

        // When
        List<Product> popularProducts = productService.readPopularProducts();

        // Then
        assertThat(popularProducts.size()).isEqualTo(2);
    }
}