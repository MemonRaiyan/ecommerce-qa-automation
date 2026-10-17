
package tests;

import utils.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class InvalidLoginTest {

    @Test
    public void invalidPasswordShowsError() {
        WebDriver driver = DriverFactory.createDriver();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "wrong_password");

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

            String actualError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ).getText();

            Assert.assertTrue(
                actualError.contains(
                    "Username and password do not match"
                ),
                "An appropriate error should be displayed"
            );

            System.out.println("PASS: Invalid login displays the expected error.");

        } finally {
            driver.quit();
        }
    }
}
