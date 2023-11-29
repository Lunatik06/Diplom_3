package praktikum;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import other.DriverRule;
import other.user.Credentials;
import other.user.User;
import other.user.UserActionApi;
import other.user.UserGenerator;
import pageobjects.*;

@Epic("Вход и выход")
public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    MainPage mainPage;
    LoginPage loginPage;
    Header header;
    ProfilePage profilePage;
    ForgotPasswordPage forgotPasswordPage;
    RegisterPage registerPage;
    UserActionApi userActionApi;
    User user;
    String accessToken;

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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginWithButtonLoginFromMainPageTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.clickLinkRegister();
        mainPage.open()
                .clickButtonLoginFromMainPage();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        mainPage.checkButtonCreateOrderFromMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginWithPersonalAreaTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        mainPage.checkButtonCreateOrderFromMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginWithLinkFromRegisterPageTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.clickLinkRegister();
        registerPage.clickLinkLogin();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        mainPage.checkButtonCreateOrderFromMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginWithLinkFromForgotPasswordPageTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.clickLinkForgotPassword();
        forgotPasswordPage.clickLinkLoginFromForgotPasswordPage();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        mainPage.checkButtonCreateOrderFromMainPage();
    }

    @Test
    @DisplayName("Выход  по кнопке «Выйти» в личном кабинете.")
    public void logoutTest() {
        mainPage.open();
        header.openPersonalArea();
        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();
        header.openPersonalArea();
        profilePage.clickButtonLogout();
        loginPage.checkHeaderEnter();
    }
}
