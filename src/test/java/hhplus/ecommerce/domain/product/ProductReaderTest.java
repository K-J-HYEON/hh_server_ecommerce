package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class ProductReaderTest {

    private ProductRepository productRepository;

    private ProductReader productReader;

    @BeforeEach
    void setUp() {
//        productRepository = mock(ProductRepository.class);

        productReader = new ProductReader(productRepository);
    }

    @Test
    @DisplayName("상품 목록을 불러옴")
    void read_all() {
        // given
        Product product1 = TestFixtures.product("신발");
        Product product2 = TestFixtures.product("바지");

        given(productRepository.findAll()).willReturn(List.of(product1, product2));

        // when
        List<Product> products = productReader.retrieveAll();

        // Then
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).name()).isEqualTo("신발");
        assertThat(products.get(1).name()).isEqualTo("바지");
    }

    @Test
    @DisplayName("상품 상세 정보를 조회")
    void read_product_detail() {
        // given
        Long productId = 1L;

        Product product = TestFixtures.product("신발");

        given(productRepository.findById(any())).willReturn(product);

        // when
        Product foundProduct = productReader.retrieveById(productId);

        // then
        assertThat(foundProduct.name()).isEqualTo("신발");
        assertThat(foundProduct.color()).isEqualTo("화이트");
    }

    @Test
    @DisplayName("상품 id list 기반 상품 조회")
    void read_all_product_by_id() {
        // given
        List<OrderRequest.ProductOrderRequest> productOrderRequests = List.of(
                new OrderRequest.ProductOrderRequest(1L, 1L),
                new OrderRequest.ProductOrderRequest(2L, 5L)
        );

        List<Product> products = List.of(
                TestFixtures.product("신발"),
                TestFixtures.product("바지")
        );

        List<Long> productIds = productOrderRequests.stream().map(OrderRequest.ProductOrderRequest::id).toList();

        given(productRepository.findByIdIn(any())).willReturn(products);

        // when
        List<Product> foundProducts = productReader.retrieveAllByIds(productIds);

        // then
        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.get(0).name()).isEqualTo("신발");
        assertThat(foundProducts.get(0).size()).isEqualTo("270");
        assertThat(foundProducts.get(0).color()).isEqualTo("화이트");
    }

    @Test
    @DisplayName("3일 동안 가장 많이 팔린 상품 5개 조회")
    void read_three_days_popular_products() {
        // given
//        given(productRepository.findTopSellingProducts(any(), any(), any(), any())).willReturn(
//                List.of(TestFixtures.product("신발")),
//                List.of(TestFixtures.product("바지")),
//                List.of(TestFixtures.product("아우터")),
//                List.of(TestFixtures.product("박스티")),
//                List.of(TestFixtures.product("티셔츠"))
//        );

        // when
//        List<Product> popularProducts = productReader.retrievePopularProducts();
//
//        // then
//        assertThat(popularProducts.size()).isEqualTo(5);
//        assertThat(popularProducts.getFirst().name()).isEqualTo("신발");
//        assertThat(popularProducts.getLast().name()).isEqualTo("티셔츠");
    }
}