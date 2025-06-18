package com.pratice.spring.security.service;

import com.pratice.spring.security.model.Users;
import com.pratice.spring.security.repo.UserRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;


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







}