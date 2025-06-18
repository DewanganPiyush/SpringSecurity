package com.pratice.spring.security.service;

import com.pratice.spring.security.model.Users;
import com.pratice.spring.security.repo.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //testUser = new Users(1, "piyush", "pass123");
    }
    @Test
    void testRegisterUser() {
        // Arrange
        Users c1 = new Users(1, "piyush", "pass123");
        when(userService.register(c1)).thenReturn(c1);
        // Act
        Users com = userService.register(c1);
        // Assert
        assertThat(com).isNotNull();
        assertThat(com.getId()).isEqualTo(1);
        verify(userRepo, times(1)).save(c1);
    }

    @Test
    void testVerifyUser() {
        // Arrange
        Users c1 = new Users(1, "piyush", "pass123");

        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any()))
                .thenReturn(auth);
        when(auth.isAuthenticated()).thenReturn(true);
        when(jwtService.generateToken("piyush"))
                .thenReturn("mocked-jwt-token");

        // Act
        String result = userService.verify(c1);

        // Assert
        assertEquals("mocked-jwt-token", result);
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtService, times(1)).generateToken("piyush");
    }

}