package ru.praktikum_services.qa_scooter.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    //Локатор "Вопросы о важном"
    private final By questionPartSight = By.className("Home_FAQ__3uVm4");
    //Локатор кнопки "Заказать" в верху страницы
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    private final WebDriver driver;
    //Шаблон для локатора вопроса
    private final By buttonQuestion = By.className("accordion__button");
    //Шаблон для локатора ответа
    private final String xpathAccordingAnswer = ".//p//parent::div[@id='accordion__panel-(index)' and not(@hidden)]";
    private final By cookiesButton = By.id("rcc-confirm-button");
    //

    //Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Переместиться к "Вопросы о важном"
    public void scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionPartSight));
    }

    //Получить список вопросов
    public List<WebElement> getListQuestions() {
        return driver.findElements(buttonQuestion);
    }

    // Кликнуть по кнопке "Заказать"
    public void clickOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //Подтвердить полученние cookies
    public void confirmCookies() {
        var cookies = driver.findElement(By.className("App_CookieConsent__1yUIN"));
        if (cookies.isEnabled()) {
            driver.findElement(cookiesButton).click();
        }
    }

    public WebElement getAccordingAnswerBy(int index) {
        String xpath = this.xpathAccordingAnswer.replace("(index)", Integer.toString(index));
        return driver.findElement(By.xpath(xpath));
    }

}
