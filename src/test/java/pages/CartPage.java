
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link")
            )
        ).click();

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

   public void checkout() {
    wait.until(
        ExpectedConditions.elementToBeClickable(checkoutButton)
    ).click();

    wait.until(
        ExpectedConditions.urlContains("checkout-step-one.html")
    );
} 

}
