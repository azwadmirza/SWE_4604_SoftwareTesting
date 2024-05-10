import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignuptestEmailTest {
    private static WebDriver driver;
    private static Map<String, Object> vars;
    static JavascriptExecutor js;
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
    @Test
    public void signuptestEmail() {
        driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
        driver.findElement(By.id("firstName")).sendKeys("mirza");
        driver.findElement(By.id("lastName")).sendKeys("azwad");
        driver.findElement(By.id("email")).sendKeys("mirzaazwad_200042121");
        driver.findElement(By.id("password")).sendKeys("1234567");
        {
            WebElement element = driver.findElement(By.id("email"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }

        WebElement submit=driver.findElement(By.id("submit"));
        Actions actions=new Actions(driver);
        actions.moveToElement(submit).click().pause(Duration.ofSeconds(2)).perform();
        String emailText=driver.findElement(By.id("error")).getText();
        System.out.printf("Email: %s",emailText);
        assertTrue(emailText.contains("User validation failed: email: Email is invalid"));
    }
}
