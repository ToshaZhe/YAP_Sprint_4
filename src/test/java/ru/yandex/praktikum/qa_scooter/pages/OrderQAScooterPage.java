package ru.yandex.praktikum.qa_scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class OrderQAScooterPage {
    private final WebDriver driver;

    //Тест2:
    // Заказ самоката. Весь флоу позитивного сценария.
    // Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
    // Из чего состоит позитивный сценарий:
    //  * Нажать кнопку «Заказать». На странице две кнопки заказа.
    //  * Заполнить форму заказа.
    //  * Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.

    //Добавить локаторы
    //Name
    private final By orderName = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Имя']");
    //Surname
    private final By orderSurname = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Фамилия']");
    //Address
    private final By orderAddress = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Адрес']");
    //Metro_input
    private final By orderMetroStation = By.cssSelector(".select-search input[placeholder *= 'метро']");
    //Metro_List
    private final By orderMetroStationsList = By.cssSelector("li.select-search__row button");
    //Phone
    private final By orderPhone = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Телефон']");
    //Кнопка "Далее"
    private final By orderNextButton = By.cssSelector(".Order_NextButton__1_rCA button");
    //Страница "Про Аренду"
    private final By orderAboutRent = By.xpath("//div[@class = 'Order_Header__BZXOb' and text() = 'Про аренду']");
    //Дата css - .Order_MixedDatePicker__3qiay input[placeholder *= 'Когда']
    private final By orderDate = By.cssSelector(".Order_MixedDatePicker__3qiay input[placeholder *= 'Когда']");
    //Срок клик по class - .Dropdown-placeholder выбор из списка  class Dropdown-option
    private final By orderTerm = By.xpath(".//div[@class = 'Dropdown-placeholder']");
    private final By orderTermList = By.xpath(".//div[@class = 'Dropdown-option']");
    //Цвет самоката чекбокс черный id = black
    private final By orderColorBlack = By.id("black");
    //Цвет самоката чекбокс серый id = gray
    private final By orderColorGrey = By.id("grey");
    //Комментарий css - .Input_InputContainer__3NykH input[placeholder *= 'Комментарий']
    private final By orderComment = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Комментарий']");
    //Кнопка заказать css - .Order_Buttons__1xGrp button ?????????
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Всплывающее окно подтверждения css - .Order_ModalHeader__3FDaJ
    private final By orderConfirmation = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //Да .Order_Buttons__1xGrp button Да ?????
    private final By orderConfirmationYesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Нет .Order_Buttons__1xGrp button Нет ?????
    private final By orderConfirmationNoButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Нет']");
    //Всплывающее окно "Заказ оформлен"
    private final By orderProcessed = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");
    private final By orderProcessedMessage = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']/div[@class='Order_Text__2broi']");
    //кнопка "Посмотреть статус"
    private final By orderCheckStatusButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Посмотреть статус']");

    public OrderQAScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Добавить методы
    public void waitForLoadOrderPage() {
        //Ждем когда отобразится страница заказа
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(orderName));
    }

    public void waitForLoadOrderTermPage() {
        //Ждем когда отобразится страница заказа
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderAboutRent));
    }

    public void waitForLoadOrderConfirmationPage() {
        //Ждем когда отобразится страница подтверждения
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
    }

    public void waitForLoadOrderProcessedPage() {
        //Ждем когда отобразится страница подтверждения
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderProcessed));
    }

    public void setName(String name) {
        driver.findElement(orderName).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(orderSurname).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(orderAddress).sendKeys(address);
    }

    public void setMetroStation() {
        Random random = new Random();
        driver.findElement(orderMetroStation).click();
        List<WebElement> metroStationsList = driver.findElements(orderMetroStationsList);
        metroStationsList.get(random.nextInt(metroStationsList.size())).click();
    }

    public void setPhone(String phone) {
        driver.findElement(orderPhone).sendKeys(phone);
    }

    public void setDate(String date) {
        driver.findElement(orderDate).sendKeys(date, Keys.ENTER);
    }

    public void setTerm() {
        Random random = new Random();
        driver.findElement(orderTerm).click();
        List<WebElement> orderTermListElements = driver.findElements(orderTermList);
        orderTermListElements.get(random.nextInt(orderTermListElements.size())).click();
    }

    public void setColor() {
        Random random = new Random();
        if (random.nextInt(2) > 0) {
            driver.findElement(orderColorBlack).click();
        } else {
            driver.findElement(orderColorGrey).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment, Keys.ENTER);
    }

    public void clickNextButton() {
        driver.findElement(orderNextButton).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOrderConfirmationYesButton() {
        driver.findElement(orderConfirmationYesButton).click();
    }

    public void clickOrderConfirmationNoButton() {
        driver.findElement(orderConfirmationNoButton).click();
    }

    public void clickCheckStatusButton() {
        driver.findElement(orderCheckStatusButton).click();
    }

    public String getOrderProcessedMessage() {
        return driver.findElement(orderProcessedMessage).getText();
    }

    public int getOrderNumber() {
        String orderProcessedMessage = getOrderProcessedMessage();
        String[] parts = orderProcessedMessage.split("[.\\s]+");
        return Integer.parseInt(parts[2]);
    }
}
