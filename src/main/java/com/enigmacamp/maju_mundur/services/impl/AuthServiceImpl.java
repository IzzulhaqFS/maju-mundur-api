package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.constant.Role;
import com.enigmacamp.maju_mundur.dto.request.auth.AuthRequest;
import com.enigmacamp.maju_mundur.dto.response.auth.AuthResponse;
import com.enigmacamp.maju_mundur.entities.User;
import com.enigmacamp.maju_mundur.repositories.UserRepository;
import com.enigmacamp.maju_mundur.security.JwtAuthenticationFilter;
import com.enigmacamp.maju_mundur.security.JwtTokenProvider;
import com.enigmacamp.maju_mundur.services.AuthService;
import com.enigmacamp.maju_mundur.services.RedisTokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public AuthResponse registerMerchant(AuthRequest request) {
        try {
            Role role = Role.ROLE_MERCHANT;

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();

            userRepository.saveAndFlush(user);
            return AuthResponse.builder()
                    .username(user.getUsername())
                    .role(user.getRole().name())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated Username.");
        }
    }

    @Override
    public AuthResponse registerCustomer(AuthRequest request) {
        try {
            Role role = Role.ROLE_CUSTOMER;

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();

            userRepository.saveAndFlush(user);
            return AuthResponse.builder()
                    .username(user.getUsername())
                    .role(user.getRole().name())
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated Username.");
        }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String token = tokenProvider.generateToken(user.getUsername(), user.getAuthorities().toString());
        return AuthResponse.builder()
                .username(user.getUsername())
                .role(user.getAuthorities().toString())
                .token(token)
                .build();
    }
}
