package praktikum;

import other.DriverRule;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import pageobjects.MainPage;

@Epic("Конструктор")
public class ConstructionTest {
    MainPage mainPage;

    @Before
    // вынес типовые действия для всех сценариев в before и after
    public void baseTest() {
        mainPage = new MainPage(driverRule.getDriver());
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Конструктор, переход к разделу \"Соусы\"")
    public void clickSaucesTest() {
        mainPage.open()
                .clickInactiveSectionSauces()
                .checkActiveSectionSauces();
    }

    @Test
    @DisplayName("Конструктор, переход к разделу \"Булки\"")
    public void clickBreadsTest() {
        mainPage.open()
                .clickInactiveSectionSauces()
                .clickInactiveSectionBreads()
                .checkActiveSectionBreads();
    }

    @Test
    @DisplayName("Конструктор, переход к разделу \"Начинки\"")
    public void clickEntrailsTest() {
           mainPage.open()
                .clickInactiveSectionEntrails()
                .checkActiveSectionEntrails();
    }
}