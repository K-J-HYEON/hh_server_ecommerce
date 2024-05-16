package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;

import java.util.List;

public record OrderForm(
        Long payAmount,
        List<OrderProduct> orderProducts,
        String receiveName,
        String address,
        String phoneNumber,
        String paymentMethod
) {
    public static OrderForm of(OrderRequest request, List<OrderProduct> orderProducts) {
        return new OrderForm(
                request.paymentAmount(),
                orderProducts,
                request.receiver().name(),
                request.receiver().address(),
                request.receiver().phoneNumber(),
                request.paymentMethod());
    }
}
