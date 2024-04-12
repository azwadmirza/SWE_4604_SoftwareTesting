import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void initialization(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        LoginPageTest.chrome=new ChromeDriver(options);
        LoginPageTest.js=(JavascriptExecutor) LoginPageTest.chrome;
    }

    @AfterAll
    public static void tearDown(){
        chrome.quit();
    }

    @AfterEach
    public void refreshPage(){
        chrome.navigate().refresh();
    }

    @Order(1)
    @Test
    public void testValidLogin(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("customer@practicesoftwaretesting.com");
        password.sendKeys("welcome01");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu\"]")));
        String actualUrl=chrome.getCurrentUrl();
        String expectedUrl="https://practicesoftwaretesting.com/#/account";
        assertEquals(expectedUrl,actualUrl);
    }

    @Order(2)
    @Test
    public void testInvalidPassword(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("customer@practicesoftwaretesting.com");
        password.sendKeys("welcome02");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[4]/div")));
        String expected="Invalid email or password";
        String actual=element.getText();
        assertEquals(expected,actual);
    }
    @Order(3)
    @Test
    public void testInvalidEmail(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("customer96@practicesoftwaretesting.com");
        password.sendKeys("welcome01");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[4]/div")));
        String expected="Invalid email or password";
        String actual=element.getText();
        assertEquals(expected,actual);
    }

    @Order(4)
    @Test
    public void testEmptyEmail(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("");
        password.sendKeys("welcome01");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[2]/div")));
        String expected="E-mail is required.";
        String actual=element.getText();
        assertEquals(expected,actual);
    }

    @Order(5)
    @Test
    public void testEmptyPassword(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("customer@practicesoftwaretesting.com");
        password.sendKeys("");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/div")));
        String expected="Password is required.";
        String actual=element.getText();
        assertEquals(expected,actual);
    }

    @Order(6)
    @Test
    public void testInvalidEmailAndPassword(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("123@practicesoftwaretesting.com");
        password.sendKeys("12345678");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[4]/div")));
        String expected="Invalid email or password";
        String actual=element.getText();
        assertEquals(expected,actual);
    }


}
