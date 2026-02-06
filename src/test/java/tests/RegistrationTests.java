package tests;

import api.UserApi;
import factory.UserFactory;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import pageObject.AuthorizationPage;
import pageObject.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTests extends BaseTest {
    private  User user;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    UserApi userApi = new UserApi();
    @BeforeEach
    void setUp(){
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage = new RegistrationPage(driver);
        authorizationPage = new AuthorizationPage(driver);
    }


    @Test
    @DisplayName("Успешная регистрация")
    void successRegisterTest(){
        user = UserFactory.validUser();
        registrationPage.regData(user);
        registrationPage.clickRegButton();
        authorizationPage.waitForAuthPageVisible();
        assertTrue(driver.findElement(authorizationPage.getEmailAuthField()).isDisplayed());
        assertTrue(driver.findElement(authorizationPage.getPasswordAuthField()).isDisplayed());
    }
    @Test
    @DisplayName("Попытка создать пользователя с паролем меньше 6 символов")
    void passwordLessThan6CharactersTest(){
        user = UserFactory.incorrectPassword();
        registrationPage.regData(user);
        driver.findElement(By.tagName("body")).click();
        assertTrue(driver.findElement(registrationPage.getPasswordErrorMessage()).isDisplayed());
    }

    @AfterEach
    void removeUser(){
        if(user != null){
            userApi.removeUserApi(user);
        }
    }
}
