package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.orderitem.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class StockUpdator {

    private final StockRepository stockRepository;

    public StockUpdator(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void decreaseStock(Stock stock, OrderItem item) {
        Stock decreaseStock = stock.decreaseStock(item.quantity());
        stockRepository.updateStock(decreaseStock);
    }

    public void increaseStock(Stock stock, OrderItem item) {
        Stock increasedStock = stock.increaseStock(item.quantity());
        stockRepository.updateStock(increasedStock);
    }


//    public List<Stock> updateStockForOrder(List<Stock> stocks, List<OrderRequest.ProductOrderRequest> products) {
//        List<Stock> stockList = new ArrayList<>();
//        for (OrderRequest.ProductOrderRequest orderRequest : products) {
//            Stock stock = stocks.stream()
//                    .filter(s -> s.productId().equals(orderRequest.id()))
//                    .findFirst()
//                    .orElseThrow(() -> new EntityNotFoundException(orderRequest.id() + " 상품 정보를 찾지 못했습니다."));
//
//            Stock decreaseStock = stock.decreaseStock(orderRequest.orderCount());
//            stockRepository.updateStock(decreaseStock);
//            stockList.add(decreaseStock);
//        }
//        return stocks;
//    }
}
