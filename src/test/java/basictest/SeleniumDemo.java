package basictest;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.service.DriverFinder;  

public class SeleniumDemo {
	WebDriver driver;
	
	@BeforeClass
    public static void installDriver() {
        String location = DriverFinder.getPath(ChromeDriverService.createDefaultService(), new ChromeOptions());
        System.out.println(location);
    }

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		// Launch Website  
        driver.navigate().to("https://www.google.com");  
          
        // Maximize the browser  
        driver.manage().window().maximize();  
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
          
        // Scroll down the webpage by 5000 pixels  
        JavascriptExecutor js = (JavascriptExecutor)driver;  
        js.executeScript("scrollBy(0, 5000)");   
          
        // Click on the Search button  
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys("find element with selenium");
        searchBar.sendKeys(Keys.RETURN);
        
        WebElement firstLink = driver.findElement(By.partialLinkText("Finding web elements - Selenium"));
        Thread.sleep(1000);
        firstLink.click();
	}
}
