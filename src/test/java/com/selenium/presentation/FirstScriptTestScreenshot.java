package com.selenium.presentation;

import static com.selenium.utils.Utils.takeScreenshot;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverFinder;

public class FirstScriptTestScreenshot {
	final String URL = "https://www.selenium.dev/selenium/web/web-form.html";
	final String SCREENSHOT_PATH_1 = "c://temp//selenium//screenshot_1.png";
	final String SCREENSHOT_PATH_2 = "c://temp//selenium//screenshot_2.png";
	final By TEXT_INPUT_LOCATOR = By.name("my-text");
	final By PASSWORD_INPUT_LOCATOR = By.name("my-password");
	final By TEXT_AREA_INPUT_LOCATOR = By.name("my-textarea");
	WebDriver driver;
	
	@BeforeClass
    public static void installDriver() {
        String location = DriverFinder.getPath(ChromeDriverService.createDefaultService(), new ChromeOptions());
        System.out.println(location);
    }

	@Before
	public void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		// Chrome Capabilities: https://chromedriver.chromium.org/capabilities
		// options.addArguments("--headless=new"); // https://www.selenium.dev/blog/2023/headless-is-going-away/
        driver = new ChromeDriver(options);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws Exception {
        driver.get(URL);

        // Get information from the page
        String title = driver.getTitle();
        assertEquals("Web form", title);

        // maximize window
        driver.manage().window().maximize();
        
        // Specify the time selenium will wait to locate elements
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // Find DOM elements 
        WebElement textBox = driver.findElement(TEXT_INPUT_LOCATOR);
        WebElement password = driver.findElement(PASSWORD_INPUT_LOCATOR);
        WebElement textArea = driver.findElement(TEXT_AREA_INPUT_LOCATOR);
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        // Performing actions on the element
        textBox.sendKeys("Selenium test");
        password.sendKeys("password");
        textArea.sendKeys("Hello world! 42!");
        takeScreenshot(driver, SCREENSHOT_PATH_1);
        submitButton.click();

        // Getting result after submit, and check if it is the expected
        WebElement message = driver.findElement(By.xpath("//h1[contains(text(),'Form submitted')]"));
        String value = message.getText();
        takeScreenshot(driver, SCREENSHOT_PATH_2);
        assertEquals("Form submitted", value);
	}
}
