
package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailureScreenshot {

    public static void capture(WebDriver driver, String testName) {
        if (driver == null || !(driver instanceof TakesScreenshot)) {
            return;
        }

        try {
            Path directory = Path.of(
                "target", "failure-screenshots"
            );
            Files.createDirectories(directory);

            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")
            );

            String safeName = testName.replaceAll(
                "[^a-zA-Z0-9._-]", "_"
            );

            Path destination = directory.resolve(
                safeName + "-" + timestamp + ".png"
            );

            byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

            Files.write(destination, screenshot);

            System.out.println(
                "Failure screenshot saved: " + destination
            );

        } catch (IOException | RuntimeException e) {
            System.err.println(
                "Screenshot capture failed: " + e.getMessage()
            );
        }
    }
}
