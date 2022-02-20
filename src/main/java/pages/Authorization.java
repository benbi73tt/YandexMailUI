package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Authorization {
    private WebDriver driver;

    private String fieldLogin = "//input[@class='Textinput-Control']";
    private String fieldPassword = "//input[@type='password' and @name='passwd']";
    private String btn = "//a[contains(@href, 'https://mail.yandex.ru')]";

    public Authorization(WebDriver driver) {
        this.driver = driver;
    }

    public void logInAccount(String login, String password) {
        driver.findElement(By.xpath(fieldLogin)).sendKeys(login, Keys.ENTER);
        driver.findElement(By.xpath(fieldPassword)).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


}
