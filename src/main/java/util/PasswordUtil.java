package com.coffeepoly.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean matches(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || plainPassword == null) {
            return false;
        }
        // Kiểm tra nếu mật khẩu trong DB đã được mã hóa BCrypt chuẩn
        if (hashedPassword.startsWith("$2a$") || hashedPassword.startsWith("$2b$") || hashedPassword.startsWith("$2y$")) {
            try {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } catch (Exception e) {
                return false;
            }
        }
        // Fallback: So sánh trực tiếp nếu mật khẩu trong DB là chữ thường
        return plainPassword.equals(hashedPassword);
    }
}