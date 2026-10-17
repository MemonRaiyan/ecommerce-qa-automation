
package tests;

import utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstBrowserTest {

    @Test
    public void verifySauceDemoPageTitle() {
        WebDriver driver = DriverFactory.createDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            String actualTitle = driver.getTitle();

            Assert.assertEquals(
                actualTitle,
                "Swag Labs",
                "The page title should be Swag Labs"
            );

            System.out.println("Actual page title: " + actualTitle);

        } finally {
            driver.quit();
        }
    }
}
