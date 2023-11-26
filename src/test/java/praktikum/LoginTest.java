package praktikum;

import Other.DriverRule;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.user.Credentials;
import org.example.user.UserActionApi;
import org.example.user.UserGenerator;
import org.junit.Rule;
import org.junit.Test;
import pages.*;

@Epic("Вход и выход")
public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginWithButtonLoginFromMainPageTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        Header header = new Header(driverRule.getDriver());
        String accessToken;
        UserActionApi userActionApi = new UserActionApi();
        var user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);

        mainPage.open();
        header.openPersonalArea();
        loginPage.clickLinkRegister();

        mainPage.open()
                .clickButtonLoginFromMainPage();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginWithPersonalAreaTest() {

        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        Header header = new Header(driverRule.getDriver());
        String accessToken;
        UserActionApi userActionApi = new UserActionApi();
        var user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);

        mainPage.open();

        header.openPersonalArea();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginWithLinkFromRegisterPageTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        RegisterPage registerPage = new RegisterPage(driverRule.getDriver());
        Header header = new Header(driverRule.getDriver());
        String accessToken;
        UserActionApi userActionApi = new UserActionApi();
        var user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);

        mainPage.open();

        header.openPersonalArea();

        loginPage.clickLinkRegister();

        registerPage.clickLinkLogin();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginWithLinkFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        Header header = new Header(driverRule.getDriver());
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driverRule.getDriver());
        String accessToken;
        UserActionApi userActionApi = new UserActionApi();
        var user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);

        mainPage.open();

        header.openPersonalArea();

        loginPage.clickLinkForgotPassword();

        forgotPasswordPage.clickLinkLoginFromForgotPasswordPage();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Выход  по кнопке «Выйти» в личном кабинете.")
    public void logoutTest() {

        MainPage mainPage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = new LoginPage(driverRule.getDriver());
        Header header = new Header(driverRule.getDriver());
        ProfilePage profilePage = new ProfilePage(driverRule.getDriver());
        String accessToken;
        UserActionApi userActionApi = new UserActionApi();
        var user = UserGenerator.random();

        userActionApi.create(user);
        ValidatableResponse loginResponse = userActionApi.login(Credentials.from(user));
        accessToken = userActionApi.getAccessToken(loginResponse);

        mainPage.open();

        header.openPersonalArea();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        header.openPersonalArea();

        profilePage.clickButtonLogout();

        loginPage.checkHeaderEnter();

        userActionApi.delete(accessToken);
    }
}
