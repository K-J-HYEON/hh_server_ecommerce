package hhplus.ecommerce.domain.payment;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.storage.payment.PayType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PaymentAppenderTest {

    private PaymentRepository paymentRepository;

    private PaymentAppender paymentAppender;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);

        paymentAppender = new PaymentAppender(paymentRepository);
    }

    @Test
    @DisplayName("결제 생성 성공")
    void create_payment_success() {
        // given
        Order order = TestFixtures.order(OrderStatus.COMPLETE);
        Payment payment = TestFixtures.payment(order.id());

        given(paymentRepository.create(any(), any(), any())).willReturn(payment);

        // when
        Payment paymentDo = paymentAppender.create(order, order.payAmount(), PayType.MOBILE_PAY.toString());

        // then
        assertThat(paymentDo).isNotNull();
        assertThat(paymentDo.payAmount()).isEqualTo(500_000L);
        assertThat(paymentDo.paymentMethod()).isEqualTo("MOBILE_PAY");
    }
}