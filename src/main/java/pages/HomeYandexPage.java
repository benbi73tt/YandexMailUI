package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomeYandexPage {
    protected WebDriver driver;
    WebElement searchField;
    WebElement searchButtonInJSController;

    public HomeYandexPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://yandex.ru/");
         }

    public boolean goPage(String namePage) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            System.out.println(driver.getTitle());
            if (driver.getTitle().contains(namePage))
                return true;
        }
        Assertions.fail("Не удалось открыть вкладку, содержащую " + namePage);
        return false;
    }
}
