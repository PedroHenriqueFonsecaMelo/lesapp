package fatec.ph.les;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class NovosCuponsTest {
  private WebDriver driver;
  private Map<String, Object> vars;
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
  public void novosCupons() {
    try {
      driver.get("http://localhost:8080/");
      Thread.sleep(100);
      driver.manage().window().setSize(new Dimension(1536, 823));
      driver.findElement(By.linkText("Shop")).click();
      driver.findElement(By.linkText("Learn More")).click();
      driver.findElement(By.id("plus2")).click();
      driver.findElement(By.id("livrolink")).click();
      driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
      driver.findElement(By.id("save-btn")).click();
      driver.findElement(By.cssSelector(".fa-user")).click();
      driver.findElement(By.id("ordem-tab")).click();
      Thread.sleep(100);
      driver.findElement(By.id("cliDetails1")).click();
      driver.findElement(By.id("trocaQuant")).sendKeys("1");
      driver.findElement(By.id("trocaQuant")).click();
      driver.findElement(By.id("save-btn")).click();
      driver.findElement(By.id("cartoes-tab")).click();

      driver.get("http://localhost:8080/admin/admin");
      driver.findElement(By.id("relatorio-tab")).click();
      driver.findElement(By.id("livros-tab")).click();
      driver.findElement(By.id("cartoes-tab")).click();

      Thread.sleep(100);
      driver.findElement(By.cssSelector(".tg-0lax:nth-child(8) > #save-btn")).click();
      driver.findElement(By.linkText("Shop")).click();
      driver.findElement(By.linkText("Learn More")).click();
      driver.findElement(By.id("plus2")).click();
      driver.findElement(By.id("plus2")).click();
      {
        WebElement element = driver.findElement(By.id("plus2"));
        Actions builder = new Actions(driver);
        builder.doubleClick(element).perform();
      }
      driver.findElement(By.id("livrolink")).click();
      driver.findElement(By.cssSelector(".fa-shopping-cart")).click();
      driver.findElement(By.id("cupon")).click();
      {
        WebElement dropdown = driver.findElement(By.id("cupon"));
        dropdown.findElement(By.xpath("//option[. = '20.00']")).click();
      }
      driver.findElement(By.id("cupon2")).click();
      {
        WebElement dropdown = driver.findElement(By.id("cupon2"));
        dropdown.findElement(By.xpath("//option[. = '30.00']")).click();
      }
      driver.findElement(By.id("save-btn")).click();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
