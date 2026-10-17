
package tests;

import utils.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

public class CartTest {

    @Test
    public void userCanAddBackpackToCart() {
        WebDriver driver = DriverFactory.createDriver();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                ExpectedConditions.urlContains("inventory.html")
            );

            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.addBackpackToCart();

            Assert.assertEquals(
                inventoryPage.getCartItemCount(),
                "1",
                "Cart should contain one item"
            );

            inventoryPage.openCart();

            Assert.assertTrue(
                wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item_name")
                    )
                ).getText().contains("Sauce Labs Backpack"),
                "Cart should contain the selected backpack"
            );

            System.out.println("PASS: Backpack was added to the cart.");

        } finally {
            driver.quit();
        }
    }
}
