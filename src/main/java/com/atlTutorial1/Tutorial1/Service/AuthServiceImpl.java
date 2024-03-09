package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.User;
import com.atlTutorial1.Tutorial1.Repository.UserRepository;
import com.atlTutorial1.Tutorial1.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setLocked(false);
        userRepository.save(user);
    }

}
