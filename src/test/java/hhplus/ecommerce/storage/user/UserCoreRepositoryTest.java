package hhplus.ecommerce.storage.user;

import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
    class UserCoreRepositoryTest {

//    @Mock
    private UserJpaRepository userJpaRepository;

//    @InjectMocks
    private UserCoreRepository userCoreRepository;

    @BeforeEach
    void setUp() {
        userJpaRepository = mock(UserJpaRepository.class);
        userCoreRepository = new UserCoreRepository(userJpaRepository);
    }

    @Test
    @DisplayName("사용자 정보를 찾지 못하였을 경우 에러 발생")
    void user_not_found_then_error() {
        // Given
        Long userId = 999L;

        // When && Then
        assertThrows(EntityNotFoundException.class, () -> {
            userCoreRepository.findById(userId);
        });
    }
}