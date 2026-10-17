
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstAddToCartButton =
        By.id("add-to-cart-sauce-labs-backpack");

    private final By cartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpackToCart() {
        wait.until(
            ExpectedConditions.elementToBeClickable(firstAddToCartButton)
        ).click();
    }

    public String getCartItemCount() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void openCart() {
        wait.until(
            ExpectedConditions.elementToBeClickable(cartLink)
        ).click();
    }
}
