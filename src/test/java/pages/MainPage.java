package pages;

import Other.EnvConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    // Кнопка "Личный кабинет"
    private static final By personalArea = By.xpath(".//p[(text()='Личный Кабинет')]");
    // Ссылка "Зарегистрироваться"
    private static final By linkRegister = By.xpath(".//a[(text()='Зарегистрироваться')]");
    // Кнопка «Войти в аккаунт»
    private static final By buttonLoginFromMainPage = By.xpath(".//button[(text()='Войти в аккаунт')]");
    // Кнопка «Оформить заказ»
    private static final By buttonCreateOrderFromMainPage = By.xpath(".//button[(text()='Оформить заказ')]");
    // Конструктор, раздел "булки"
    private static final By activeSectionBreads = By.xpath(".//div[contains(@class,'tab_tab_type_current')]/span[(text()='Булки')]");
    private static final By inactiveSectionBreads = By.xpath(".//span[(text()='Булки')]");
    // Конструктор, раздел "соусы"
    private static final By activeSectionSauces = By.xpath(".//div[contains(@class,'tab_tab_type_current')]/span[(text()='Соусы')]");
    private static final By inactiveSectionSauces = By.xpath(".//span[(text()='Соусы')]");
    // Конструктор, раздел "Начинки"
    private static final By activeSectionEntrails = By.xpath(".//div[contains(@class,'tab_tab_type_current')]/span[(text()='Начинки')]");
    private static final By inactiveSectionEntrails = By.xpath(".//span[(text()='Начинки')]");

    final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    @Step("Нажать на 'личный кабинет' в хедере")
    public MainPage openPersonalArea() {
        driver.findElement(personalArea).click();
        return this;
    }

    @Step("Нажать на 'войти' на главной странице")
    public MainPage clickButtonLoginFromMainPage() {
        driver.findElement(buttonLoginFromMainPage).click();
        return this;
    }

    @Step("Проверить, что выводится кнопка 'оформить заказ'.")
    public MainPage checkButtonCreateOrderFromMainPage() {
        driver.findElement(buttonCreateOrderFromMainPage);
        return this;
    }

    @Step("Нажать на неактивный раздел булки")
    public MainPage clickInactiveSectionBreads() {
        driver.findElement(inactiveSectionBreads).click();
        return this;
    }

    @Step("Проверить, что раздел булки стал активным")
    public MainPage checkActiveSectionBreads() {
        driver.findElement(activeSectionBreads);
        return this;
    }

    @Step("Нажать на неактивный раздел соусы")
    public MainPage clickInactiveSectionSauces() {
        driver.findElement(inactiveSectionSauces).click();
        return this;
    }

    @Step("Проверить, что раздел соусы стал активным")
    public MainPage checkActiveSectionSauces() {
        driver.findElement(activeSectionSauces);
        return this;
    }

    @Step("Нажать на неактивный раздел начинки")
    public MainPage clickInactiveSectionEntrails() {
        driver.findElement(inactiveSectionEntrails).click();
        return this;
    }

    @Step("Проверить, что раздел начинки стал активным")
    public MainPage checkActiveSectionEntrails() {
        driver.findElement(activeSectionEntrails);
        return this;
    }


}


