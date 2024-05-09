package hhplus.ecommerce.domain.payment;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.common.LockHandler;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderUpdater;
import hhplus.ecommerce.domain.order.OrderValidator;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserPointManager;
import hhplus.ecommerce.domain.user.UserPointValidator;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.storage.payment.PayType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private OrderUpdater orderUpdater;
    private OrderValidator orderValidator;
    private PaymentAppender paymentAppender;
    private UserPointManager userPointManager;
    private UserPointValidator userPointValidator;
    private PaymentService paymentService;
    private LockHandler lockHandler;

    private Long userId;
    private Long orderId;
    private Long payAmount;
    private User user;
    private OrderRequest request;

    @BeforeEach
    void setUp() {
        orderUpdater = mock(OrderUpdater.class);
        orderValidator = mock(OrderValidator.class);
        paymentAppender = mock(PaymentAppender.class);
        userPointManager = mock(UserPointManager.class);
        userPointValidator = mock(UserPointValidator.class);
        lockHandler = mock(LockHandler.class);



        paymentService =
                new PaymentService(orderUpdater, paymentAppender, userPointManager);

        userId = 1L;
        orderId = 1L;
        payAmount = 50_000L;
        String paymentMethod = PayType.ACCOUNT_TRANSFER.toString();

        user = TestFixtures.user(userId);

        request = new OrderRequest(new Receiver(
                user.name(),
                user.address(),
                user.phoneNumber()),
                List.of(new OrderRequest.ProductOrderRequest(1L, 1L)),
                payAmount,
                paymentMethod
        );
    }

    @Test
    @DisplayName("계좌이체 결제 성공")
    void account_transfer_success() {

        // Given
        Payment payment = TestFixtures.payment(orderId);
        Order order = TestFixtures.order(OrderStatus.PENDING_FOR_PAY);

        given(orderValidator.isOrderStatusPendingForPay(any())).willReturn(true);
        given(paymentAppender.create(any(), any(), any())).willReturn(payment);

        // When
        Payment paid = paymentService.pay(user, order, request);

        // Then
        assertThat(paid).isNotNull();
        assertThat(paid.payAmount()).isEqualTo(500_000L);
        assertThat(paid.paymentMethod()).isEqualTo("ACCOUNT_TRANSFER");
        verify(userPointManager, atLeastOnce()).usePoint(any(), any());
    }

    @Test
    @DisplayName("주문 상태가 결제 대기 상태가 아니면 결제 실패")
    void order_status_not_pending_forpay_then_payment_failed() {
        // Given
        Order order = TestFixtures.order(OrderStatus.PAY_FAILED);

        given(orderValidator.isOrderStatusPendingForPay(any())).willReturn(false);

        // When && Then
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.pay(user, order, request);
        });
    }
}