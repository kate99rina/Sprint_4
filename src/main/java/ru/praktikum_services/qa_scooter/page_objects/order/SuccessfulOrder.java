package ru.praktikum_services.qa_scooter.page_objects.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulOrder {
    private WebDriver driver;

    private By successfulMsg = By.xpath(".//*[text()='Заказ оформлен']");

    public SuccessfulOrder(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasSuccessfulMsg() throws Exception {
        try {
            return driver.findElement(successfulMsg).isDisplayed();
        } catch (Exception e) {
            throw new Exception("ERROR! Not found msg: 'Заказ оформлен'");
        }
    }
}
