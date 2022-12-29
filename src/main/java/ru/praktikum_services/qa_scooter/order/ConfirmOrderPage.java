package ru.praktikum_services.qa_scooter.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmOrderPage {
    private By confirmButton = By.xpath(".//button[text()='Да']");
    private WebDriver driver;

    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmOrder() {
        driver.findElement(confirmButton).click();
    }
}
