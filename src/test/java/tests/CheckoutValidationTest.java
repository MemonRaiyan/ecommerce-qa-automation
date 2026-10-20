package tests;

import utils.DriverFactory;
import utils.FailureScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutValidationTest {

    @Test
    public void missingFirstNameShowsValidationError() {
        WebDriver driver = DriverFactory.createDriver();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(
                ExpectedConditions.urlContains("inventory.html")
            );

            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.addBackpackToCart();

            CartPage cartPage = new CartPage(driver);
            cartPage.open();
            cartPage.checkout();

            CheckoutPage checkoutPage = new CheckoutPage(driver);

            // Leave the first name blank.
            checkoutPage.enterInformation("", "QA", "380055");
            checkoutPage.continueToOverview();

            Assert.assertTrue(
                checkoutPage.getCheckoutError()
                    .contains("First Name is required"),
                "Checkout should require a first name"
            );

            System.out.println(
                "PASS: Missing first name is rejected."
            );

        } catch (AssertionError | RuntimeException e) {
            FailureScreenshot.capture(driver, "CheckoutValidationTest");
            throw e;
        } finally {
            driver.quit();
        }
    }
}
