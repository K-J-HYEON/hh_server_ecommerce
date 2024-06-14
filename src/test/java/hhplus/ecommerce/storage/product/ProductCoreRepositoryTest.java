package hhplus.ecommerce.storage.product;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCoreRepositoryTest {

    @Mock
    private ProductJpaRepository productJpaRepository;

    @InjectMocks
    private ProductCoreRepository productCoreRepository;

//    @BeforeEach
//    void setUp() {
//        productJpaRepository = mock(ProductJpaRepository.class);
//        productCoreRepository = new ProductCoreRepository(productJpaRepository);
//    }

    @Test
    @DisplayName("상품을 찾지 못하였을 경우 에러 발생")
    void product_not_found_then_error() {

        // Given
        Long productId = 100L;

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            productCoreRepository.findById(productId);
        });
    }
}