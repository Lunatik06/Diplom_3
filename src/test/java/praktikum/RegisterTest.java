package praktikum;

import other.DriverRule;
import other.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import other.user.Credentials;
import other.user.User;
import other.user.UserActionApi;
import other.user.UserGenerator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import pageobjects.*;

@Epic("Регистрация")
public class RegisterTest {
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