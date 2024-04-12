package hhplus.ecommerce.order.application;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.presentation.dto.request.OrderReq;
import hhplus.ecommerce.order.domain.component.OrderProcessor;
import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.order.domain.component.OrderUpdater;
import hhplus.ecommerce.orderitem.domain.component.OrderItemAppender;
import hhplus.ecommerce.product.domain.component.ProductReader;
import hhplus.ecommerce.product.domain.component.ProductUpdater;
import hhplus.ecommerce.product.domain.component.ProductValidator;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.domain.component.UserReader;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserReader userReader;
    private final ProductReader productReader;
    private final ProductUpdater productUpdater;
    private final OrderItemAppender orderItemAppender;
    private final OrderProcessor orderProcessor;
    private final OrderUpdater orderUpdater;
    private final ProductValidator productValidator;

    public OrderServiceImpl(UserReader userReader,
                            ProductReader productReader,
                            ProductUpdater productUpdater,
                            OrderItemAppender orderItemAppender,
                            OrderProcessor orderProcessor,
                            OrderUpdater orderUpdater,
                            ProductValidator productValidator) {
        this.userReader = userReader;
        this.productReader = productReader;
        this.productUpdater = productUpdater;
        this.orderItemAppender = orderItemAppender;
        this.orderProcessor = orderProcessor;
        this.orderUpdater = orderUpdater;
        this.productValidator = productValidator;
    }

    @Override
    public Order order(Long userId, OrderReq req) {
        User user = userReader.retrieveByUserId(userId);

        Order order = orderProcessor.order(user, req);

        List<Product> products = productReader.retrieveAllByIds(req.products());

        productValidator.checkProductStockCount(order, products, req.products());
        productUpdater.updateStock(products, req.products());

        orderItemAppender.create(order, products, req.products());
        return orderUpdater.changeStatus(order, OrderStatus.COMPLETE);
    }
}
