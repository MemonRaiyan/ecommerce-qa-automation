
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {

    @Test
    public void validUserCanLogin() {
        WebDriver driver = new ChromeDriver();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.urlContains("inventory.html"));

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Successful login should navigate to inventory"
            );

            Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_list")
                )).isDisplayed(),
                "The product list should be visible"
            );

            System.out.println("PASS: POM login test succeeded.");

        } finally {
            driver.quit();
        }
    }
}
