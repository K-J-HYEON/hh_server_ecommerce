package hhplus.ecommerce;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;
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
            return new User(1L, "Kwon Jae Hyeon", "서울특별시 마포구", "01012344321", 10_00_000L);
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
}