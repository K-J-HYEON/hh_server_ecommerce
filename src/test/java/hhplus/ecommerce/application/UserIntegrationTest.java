package hhplus.ecommerce.application;


import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserPointService;
import hhplus.ecommerce.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIntegrationTest {

    @Autowired
    private UserPointService userPointService;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("동시에 2건 포인트 충전")
    void concurrency_charge_point() throws InterruptedException {
        // given
        int numThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        Long userId = 1L;
        Long amount = 5000L;

        // when

        for (int i = 0; i < numThreads; i += 1) {
            executorService.submit(() -> {
                try {
                    userPointService.chargePoint(1L, amount);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // then
        User user = userService.getUser(userId);

        assertThat(user.point()).isEqualTo(100_000L + 5000L + 5000L);
    }
}
