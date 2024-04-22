package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.api.dto.CartItemResult;
import hhplus.ecommerce.api.dto.request.CartItemRequest;
import hhplus.ecommerce.api.dto.request.DeleteCartItemRequest;
import hhplus.ecommerce.api.dto.response.AddCartItemResponse;
import hhplus.ecommerce.api.dto.response.CartItemResponse;
import hhplus.ecommerce.api.dto.response.DeleteCartItemResponse;
import hhplus.ecommerce.application.CartUseCase;
import hhplus.ecommerce.domain.cart.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api")
public class CartController {

    private CartUseCase cartUseCase;
    private CartService cartService;

    public CartController(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    @Tag(name = "장바구니 조회 API", description = "장바구니 조회 API 입니다.")
    @GetMapping("/cart/{cartId}/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponse cart(@PathVariable Long cartId, Long userId) {
        CartItemResult cartItemResult = cartUseCase.getCartItems(cartId, userId);
        return CartItemResponse.from(cartItemResult);
    }

    @Tag(name = "장바구니 추가 API", description = "장바구니 추가 API 입니다.")
    @PostMapping("/cart/{cartId}/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddCartItemResponse addCartItems(@PathVariable Long cartId, Long userId,
                                            @RequestBody CartItemRequest cartItemRequest) {
        cartUseCase.addItem(cartId, userId, cartItemRequest.toNewCartItem());
        return AddCartItemResponse.from("장바구니에 상품이 추가되었습니다.");
    }

    @Tag(name = "장바구니 삭제 API", description = "장바구니 삭제 API 입니다.")
    @PostMapping("/cart/{cartId}/user/{userId}")
    public DeleteCartItemResponse deleteCartItems(@PathVariable Long cartId, Long userId,
                                                  @RequestBody DeleteCartItemRequest cartItemRequest) {

        cartUseCase.deleteItem(cartId, userId, cartItemRequest.cartItemIdList());
        return DeleteCartItemResponse.from("장바구니에서 상품이 삭제되었습니다.");
    }
}