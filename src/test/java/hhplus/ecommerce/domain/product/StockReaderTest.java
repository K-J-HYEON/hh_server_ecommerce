package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

class StockReaderTest {

    @MockBean
    private StockRepository stockRepository;

    @InjectMocks
    private StockReader stockReader;

    @Test
    @DisplayName("상품의 재고를 가져옴")
    void getProductStock() {
        // given
        List<Product> products = List.of(
                TestFixtures.product("신발"),
                TestFixtures.product("바지")
        );

        given(stockRepository.findByProductIdIn(anyList())).willReturn(List.of(
                TestFixtures.stock(products.get(0).id()),
                TestFixtures.stock(products.get(1).id())
        ));

        // when
        List<Stock> stocks = stockReader.readByProductIds(products);

        // then
        assertThat(stocks.size()).isEqualTo(2L);
//        assertThat(stocks.getFirst().productId()).isEqualTo(1L);
//        assertThat(stocks.getLast().productId()).isEqualTo(2L);
    }
}