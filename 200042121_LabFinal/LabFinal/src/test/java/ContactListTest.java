

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactListTest {
  private static WebDriver driver;
  private static Map<String, Object> vars;
  private static Actions actions;
  static JavascriptExecutor js;
  static String firstName="john";
  static String lastName="doe";
  static String birthdate="2024-02-21";
  static String email="johndoe@doe.com";
  static String phone="01991681338";
  static String street1="Mohammadpur";
  static String street2="Asad Avenue";
  static String city="Haha";
  static String stateProvince="Dhaka";
  static String postalCode="1207";
  static String country="Bangladesh";

  @BeforeAll
  public static void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    driver.findElement(By.id("email")).sendKeys("mirzaazwad_200042121@user.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("1234567");
    WebElement submit=driver.findElement(By.id("submit"));
    actions=new Actions(driver);
    actions.moveToElement(submit).click().pause(Duration.ofSeconds(2)).perform();
  }
  @AfterAll
  public static void tearDown() {
    driver.quit();
  }
  public void sendData(){
    driver.findElement(By.id("lastName")).sendKeys(lastName);
    driver.findElement(By.id("email")).sendKeys(email);
    driver.findElement(By.id("phone")).sendKeys(phone);
    driver.findElement(By.id("street1")).sendKeys(street1);
    driver.findElement(By.id("street2")).sendKeys(street2);
    driver.findElement(By.id("city")).sendKeys(city);
    driver.findElement(By.id("stateProvince")).sendKeys(stateProvince);
    driver.findElement(By.id("postalCode")).sendKeys(postalCode);
    driver.findElement(By.id("country")).sendKeys(country);
  }

  @Order(1)
  @Test
  public void contactListInvalidFirstName() {
    driver.get("https://thinking-tester-contact-list.herokuapp.com/addContact");
    driver.findElement(By.id("birthdate")).sendKeys(birthdate);
    sendData();
    WebElement contactSubmit=driver.findElement(By.id("submit"));
    actions.moveToElement(contactSubmit).click().pause(Duration.ofSeconds(2)).perform();
    System.out.println(driver.findElement(By.id("error")).getText());
    assertTrue(driver.findElement(By.id("error")).getText().contains("Contact validation failed: firstName: Path `firstName` is required."));
  }

  @Order(2)
  @Test
  public void contactListInvalidDate() {
    driver.get("https://thinking-tester-contact-list.herokuapp.com/addContact");
    driver.findElement(By.id("firstName")).sendKeys(firstName);
    driver.findElement(By.id("birthdate")).sendKeys("02-21-2024");
    sendData();
    WebElement contactSubmit=driver.findElement(By.id("submit"));
    actions.moveToElement(contactSubmit).click().pause(Duration.ofSeconds(2)).perform();
    assertTrue(driver.findElement(By.id("error")).getText().contains("Contact validation failed: birthdate: Birthdate is invalid"));
  }

  @Order(3)
  @Test
  public void contactListValid() {
    driver.get("https://thinking-tester-contact-list.herokuapp.com/addContact");
    driver.findElement(By.id("firstName")).sendKeys(firstName);
    driver.findElement(By.id("birthdate")).sendKeys(birthdate);
    sendData();
    WebElement contactSubmit=driver.findElement(By.id("submit"));
    actions.moveToElement(contactSubmit).click().pause(Duration.ofSeconds(2)).perform();
    WebElement NameTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[2]"));
    WebElement BirthDateTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[3]"));
    WebElement EmailTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[4]"));
    WebElement PhoneTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[5]"));
    WebElement StreetTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[6]"));
    WebElement CityTD=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[7]"));
    WebElement Country=driver.findElement(By.xpath("//*[@id=\"myTable\"]/tr/td[8]"));
    assertTrue(NameTD.getText().contains(firstName+" "+lastName) && BirthDateTD.getText().contains(birthdate) && EmailTD.getText().contains(email) && PhoneTD.getText().contains(phone) && StreetTD.getText().contains(street1+" "+street2) && CityTD.getText().contains(city) && Country.getText().contains(country));
  }
}
