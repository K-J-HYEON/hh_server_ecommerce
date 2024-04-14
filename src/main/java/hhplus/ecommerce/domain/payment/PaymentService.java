package hhplus.ecommerce.domain.payment;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.order.OrderValidator;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserPointManager;
import hhplus.ecommerce.domain.user.UserPointValidator;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final OrderUpdater orderUpdater;
    private final OrderValidator orderValidator;
    private final PaymentAppender paymentAppender;
    private final UserPointManager userPointManager;
    private final UserPointValidator userPointValidator;

    public PaymentService(OrderUpdater orderUpdater, OrderValidator orderValidator,
                          PaymentAppender paymentAppender,
                          UserPointManager userPointManager,
                          UserPointValidator userPointValidator) {
        this.orderUpdater = orderUpdater;
        this.orderValidator = orderValidator;
        this.paymentAppender = paymentAppender;
        this.userPointManager = userPointManager;
        this.userPointValidator = userPointValidator;
    }

    public Payment pay(User user, Order order, OrderRequest request) {
        if (!orderValidator.isOrderStatusPendingForPay(order)) {
            throw new IllegalArgumentException("결제 대기 상태가 아닙니다. order status : " + order.orderStatus());
        }

        userPointValidator.checkUserPointForPay(user, request.paymentAmount());
        userPointManager.usePoint(user, request.paymentAmount());

        Order paidOrder = orderUpdater.changeStatus(order, OrderStatus.PAID);
        return paymentAppender.create(paidOrder, request.paymentAmount(), request.paymentMethod());
    }
}
