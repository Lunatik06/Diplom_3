//Убрал служебные классы из test
package other.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import other.StellarBurgerSpec;

import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.core.Is.is;

public class UserActionApi extends StellarBurgerSpec {
    static final String USER_PATH = "/auth";

    @Step("Создать пользователя")
    public ValidatableResponse create(User user) {
        return spec()
                .body(user)
                .when()
                .post(USER_PATH + "/register")
                .then().log().all();
    }

    @Step("Авторизоваться пользователем")
    public ValidatableResponse login(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(USER_PATH + "/login")
                .then().log().all();
    }

    @Step("Проверить, что авторизация прошла успешно")
    public String getAccessToken(ValidatableResponse loginResponse) {
        String accessToken = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true))
                .extract()
                .path("accessToken");
        return accessToken;
    }

    @Step("Удалить пользователя")
    public ValidatableResponse delete(String accessToken) {
        return spec()
                .headers(Map.of("Authorization", accessToken))
                .when()
                .delete(USER_PATH + "/user")
                .then().log().all();
    }

}
