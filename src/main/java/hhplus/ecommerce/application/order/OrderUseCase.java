package hhplus.ecommerce.application.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.event.OrderEventListener;
import hhplus.ecommerce.domain.order.event.OrderEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderUseCase {

    private final CartService cartService;
    private final OrderService orderService;
    private final OrderEventPublisher orderEventPublisher;

    public OrderUseCase(CartService cartService, OrderService orderService, OrderEventPublisher orderEventPublisher) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    public Order order(Long userId, OrderRequest request) {
        Cart cart = cartService.getCart(userId);
        Order order = orderService.order(userId, cart, request);

//        orderEventPublisher.publicEvent(new OrderEventListener(order));
        return order;
    }


//
//    private final UserService userService;
//    private final CartService cartService;
//    private final OrderService orderService;
//    private final StockService stockService;
//    private final PaymentService paymentService;
//    private final ApplicationEventPublisher applicationEventPublisher;
//
//    public OrderUseCase(UserService userService,
//                        CartService cartService,
//                        OrderService orderService,
//                        ApplicationEventPublisher applicationEventPublisher,
//                        StockService stockService,
//                        PaymentService paymentService) {
//        this.userService = userService;
//        this.cartService = cartService;
//        this.orderService = orderService;
//        this.stockService = stockService;
//        this.paymentService = paymentService;
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//
//    @Transactional
//    public OrderPaidResult order(Long userId, OrderRequest request) {
//        User user = userService.getUser(userId);
//        Cart cart = cartService.getCart(user);
//        Order order = orderService.order(user, cart, request);
//
//        try {
//            for (OrderItem item : order.items()) {
//                stockService.decreaseProductStock(item);
//                orderService.updateItemStatus(item, OrderItemStatus.SUCCESS);
//            }
//            cartService.resetCart(userId);
//            Payment payment = paymentService.pay(user, order, request);
//
//            applicationEventPublisher.publishEvent(new OrderCreatedEvent(order, payment));
//            return OrderPaidResult.of(order, payment);
//        } catch (Exception e) {
//            orderService.orderFailed(order);
//            stockService.compensationOrderStock(order);
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}
