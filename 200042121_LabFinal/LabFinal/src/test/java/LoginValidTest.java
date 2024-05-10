import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;
public class LoginValidTest {
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
  public void loginInvalid() {
    driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    driver.findElement(By.id("email")).sendKeys("mirzaazwad_200042121@user.com");
    driver.findElement(By.id("password")).sendKeys("1234567");
    WebElement submit=driver.findElement(By.id("submit"));
    Actions actions=new Actions(driver);
    actions.moveToElement(submit).click().pause(Duration.ofSeconds(2)).perform();
    assertTrue(driver.getCurrentUrl().contains("https://thinking-tester-contact-list.herokuapp.com/contactList"));
  }
}
