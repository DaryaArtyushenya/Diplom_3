package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserApi {
    @Step("Создание  пользователя")
    public Response createUserApi(User user){
        return given().log().all()
                .header("Content-type","application/json")
                .body(user)
                .post("/api/auth/register");
    }
    @Step("Авторизация пользователя")
    public Response loginUserApi(User user){
        return given().log().all()
                .header("Content-type","application/json")
                .body(user)
                .post("/api/auth/login");
    }
    @Step("Удаление пользователя")
    public void removeUserApi(User user){
        Response loginResponse = loginUserApi(user);
        String token = loginResponse.jsonPath().getString("accessToken");
        given()
                .header("Authorization", token)
                .header("Content-type","application/json")
                .delete("/api/auth/userModel");
    }
}
