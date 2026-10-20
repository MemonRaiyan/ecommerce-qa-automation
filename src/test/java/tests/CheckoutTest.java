
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

public class CheckoutTest {

    @Test
    public void userCanCompleteCheckout() {
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

            checkoutPage.enterInformation(
                "Raiyan",
                "QA",
                "380055"
            );

            checkoutPage.continueToOverview();
            checkoutPage.finishOrder();

            Assert.assertEquals(
                checkoutPage.getConfirmationMessage(),
                "Thank you for your order!",
                "A successful checkout should show confirmation"
            );

            System.out.println("PASS: Checkout completed successfully.");
                         } catch (AssertionError | RuntimeException e) {
            FailureScreenshot.capture(
                driver,
                "CheckoutTest-userCanCompleteCheckout"
            );
            throw e;
        } finally {
            driver.quit();
        }

    }
}
