package fatec.ph.les;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

public class scupon {
  private WebDriver driver;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver",
        "src/test/driver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;

  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void sem_cupon() {

    driver.get("http://localhost:8080/shop");
    driver.manage().window().setSize(new Dimension(1524, 811));
    driver.findElement(By.linkText("Learn More")).click();
    driver.findElement(By.id("livrolink")).click();
    driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
    driver.findElement(By.id("save-btn")).click();

  }
}
