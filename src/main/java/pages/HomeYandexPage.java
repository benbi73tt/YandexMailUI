package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HomeYandexPage {
    protected WebDriver driver;
    private String authorization = "//div[@class='desk-notif-card__card']//a[contains(@href, 'https://passport.yandex.ru/')]";
    private String fieldMail = "//a[contains(@href, 'https://mail.yandex.ru')]";

    private List<WebElement> authorizationList = new ArrayList<>();

    public HomeYandexPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://yandex.ru/");
        this.authorizationList = driver.findElements(By.xpath(authorization));
    }

    public void goToMail() {
        driver.findElement(By.xpath(fieldMail)).click();
    }

    public void goToAuthorization(String name) {
        WebElement go = authorizationList.stream().filter(x -> x.getText().contains(name)).findFirst().get();
        go.click();
    }

    public boolean goPage(String namePage) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            if (driver.getTitle().contains(namePage))
                return true;
        }
        Assertions.fail("Не удалось открыть вкладку, содержащую " + namePage);
        return false;
    }
}
