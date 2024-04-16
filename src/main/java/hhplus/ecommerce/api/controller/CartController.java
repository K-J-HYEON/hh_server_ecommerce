package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.api.dto.CartResult;
import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.api.dto.response.CartResponse;
import hhplus.ecommerce.application.CartUseCase;
import hhplus.ecommerce.domain.cart.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController("/ecommerce/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartUseCase cartUseCase;

    public CartController(CartService cartService, CartUseCase cartUseCase) {
        this.cartService = cartService;
        this.cartUseCase = cartUseCase;
    }

    @Tag(name = "장바구니 추가 API", description = "장바구니 추가 API입니다.")
    @PostMapping("/{userId}")
    public CartResponse cart(@PathVariable Long userId,
                             @Valid @RequestBody CartRequest request) {
        CartResult cartResult = cartUseCase.cart(userId, request);
        return CartResponse.from(cartResult);
    }
}