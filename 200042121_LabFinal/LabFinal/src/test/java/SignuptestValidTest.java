import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;
public class SignuptestValidTest {
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
  public void signuptestValid() {
    driver.get("https://thinking-tester-contact-list.herokuapp.com/addUser");
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).sendKeys("mirza");
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("lastName")).sendKeys("azwad");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("mirzaazwad_200042121@user.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("1234567");
    driver.findElement(By.id("submit")).click();
    Actions actions=new Actions(driver);
    actions.moveToElement(driver.findElement(By.id("submit"))).click().pause(Duration.ofSeconds(2)).perform();
    String text=driver.getCurrentUrl();
    System.out.printf("URL: %s",text);
    assertTrue(text.contains("https://thinking-tester-contact-list.herokuapp.com/contactList"));
  }
}
