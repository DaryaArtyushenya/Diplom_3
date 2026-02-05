package tests;

import api.UserApi;
import factory.UserFactory;
import io.restassured.RestAssured;
import model.User;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTests {
    @BeforeAll
    static void uriSetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
    private WebDriver driver;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    HomePage homePage;
    Header header;
    ResetPasswordPage resetPasswordPage;
    String browser = System.getProperty("browser", "chrome");
    UserApi userApi = new UserApi();
    @BeforeEach
    void setUp(){
        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/yandexdriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            driver = new ChromeDriver(options);
        }
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
           User user = UserFactory.validUser();
           userApi.createUserApi(user);
           homePage.clickLoginButtonHomePage();
           authorizationPage.waitForAuthPageVisible();
           authorizationPage.authorization(user);
           homePage.waitCreateBurgerHeader();
           assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
           userApi.removeUserApi(user);
        }
        @Test
        @DisplayName("Логин через кнопку Личный кабинет в хэдэре")
        void loginViaAccountButtonTest(){
            driver.get("https://stellarburgers.education-services.ru");
            User user = UserFactory.validUser();
            userApi.createUserApi(user);
            header.clickAccountButton();
            authorizationPage.waitForAuthPageVisible();
            authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
            assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
            userApi.removeUserApi(user);
        }
        @Test
        @DisplayName("Логин через кнопку Войти на странице регистрации")
        void loginViaButtonOnRegisterPageTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage.clickLoginButtonOnRegisterPage();
        authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
        assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        userApi.removeUserApi(user);
        }
        @Test
        @DisplayName("Логин через кнопку войти на странице сброса пароля")
        void loginViaButtonOnResetPasswordTest(){
            User user = UserFactory.validUser();
            userApi.createUserApi(user);
            driver.get("https://stellarburgers.education-services.ru/forgot-password");
            resetPasswordPage.clickLoginButtonOnResetPage();
            authorizationPage.authorization(user);
            homePage.waitCreateBurgerHeader();
            assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
            userApi.removeUserApi(user);
        }


    @AfterEach
    void quit(){
        driver.quit();
    }
}
