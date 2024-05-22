package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.product.ProductReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProductReader {

    private final ProductReader productReader;

    public OrderProductReader(ProductReader productReader) {
        this.productReader = productReader;
    }

    public List<OrderProduct> read(Cart cart) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        productReader.retrieveAllByIds(cart.items().stream().map(CartItem::productId).toList())
                .forEach(product -> {
                    CartItem item = cart.items().stream().filter(cartItem -> cartItem.productId().equals(product.id()))
                            .findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("장바구니에 담긴 상품이 존재하지 않습니다."));
                    orderProducts.add(OrderProduct.of(product, item));
                });
        return orderProducts;
    }
}
