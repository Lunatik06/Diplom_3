package praktikum;

import other.DriverRule;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import other.user.Credentials;
import other.user.User;
import other.user.UserActionApi;
import other.user.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import pageobjects.*;

@Epic("Переходы")
public class TransitionsTest {
    MainPage mainPage;
    LoginPage loginPage;
    Header header;
    ProfilePage profilePage;
    ForgotPasswordPage forgotPasswordPage;
    RegisterPage registerPage;
    UserActionApi userActionApi;
    User user;
    String accessToken;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    // вынес типовые действия для всех сценариев в before и after
    public void baseTest() {
        mainPage = new MainPage(driverRule.getDriver());
        loginPage = new LoginPage(driverRule.getDriver());
        header = new Header(driverRule.getDriver());
        profilePage = new ProfilePage(driverRule.getDriver());
        forgotPasswordPage = new ForgotPasswordPage(driverRule.getDriver());
        registerPage = new RegisterPage(driverRule.getDriver());
        userActionApi = new UserActionApi();
        user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);
    }

    @After
    // вынес типовые действия для всех сценариев в before и after
    public void afterAction() {
        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void clickPersonalAreaTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        header.openPersonalArea();
        profilePage.checkButtonProfile();
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void clickConstructionTest() {
        mainPage.open();
        header.openPersonalArea();
        header.openPersonalArea();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        header.openPersonalArea();
        header.clickConstruction();
        mainPage.checkButtonCreateOrderFromMainPage();
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void clickLogoTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        header.openPersonalArea();
        header.clickLogo();
        mainPage.checkButtonCreateOrderFromMainPage();
    }
}