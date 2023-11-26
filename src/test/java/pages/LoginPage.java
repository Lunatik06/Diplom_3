package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //Инпут "Email"
    private static final By loginEmail = By.xpath(".//label[.='Email']/../input");
    //Инпут "Пароль"
    private static final By loginPassword = By.xpath(".//label[(text()='Пароль')]/../input");
    // Кнопка «Войти»
    private static final By buttonLoginFromLoginPage = By.xpath(".//button[(text()='Войти')]");
    // Заголовок "Вход"
    private static final By headerEnter = By.xpath(".//h2[(text()='Вход')]");
    // Ссылка "Зарегистрироваться"
    private static final By linkRegister = By.xpath(".//a[(text()='Зарегистрироваться')]");
    // Ссылка "Восстановить пароль"
    private static final By linkForgotPassword = By.xpath(".//a[(text()='Восстановить пароль')]");
    final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Указать email для авторизации")
    public LoginPage typeLoginEmail(String email) {
        driver.findElement(loginEmail).sendKeys(email);
        return this;
    }

    @Step("Указать пароль для авторизации")
    public LoginPage typeLoginPassword(String password) {
        driver.findElement(loginPassword).sendKeys(password);
        return this;
    }

    @Step("Нажать на кнопку 'войти' на странице login")
    public void clickButtonLoginFromLoginPage() {
        driver.findElement(buttonLoginFromLoginPage).click();
        new LoginPage(driver);
    }

    @Step("Проверить, что на странице есть заголовок 'войти'")
    public void checkHeaderEnter() {
        driver.findElement(headerEnter);
        new LoginPage(driver);
    }

    @Step("Нажать на ссылку 'регистрация'")
    public void clickLinkRegister() {
        driver.findElement(linkRegister).click();
    }

    @Step("Нажать на ссылку 'забыли пароль'")
    public void clickLinkForgotPassword() {
        driver.findElement(linkForgotPassword).click();
    }
}
