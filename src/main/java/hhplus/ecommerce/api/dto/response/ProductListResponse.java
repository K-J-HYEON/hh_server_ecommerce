package hhplus.ecommerce.api.dto.response;

import java.util.List;

public record ProductListResponse(List<ProductSummaryResponse> products) {
}
