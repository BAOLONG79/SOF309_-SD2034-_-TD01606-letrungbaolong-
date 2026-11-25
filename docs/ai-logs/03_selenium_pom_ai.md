# Nhật ký AI - Tuần 4: TestNG Nâng cao & Data-Driven

- **Nội dung thực hiện**:
  - Cấu hình file `testng.xml` để quản lý Suite và tích hợp `ITestListener`.
  - Tạo class `TestListener` để tự động chụp ảnh màn hình và lưu vào thư mục `screenshots/` khi test case bị thất bại (fail).
  - Nâng cấp kịch bản kiểm thử Selenium UI sang TestNG framework, sử dụng `@DataProvider` để kiểm thử hàng loạt tài khoản đăng nhập.
  - Phân nhóm test case với `groups = "smoke"` và ràng buộc thứ tự thực thi.
- **Kết quả**: Hệ thống chạy ổn định, sẵn sàng đóng gói và đẩy code lên GitHub.