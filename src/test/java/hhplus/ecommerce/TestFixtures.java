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
            return new Product(1L, "나이키 에어포스", 90000L, 10L, "270", "화이트");
        }

        if (name.equals("바지")) {
            return new Product(10L, "와이드 팬츠", 10000L, 50L, "28", "베이직");
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
            return new Order(1L, 1L, 200_000L, "Kwon Jae Hyeon", "서울특별시 마포구", "01012344321", "ready", LocalDateTime.now());
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