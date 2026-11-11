# AI Prompt Log - Thiết kế Test Plan & Test Cases

## 1. Ngữ cảnh & Mục tiêu
- **Mục tiêu**: Sử dụng AI (đóng vai QA Lead) áp dụng kỹ thuật Phân vùng tương đương và Phân tích giá trị biên để thiết kế Test Plan và Bộ Test Case cho dự án CoffeePoly.

## 2. Câu lệnh đã gửi cho AI (Prompt)
> "Bạn là QA Lead. Dưới đây là đặc tả nghiệp vụ dự án Web của tôi: 
> # ĐẶC TẢ NGHIỆP VỤ WEB CÁ NHÂN (SPECIFICATIONS)
> ## 1. THÔNG TIN DỰ ÁN
> - Tên đề tài: Website Quản lý Bán hàng CoffeePoly
> - Công nghệ: Java 17, Jakarta EE 10, Tomcat 10, SQL Server, Maven
> - URL chạy local: http://localhost:8080/CoffeePoly/
> 
> ## 2. QUY TẮC NGHIỆP VỤ (BUSINESS RULES)
> - Chức năng Đăng ký (/register): Form gồm username (3-20 ký tự, không dấu), email, password (tối thiểu 6 ký tự), confirmPassword.
> - Chức năng Đăng nhập (/login): Kiểm tra đúng tài khoản / mật khẩu -> chuyển hướng về trang chủ; sai -> báo lỗi.
> - Chức năng CRUD kiểm thử (Quản lý sản phẩm - /products):
>   - Thêm mới: Tên sản phẩm (bắt buộc, không trùng), Giá bán (> 0), Số lượng tồn kho (>= 0).
>   - Sửa / Xóa: Có xác nhận trước khi xóa, cập nhật dữ liệu vào DB.
> 
> Hãy áp dụng kỹ thuật Phân vùng tương đương và Phân tích giá trị biên để thiết kế Test Plan và Bộ Test Case chi tiết cho Controller và UI."

## 3. Kết quả đầu ra từ AI (Raw Output)
- Đã nhận được bộ Test Plan và các Test Case chi tiết cho các chức năng Đăng nhập (`/login`), Đăng ký (`/register`) và Quản lý sản phẩm (`/products`).