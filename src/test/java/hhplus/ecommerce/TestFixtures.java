package hhplus.ecommerce;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.payment.PayType;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

public class TestFixtures {
    public static Product product(String name) {
        if (name.equals("신발")) {
            return new Product(1L, "신발", 90_000L, 10L, "270", "화이트");
        }

        if (name.equals("바지")) {
            return new Product(2L, "바지", 10_000L, 50L, "28", "베이직");
        }

        if (name.equals("아우터")) {
            return new Product(3L, "아우터", 200_000L, 100L, "FREE", "블랙");
        }

        if (name.equals("박스티")) {
            return new Product(4L, "박스티", 10_000L, 200L, "FREE", "차콜");
        }

        if (name.equals("티셔트")) {
            return new Product(5L, "티셔트", 20_000L, 400L, "FREE", "화이트");
        }

        if (name.equals("와이드 청바지")) {
            return new Product(6L, "와이드 청바지", 50_000L, 200L, "30", "연청");
        }

        throw new EntityNotFoundException("There is no product there. - name: " + name);
    }

    public static User user(Long userId) {
        if (userId.equals(1L)) {
            return new User(1L, "김아무개", "서울특별시 마포구", "01012344321", 10_00_000L);
        }
        throw new EntityNotFoundException("User Not Found - id: " + userId);
    }

    public static Order order(OrderStatus orderStatus) {
        if (orderStatus.equals(OrderStatus.READY)) {
            return new Order(1L, 1L, 200_000L, "김아무개", "서울특별시 마포구", "01012344321", "ready", LocalDateTime.now());
        }

        if (orderStatus.equals(OrderStatus.READY)) {
            return new Order(1L, 1L, 200_000L, "Kwon Jae Hyeon", "서울특별시 마포구", "01012344321", "complete", LocalDateTime.now());
        }

        if (orderStatus.equals(OrderStatus.READY)) {
            return new Order(1L, 1L, 200_000L, "Kwon Jae Hyeon", "서울특별시 마포구", "01012344321", "canceled", LocalDateTime.now());
        }

        if (orderStatus.equals(OrderStatus.READY)) {
            return new Order(1L, 1L, 200_000L, "Kwon Jae Hyeon", "서울특별시 마포구", "01012344321", "paid", LocalDateTime.now());
        }
        throw new EntityNotFoundException("Order Not Found - order status: " + orderStatus);
    }

    public static OrderItem orderItem(Long orderId, Long productId) {
        if (orderId.equals(1L) && productId.equals(1L)) {
            return new OrderItem(1L, orderId, productId, "신발", 100_000L, 500_000L, 5L);
        }

        if (orderId.equals(2L) && productId.equals(2L)) {
            return new OrderItem(2L, orderId, productId, "바지", 30_000L , 90_000L, 3L);
        }
        throw new EntityNotFoundException("The order item does not exist - order id: " + orderId + ", product id: " + productId);
    }

    public static Payment payment(Long orderId) {
        if (orderId.equals(1L)) {
            return new Payment(1L, orderId, 500_000L, PayType.MOBILE_PAY.toString(), LocalDateTime.now());
        }

        throw new EntityNotFoundException("The payment has not been made - order id: " + orderId);
    }
}