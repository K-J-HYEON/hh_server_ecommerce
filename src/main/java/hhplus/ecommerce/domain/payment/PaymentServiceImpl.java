package hhplus.ecommerce.domain.payment;


import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderReader;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.order.OrderValidator;
import hhplus.ecommerce.api.dto.request.PaymentReq;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserPointManager;
import hhplus.ecommerce.domain.user.UserPointValidator;
import hhplus.ecommerce.domain.user.UserReader;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final UserReader userReader;
    private final OrderReader orderReader;
    private final OrderUpdater orderUpdater;
    private final OrderValidator orderValidator;
    private final UserPointManager userPointManager;
    private final UserPointValidator userPointValidator;
    private final PaymentAppender paymentAppender;

    public PaymentServiceImpl(UserReader userReader, OrderReader orderReader, OrderUpdater orderUpdater, OrderValidator orderValidator, UserPointManager userPointManager, UserPointValidator userPointValidator, PaymentAppender paymentAppender) {
        this.userReader = userReader;
        this.orderReader = orderReader;
        this.orderUpdater = orderUpdater;
        this.orderValidator = orderValidator;
        this.userPointManager = userPointManager;
        this.userPointValidator = userPointValidator;
        this.paymentAppender = paymentAppender;
    }

    @Override
    public Payment pay(Long userId, PaymentReq req) {
        User user = userReader.retrieveByUserId(userId);

        Order order = orderReader.retrieveByOrderId(req.orderId());
        orderValidator.confirmOrderStatus(order);

        userPointValidator.confirmUserPoint(order, user, req.payAmount());
        userPointManager.usePoint(user, req.payAmount());

        orderUpdater.changeStatus(order, OrderStatus.PAID);
        return paymentAppender.create(order, req.payAmount(), req.paymentMethod());
    }
}
