package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderReader;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.storage.orderitem.OrderItemStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockReader stockReader;
    private final StockValidator stockValidator;
    private final StockUpdator stockUpdator;
    private final OrderReader orderReader;

    public StockService(StockReader stockReader, StockValidator stockValidator, StockUpdator stockUpdator, OrderReader orderReader) {
        this.stockReader = stockReader;
        this.stockValidator = stockValidator;
        this.stockUpdator = stockUpdator;
        this.orderReader = orderReader;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseProductStock(OrderItem item) {
        Stock stock = stockReader.readByProductId(item.productId());
        stockValidator.checkProductStockCountForOrder(stock, item);
        stockUpdator.decreaseStock(stock, item);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void compensationOrderStock(Order order) {
        Order foundOrder = orderReader.read(order);
        for (OrderItem item : foundOrder.items()) {
            if (item.status().equals(OrderItemStatus.SUCCESS.toString())) {
                Stock stock = stockReader.readByProductId(item.productId());
                stockUpdator.increaseStock(stock, item);
            }
        }
    }
}
