package hhplus.ecommerce.product.presentation;

import hhplus.ecommerce.product.application.ProductService;
import hhplus.ecommerce.product.domain.Product;
import hhplus.ecommerce.product.dto.res.ProductInfoDetailRes;
import hhplus.ecommerce.product.dto.res.ProductInfoRes;
import hhplus.ecommerce.product.dto.res.ProductListRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "상품 조회", description = "상품 조회 관련 api 입니다.")
@RestController
@RequestMapping("/ecommerce/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "상품 조회 메서드", description = "상품 조회 메서드입니다.", tags = "상품")	// (1)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class)))
    })
    @GetMapping
    public ProductListRes readProductInfo() {
        List<Product> products = productService.readProductInfo();

        List<ProductInfoRes> productInfoResList = products.stream()
                .map(ProductInfoRes::from)
                .toList();
        return new ProductListRes(productInfoResList);
    }

    @Operation(summary = "상품 상세 조회 메서드", description = "상품 상세 조회 메서드입니다.", tags = "상품")	// (2)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProductInfoDetailRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ProductInfoDetailRes.class)))
    })
    @GetMapping("/{productId}")
    public ProductInfoDetailRes readProductInfoDetail(@PathVariable Long productId) {
        Product product = productService.readProductInfoDetail(productId);
        return ProductInfoDetailRes.from(product);
    }

    @Operation(summary = "인기 상품 조회 메서드", description = "인기 상품 조회 메서드입니다.", tags = "상품")	// (3)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class)))
    })
    @GetMapping("/popular")
    public ProductListRes popularProducts() {
        List<Product> products = productService.readPopularProduct();

        List<ProductInfoRes> productInfoResList = products.stream()
                .map(ProductInfoRes::from)
                .toList();
        return new ProductListRes(productInfoResList);
    }
}
