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
public class HomePageTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void initialization(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        HomePageTest.chrome=new ChromeDriver(options);
        HomePageTest.js=(JavascriptExecutor) HomePageTest.chrome;
    }

    @Order(1)
    @Test
    public void TestSortAZ(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (A - Z)']")).click();
        }
        Actions actions=new Actions(chrome);
        actions.pause(Duration.ofSeconds(4)).perform();
        WebElement element=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=element.getText();
        assertEquals("Adjustable Wrench",text);
    }

    @Order(2)
    @Test
    public void TestSortZA(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (Z - A)']")).click();
        }
        Actions actions=new Actions(chrome);
        actions.pause(Duration.ofSeconds(2)).perform();
        WebElement element=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=element.getText();
        assertEquals("Wood Saw",text);
    }
    @Order(3)
    @Test
    public void TestSortPriceHighLow(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (High - Low)']")).click();
        }
        Actions actions=new Actions(chrome);
        actions.pause(Duration.ofSeconds(2)).perform();
        WebElement element=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=element.getText();
        assertEquals("Drawer Tool Cabinet",text);
    }
    @Order(4)
    @Test
    public void TestSortPriceLowHigh(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (Low - High)']")).click();
        }
        Actions actions=new Actions(chrome);
        actions.pause(Duration.ofSeconds(2)).perform();
        WebElement element=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=element.getText();
        assertEquals("Washers",text);
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = '']")).click();
        }
    }
    @Order(5)
    @Test
    public void FilterByBrandTestForgeFlex(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,2500)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[6]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
        assertEquals("Combination Pliers",text);
    }
    @Order(6)
    @Test
    public void FilterByBrandTestMightyCraft(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,2500)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[7]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        assertEquals("Bolt Cutters",text);
    }
    @Order(7)
    @Test
    public void FilterByBrandTestMightyCraftAndForgeFlex(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,2500)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[6]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
        element=wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[7]/label/input")));
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
        assertEquals("Combination Pliers",text);
    }
    @Order(8)
    @Test
    public void TestDoubleClickCheckBoxMightyCraft(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,2500)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[7]/label/input")));
        Actions actions=new Actions(chrome);
        actions.doubleClick(element).pause(Duration.ofSeconds(1)).perform();
        assertFalse(element.isSelected());
    }
    @Order(9)
    @Test
    public void FilterByCategoryHandTools(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        assertEquals("Claw Hammer with Shock Reduction Grip",text);
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
    }
    @Order(10)
    @Test
    public void FilterByCategoryPowerTools(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[3]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        assertEquals("Sheet Sander",text);
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
    }
    @Order(11)
    @Test
    public void FilterByCategoryOtherTools(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(4)).perform();
        WebElement anotherElement=chrome.findElement(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]/div[2]/h5"));
        String text=anotherElement.getText();
        assertEquals("Leather toolbelt",text);
        actions.click(element).pause(Duration.ofSeconds(2)).perform();
    }
    @Order(12)
    @Test
    public void CheckedHammer(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[1]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(13)
    @Test
    public void CheckedWrench(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[3]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(14)
    @Test
    public void CheckedScrewDriver(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[4]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(15)
    @Test
    public void CheckedPliers(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[5]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(16)
    @Test
    public void CheckedChisels(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[6]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(17)
    @Test
    public void CheckedMeasures(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[7]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(18)
    @Test
    public void CheckedHandSaw(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[2]/ul/div[2]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(19)
    @Test
    public void CheckedGrinder(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[3]/ul/div[1]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(20)
    @Test
    public void CheckedSander(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[3]/ul/div[2]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(21)
    @Test
    public void CheckedSaw(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[3]/ul/div[3]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(22)
    @Test
    public void CheckedToolBelts(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/ul/div[1]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(23)
    @Test
    public void CheckedStorageSolutions(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/ul/div[2]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(24)
    @Test
    public void CheckedWorkBench(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/ul/div[3]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(25)
    @Test
    public void CheckedSafetyGears(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/ul/div[4]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(26)
    @Test
    public void CheckedFasteners(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[4]/ul/div[5]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }
    @Order(27)
    @Test
    public void CheckedDrill(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        js.executeScript("window.scrollBy(0,750)","");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.
                visibilityOfElementLocated (By.xpath("/html/body/app-root/div/app-overview/div[3]/div[1]/div[3]/ul/div[4]/label/input")));
        Actions actions=new Actions(chrome);
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
        assertTrue(element.isSelected());
        actions.click(element).pause(Duration.ofSeconds(1)).perform();
    }

    @AfterAll
    public static void terminate(){
        HomePageTest.chrome.quit();
    }
}
