package hhplus.ecommerce.order.application;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.dto.request.OrderReq;
import hhplus.ecommerce.order.util.OrderProcessor;
import hhplus.ecommerce.order.util.OrderStatus;
import hhplus.ecommerce.order.util.OrderUpdater;
import hhplus.ecommerce.orderitem.application.OrderItemAppender;
import hhplus.ecommerce.product.application.ProductRetrieve;
import hhplus.ecommerce.product.application.ProductUpdater;
import hhplus.ecommerce.product.application.ProductValidator;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.util.UserRetrieve;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRetrieve userRetrieve;
    private final ProductRetrieve productRetrieve;
    private final ProductUpdater productUpdater;
    private final OrderItemAppender orderItemAppender;
    private final OrderProcessor orderProcessor;
    private final OrderUpdater orderUpdater;
    private final ProductValidator productValidator;

    public OrderServiceImpl(UserRetrieve userRetrieve,
                        ProductRetrieve productRetrieve,
                        ProductUpdater productUpdater,
                        OrderItemAppender orderItemAppender,
                        OrderProcessor orderProcessor,
                        OrderUpdater orderUpdater,
                        ProductValidator productValidator) {
        this.userRetrieve = userRetrieve;
        this.productRetrieve = productRetrieve;
        this.productUpdater = productUpdater;
        this.orderItemAppender = orderItemAppender;
        this.orderProcessor = orderProcessor;
        this.orderUpdater = orderUpdater;
        this.productValidator = productValidator;
    }

    @Override
    public Order order(Long userId, OrderReq req) {
        User user = userRetrieve.retrieveByUserId(userId);

        Order order = orderProcessor.order(user, req);

        List<Product> products = productRetrieve.retrieveAllByIds(req.products());

        productValidator.checkProductStockCount(order, products, req.products());
        productUpdater.updateStock(products, req.products());

        orderItemAppender.create(order, products, req.products());
        return orderUpdater.changeStatus(order, OrderStatus.COMPLETE);
    }
}
