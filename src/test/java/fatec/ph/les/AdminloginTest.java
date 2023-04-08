package fatec.ph.les;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

public class AdminloginTest {
  private WebDriver driver;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver",
        "src/test/resources/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void adminlogin() {
    try {
      driver.get("http://localhost:8080/");
      Thread.sleep(100);
      driver.manage().window().setSize(new Dimension(1524, 811));
      driver.findElement(By.linkText("Login")).click();
      driver.findElement(By.id("email")).click();
      driver.findElement(By.id("email")).sendKeys("admin@admin");
      driver.findElement(By.id("password")).click();
      driver.findElement(By.id("password")).sendKeys("admin");
      driver.findElement(By.cssSelector("button")).click();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
