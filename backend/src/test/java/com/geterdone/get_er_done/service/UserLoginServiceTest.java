package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.UserLogin;
import com.geterdone.get_er_done.repository.UserLoginRepository;

@ExtendWith(MockitoExtension.class)
class UserLoginServiceTest {

    @Mock
    private UserLoginRepository repository;

    @InjectMocks
    private UserLoginService service;

    /* ---------------- registerUser ---------------- */

    @Test
    void registerUser_hashesPassword_andSavesUser() {
        UserLogin user = new UserLogin();
        user.setUsername("alice");
        user.setPasswordHash("plainPassword");

        service.registerUser(user);

        // password should be hashed, not equal to plaintext
        assertNotEquals("plainPassword", user.getPasswordHash());

        // hashed password should validate against original
        assertTrue(
            org.springframework.security.crypto.bcrypt.BCrypt
                .checkpw("plainPassword", user.getPasswordHash())
        );

        verify(repository).save(user);
    }

    /* ---------------- validateLogin ---------------- */

    @Test
    void validateLogin_returnsTrue_whenPasswordMatches() {
        String hashed = org.springframework.security.crypto.bcrypt.BCrypt
            .hashpw("secret", org.springframework.security.crypto.bcrypt.BCrypt.gensalt());

        UserLogin stored = new UserLogin();
        stored.setUsername("alice");
        stored.setPasswordHash(hashed);

        when(repository.findByUsername("alice")).thenReturn(stored);

        boolean result = service.validateLogin("alice", "secret");

        assertTrue(result);
    }

    @Test
    void validateLogin_returnsFalse_whenPasswordDoesNotMatch() {
        String hashed = org.springframework.security.crypto.bcrypt.BCrypt
            .hashpw("secret", org.springframework.security.crypto.bcrypt.BCrypt.gensalt());

        UserLogin stored = new UserLogin();
        stored.setUsername("alice");
        stored.setPasswordHash(hashed);

        when(repository.findByUsername("alice")).thenReturn(stored);

        boolean result = service.validateLogin("alice", "wrongPassword");

        assertFalse(result);
    }

    @Test
    void validateLogin_returnsFalse_whenUserNotFound() {
        when(repository.findByUsername("missing")).thenReturn(null);

        boolean result = service.validateLogin("missing", "anything");

        assertFalse(result);
    }

    /* ---------------- getUserLogin ---------------- */

    @Test
    void getUserLogin_returnsUserFromRepository() {
        UserLogin user = new UserLogin();
        user.setUsername("alice");

        when(repository.findByUsername("alice")).thenReturn(user);

        UserLogin result = service.getUserLogin("alice");

        assertEquals("alice", result.getUsername());
        verify(repository).findByUsername("alice");
    }
}
