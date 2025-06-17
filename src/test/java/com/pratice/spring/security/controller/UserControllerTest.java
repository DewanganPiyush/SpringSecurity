package com.pratice.spring.security.controller;

import com.pratice.spring.security.model.Users;
import com.pratice.spring.security.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    public void testRegister() {
        // Arrange
        Users inputUser = new Users(1, "Piyush", "password123");
        Users savedUser = new Users(1, "Piyush", "password123");

        when(userService.register(any(Users.class))).thenReturn(savedUser);

        // Act
        Users result = userController.register(inputUser);

        // Assert
        assertNotNull(result);
        assertEquals("Piyush", result.getUsername());
        assertEquals("password123", result.getPassword());
        verify(userService, times(1)).register(any(Users.class));
    }

    @Test
    public void testLogin() {
        // Arrange
        Users inputUser = new Users(2, "Chandu", "secret");
        String expected = "Login Successful";

        when(userService.verify(any(Users.class))).thenReturn(expected);

        // Act
        String result = userController.login(inputUser);

        // Assert
        assertEquals(expected, result);
        verify(userService, times(1)).verify(any(Users.class));
    }
}
