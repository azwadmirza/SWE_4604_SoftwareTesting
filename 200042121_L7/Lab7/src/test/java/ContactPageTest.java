import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ContactPageTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void initialization(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ContactPageTest.chrome=new ChromeDriver(options);
        ContactPageTest.js=(JavascriptExecutor) ContactPageTest.chrome;
    }

    @AfterAll
    public static void tearDown(){
        chrome.quit();
    }

    @AfterEach
    public void refreshPage(){
        chrome.navigate().refresh();
    }

    @Order(0)
    @Test
    public void testValidSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-contact/div/div/div/div")));
        String text=element.getText();
        assertEquals("Thanks for your message! We will contact you shortly.",text);

    }

    @Order(1)
    @Test
    public void testInvalidEmailSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email_alert\"]/div")));
        String text=element.getText();
        assertEquals("Email format is invalid",text);
    }

    @Order(2)
    @Test
    public void testInvalidMessageSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[2]/div[2]/div")));
        String text=element.getText();
        assertEquals("Message must be minimal 50 characters",text);
    }

    @Order(3)
    @Test
    public void testEmptyEmailSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email_alert\"]/div")));
        String text=element.getText();
        assertEquals("Email is required",text);
    }

    @Order(4)
    @Test
    public void testEmptyMessageSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="";
        message.sendKeys(dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message_alert\"]/div")));
        String text=element.getText();
        assertEquals("Message is required",text);
    }


    @Order(5)
    @Test
    public void testEmptyFirstName(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"first_name_alert\"]/div")));
        String text=element.getText();
        assertEquals("First name is required",text);
    }

    @Order(6)
    @Test
    public void testEmptyLastName(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"last_name_alert\"]/div")));
        String text=element.getText();
        assertEquals("Last name is required",text);
    }


    @Order(7)
    @Test
    public void testEmptySubject(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"subject_alert\"]/div")));
        String text=element.getText();
        assertEquals("Subject is required",text);
    }


    @Order(8)
    @Test
    public void testEmptyAttachment(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        String dummy="AAAAAAAAAAA";
        message.sendKeys(dummy+dummy+dummy+dummy+dummy);
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-contact/div/div/div/div")));
        String text=element.getText();
        assertEquals("Thanks for your message! We will contact you shortly.",text);
    }
}
