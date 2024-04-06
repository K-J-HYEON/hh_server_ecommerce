package hhplus.ecommerce.util.jwt;

import hhplus.ecommerce.user.domain.User;
import hhplus.ecommerce.user.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = null;
        try {
            user = (User) userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return new UserDetailsImpl(user);
    }
}
