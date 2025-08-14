package ru.ex.managerms.serucity;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ex.managerms.entity.Authority;
import ru.ex.managerms.exceptions.custom.UserNotFound;
import ru.ex.managerms.repository.UserRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UserNotFound {
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(
                                user.getAuthorities().stream()
                                        .map(Authority::getAuthority)
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toSet()))
                        .build())
                .orElseThrow(() -> new UserNotFound("User '%s' not found".formatted(username)));
    }
}
