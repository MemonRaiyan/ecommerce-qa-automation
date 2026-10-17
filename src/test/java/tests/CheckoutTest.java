
package tests;

import utils.DriverFactory;

import org.openqa.selenium.WebDriver;
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

        } finally {
            driver.quit();
        }
    }
}
