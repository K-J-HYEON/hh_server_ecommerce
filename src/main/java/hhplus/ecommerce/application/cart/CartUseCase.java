package hhplus.ecommerce.application.cart;

import hhplus.ecommerce.api.dto.CartItemResult;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.cart.cartitem.CartItem;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class CartUseCase {
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    public CartUseCase(UserService userService, CartService cartService, ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }

    public CartItemResult getCartItems(Long userId) {
        User user = userService.getUser(userId);

        Cart cart = cartService.getCart(user);

        List<CartItem> cartItems = cartService.getAllCartItems(cart);

        List<Product> products = productService.readProductsByIds(cartItems.stream().map(CartItem::productId).toList());

        return CartItemResult.of(cartItems, products);
    }

    @Transactional
    public void addItem(Long userId, List<NewCartItem> cartItems) {
        User user = userService.getUser(userId);

        Cart cart = cartService.getCart(user);

        productService.verifyProductStockForAddToCart(cartItems);

        cartService.addItemToCart(cart, cartItems);
    }

    @Transactional
    public void deleteItem(Long userId, List<Long> cartItemsIds) {
        User user = userService.getUser(userId);

        Cart cart = cartService.getCart(user);

        List<CartItem> cartItems = cartService.getCartItemsByIds(cart, cartItemsIds);

        cartService.deleteItem(cartItems);
    }
}