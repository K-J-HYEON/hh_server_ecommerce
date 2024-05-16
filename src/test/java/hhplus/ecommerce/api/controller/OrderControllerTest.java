package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.application.order.OrderUseCase;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderUseCase orderUseCase;

    /**
     * 1. 주문 요청 성공
     * 2. 받는 사람 정보가 없을 경우 주문 생성 실패
     * 3. 주문 상품 정보 없을 경우 주문 요청 실패
     * 4. 결제 금액 없을 경우 주문 요청 실패
     * 5. 결제 수단 없을 경우 주문 요청 실패
     */

    @Test
    @DisplayName("주문 요청")
    void post_order() throws Exception {
        // Given
        Long userId = 1L;

        Order order = TestFixtures.order(OrderStatus.READY);

//        OrderPaidResult result = new OrderPaidResult(
//                1L,
//                1L,
//                10_00_000L,
//                new Receiver("김 아무개", "서울특별시 마포구", "01012344321"),
//                "CARD",
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );

        given(orderUseCase.order(anyLong(), any())).willReturn(order);

        // When && Then
        mockMvc.perform(post("/ecommerce/api/order/" + userId)
                        .contentType(MediaType.APPLICATION_CBOR)
                        .content("""
                                {
                                    "recriver": {
                                        "name": "김아무개",
                                        "address": "서울특별시 마포구",
                                        "phoneNumber": "01012344321"
                                    },
                                    "products": [
                                        {
                                            "id": 1,
                                            "quantity": 1
                                        }
                                    ],
                                    "paymentAmount": 100000,
                                    "paymentMethod": "TRANSFER"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(1L));
//                .andExpectAll(
//                        jsonPath("$.receiver.name").value("김아무개"),
//                        jsonPath("$.receiver.address").value("서울특별시 마포구"),
//                        jsonPath("$.receiver.payAmount").value("100000"),
//                        jsonPath("$.receiver.paymentMethod").value("TRANSFER")
//                );
    }

    @Test
    @DisplayName("받는 사람 정보가 없을 경우 주문 요청 실패")
    void receiver_not_exist_then_failed_order() throws Exception {
        // Given
        Long userId = 1L;

        // When && Then
        mockMvc.perform(post("/ecommerce/api/order/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "recriver": {
                                        "name": "",
                                        "address": "",
                                        "phoneNumber": ""
                                    },
                                    "products": [
                                        {
                                            "id": 1,
                                            "quantity": 1
                                        }
                                    ],
                                    "paymentAmount": 100000,
                                    "paymentMethod": "TRANSFER"
                                    }
                                """))
                .andExpect(status().isBadRequest());
        verify(orderUseCase, never()).order(anyLong(), any());
    }


    @Test
    @DisplayName("상품 주문 없는 경우 주문 요청 실패")
    void order_product_not_exist_then_failed_order() throws Exception {
        // Given
        Long userId = 1L;

        // When && Then
        mockMvc.perform(post("/ecommerce/api/order/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "recriver": {
                                        "name": "김아무개",
                                        "address": "서울특별시 마포구",
                                        "phoneNumber": "01012344321"
                                    },
                                    "products": [
                                        {
                                            "id": "",
                                            "quantity": ""
                                        }
                                    ],
                                    "paymentAmount": 100000,
                                    "paymentMethod": "TRANSFER"
                                }
                                """))
                .andExpect(status().isBadRequest());
        verify(orderUseCase, never()).order(anyLong(), any());
    }

    @Test
    @DisplayName("결제 수단 없는 경우 주문 요청 실패")
    void payment_method_not_exist_then_failed_order() throws Exception {
        // Given
        Long userId = 1L;

        // When && Then
        mockMvc.perform(post("/ecommerce/api/order/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "recriver": {
                                        "name": "김아무개",
                                        "address": "서울특별시 마포구",
                                        "phoneNumber": "01012344321"
                                    },
                                    "products": [
                                        {
                                            "id": 1,
                                            "quantity": 1
                                        }
                                    ],
                                    "paymentAmount": 100000,
                                    "paymentMethod": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
        verify(orderUseCase, never()).order(anyLong(), any());
    }

    @Test
    @DisplayName("결제 금액 없는 경우 주문 요청 실패")
    void payment_amount_not_exist_then_failed_order() throws Exception {
        // Given
        Long userId = 1L;

        // When && Then
        mockMvc.perform(post("/ecommerce/api/order/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "recriver": {
                                        "name": "김아무개",
                                        "address": "서울특별시 마포구",
                                        "phoneNumber": "01012344321"
                                    },
                                    "products": [
                                        {
                                            "id": 1,
                                            "quantity": 1
                                        }
                                    ],
                                    "paymentAmount": ,
                                    "paymentMethod": "TRANSFER"
                                }
                                """))
                .andExpect(status().isBadRequest());
        verify(orderUseCase, never()).order(anyLong(), any());
    }
}