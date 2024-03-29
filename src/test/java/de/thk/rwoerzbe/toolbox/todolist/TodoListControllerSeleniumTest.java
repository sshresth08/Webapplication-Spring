package de.thk.rwoerzbe.toolbox.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.thk.rwoerzbe.toolbox.AbstractToolboxSeleniumTest;

class TodoListControllerSeleniumTest extends AbstractToolboxSeleniumTest {

    private final String TODOLIST_URL = getBaseUrl() + "/todolist";

    @Test
    void todoForm_SubmitNewTodo_PageContainsNewTodo() throws InterruptedException {
        // 2 Exercise SUT
        // Open the web page, submit a todo with a random description and reopen the
        // page
        final String DESCRIPTION_TEXT = UUID.randomUUID().toString();
        driver.get(TODOLIST_URL);
        driver.findElement(By.id("description")).sendKeys(DESCRIPTION_TEXT);
        driver.findElement(By.id("todolistform")).submit();

        // 3 Result Verification
        // Check if the last row in the one we submitted with the randm description
        WebElement lastDescription = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"todoTable\"]/tbody/tr[last()]/td[3]")));
        assertEquals(DESCRIPTION_TEXT, lastDescription.getText());
    }

    @Test
    void todoForm_SubmitNewTodo_TodoCounterIncrements() {
        // TODO 2 Exercise SUT: Just open the web page again

        // TODO 3 Result Verification: Check if the todo count is greater than 0
        // Tip: You can find the element with the respective text (String) under its id
        // "todonumbers"
        // You can convert a String to an Integer via Integer.parseInt(String)

    }
}
