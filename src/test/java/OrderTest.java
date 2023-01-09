import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.page_objects.HomePage;
import ru.praktikum_services.qa_scooter.page_objects.order.ConfirmOrderPage;
import ru.praktikum_services.qa_scooter.page_objects.order.OrderPage;
import ru.praktikum_services.qa_scooter.page_objects.order.OrderRentPage;
import ru.praktikum_services.qa_scooter.page_objects.order.SuccessfulOrder;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String orderButton;
    private final String name;
    private final String surName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String daysForRent;
    private final String color;
    private WebDriver driver;
    private final static String WEB_LINK = "https://qa-scooter.praktikum-services.ru/";

    public OrderTest(String button,
                     String name,
                     String surName,
                     String address,
                     String metroStation,
                     String phoneNumber,
                     String date,
                     String daysForRent,
                     String color) {
        this.orderButton = button;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.daysForRent = daysForRent;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {HomePage.getTopOrderButton(), "Катя", "Лепехина", "г.Москва,ул.Басманная", "Бульвар Рокоссовского", "89999999999", "02.12.2022", "сутки", "grey"},
                {HomePage.getBottomOrderButton(), "Иван", "Иванов", "г.Москва,ул.Проспект Вернадского", "Черкизовская", "89998889999", "02.09.2023", "трое суток", "black"}
        };
    }

    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get(WEB_LINK);
    }

    @Test
    public void checkOrderIsCreatedSuccessfullyTrue() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.confirmCookies();
        homePage.clickOrderButton(orderButton);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.createOrder(name, surName, address, metroStation, phoneNumber);
        orderPage.clickContinueFillOrder();

        OrderRentPage orderRentPage = new OrderRentPage(driver);
        orderRentPage.fillRentInfo(date, daysForRent, color);
        orderRentPage.saveOrder();

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage.confirmOrder();

        SuccessfulOrder successfulOrder = new SuccessfulOrder(driver);
        boolean isOrderDone = successfulOrder.hasSuccessfulMsg();
        assertTrue("ERROR! Order is NOT created successfully", isOrderDone);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
