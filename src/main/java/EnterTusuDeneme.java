import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class EnterTusuDeneme {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zeynep\\Desktop\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beinsports.com.tr/");

        WebElement search = driver.findElement(By.xpath("//div[@class='search-button cursor-pointer p-2 pe-0']"));
        search.click();

        WebElement searchElement = driver.findElement(By.xpath("//input[@placeholder='Ara']"));
        searchElement.sendKeys("Galatasaray");
        searchElement.sendKeys(Keys.ENTER);
    }
}
