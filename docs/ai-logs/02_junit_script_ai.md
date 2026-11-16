# Nhật ký AI: Viết Unit Test Controller với JUnit 5

## 1. Ngữ cảnh & Mục tiêu
- Viết bộ Unit Test cho các chức năng xác thực tài khoản (Auth) và quản lý sản phẩm (Product) trong dự án CoffeePoly.
- Áp dụng các kỹ thuật JUnit 5 nâng cao: `@ParameterizedTest` kết hợp `@CsvSource`, kiểm thử ngoại lệ `assertThrows()`, và giới hạn thời gian `@Timeout`.

## 2. Câu lệnh đã gửi cho AI (Prompt)
> "Dựa vào đặc tả nghiệp vụ CoffeePoly, hãy viết Unit Test bằng JUnit 5 cho AuthControllerTest và ProductControllerTest sử dụng @ParameterizedTest + @CsvSource để test giá trị biên, assertThrows() để kiểm thử ngoại lệ nghiệp vụ, và @Timeout(2s)."

## 3. Kết quả do AI sinh ra (Raw Output)
- Đã tạo hoàn chỉnh 2 class kiểm thử `AuthControllerTest.java` và `ProductControllerTest.java` với đầy đủ các trường hợp biên hợp lệ và không hợp lệ.

## 4. Chỉnh sửa của Sinh viên & Lý do sửa
- Điều chỉnh lại tên package thành `com.poly.sof309.controllers` cho khớp với cấu trúc thư mục thực tế của dự án và chạy kiểm thử thành công 100% Pass qua Maven.