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
public class AddProductToCartTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;
    private static String url;

    private static String email;
    private static String password;

    private static void login(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys(AddProductToCartTest.email);
        password.sendKeys(AddProductToCartTest.password);
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
    }

    private static void goToProductPage(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]")));
        Actions actions=new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        url=chrome.getCurrentUrl();
    }

    @BeforeAll
    public static void initialization() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        AddProductToCartTest.chrome = new ChromeDriver(options);
        AddProductToCartTest.js = (JavascriptExecutor) AddProductToCartTest.chrome;
        AddProductToCartTest.email="customer@practicesoftwaretesting.com";
        AddProductToCartTest.password="welcome01";
        AddProductToCartTest.login();
        AddProductToCartTest.goToProductPage();
    }

    @AfterAll
    public static void tearDown() {
        chrome.quit();
    }

    @Order(1)
    @Test
    public void addToCartCartComponentTitle() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait waitAgain = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=waitAgain.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        actions.moveToElement(visibleCart).click().pause(Duration.ofSeconds(5)).perform();
        WebElement cartComponentTitle=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[1]/span"));
        String actualTitle=cartComponentTitle.getText();
        String expectedTitle="Combination Pliers";
        assertTrue(actualTitle.contains(expectedTitle));
    }

    @Order(2)
    @Test
    public void addToCartCartComponentQuantity() {
        WebElement cartComponentInput=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[3]/input"));
        String actualValue=cartComponentInput.getAttribute("value");
        String expectedTitle="2";
        assertEquals(expectedTitle,actualValue);
    }

    @Order(3)
    @Test
    public void addToCartCartComponentPrice() {
        WebElement cartComponentPrice=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[4]/span"));
        String actualValue=cartComponentPrice.getText();
        String expectedTitle="$14.15";
        assertEquals(expectedTitle,actualValue);
    }

    @Order(4)
    @Test
    public void addToCartCartComponentTotal() {
        WebElement cartComponentPrice=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[5]/span"));
        String actualValue=cartComponentPrice.getText();
        String expectedTitle="$28.30";
        assertEquals(expectedTitle,actualValue);
    }

    @Order(5)
    @Test
    public void addToCartCartComponentTotalRow() {
        WebElement cartComponentPrice=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tfoot/tr/td[5]"));
        String actualValue=cartComponentPrice.getText();
        String expectedTitle="$28.30";
        assertEquals(expectedTitle,actualValue);
    }

    @Order(6)
    @Test
    public void testRemoveFromCart() {
        WebElement removeButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[6]/a"));
        Actions actions=new Actions(chrome);
        actions.moveToElement(removeButton).click().pause(Duration.ofSeconds(10)).perform();
        WebElement cartComponentPrice=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tfoot/tr/td[5]"));
        String actualValue=cartComponentPrice.getText();
        String expectedTitle="$0.00";
        assertEquals(expectedTitle,actualValue);
    }

    @Order(7)
    @Test
    public void proceedToBillingAddress() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait waitAgain = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=waitAgain.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        actions.moveToElement(visibleCart).click().pause(Duration.ofSeconds(5)).perform();
        WebElement proceedToCheckout=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/div/button"));
        actions.moveToElement(proceedToCheckout).click().pause(Duration.ofSeconds(2)).perform();
        WebElement checkoutButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[2]/app-login/div/div/div/div/button"));
        actions.moveToElement(checkoutButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement checkoutTitle=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[3]/app-address/div/div/div/h3"));
        assertEquals("Billing Address",checkoutTitle.getText());
    }

    @Order(8)
    @Test
    public void paymentBuyNowPayLater(){
        WebElement state=chrome.findElement(By.id("state"));
        state.sendKeys("RandomState");
        WebElement postcode=chrome.findElement(By.id("postcode"));
        postcode.sendKeys("1218");
        WebElement proceedToPaymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[3]/app-address/div/div/div/div/button"));
        Actions actions=new Actions(chrome);
        actions.moveToElement(proceedToPaymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(paymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement select=chrome.findElement(By.xpath("//*[@id=\"payment-method\"]"));
        select.sendKeys("Buy Now Pay Later");
        WebElement installments=chrome.findElement(By.xpath("//*[@id=\"monthly_installments\"]"));
        installments.sendKeys("3 monthly installments");
        WebElement payButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(payButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentConfirmation=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[3]/div"));
        assertEquals("Payment was successful",paymentConfirmation.getText());
    }

    @Order(9)
    @Test
    public void paymentCreditCard() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait waitAgain = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=waitAgain.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        actions.moveToElement(visibleCart).click().pause(Duration.ofSeconds(5)).perform();
        WebElement proceedToCheckout=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/div/button"));
        actions.moveToElement(proceedToCheckout).click().pause(Duration.ofSeconds(2)).perform();
        WebElement checkoutButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[2]/app-login/div/div/div/div/button"));
        actions.moveToElement(checkoutButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement state=chrome.findElement(By.id("state"));
        state.sendKeys("RandomState");
        WebElement postcode=chrome.findElement(By.id("postcode"));
        postcode.sendKeys("1218");
        WebElement proceedToPaymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[3]/app-address/div/div/div/div/button"));
        actions.moveToElement(proceedToPaymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(paymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement select=chrome.findElement(By.xpath("//*[@id=\"payment-method\"]"));
        select.sendKeys("Credit Card");
        WebElement cardNumber=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[1]"));
        cardNumber.sendKeys("0000-0000-0000-0000");
        WebElement expirartion=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[2]"));
        expirartion.sendKeys("12/2025");
        WebElement cvc=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[3]"));
        cvc.sendKeys("123");
        WebElement cardHolder=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[4]"));
        cardHolder.sendKeys("Jane Doe");
        WebElement payButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(payButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentConfirmation=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[3]/div"));
        assertEquals("Payment was successful",paymentConfirmation.getText());
    }


}
