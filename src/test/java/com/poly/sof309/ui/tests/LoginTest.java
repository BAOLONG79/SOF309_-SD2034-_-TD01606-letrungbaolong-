package com.poly.sof309.ui.tests;

import com.poly.sof309.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * =========================================================================
 * [Y4 - Tuần 4] KỊCH BẢN TESTNG CHO SELENIUM UI & DATA-DRIVEN
 * =========================================================================
 */
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String BASE_URL = "http://localhost:8080/login"; // URL web local của bạn

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Kiểm tra nếu chạy trên CI/CD GitHub Actions thì bật Headless
        if (Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(groups = "smoke", enabled = false)
    public void testLoginSuccessUI() {
        driver.get(BASE_URL);
        loginPage.login("admin", "123");
        Assert.assertTrue(driver.getCurrentUrl().contains("/home") || driver.getCurrentUrl().contains("/dashboard"),
                "Sau khi đăng nhập thành công phải chuyển về trang chủ");
    }

    // --- Cấu hình Data-Driven Testing với @DataProvider ---
    @DataProvider(name = "loginAccounts")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"admin", "123", true},           // Tài khoản đúng -> Kỳ vọng thành công
            {"admin", "wrongPass", false},     // Sai mật khẩu -> Kỳ vọng thất bại
            {"lockedUser", "123", false}       // Tài khoản sai -> Kỳ vọng thất bại
        };
    }

    @Test(dataProvider = "loginAccounts", groups = "smoke", enabled = false)
    public void testLoginDataDriven(String user, String pass, boolean isExpectedSuccess) {
        driver.get(BASE_URL);
        loginPage.login(user, pass);
        
        if (isExpectedSuccess) {
            Assert.assertTrue(driver.getCurrentUrl().contains("/home") || driver.getCurrentUrl().contains("/dashboard"),
                    "Kỳ vọng đăng nhập thành công cho tài khoản: " + user);
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                    "Kỳ vọng ở lại trang login khi đăng nhập thất bại với: " + user);
        }
    }
}