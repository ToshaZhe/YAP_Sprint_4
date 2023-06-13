package ru.yandex.praktikum.qa_scooter.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.qa_scooter.pages.MainQAScooterPage;
import ru.yandex.praktikum.qa_scooter.pages.OrderQAScooterPage;

@RunWith(Parameterized.class)
public class CheckOrderTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String comment;
    private final boolean isOrderButtonUp;
    WebDriver driver;

    public CheckOrderTest(String name, String surname, String address, String phone, String date, String comment, boolean isOrderButtonUp) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.isOrderButtonUp = isOrderButtonUp;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Иван", "Иванов", "Ивановская 3", "+79998887766", "01.07.2023", "Комментарий 1", true},
                {"Пётр", "Петров", "Петровская 3", "+71234567890", "31.12.2023", "Комментарий 2", false},
        };
    }

    @Before
    public void setupAll() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void checkOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainQAScooterPage objMainQAScooterPage = new MainQAScooterPage(driver);
        OrderQAScooterPage objOrderQAScooterPage = new OrderQAScooterPage(driver);
        objMainQAScooterPage.clickOrderButton(isOrderButtonUp);
        objOrderQAScooterPage.waitForLoadOrderPage();
        objOrderQAScooterPage.setName(name);
        objOrderQAScooterPage.setSurname(surname);
        objOrderQAScooterPage.setAddress(address);
        objOrderQAScooterPage.setMetroStation();
        objOrderQAScooterPage.setPhone(phone);
        objOrderQAScooterPage.clickNextButton();
        objOrderQAScooterPage.waitForLoadOrderTermPage();
        objOrderQAScooterPage.setDate(date);
        objOrderQAScooterPage.setTerm();
        objOrderQAScooterPage.setColor();
        objOrderQAScooterPage.setComment(comment);
        objOrderQAScooterPage.clickOrderButton();
        objOrderQAScooterPage.waitForLoadOrderConfirmationPage();
        objOrderQAScooterPage.clickOrderConfirmationYesButton();
        objOrderQAScooterPage.waitForLoadOrderProcessedPage();
        System.out.println("Номер заказа: " + objOrderQAScooterPage.getOrderNumber());
        objOrderQAScooterPage.clickCheckStatusButton();
    }

    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}
