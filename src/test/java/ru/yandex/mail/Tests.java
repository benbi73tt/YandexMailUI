package ru.yandex.mail;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.Authorization;
import pages.HomeYandexPage;
import pages.YandexMail;

public class Tests extends BaseTests {

    @Feature("Проверка результатов")
    @DisplayName("Отправка сообщений")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @CsvSource({"Почта,testui2022@yandex.ru, testui123, Simbirosft theme"})
    public void mailTest(String authorization, String login, String pass, String about) {
        HomeYandexPage homeGooglePage = new HomeYandexPage(driver);
        homeGooglePage.goToAuthorization(authorization);
        homeGooglePage.goPage("Авторизация");

        Authorization logIn = new Authorization(driver);
        logIn.logInAccount(login, pass);

        YandexMail yandexMail = new YandexMail(driver);
        String was = yandexMail.getAllEmails();

        yandexMail.sendMessage(login, about, "Найдено " + was + " писем\\ьма");
        yandexMail.exitComposeDone();
        yandexMail.updateMessage();

        String become = yandexMail.getAllEmails();
        Assertions.assertTrue(
                Integer.parseInt(was.replaceAll("[^0-9]", ""))
                        <
                        Integer.parseInt(become.replaceAll("[^0-9]", "")),
                "Количество писем не изменилось");
    }
}
