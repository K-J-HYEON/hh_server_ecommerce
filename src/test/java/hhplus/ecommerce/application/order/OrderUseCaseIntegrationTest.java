package hhplus.ecommerce.application.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.application.cart.CartUseCase;
import hhplus.ecommerce.domain.cart.cartitem.NewCartItem;
import hhplus.ecommerce.domain.orderitem.OrderItemReader;
import hhplus.ecommerce.domain.product.Stock;
import hhplus.ecommerce.domain.product.StockReader;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderUseCaseIntegrationTest {

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private CartUseCase cartUseCase;

    @Autowired
    private UserService userService;

    @Autowired
    private StockReader stockReader;

    @Autowired
    private OrderItemReader orderItemReader;

    private Long userId1;
    private Long userId2;
    private Long userId3;
    private Long productId;

    @BeforeEach
    void setUp() {
        userId1 = 1L;
        userId2 = 2L;
        userId3 = 3L;

        productId = 2L;
        Long orderQuantity = 1L;

        cartUseCase.addItem(userId1, List.of(new NewCartItem(productId, orderQuantity)));
        cartUseCase.addItem(userId2, List.of(new NewCartItem(productId, orderQuantity)));
        cartUseCase.addItem(userId3, List.of(
                new NewCartItem(productId, orderQuantity),
                new NewCartItem(productId, 10L)
        ));
    }

    @Test
    @DisplayName("동시에 2건의 주문 시 재고 감소 테스트")
    void when_concurrent_two_order_then_decrease_stock() throws InterruptedException {

        // given
        int numThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        OrderRequest request = new OrderRequest(
                new Receiver(
                        "김 아무개",
                        "서울특별시 마포구",
                        "01012344321"
                        ),
                10_00_000L,
                "CARD"
        );

        // when
        executorService.submit(() -> {
            try {
                orderUseCase.order(userId1, request);
            } finally {
                latch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                orderUseCase.order(userId1, request);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
        executorService.shutdown();

        // then

        Stock stock = stockReader.readByProductId(productId);
        assertThat(stock.stockCount()).isEqualTo(10L - 1L - 1L);

        User user = userService.getUser(userId1);
        assertThat(user.point()).isEqualTo(10_00_000L - 1000L);

    }

    @Test
    @DisplayName("동시 5건 주문 시 포인트 차감 동시성 테스트")
    void when_concurrent_five_order_then_decrease_point() throws InterruptedException {

        // given
        int numThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        OrderRequest request = new OrderRequest(
                new Receiver(
                        "김 아무개",
                        "서울특별시 마포구",
                        "01012344321"
                ),
                10_00_000L,
                "CARD"

        );

        // when
        for (int i = 0; i < numThreads; i += 1) {
            executorService.submit(() -> {
                try {
                    orderUseCase.order(userId1, request);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // then
        User user = userService.getUser(userId1);
        assertThat(user.point()).isEqualTo(10_00_000L - (1000L * numThreads));
    }

    @Test
    @DisplayName("재고 부족일 때 재고 롤백 테스트")
    void when_not_enough_product_stock_then_roll_back_product_stock() {

        // given
        OrderRequest request = new OrderRequest(
                new Receiver(
                        "김 아무개",
                        "서울특별시 마포구",
                        "01012344321"
                ),
                10_00_000L,
                "CARD"
        );

        // when & then
        assertThrows(RuntimeException.class, () -> {
            orderUseCase.order(userId3, request);
        });

        Stock stock = stockReader.readByProductId(productId);
        assertThat(stock.stockCount()).isEqualTo(10L);
    }

    @Test
    @DisplayName("잔액 부족일 때 재고 및 잔액 롤백 테스트")
    void when_not_enough_user_point_then_roll_back_user_point() {

        // given
        OrderRequest request = new OrderRequest(
                new Receiver(
                        "김 아무개",
                        "서울특별시 마포구",
                        "01012344321"
                ),
                10_00_000L,
                "CARD"
        );

        // when & then
        assertThrows(RuntimeException.class, () -> {
            orderUseCase.order(userId1, request);
        });

        User user = userService.getUser(userId1);
        assertThat(user.point()).isEqualTo(100_000L);

        Stock stock = stockReader.readByProductId(productId);
        assertThat(stock.stockCount()).isEqualTo(10L);
    }
}