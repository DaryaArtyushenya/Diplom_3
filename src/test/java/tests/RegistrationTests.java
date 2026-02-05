package tests;

import api.UserApi;
import factory.UserFactory;
import model.User;
import org.junit.jupiter.api.DisplayName;
import pageObject.AuthorizationPage;
import pageObject.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTests {
    private WebDriver driver;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    UserApi userApi = new UserApi();
    String browser = System.getProperty("browser", "chrome");
    @BeforeEach
    void setUp(){
        if (browser.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();

        } else if (browser.equalsIgnoreCase("yandex")) {
            driver = new FirefoxDriver();
           // driver = new YandexDriver;
        }
        driver.get("https://stellarburgers.education-services.ru/register");
        registrationPage = new RegistrationPage(driver);
        authorizationPage = new AuthorizationPage(driver);
    }


    @Test
    @DisplayName("Успешная регистрация")
    void successRegisterTest(){
        User user = UserFactory.validUser();
        registrationPage.registration(user);
        authorizationPage.waitForAuthPageVisible();
        assertTrue(driver.findElement(authorizationPage.getEmailAuthField()).isDisplayed());
        assertTrue(driver.findElement(authorizationPage.getPasswordAuthField()).isDisplayed());
        userApi.removeUserApi(user);
    }
    @Test
    @DisplayName("Попытка создать пользователя с паролем меньше 6 символов")
    void passwordLessThan6CharactersTest(){
        User user = UserFactory.incorrectPassword();
        registrationPage.registration(user);
        assertTrue(driver.findElement(registrationPage.getPasswordErrorMessage()).isDisplayed());
    }

    @AfterEach
    void quit(){
        driver.quit();
    }
}
