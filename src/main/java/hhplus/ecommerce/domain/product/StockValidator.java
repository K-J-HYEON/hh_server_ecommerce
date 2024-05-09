package hhplus.ecommerce.domain.product;


import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockValidator {

    public void checkProductStockCountForOrder(Stock stock, OrderItem item) {
        stock.isEnoughProductStockCountForOrder(item.quantity());
    }

//    public void checkProductStockCountForOrder(List<Stock> stocks, List<OrderRequest.ProductOrderRequest> products) {
//        for (OrderRequest.ProductOrderRequest orderRequest : products) {
//            Stock stock = stocks.stream()
//                    .filter(s -> s.productId().equals(orderRequest.id()))
//                    .findFirst()
//                    .orElseThrow(() -> new EntityNotFoundException(orderRequest.id() + " 상품의 정보를 찾지 못했습니다."));
//
//            stock.isEnoughProductStockCountForOrder(orderRequest.orderCount());
//        }
//    }
}
