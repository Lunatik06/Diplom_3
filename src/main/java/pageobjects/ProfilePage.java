// Перенес Page Objects в src.main.java.pageobjects
package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    // Кнопка "Профиль"
    private static final By buttonProfile = By.xpath(".//a[(text()='Профиль')]");
    // Кнопка "Выход"
    private static final By buttonLogout = By.xpath(".//button[(text()='Выход')]");
    final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверить наличие кнопки 'профиль'")
    public void checkButtonProfile() {
        driver.findElement(buttonProfile);
    }

    @Step("Нажать на кнопку 'выход")
    public void clickButtonLogout() {
        driver.findElement(buttonLogout).click();
    }
}


