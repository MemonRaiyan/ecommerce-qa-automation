
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By confirmationHeader =
        By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterInformation(
            String first,
            String last,
            String postal) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstName)
        ).sendKeys(first);

        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postalCode).sendKeys(postal);
    }

    public void continueToOverview() {
        wait.until(
            ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }
    public String getCheckoutError() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']")
            )
        ).getText();
    }

    public void finishOrder() {
        wait.until(
            ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public String getConfirmationMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                confirmationHeader
            )
        ).getText();
    }
}
