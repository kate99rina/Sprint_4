package ru.praktikum_services.qa_scooter.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomePage {
    //Локатор "Вопросы о важном"
    private final By questionPartSight = By.className("Home_FAQ__3uVm4");
    //css selector для верхней и нижней кнопки "Заказать"
    private static final String topOrderButton = "div.Header_Nav__AGCXC button.Button_Button__ra12g";
    private static final String bottomOrderButton = "div.Home_FinishButton__1_cWm > button";
    private final WebDriver driver;
    //Шаблон для локатора вопроса
    private final String buttonQuestionXpath = ".//div[@class = 'accordion__button' and text() = '(text)']";
    //Шаблон для локатора ответа
    private final String xpathAccordingAnswer = ".//p//parent::div[@class='accordion__panel' and not(@hidden)]";
    private final By cookiesButton = By.id("rcc-confirm-button");

    //Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public static String getTopOrderButton() {
        return topOrderButton;
    }

    public static String getBottomOrderButton() {
        return bottomOrderButton;
    }

    //Переместиться к "Вопросы о важном"
    public void scrollTo(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }

    //Получить список вопросов todo
    public void clickQuestionByText(String text) {
        String xpath = this.buttonQuestionXpath.replace("(text)", text);
        driver.findElement(By.xpath(xpath)).click();
    }

    // Кликнуть по кнопке "Заказать"
    public void clickOrderButton(String orderButton) {
        By element = By.cssSelector(orderButton);
        scrollTo(element);
        driver.findElement(element).click();
    }

    public By getQuestionPartSight() {
        return this.questionPartSight;
    }

    //Подтвердить полученние cookies
    public void confirmCookies() {
        var cookies = driver.findElement(By.className("App_CookieConsent__1yUIN"));
        if (cookies.isEnabled()) {
            driver.findElement(cookiesButton).click();
        }
    }

    public String getAccordingAnswer() {
        return driver.findElement(By.xpath(this.xpathAccordingAnswer)).getText();
    }

}
