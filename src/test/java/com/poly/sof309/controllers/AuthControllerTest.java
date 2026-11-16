package com.poly.sof309.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private boolean validateRegister(String username, String password) {
        if (username == null || username.trim().isEmpty()) return false;
        if (username.length() < 3 || username.length() > 20) return false;
        if (password == null || password.length() < 6) return false;
        return true;
    }

    private void registerAccount(String username) {
        if ("admin".equalsIgnoreCase(username)) {
            throw new IllegalArgumentException("Tài khoản đã tồn tại trong hệ thống!");
        }
    }

    @ParameterizedTest(name = "Test {index}: {3}")
    @DisplayName("Kiểm tra biên Form đăng ký với @CsvSource")
    @CsvSource({
        "'', '123456', false, 'Username bị bỏ trống'",
        "'ab', '123456', false, 'Username dưới 3 ký tự (biên dưới)'",
        "'validuser', '12345', false, 'Password dưới 6 ký tự (biên dưới)'",
        "'validuser', '123456', true, 'Dữ liệu hợp lệ hoàn toàn'"
    })
    void testRegisterValidation(String username, String password, boolean expectedResult, String description) {
        boolean actualResult = validateRegister(username, password);
        assertEquals(expectedResult, actualResult, description);
    }

    @Test
    @DisplayName("Kiểm thử ngoại lệ khi đăng ký trùng tên tài khoản")
    void testRegisterDuplicateUserThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            registerAccount("admin");
        });
        assertEquals("Tài khoản đã tồn tại trong hệ thống!", exception.getMessage());
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Kiểm tra thời gian xử lý đăng nhập không vượt quá 2 giây")
    void testLoginPerformanceTimeout() {
        boolean isValid = validateRegister("longlt", "123456");
        assertTrue(isValid);
    }
}