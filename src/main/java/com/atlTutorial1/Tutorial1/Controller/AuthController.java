package com.atlTutorial1.Tutorial1.Controller;

import com.atlTutorial1.Tutorial1.Service.AuthService;
import com.atlTutorial1.Tutorial1.Service.CustomUserDetailsService;
import com.atlTutorial1.Tutorial1.dto.LoginDto;
import com.atlTutorial1.Tutorial1.dto.RegisterDto;
import com.atlTutorial1.Tutorial1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    AuthService authService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${jwtSecret}")
    String jwtSecret;

    String token;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.token = jwtTokenProvider.generateToken(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
    }

}
