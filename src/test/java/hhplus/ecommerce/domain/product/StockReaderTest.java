package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class StockReaderTest {

    @MockBean
    private StockRepository stockRepository;

    @InjectMocks
    private StockReader stockReader;

//    @BeforeEach
//    void setUp() {
//        stockRepository = mock(StockRepository.class);
//
//        stockReader = new StockReader(stockRepository);
//    }

    @Test
    @DisplayName("상품의 재고를 가져옴")
    void getProductStock() {
        // Given
        Product product = TestFixtures.product("후드티");
        Long stockCount = 5L;

        Stock stock = new Stock(1L, product.id(), stockCount);

        given(stockRepository.findByProductId(any())).willReturn(stock);

        // when
        Stock foundStock = stockReader.readByProductId(product.id());

        // then
        assertThat(foundStock.productId()).isEqualTo(1L);
        assertThat(foundStock.stockCount()).isEqualTo(5L);
//        assertThat(stocks.getFirst().productId()).isEqualTo(1L);
//        assertThat(stocks.getLast().productId()).isEqualTo(2L);
    }
}