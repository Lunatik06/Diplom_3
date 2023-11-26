package praktikum;

import Other.DriverRule;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import pages.MainPage;

@Epic("Конструктор")
public class ConstructionTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Конструктор, переход к разделу \"Соусы\"")
    public void clickSaucesTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());

        mainPage.open()
                .clickInactiveSectionSauces()
                .checkActiveSectionSauces();
    }

    @Test
    @DisplayName("Конструктор, переход к разделу \"Булки\"")
    public void clickBreadsTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());

        mainPage.open()
                .clickInactiveSectionSauces()
                .clickInactiveSectionBreads()
                .checkActiveSectionBreads();
    }

    @Test
    @DisplayName("Конструктор, переход к разделу \"Начинки\"")
    public void clickEntrailsTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());

        mainPage.open()
                .clickInactiveSectionEntrails()
                .checkActiveSectionEntrails();
    }

}