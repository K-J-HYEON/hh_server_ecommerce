package hhplus.ecommerce.product.application;

import hhplus.ecommerce.config.BaseException;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;

public interface ProductsService {

    ProductInfoRes userReadProductInfo(Long productId, Long userId) throws BaseException;
}
