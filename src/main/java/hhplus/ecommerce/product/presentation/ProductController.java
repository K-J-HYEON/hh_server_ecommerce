package hhplus.ecommerce.product.presentation;


import hhplus.ecommerce.config.BaseException;
import hhplus.ecommerce.config.BaseResponse;
import hhplus.ecommerce.product.application.ProductsService;
import hhplus.ecommerce.product.application.ProductsServiceImpl;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import hhplus.ecommerce.util.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api/product")
public class ProductController {

    private final ProductsService productsService;
    private final JwtTokenProvider jwtTokenProvider;

    public ProductController(ProductsService productsService, JwtTokenProvider jwtTokenProvider) {
        this.productsService = productsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/{productId}")
    public BaseResponse<ProductInfoRes> userReadProductInfo(@PathVariable Long productId) {
        String token = jwtTokenProvider.getHeader();
        Long userId = Long.valueOf(JwtTokenProvider.getUserPk(token));

        try {
            ProductInfoRes product = productsService.userReadProductInfo(productId, userId);
            return new BaseResponse<>(product);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
