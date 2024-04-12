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
public class ProfilePageTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;
    private  static String email;
    private static String password;

    private static String firstName;

    private static String lastName;

    private static String address;

    private static String city;

    private static String country;

    private static String phone;

    private static void login(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        email.sendKeys(ProfilePageTest.email);
        password.sendKeys(ProfilePageTest.password);
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
    }

    @BeforeAll
    public static void initialization() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ProfilePageTest.chrome = new ChromeDriver(options);
        ProfilePageTest.js = (JavascriptExecutor) ProfilePageTest.chrome;
        ProfilePageTest.email="customer@practicesoftwaretesting.com";
        ProfilePageTest.password="helloWorld123.";
        ProfilePageTest.firstName="Jane";
        ProfilePageTest.lastName="Doe";
        ProfilePageTest.address="Test street 98";
        ProfilePageTest.city="Vienna";
        ProfilePageTest.country="Austria";
        ProfilePageTest.phone="123456789";

        ProfilePageTest.login();
    }

    @AfterAll
    public static void tearDown() {
        chrome.quit();
    }

    @Order(1)
    @Test
    public void testFirstName(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        Actions actions=new Actions(chrome);
        actions.pause(Duration.ofSeconds(5)).perform();
        WebElement FirstName=chrome.findElement(By.id("first_name"));
        String expected=ProfilePageTest.firstName;
        String actual=FirstName.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(2)
    @Test
    public void testLastName(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement LastName=chrome.findElement(By.id("last_name"));
        String expected=ProfilePageTest.lastName;
        String actual=LastName.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(3)
    @Test
    public void testEmail() {
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement Email = chrome.findElement(By.id("email"));
        String expected = ProfilePageTest.email;
        String actual = Email.getAttribute("value");
        assertEquals(expected, actual);
    }

    @Order(4)
    @Test
    public void testAddress(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement Address=chrome.findElement(By.id("address"));
        String expected=ProfilePageTest.address;
        String actual=Address.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(5)
    @Test
    public void testCity(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement City=chrome.findElement(By.id("city"));
        String expected=ProfilePageTest.city;
        String actual=City.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(6)
    @Test
    public void testCountry(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement Country=chrome.findElement(By.id("country"));
        String expected=ProfilePageTest.country;
        String actual=Country.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(7)
    @Test
    public void testUpdatePhone(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement Phone=chrome.findElement(By.id("phone"));
        Phone.clear();
        Phone.sendKeys(ProfilePageTest.phone);
        WebElement saveButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-profile/div/form[1]/div[3]/div/button"));
        Actions actions=new Actions(chrome);
        actions.moveToElement(saveButton).click().pause(Duration.ofSeconds(2)).perform();
        chrome.navigate().refresh();
        actions.pause(Duration.ofSeconds(5)).perform();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
        String expected=ProfilePageTest.phone;
        String actual=element.getAttribute("value");
        assertEquals(expected,actual);
    }

    @Order(8)
    @Test
    public void testUpdatePassword(){
        chrome.get("https://practicesoftwaretesting.com/#/account/profile");
        WebElement password=chrome.findElement(By.id("current-password"));
        WebElement newPassword=chrome.findElement(By.id("new-password"));
        WebElement confirmPassword=chrome.findElement(By.id("new-password-confirm"));
        password.sendKeys(ProfilePageTest.password);
        newPassword.sendKeys("helloWorld1234.");
        confirmPassword.sendKeys("helloWorld1234.");
        WebElement saveButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-profile/div/form[2]/div[3]/div/button"));
        Actions actions=new Actions(chrome);
        actions.moveToElement(saveButton).click().pause(Duration.ofSeconds(2)).perform();
        ProfilePageTest.password="helloWorld1234.";
        ProfilePageTest.login();
        String actualUrl=chrome.getCurrentUrl();
        String expectedUrl="https://practicesoftwaretesting.com/#/account";
        assertEquals(expectedUrl,actualUrl);
    }




}
