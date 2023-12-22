package ua.com.alevel.config.security.jwt;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.persistence.repository.UserRepository;
import ua.com.alevel.util.InnerConverter;
import ua.com.alevel.web.dto.responses.JwtUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = InnerConverter.convertUserToJwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new DisabledException("User account is disabled");
        }
        return jwtUser;
    }


}
