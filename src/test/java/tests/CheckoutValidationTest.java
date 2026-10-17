package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutValidationTest {

    @Test
    public void missingFirstNameShowsValidationError() {
        WebDriver driver = new ChromeDriver();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

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

        } finally {
            driver.quit();
        }
    }
}
