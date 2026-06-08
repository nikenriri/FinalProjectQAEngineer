package common.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class WebHook {
    private static WebDriver driver;
    // Variabel statis untuk menyimpan akun yang dibuat saat Sign Up agar bisa dipakai Login & Checkout
    public static String currentUsername;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan saat menutup browser: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}