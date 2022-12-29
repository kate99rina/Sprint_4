package ru.praktikum_services.qa_scooter.page_objects.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderRentPage {
    private By listBox = By.className("Dropdown-control");
    private By dateOrder = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    private WebDriver driver;

    public OrderRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRentInfo(String dateOrder, String days, String color) throws Exception {
        driver.findElement(this.dateOrder).sendKeys(dateOrder);
        driver.findElement(this.dateOrder).sendKeys("\n");
        driver.findElement(listBox).click();
        waitOrMoveToElement(".//div[@class='Dropdown-menu']");
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + days + "']")).click();
        driver.findElement(By.xpath(".//input[@id='" + color + "']")).click();
    }

    public void saveOrder() {
        driver.findElement(By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    public void waitOrMoveToElement(String xpath) throws Exception {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(xpath)));
        } catch (Exception e) {
            throw new Exception("ERROR! Expected element is NOT FOUND by xpath " + xpath);
        }

    }
}
