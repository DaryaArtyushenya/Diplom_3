package tests;

import api.UserApi;
import factory.UserFactory;
import io.restassured.RestAssured;
import model.User;
import org.junit.jupiter.api.*;
import pageObject.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTests extends BaseTest{
    @BeforeAll
    static void uriSetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
    private User user;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    HomePage homePage;
    Header header;
    ResetPasswordPage resetPasswordPage;
    UserApi userApi = new UserApi();
    @BeforeEach
    void setUp(){
        registrationPage = new RegistrationPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        homePage = new HomePage(driver);
        header = new Header(driver);
        resetPasswordPage = new ResetPasswordPage(driver);

    }
        @Test
        @DisplayName("Успешный логин после нажатия на кнопку Войти в аккаунт на главной")
        void loginViaLoginButtonOnHomePageTest(){
            driver.get("https://stellarburgers.education-services.ru");
           user = UserFactory.validUser();
           userApi.createUserApi(user);
           homePage.clickLoginButtonHomePage();
           authorizationPage.waitForAuthPageVisible();
           authorizationPage.authorization(user);
           homePage.waitCreateBurgerHeader();
           assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        }
        @Test
        @DisplayName("Логин через кнопку Личный кабинет в хэдэре")
        void loginViaAccountButtonTest(){
            driver.get("https://stellarburgers.education-services.ru");
            user = UserFactory.validUser();
            userApi.createUserApi(user);
            header.clickAccountButton();
            authorizationPage.waitForAuthPageVisible();
            authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
            assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        }
        @Test
        @DisplayName("Логин через кнопку Войти на странице регистрации")
        void loginViaButtonOnRegisterPageTest(){
        user = UserFactory.validUser();
        userApi.createUserApi(user);
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage.clickLoginButtonOnRegisterPage();
        authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
        assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        }
        @Test
        @DisplayName("Логин через кнопку войти на странице сброса пароля")
        void loginViaButtonOnResetPasswordTest(){
            user = UserFactory.validUser();
            userApi.createUserApi(user);
            driver.get("https://stellarburgers.education-services.ru/forgot-password");
            resetPasswordPage.clickLoginButtonOnResetPage();
            authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
            assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        }

        @AfterEach
             void removeUser(){
                 if(user!=null){
                 userApi.removeUserApi(user);
             }
            }

}
