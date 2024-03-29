package de.thk.rwoerzbe.toolbox;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Common super class for Selenium tests that contains a 
 * {@link AbstractToolboxSeleniumTest#configureSelenium()} method.
 */
abstract public class AbstractToolboxSeleniumTest {

    /**
     * Member variable that holds the driver by means of which
     * one can remotely control Chrome.
     */
    protected static WebDriver driver;

    /**
     * Sets up the the Chrome driver for all selenium tests.
     */
    @BeforeAll
    static void configureSelenium() {
        // 1 Fixture Setup
        // Necessary executables (chrome and chromedriver) are supposed to be downloaded
        // during the Maven build
        System.setProperty("webdriver.chrome.driver", "./target/chromedriver/chromedriver"
                + (System.getProperty("os.name").contains("Windows") ? ".exe" : ""));
        // needed for running the test in Docker (cf.
        // https://stackoverflow.com/questions/55844788/how-to-fix-severe-bind-failed-cannot-assign-requested-address-99-while)
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        // mandatory options
        options.addArguments("--remote-allow-origins=*");
        // mvn -P autotest clean verify
        // -DargLine="-Dchromedriver.additionalArguments='--headless'"
        final var additionalArguments = getChromeDriverAdditionalArguments();
        if (!additionalArguments.isEmpty()) {
            options.addArguments(getChromeDriverAdditionalArguments());
        }
        // You can explicitly specify the path to the chrome executable.
        // In particular, you can download chrome-for-testing and target its chrome
        // executable.
        String binaryPath;
        final var osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            binaryPath = "./target/chrome/chrome.exe";
        } else if (osName.contains("Mac")) {
            binaryPath = "./target/chrome/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing";
        } else {
            binaryPath = "./target/chrome/chrome";
        }
        options.setBinary(binaryPath);
        driver = new ChromeDriver(options);
    }

    @AfterAll
    static void shutDown() {
        // 4 Fixture Teardown
        driver.quit();
    }

    /**
     * Resolves the common prefix for all URLs, i.e., basically the scheme+server+port part
     * from the property <code>base</code>. If that is not set, the base URL defaults to 
     * <code>http://localhost:8080</code>
     * 
     * @return The base URL resolved as described above
     */
    protected String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    private static List<String> getChromeDriverAdditionalArguments() {
        final String additionalArguments = System.getProperty("chromedriver.additionalArguments");
        if (additionalArguments != null) {
            return Arrays.asList(additionalArguments.split(" "));
        } else {
            return List.of();
        }
    }

}
