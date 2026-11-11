# ĐẶC TẢ NGHIỆP VỤ WEB CÁ NHÂN (SPECIFICATIONS)
> *Dùng làm ngữ cảnh (Context) gửi cho AI để sinh Test Plan & Test Case chính xác theo dự án riêng của bạn.*

---

## ## 1. THÔNG TIN DỰ ÁN
* **Tên đề tài:** Website Quản lý Bán hàng CoffeePoly
* **Công nghệ:** Java 17, Jakarta EE 10, Tomcat 10, SQL Server, Maven
* **URL chạy local:** `http://localhost:8080/CoffeePoly/`

## ## 2. QUY TẮC NGHIỆP VỤ (BUSINESS RULES)
* **Chức năng Đăng ký (`/register`):** Form gồm `username` (3-20 ký tự, không dấu), `email`, `password` (tối thiểu 6 ký tự), `confirmPassword`.
* **Chức năng Đăng nhập (`/login`):** Kiểm tra đúng tài khoản / mật khẩu $\rightarrow$ chuyển hướng về trang chủ; sai $\rightarrow$ báo lỗi.
* **Chức năng CRUD kiểm thử (Quản lý sản phẩm - `/products`):**
  * **Thêm mới:** Tên sản phẩm (bắt buộc, không trùng), Giá bán ($> 0$), Số lượng tồn kho ($\ge 0$).
  * **Sửa / Xóa:** Có xác nhận trước khi xóa, cập nhật dữ liệu vào DB.