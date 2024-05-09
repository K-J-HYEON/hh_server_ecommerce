//package hhplus.ecommerce.domain.orderitem;
//
//import hhplus.ecommerce.api.dto.request.OrderRequest;
//import hhplus.ecommerce.domain.order.Order;
//import hhplus.ecommerce.domain.product.Product;
//import hhplus.ecommerce.storage.orderitem.OrderItemEntity;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class OrderItemAppender {
//    private final OrderItemRepository orderItemRepository;
//
//    public OrderItemAppender(OrderItemRepository orderItemRepository) {
//        this.orderItemRepository = orderItemRepository;
//    }
//
//    @Transactional
//    public List<OrderItem> create(Order order,
//                                  List<Product> products,
//                                  List<OrderRequest.ProductOrderRequest> productOrderRequests) {
//        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
//
//        for (OrderRequest.ProductOrderRequest orderRequest : productOrderRequests) {
//            Product product = products.stream()
//                    .filter(p -> p.id().equals(orderRequest.id()))
//                    .findFirst()
//                    .orElseThrow(() -> new EntityNotFoundException(orderRequest.id() + " 상품의 정보를 찾지 못했습니다."));
//
//            orderItemEntities.add(
//                    new OrderItemEntity(
//                            order.id(),
//                            product.id(),
//                            product.name(),
//                            product.price(),
//                            product.orderTotalPrice(orderRequest.orderCount()),
//                            orderRequest.orderCount()
//                    )
//            );
//        }
//        return orderItemRepository.createOrderItem(orderItemEntities);
//    }
//}
