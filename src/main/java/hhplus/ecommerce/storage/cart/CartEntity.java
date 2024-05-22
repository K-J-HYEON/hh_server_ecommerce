package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.storage.cart.cartitem.CartItemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    public Long getId() {
        return id;
    }

    public CartEntity(Long userId) {
        this.userId = userId;
    }

    public Cart toCart(List<CartItemEntity> items) {
        return new Cart(
                getId(),
                userId,
                items.stream()
                        .map(item -> new CartItem(item.getId(), item.getProductId(), item.getQuantity()))
                        .toList()
        );
    }
}
