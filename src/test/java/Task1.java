import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum_services.qa_scooter.page_objects.HomePage;

import java.util.List;

@RunWith(Parameterized.class)
public class Task1 {
    private final static String WEB_LINK = "https://qa-scooter.praktikum-services.ru/";
    private String question;
    private String expectedAnswer;
    private WebDriver driver;

    public Task1(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат." +
                                " Если хотите покататься с друзьями," +
                                " можете просто сделать несколько заказов — один за другим."},
                {"Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."}
        };
    }

    @Before
    public void init() {
        driver = new ChromeDriver();
        driver.get(WEB_LINK);
    }

    @Test
    public void hasAccordingAnswerForQuestionTrue() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.confirmCookies();
        homePage.scrollToQuestions();
        List<WebElement> questions = homePage.getListQuestions();
        boolean result = false;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getText().equals(question)) {
                questions.get(i).click();
                try {
                    result = homePage.getAccordingAnswerBy(i).getText().equals(expectedAnswer);
                } catch (Exception e) {
                    throw new Exception("ERROR! Web element is NOT FOUND");
                }
                break;
            }
        }
        Assert.assertTrue(result);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
