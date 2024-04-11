package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.domain.orderitem.OrderItemAppender;
import hhplus.ecommerce.domain.product.ProductReader;
import hhplus.ecommerce.domain.product.ProductUpdater;
import hhplus.ecommerce.domain.product.ProductValidator;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserReader;
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
