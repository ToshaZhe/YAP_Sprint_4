package ru.yandex.praktikum.qa_scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainQAScooterPage {

    private final WebDriver driver;

    //Тест1:
    // Выпадающий список в разделе «Вопросы о важном».
    // Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.

    //Кнопка заказать верхняя
    private final By orderButtonHeader = By.cssSelector(".Header_Nav__AGCXC button.Button_Button__ra12g");
    //Кнопка заказать нижняя
    private final By orderButtonDown = By.cssSelector(".Home_FinishButton__1_cWm button");

    public MainQAScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Добавить методы
    //Метод получения ответа
    public String getAccordionAnswer(String question) {
        //Поиск элемента с вопросом
        WebElement accordionQuestionElement = driver.findElement(
                By.xpath("//div[@class='accordion__button' and text()='" + question + "']"));
        //Скролим до найденного элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionQuestionElement);
        //Ждем когда на элемент можно кликнуть, иначе картинка с самокатом может перекрыть его.
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(accordionQuestionElement));
        //Кливаем на него что бы раскрыть его
        accordionQuestionElement.click();
        //Считываем ответ и возвращаем его
        return accordionQuestionElement.findElement(
                By.xpath("./../../div[@class='accordion__panel']/p")).getText();
    }

    public void clickOrderButton(boolean isOrderButtonUp) {
        if (isOrderButtonUp) {
            driver.findElement(orderButtonHeader).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonDown));
            driver.findElement(orderButtonDown).click();
        }

    }
}
