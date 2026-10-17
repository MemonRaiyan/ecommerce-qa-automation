
package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        String ci = System.getenv("CI");

        if ("true".equalsIgnoreCase(ci)) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
        }

        return new ChromeDriver(options);
    }
}
