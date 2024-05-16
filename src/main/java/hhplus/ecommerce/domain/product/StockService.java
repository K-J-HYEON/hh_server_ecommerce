package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.cart.cartitem.CartItemRemover;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderReader;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.product.event.StockDecreaseEventFail;
import hhplus.ecommerce.domain.product.event.StockDecreaseEventSuccess;
import hhplus.ecommerce.domain.product.event.StockEventPublisher;
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
    private final OrderUpdater orderUpdater;
    private final CartItemRemover cartItemRemover;
    private final StockEventPublisher eventPublisher;

    public StockService(StockReader stockReader, StockValidator stockValidator, StockUpdator stockUpdator, OrderReader orderReader, OrderUpdater orderUpdater, CartItemRemover cartItemRemover, StockEventPublisher eventPublisher) {
        this.stockReader = stockReader;
        this.stockValidator = stockValidator;
        this.stockUpdator = stockUpdator;
        this.orderReader = orderReader;
        this.orderUpdater = orderUpdater;
        this.cartItemRemover = cartItemRemover;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void updateStockCountForOrder(Order order) {
        try {
            for (OrderItem item : order.items()) {
                decreaseProductStock(item);
                orderUpdater.changeItemStatus(item, OrderItemStatus.SUCCESS);
            }
            cartItemRemover.resetCart(order.userId());
            eventPublisher.success(new StockDecreaseEventSuccess(order));
        } catch (Exception e) {
            eventPublisher.fail(new StockDecreaseEventFail(order));
            throw new RuntimeException(e.getMessage());
        }
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
