import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class AliOsman {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zeynep\\Desktop\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/campaign/landing.php?&campaign_id=1662524771&extra_1=s%7Cc%7C320628650080%7Ce%7Cfacebook%7C&placement=&creative=320628650080&keyword=facebook&partner_id=googlesem&extra_2=campaignid%3D1662524771%26adgroupid%3D67039035794%26matchtype%3De%26network%3Dg%26source%3Dnotmobile%26search_or_content%3Ds%26device%3Dc%26devicemodel%3D%26adposition%3D%26target%3D%26targetid%3Dkwd-541132862%26loc_physical_ms%3D1000073%26loc_interest_ms%3D%26feeditemid%3D%26param1%3D%26param2%3D&gclid=EAIaIQobChMI_tbY3Oaa8wIVYQJ9Ch1H2QLpEAAYASAAEgLUJvD_BwE");

        WebElement clickEnglish = driver.findElement(By.partialLinkText("English"));
        clickEnglish.click();

        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstname']"));
        firstName.sendKeys("Ali Osman");

        Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
        selectDay.selectByValue("25");

        Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
        selectMonth.selectByValue("2");

        Thread.sleep(2000);

        Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
        selectYear.selectByValue("1995");



        Thread.sleep(5000);
        driver.quit();






    }
}
