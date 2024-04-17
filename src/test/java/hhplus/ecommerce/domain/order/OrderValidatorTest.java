package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class OrderValidatorTest {

    private OrderValidator orderValidator;

    @BeforeEach
    void setUp() {
        orderValidator = new OrderValidator();
    }

    @Test
    @DisplayName("주문 상태가 취소인 경우 false 반환.")
    void pay_isfail_when_order_status_iscanceled() {

        // given
        Order order = TestFixtures.order(OrderStatus.CANCELED);

        // when
        boolean result = orderValidator.isOrderStatusPendingForPay(order);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("주문 상태가 대기 상태인 경우 True 반환")
    void pay_issuccess_when_order_status_ispaid() {

        // given
        Order order = TestFixtures.order(OrderStatus.PENDING_FOR_PAY);

        // when
        boolean result = orderValidator.isOrderStatusPendingForPay(order);

        // then
        assertThat(result).isTrue();
    }
}