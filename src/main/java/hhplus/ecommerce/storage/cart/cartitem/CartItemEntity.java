package hhplus.ecommerce.storage.cart.cartitem;

import hhplus.ecommerce.config.BaseTimeEntity;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean deleted = false;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    private LocalDateTime createAt;


    public CartItemEntity(Long cartId, Long productId, Long quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void addQuntity(Long addedQuantity) {
        this.quantity += addedQuantity;
    }

    public void delete() {
        this.deleted = true;
    }

    public CartItem toCartItem() {
        return new CartItem(getId(), productId, quantity);
    }

    public boolean isDeleted() {
        return deleted;
    }
}
