package com.selenium.presentation;

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
import static com.selenium.utils.Utils.sleep;

public class FirstScriptTest {
	final String URL = "https://www.selenium.dev/selenium/web/web-form.html";
	WebDriver driver;
	
	@BeforeClass
    public static void installDriver() {
        String location = DriverFinder.getPath(ChromeDriverService.createDefaultService(), new ChromeOptions());
        System.out.println(location);
    }

	@Before
	public void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
        driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
        driver.get(URL);

        // Get information from the page
        String title = driver.getTitle();
        assertEquals("Web form", title);

        // maximize window
        driver.manage().window().maximize();
        
        // Specify the time selenium will wait to locate elements
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // Find DOM elements 
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        // Performing actions on the element
        textBox.sendKeys("Selenium");
        sleep(5000);
        submitButton.click();

        // Getting result after submit, and check if it is the expected
        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
        sleep(5000);
        System.out.println("test");
	}
}
