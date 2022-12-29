package ru.praktikum_services.qa_scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private By name = By.xpath(".//input[@placeholder='* Имя']");
    private By surName = By.xpath(".//input[@placeholder='* Фамилия']");
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By telephoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By continueButton = By.xpath(".//button[text()='Далее']");
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createOrder(String name,
                            String surname,
                            String address,
                            String metroStation,
                            String telephoneNumber) {
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.surName).sendKeys(surname);
        driver.findElement(this.address).sendKeys(address);
        driver.findElement(this.metroStation).click();
        moveToElement(By.xpath(".//*[text()='" + metroStation + "']"));
        driver.findElement(By.xpath(".//*[text()='" + metroStation + "']")).click();
        driver.findElement(this.telephoneNumber).sendKeys(telephoneNumber);
    }

    public void clickContinueFillOrder() {
        moveToElement(continueButton);
        driver.findElement(continueButton).click();
    }

    public void moveToElement(By element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(element));
    }

}
