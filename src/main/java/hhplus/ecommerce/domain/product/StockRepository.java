package hhplus.ecommerce.domain.product;

import java.util.List;

public interface StockRepository {
//    List<Stock> findByProductIdIn(List<Long> productIds);

    void updateStock(Stock stock);

    Stock findByProductId(Long productId);
}