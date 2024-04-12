package hhplus.ecommerce.product.presentation;

import hhplus.ecommerce.product.application.ProductService;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.presentation.dto.res.ProductInfoDetailRes;
import hhplus.ecommerce.product.presentation.dto.res.ProductInfoRes;
import hhplus.ecommerce.product.presentation.dto.res.ProductListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Tag(name = "상품 목록 조회 API", description = "상품 목록을 조회하는 API입니다.")
    @GetMapping
    public ProductListRes readProductInfo() {
        List<Product> products = productService.readProductInfo();

        List<ProductInfoRes> productInfoResList = products.stream()
                .map(ProductInfoRes::from)
                .toList();
        return new ProductListRes(productInfoResList);
    }


    @Tag(name = "상품 세부 정보 조회 API", description = "상품 세부 정보를 조회하는 API입니다.")
    @GetMapping("/{productId}")
    public ProductInfoDetailRes readProductInfoDetail(@PathVariable Long productId) {
        Product product = productService.readProductInfoDetail(productId);
        return ProductInfoDetailRes.from(product);
    }


    @Tag(name = "인기 상품 조회 API", description = "최근 3일간 가장 많이 팔린 상품 목록을 조회하는 API입니다.")
    @GetMapping("/popular")
    public ProductListRes popularProducts() {
        List<Product> products = productService.readPopularProduct();

        List<ProductInfoRes> productInfoResList = products.stream()
                .map(ProductInfoRes::from)
                .toList();
        return new ProductListRes(productInfoResList);
    }
}
