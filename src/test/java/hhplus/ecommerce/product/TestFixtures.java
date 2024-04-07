package hhplus.ecommerce.product;

import hhplus.ecommerce.product.domain.Product;
import jakarta.persistence.EntityNotFoundException;

public class TestFixtures {
    public static Product product(String name) {
        if (name.equals("신발")) {
            return new Product(1L, "나이키 에어포스", 90000, 10, "270", "화이트");
        }

        if (name.equals("바지")) {
            return new Product(10L, "와이드 팬츠", 10000, 50, "28", "베이직");
        }

        throw new EntityNotFoundException("There is no product there. - name: " + name);
    }
}