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
public class ProductDetailsAndFavoritePage {
    private static WebDriver chrome;
    private static JavascriptExecutor js;
    private static String url;

    private static void login(){
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
    }

    @BeforeAll
    public static void initialization() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ProductDetailsAndFavoritePage.chrome = new ChromeDriver(options);
        ProductDetailsAndFavoritePage.js = (JavascriptExecutor) ProductDetailsAndFavoritePage.chrome;
        chrome.get("https://practicesoftwaretesting.com/#/");
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[6]/div[1]")));
        Actions actions=new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        url=chrome.getCurrentUrl();
        ProductDetailsAndFavoritePage.login();
    }

    @AfterAll
    public static void tearDown() {
        chrome.quit();
    }

    @Order(1)
    @Test
    public void testIncreaseQuantity() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement quantity=chrome.findElement(By.xpath("/html/body/app-root/div/app-detail/div[1]/div[2]/div[1]/input"));
        String actualQuantity=quantity.getAttribute("value");
        String expectedQuantity="2";
        assertEquals(expectedQuantity,actualQuantity);
    }

    @Order(2)
    @Test
    public void testDecreaseQuantity() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement decrease=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-decrease-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(decrease).click().pause(Duration.ofSeconds(1)).perform();
        WebElement quantity=chrome.findElement(By.xpath("/html/body/app-root/div/app-detail/div[1]/div[2]/div[1]/input"));
        String actualQuantity=quantity.getAttribute("value");
        String expectedQuantity="1";
        assertEquals(expectedQuantity,actualQuantity);
    }

    @Order(3)
    @Test
    public void testAddToCart() {
        chrome.get(url);
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        Actions actions = new Actions(chrome);
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        String actualCartCount=visibleCart.getText();
        String expectedCartCount="1";
        assertEquals(expectedCartCount,actualCartCount);
    }

    @Order(4)
    @Test
    public void testAddToFavoritesAndFavoritesPageContent() {
        chrome.get(url);
        WebElement addToFavorites=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-favorites\"]"));
        Actions actions = new Actions(chrome);
        actions.moveToElement(addToFavorites).click().pause(Duration.ofSeconds(2)).perform();
        chrome.get("https://practicesoftwaretesting.com/#/account/favorites");
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleFavorites=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-favorites/div/div[3]/div/div[2]/div/h5")));
        String actualFavorites=visibleFavorites.getText();
        String expectedFavorites="Combination Pliers";
        assertEquals(expectedFavorites,actualFavorites);
    }

}
