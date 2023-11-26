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
import pages.Header;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

@Epic("Переходы")
public class TransitionsTest {

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void clickPersonalAreaTest() {
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

        profilePage.checkButtonProfile();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void clickConstructionTest() {
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

        header.openPersonalArea();

        loginPage.typeLoginEmail(user.getEmail())
                .typeLoginPassword(user.getPassword())
                .clickButtonLoginFromLoginPage();

        header.openPersonalArea();

        header.clickConstruction();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void clickLogoTest() {
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

        header.openPersonalArea();

        header.clickLogo();

        mainPage.checkButtonCreateOrderFromMainPage();

        userActionApi.delete(accessToken);

    }
}