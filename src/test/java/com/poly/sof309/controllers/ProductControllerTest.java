package com.poly.sof309.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private boolean validateProduct(String name, double price, int stock) {
        if (name == null || name.trim().isEmpty()) return false;
        if (price <= 0) return false;
        if (stock < 0) return false;
        return true;
    }

    private void saveProduct(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0!");
        }
    }

    @ParameterizedTest(name = "Test {index}: {4}")
    @DisplayName("Kiểm tra biên thêm sản phẩm mới với @CsvSource")
    @CsvSource({
        "'', 25000, 10, false, 'Tên sản phẩm rỗng'",
        "'Cà phê sữa', 0, 10, false, 'Giá sản phẩm bằng 0 (biên dưới)'",
        "'Cà phê đá', -5000, 10, false, 'Giá sản phẩm âm'",
        "'Bạc xỉu', 30000, -1, false, 'Số lượng tồn kho âm'",
        "'Cà phê đen', 20000, 50, true, 'Dữ liệu sản phẩm hợp lệ'"
    })
    void testProductValidation(String name, double price, int stock, boolean expected, String description) {
        assertEquals(expected, validateProduct(name, price, stock), description);
    }

    @Test
    @DisplayName("Ném ngoại lệ khi lưu sản phẩm có giá <= 0")
    void testSaveProductInvalidPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            saveProduct(-10000);
        });
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    @DisplayName("Kiểm tra hiệu năng xử lý sản phẩm dưới 2 giây")
    void testProductProcessTimeout() {
        assertTrue(validateProduct("Trà đào", 35000, 20));
    }
}