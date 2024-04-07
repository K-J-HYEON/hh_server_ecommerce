package hhplus.ecommerce.product.application;

import hhplus.ecommerce.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> readProductInfo();

    Product readProductInfoDetail(Long productId);

//    ProductInfoRes readPopularProduct(Long productId) throws BaseException;
}
