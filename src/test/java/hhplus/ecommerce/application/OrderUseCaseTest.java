package hhplus.ecommerce.application;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentService;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

class OrderUseCaseTest {

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private OrderUseCase orderUseCase;

//    @BeforeEach
//    void setUp() {
//        userService = mock(UserService.class);
//        productService = mock(ProductService.class);
//        orderService = mock(OrderService.class);
//        paymentService = mock(PaymentService.class);
//        applicationEventPublisher = mock(ApplicationEventPublisher.class);
//
//        orderUseCase = new OrderUseCase(userService, productService, orderService, paymentService, applicationEventPublisher);
//    }

    @Test
    @DisplayName("주문 및 결제 성공 테스트")
    void succeed_order() {

        // given
        Long userId = 1L;
        User user = TestFixtures.user(userId);
        List<Product> products = List.of(TestFixtures.product("신발"));
        Order order = TestFixtures.order(OrderStatus.PAID);
        Payment payment = TestFixtures.payment(order.id());
        OrderRequest request = new OrderRequest(
                new Receiver(
                        user.name(),
                        user.address(),
                        user.phoneNumber()
                ),
                List.of(
                        new OrderRequest.ProductOrderRequest(1L, 1L)
                ),
                500_000L,
                "MOBILE_PAY"
        );

        given(userService.getUser(any())).willReturn(user);
        given(productService.readProductsByIds(anyList())).willReturn(products);
//        given(orderService.order(any(), anyList(), any())).willReturn(order);
        given(paymentService.pay(any(), any(), any())).willReturn(payment);

        // when
        OrderPaidResult orderPaidResult = orderUseCase.order(userId, request);

        // then
        assertThat(orderPaidResult).isNotNull();
        assertThat(orderPaidResult.orderId()).isEqualTo(1L);
        assertThat(orderPaidResult.payAmount()).isEqualTo(500_000L);
    }
}