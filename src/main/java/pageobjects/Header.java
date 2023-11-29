// Перенес Page Objects в src.main.java.pageobjects
package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    // Кнопка "Личный кабинет"
    private static final By personalArea = By.xpath(".//p[(text()='Личный Кабинет')]");
    // Кнопка "Конструктор"
    private static final By construction = By.xpath(".//p[(text()='Конструктор')]");
    // Логотип
    private static final By logo = By.className("AppHeader_header__logo__2D0X2");
    final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать на ссылку 'Личный кабинет' в хедере")
    public void openPersonalArea() {
        driver.findElement(personalArea).click();
    }

    @Step("Нажать на ссылку конструктор в хедере")
    public void clickConstruction() {
        driver.findElement(construction).click();
    }

    @Step("Нажать на лого в хедере")
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
