package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.config.LockHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserPointServiceTest {

    private UserReader userReader;
    private UserPointManager userPointManager;
    private UserPointService userPointService;
    private LockHandler lockHandler;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        userPointManager = mock(UserPointManager.class);
        lockHandler = mock(LockHandler.class);

        userPointService = new UserPointService(userReader, userPointManager, lockHandler);
    }

    @Test
    @DisplayName("잔액 충전 성공")
    void point_charge_success() {
        // Given
        Long userId = 1L;
        Long chargingPoint = 1000L;
        User user = TestFixtures.user(userId);

        given(userReader.readById(userId)).willReturn(user);
        given(userPointManager.chargePoint(userId, chargingPoint))
                .willReturn(new User(user.id(), user.name(), user.address(), user.phoneNumber(), user.point() + chargingPoint));

        // When
        Long point = userPointService.chargePoint(userId, chargingPoint);

        // Then
        assertThat(point).isEqualTo(10_00_000L + 1000L);
    }

    @Test
    @DisplayName("잔액 조회 성공")
    void read_point_success() {
        // given
        Long userId = 1L;

        given(userReader.readById(userId)).willReturn(TestFixtures.user(userId));

        // when
        Long point = userPointService.readPoint(userId);

        // then
        assertThat(point).isEqualTo(10_00_000L);
    }
}