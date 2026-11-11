# KẾ HOẠCH KIỂM THỬ (TEST PLAN)

* **Dự án:** Website Bán hàng CoffeePoly
* **Sinh viên:** Lê Trung Bảo Long - SD2034 / TD01606

---

## ## 1. MỤC TIÊU KIỂM THỬ
* Kiểm thử logic backend (Unit test với JUnit 5 cho controller).
* Kiểm thử giao diện tự động (Automation UI Test với Selenium WebDriver).
* Tích hợp kiểm thử tự động với GitHub Actions CI/CD.

## ## 2. PHẠM VI KIỂM THỬ
* **In-Scope:** Luồng Đăng ký, Đăng nhập, và thao tác CRUD sản phẩm trên giao diện Web CoffeePoly (`/login`, `/register`, `/products`).
* **Out-of-Scope:** Kiểm thử hiệu năng (Performance Test), Bảo mật chuyên sâu.

## ## 3. TIÊU CHÍ HOÀN THÀNH
* 100% test script thực thi pass.
* Pipeline GitHub Actions báo xanh (Success).