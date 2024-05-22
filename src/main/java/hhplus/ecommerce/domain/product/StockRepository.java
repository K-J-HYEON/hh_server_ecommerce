package hhplus.ecommerce.domain.product;


public interface StockRepository {
//    List<Stock> findByProductIdIn(List<Long> productIds);

    void updateStock(Stock stock);

    Stock findByProductId(Long productId);
}