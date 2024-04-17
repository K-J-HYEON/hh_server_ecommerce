package hhplus.ecommerce.domain.user;

import hhplus.ecommerce.TestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserReaderTest {

    private UserRepository userRepository;
    private UserReader userReader;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userReader = new UserReader(userRepository);
    }

    @Test
    @DisplayName("사용자 조회")
    void read_user() {
        // given
        Long userId = 1L;
        User user = TestFixtures.user(userId);

        given(userRepository.findById(userId)).willReturn(user);

        // when
        User foundUser = userReader.retrieveByUserId(userId);

        // then
        assertThat(foundUser.name()).isEqualTo("김아무개");
        assertThat(foundUser.point()).isEqualTo(10_00_000L);

    }
}