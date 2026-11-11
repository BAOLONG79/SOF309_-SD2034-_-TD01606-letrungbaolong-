package com.coffeepoly.model;

public class User {
    private int userId;
    private String fullName, email, phone, role;

    public User(int userId, String fullName, String email, String phone, String role) {
        this.userId = userId; this.fullName = fullName; this.email = email;
        this.phone = phone; this.role = role;
    }
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getRole() { return role; }
}
