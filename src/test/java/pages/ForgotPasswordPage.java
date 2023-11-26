package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    // Ссылка "Войти"
    private static final By linkLoginFromForgotPasswordPage = By.xpath(".//a[(text()='Войти')]");
    final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на ссылку 'войти' на странице сброса пароля")
    public void clickLinkLoginFromForgotPasswordPage() {
        driver.findElement(linkLoginFromForgotPasswordPage).click();
    }

}


