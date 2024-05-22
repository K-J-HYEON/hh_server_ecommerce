package hhplus.ecommerce.domain.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StockReader {
    private final StockRepository stockRepository;

    public StockReader(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock readByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

//    public List<Stock> readByProductIds(List<Product> products) {
//        return stockRepository.findByProductIdIn(products.stream()
//                .map(Product::id)
//                .toList());
//    }
}
