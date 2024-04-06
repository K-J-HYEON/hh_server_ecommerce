package hhplus.ecommerce.product.application;

import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import hhplus.ecommerce.product.infrastructure.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceImplTest {
    @InjectMocks
    ProductsServiceImpl sut;
    @Mock
    ProductRepository productRepository;


    // 상품 목록 조회 성공
    @Test
    @DisplayName("상품 목록 조회 성공")
    void userReadProductInfoSuccess() throws Exception {
        Long userId = 1L;
        Long productId = 10L;

        Product expectResult = new Product(userId, productId);
        ProductInfoRes result = sut.userReadProductInfo(1L, 10L);

        assertThat(result.equals(userId)).isEqualTo(expectResult.equals(userId));
        assertThat(result.equals(productId)).isEqualTo(expectResult.equals(productId));
    }
}