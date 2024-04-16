package hhplus.ecommerce.domain.cart;

import hhplus.ecommerce.api.dto.request.CartRequest;
import hhplus.ecommerce.api.dto.response.CartResponse;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.cart.CartStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CartRepository {
    // Order 쪽 참고 코드
    Cart cart(User user, CartRequest request);

    Cart updateStatus(Cart cart, CartStatus cartStatus);

    Cart findById(Long cartId);



    // ssg.com 참고 코드
//    List<CartResponse> findByUserId(Long userId);
//
//    boolean existByUserUserIdAndProductOption_ProductOptionId(Long userId, Long productOptionId);
//
//    Cart findByUserUserIdAndProductOption_ProductOptionId(Long userId, Long productOptionId);
//
//    @Query(value = "select count(c) from Cart as c where c.user.userId =:userId")
//    int retrieveCartCount(@Param("userId") Long userId);
}