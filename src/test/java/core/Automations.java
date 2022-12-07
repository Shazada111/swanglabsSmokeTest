package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.xml.sax.Locator;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Automations {


    static private WebDriver driver;
    public static WebDriverWait waits;

    static public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        waits= new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    static public void closeBrowser(){
        if (driver != null || waits != null) {
            driver.quit();
            waits=null;
        }
    }
    static public void visit(String url){
    driver.get(url);
    }
   static public void click(By locator){
WebElement element = waits.until(ExpectedConditions.elementToBeClickable(locator));
   element.click();
   }
   static public void type(By locator,String text){
WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
element.sendKeys(text);
   }
   static public String getText(By locator){
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
       return element.getText();

   }

   static public boolean isVisible(By locator){
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isDisplayed();

   }
   static public WebElement findElement(By locator){
       WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
   return element;
   }
   static public List<WebElement> findElements(By locator){
  List<WebElement> allElements = waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

        return allElements;
   }
   static public By xp(String query){
       return  By.xpath(query);
    }
    static public By css(String query){
        return By.cssSelector(query);

    }
}// end:: class
