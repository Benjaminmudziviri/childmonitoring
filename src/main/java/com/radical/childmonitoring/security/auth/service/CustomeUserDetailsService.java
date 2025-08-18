package com.radical.childmonitoring.security.auth.service;


import com.radical.childmonitoring.security.repository.UserRepository;
import com.radical.childmonitoring.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(input,input).orElseThrow(()-> new UsernameNotFoundException("User Not found"));
        return user;
    }
}
