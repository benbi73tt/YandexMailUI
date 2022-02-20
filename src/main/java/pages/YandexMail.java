package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class YandexMail {

    private WebDriver driver;

    private String AllEmails = "//a[@title='Входящие']//span[@class='mail-NestedList-Item-Info-Extras']";

    private String fieldUnreadEmails = "//a[@title='Входящие']//span[@class='mail-NestedList-Item-Info-Link-Text']";

    private String typeMessage = "//a[@href='#compose']";
    private String fieldRecipient = "//div[@class='composeYabbles']";
    private String fieldAbout = "//div[@class='ComposeSubject']//input";
    private String fieldTextMessage = "//div[@class='composeReact-MBodyPanels']//div[@role='textbox']";
    private String sendButton = "//div[@class='ComposeControlPanel-Part']//button//div/../..";
    private String exitComposeDone = "//a[contains(text(),'Вернуться во \"Входящие\"')]";
    private String updateMessage = "//div[contains(@class,'ns-view-compose-buttons-box')]//span[@title='Проверить, есть ли новые письма (F9)']";

//todo
//    private String ОТПРАВИТЬ СООБЩЕНИЕ

    public YandexMail(WebDriver driver) {
        this.driver = driver;
    }

    public String getAllEmails() {
        try {
            return driver.findElement(By.xpath(AllEmails)).getText();
        } catch (NoSuchElementException e) {
            return "0";
        }
    }

    public void sendMessage(String recipient, String about, String textMessage) {
        driver.findElement(By.xpath(typeMessage)).click();
        driver.findElement(By.xpath(fieldRecipient)).sendKeys(recipient);
        driver.findElement(By.xpath(fieldAbout)).sendKeys(about);
        driver.findElement(By.xpath(fieldTextMessage)).sendKeys(textMessage);
        driver.findElement(By.xpath(sendButton)).click();
    }

    public void exitComposeDone() {
        driver.findElement(By.xpath(exitComposeDone)).click();
    }

    public void updateMessage() {
//        driver.findElement(By.xpath(updateMessage)).click();
        driver.navigate().refresh();
    }


}
