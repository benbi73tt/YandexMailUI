package ru.yandex.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomeYandexPage;

import java.time.Duration;

public class Tests extends BaseTests {

    @Test
    public void mailTest() {
        HomeYandexPage homeGooglePage = new HomeYandexPage(driver);
        driver.findElement(By.xpath("//div[@class=\"desk-notif-card__card\"]//a[contains(@href, \"https://passport.yandex.ru/\")]")).click();
        driver.findElement(By.xpath("//input[@class='Textinput-Control']")).sendKeys("testui2022", Keys.ENTER);
        driver.findElement(By.xpath("//input[@type='password' and @name='passwd']")).sendKeys("testui123", Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(@href, \"https://mail.yandex.ru\")]")).click();
        homeGooglePage.goPage("Яндекс.Почта");

        String a;

        try {
            a = driver.findElement(By.xpath("//a[@title='Входящие']//span[@class='mail-NestedList-Item-Info-Extras']")).getText();
        } catch (NoSuchElementException e) {
            a="0";
        }


        driver.findElement(By.xpath("//a[@href='#compose']")).click();
//        new WebDriverWait(driver, Duration.ofSeconds(3))
//                .until(driver -> driver.findElement(By.xpath("//div[@class='ComposeRecipients-TopRow']//div[@class='compose-LabelRow-Content']")));
        driver.findElement(By.xpath("//div[@class='composeYabbles']")).sendKeys("testui2022@yandex.ru");
        driver.findElement(By.xpath("//div[@class='ComposeSubject']//input")).sendKeys("Simbirsoft");
        driver.findElement(By.xpath("//div[@class='composeReact-MBodyPanels']//div[@role='textbox']")).sendKeys("hello");
        driver.findElement(By.xpath("//div[@class='ComposeControlPanel-Part']//button//div/../..")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Вернуться во \"Входящие\"')]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ns-view-compose-buttons-box')]//span[@title='Проверить, есть ли новые письма (F9)']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String b = driver.findElement(By.xpath("//a[@title='Входящие']//span[@class='mail-NestedList-Item-Info-Extras']")).getText();
        System.out.println(a + "    " + b);
        int was = Integer.parseInt(a.replaceAll("[^0-9]", ""));
        int become = Integer.parseInt(b.replaceAll("[^0-9]", ""));
        System.out.println(was + "  //  " + become);

        Assertions.assertTrue(become > was, "Количество писем не изменилось");
    }
}
