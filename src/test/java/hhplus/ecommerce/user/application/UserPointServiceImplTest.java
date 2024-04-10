package hhplus.ecommerce.user.application;

import hhplus.ecommerce.TestFixtures;
import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.domain.component.UserReader;
import hhplus.ecommerce.user.infrastructure.UserPointManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserPointServiceImplTest {
    private UserPointServiceImpl userPointService;
    private UserReader userReader;
    private UserPointManager userPointManager;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        userPointManager = mock(UserPointManager.class);
        userPointService = new UserPointServiceImpl(userReader, userPointManager);
    }

    @Test
    @DisplayName("포인트 충전 성공")
    void pointcharge_success() {
        // Given
        Long userId = 1L;
        Long chargingPoint = 1000L;
        User user = TestFixtures.user(userId);

        given(userReader.retrieveByUserId(userId)).willReturn(user);
        given(userPointManager.chargePoint(user, chargingPoint))
                .willReturn(new User(user.id(), user.name(), user.address(), user.phoneNumber(), user.point() + chargingPoint));

        // When
        Long point = userPointService.chargePoint(userId, chargingPoint);

        // Then
        assertThat(point).isEqualTo(10_00_000L + 1000L);
    }

    @Test
    @DisplayName("포인트 조회 성공")
    void retrieve_point_success() {
        // Given
        Long userId = 1L;

        given(userReader.retrieveByUserId(userId)).willReturn(TestFixtures.user(userId));

        // when
        Long point = userPointService.readPoint(userId);

        // then
        assertThat(point).isEqualTo(10_00_000L);
    }
}