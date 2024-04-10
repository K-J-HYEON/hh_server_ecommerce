package hhplus.ecommerce.product.infrastructure;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProductCoreRepositoryTest {
    private ProductCoreRepository productCoreRepository;

    private ProductJpaRepository productJpaRepository;

    @BeforeEach
    void setUp() {
        productJpaRepository = mock(ProductJpaRepository.class);

        productCoreRepository = new ProductCoreRepository(productJpaRepository);
    }

    @Test
    @DisplayName("에러 : 상품을 찾지 못하였습니다.")
    void notFoundProduct() {

        Long productId = 1L;

        assertThrows(EntityNotFoundException.class, () -> {
            productCoreRepository.findById(productId);
        });
    }
}