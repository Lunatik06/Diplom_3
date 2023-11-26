package praktikum;

import Other.DriverRule;
import Other.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@Epic("Регистрация")
public class RegisterTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    TestData testData = new TestData();
    private final String name = testData.getName();
    private final String email = testData.getEmail();
    private final String password = testData.getPassword();
    private final String shortPassword = testData.getShortPassword();

    @Test
    @DisplayName("Успешная регистрация")
    public void registerTest() {

        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        RegisterPage registerPage = new RegisterPage(driverRule.getDriver());

        mainPage.open()
                .openPersonalArea();

        loginPage.clickLinkRegister();

        registerPage.typeRegisterName(name)
                .typeRegisterEmail(email)
                .typeRegisterPassword(password)
                .clickButtonRegister();

        loginPage.checkHeaderEnter();
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов")
    public void registerWithShortPasswordTest() {

        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        RegisterPage registerPage = new RegisterPage(driverRule.getDriver());

        mainPage.open()
                .openPersonalArea();

        loginPage.clickLinkRegister();


        registerPage.typeRegisterName(name)
                .typeRegisterEmail(email)
                .typeRegisterPassword(shortPassword)
                .clickButtonRegister()
                .checkPasswordError();
    }

}
