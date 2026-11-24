package com.poly.sof309;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case bị lỗi: " + result.getName() + " -> Đang tiến hành chụp ảnh màn hình...");
        try {
            // Chụp ảnh màn hình khi test fail và lưu vào thư mục screenshots/
            // (Đảm bảo project của bạn có cách lấy driver tương ứng, ví dụ qua DriverManager)
            // TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            // File src = ts.getScreenshotAs(OutputType.FILE);
            // File dest = new File("screenshots/" + result.getName() + "_" + System.currentTimeMillis() + ".png");
            // FileUtils.copyFile(src, dest);
            // System.out.println("Đã lưu ảnh tại: " + dest.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}