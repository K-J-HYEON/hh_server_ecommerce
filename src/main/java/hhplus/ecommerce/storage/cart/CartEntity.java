package hhplus.ecommerce.storage.cart;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.cart.Cart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

//    private LocalDateTime createAt;
//
//    private LocalDateTime updateAt;

    public Long getId() {
        return id;
    }

    public CartEntity(Long userId) {
        this.userId = userId;
    }

    public Cart toCart() {
        return new Cart(getId(), userId, getCreateAt(), getUpdateAt());
    }
}
