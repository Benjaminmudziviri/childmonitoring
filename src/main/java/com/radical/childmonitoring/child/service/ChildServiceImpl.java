package com.radical.childmonitoring.child.service;

import com.radical.childmonitoring.child.dto.ChildRequest;
import com.radical.childmonitoring.child.entity.Child;
import com.radical.childmonitoring.child.repository.ChildRepository;
import com.radical.childmonitoring.security.repository.UserRepository;
import com.radical.childmonitoring.security.user.Role;
import com.radical.childmonitoring.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChildServiceImpl implements ChildService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    @Override
    public void createChild(ChildRequest request) {

        User user = new User();

        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.CHILD);
        user.setEmail("");
        user.setDeviceId("");

        User saveduser = userRepository.save(user);

        Child child = new Child();
        child.setFirstname(request.firstName());
        child.setSurname(request.surName());
        child.setUser(user);
        Child saved = childRepository.save(child);
    }
}
