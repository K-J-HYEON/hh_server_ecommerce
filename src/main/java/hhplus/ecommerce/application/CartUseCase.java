package hhplus.ecommerce.application;

import hhplus.ecommerce.api.dto.CartResult;
import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CartUseCase {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CartUseCase(UserService userService,
                       ProductService productService,
                       CartService cartService,
                       ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public CartResult cart(Long userId, CartRequest req) {
        User user = userService.getUser(userId);
        List<Product> products = productService.readProductsByIds(req.products());

        Cart cart = cartService.cart(user, products, req);

        return CartResult.of(cart);
    }
}
