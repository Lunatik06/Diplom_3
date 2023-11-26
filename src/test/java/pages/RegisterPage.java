package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    //Инпут "Имя"
    private static final By registerName = By.xpath(".//label[.='Имя']/../input");
    //Инпут "Email"
    private static final By registerEmail = By.xpath(".//label[.='Email']/../input");
    //Инпут "Пароль"
    private static final By registerPassword = By.xpath(".//label[(text()='Пароль')]/../input");
    // Кнопка «Зарегистрироваться»
    private static final By buttonRegister = By.xpath(".//button[(text()='Зарегистрироваться')]");
    // Ошибка "Некорректный пароль"
    private static final By passwordError = By.xpath(".//p[(text()='Некорректный пароль')]");
    // Ссылка "Войти"
    private static final By linkLogin = By.xpath(".//a[(text()='Войти')]");
    final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Указать имя для регистрации")
    public RegisterPage typeRegisterName(String name) {
        driver.findElement(registerName).click();
        driver.findElement(registerName).sendKeys(name);
        return this;
    }

    @Step("Указать почту для регистрации")
    public RegisterPage typeRegisterEmail(String email) {
        driver.findElement(registerEmail).sendKeys(email);
        return this;
    }

    @Step("Указать пароль для регистрации")
    public RegisterPage typeRegisterPassword(String password) {
        driver.findElement(registerPassword).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку 'регистрация'")
    public RegisterPage clickButtonRegister() {
        driver.findElement(buttonRegister).click();
        return new RegisterPage(driver);
    }

    @Step("Проверить наличие ошибке в поле пароль")
    public void checkPasswordError() {
        driver.findElement(passwordError);
        new RegisterPage(driver);
    }

    @Step("")
    public void clickLinkLogin() {
        driver.findElement(linkLogin).click();
    }

}


