package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductValidator {
    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void checkPossibleAddToCart(List<NewCartItem> newCartItems) {
        for (NewCartItem newCartItem : newCartItems) {
            Product product = productRepository.findById(newCartItem.productId())
                    .orElseThrow(() -> new EntityNotFoundException("상품 정보를 찾지 못했습니다. - id: " + newCartItem.productId()))
                    .toProduct();

            product.isEnoughProductStockQuantityForOrder(newCartItem.quantity());
        }
    }
}