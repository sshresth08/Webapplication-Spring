package de.thk.rwoerzbe.toolbox.palindrome;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.thk.rwoerzbe.toolbox.AbstractToolboxSeleniumTest;

class PalindromeSeleniumTest extends AbstractToolboxSeleniumTest {

    private final String PALINDROME_URL = getBaseUrl() + "/palindrome";

    @Test
    void palindrome_SendPalindrome_ResultTrue() throws InterruptedException {
        // TODO implement test. Use comments as guidelines

        // 2 Exercise SUT
        final String TRUE_PALINDROME = "racecar";
        driver.get(PALINDROME_URL);
        driver.findElement(By.id("palindromecandidate")).sendKeys(TRUE_PALINDROME);
        driver.findElement(By.id("palindromeform")).submit();

        // 3 Result Verification
        // Check if the element with id "result" contains the text "true".
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"result\"]")));
        assertTrue(Boolean.parseBoolean(result.getText()));
    }

    @Test
    void palindrome_SendPalindrome_ResultFalse() throws InterruptedException {
        // TODO implement test
        // 2 Exercise SUT
        final String FALSE_PALINDROME = "car";
        driver.get(PALINDROME_URL);
        driver.findElement(By.id("palindromecandidate")).sendKeys(FALSE_PALINDROME);
        driver.findElement(By.id("palindromeform")).submit();

        // 3 Result Verification
        // Check if the last row in the one we submitted with the randm description
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"result\"]")));
        assertFalse(Boolean.parseBoolean(result.getText()));       
    }

}
