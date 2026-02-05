package tests;

import api.UserApi;
import factory.UserFactory;
import io.restassured.RestAssured;
import model.User;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransitionTests {
    @BeforeAll
    static void uriSetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
    private WebDriver driver;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    Header header;
    UserApi userApi = new UserApi();
    AccountPage accountPage;
    HomePage homePage;
    String browser = System.getProperty("browser", "chrome");
    @BeforeEach
    void setUp() {
        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("yandex")) {

            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/yandexdriver");

            ChromeOptions options = new ChromeOptions();

            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru/");

        registrationPage = new RegistrationPage(driver);
        authorizationPage = new AuthorizationPage(driver);
        header = new Header(driver);
        accountPage = new AccountPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Редирект на страницу профиля")
    void redirectToAccountPageTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        header.clickAccountButton();
        accountPage.waitForProfileButton();
        assertTrue(driver.findElement(accountPage.getProfileTab()).isDisplayed());
        assertTrue(driver.findElement(accountPage.getOrderHistoryTab()).isDisplayed());
        assertTrue(driver.findElement(accountPage.getLogoutButton()).isDisplayed());
        String nameValue = driver.findElement(accountPage.getNameAccountPage()).getAttribute("value");
        String emailValue = driver.findElement(accountPage.getEmailAccountPage()).getAttribute("Value");
        assertEquals(user.getName() , nameValue);
        assertEquals(user.getEmail(), emailValue);
    }

    @Test
    @DisplayName("Логаут")
    void logoutTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        header.clickAccountButton();
        accountPage.waitForLogoutButton();
        accountPage.clickLogout();
        authorizationPage.waitForAuthPageVisible();
        assertTrue(driver.findElement(authorizationPage.getEmailAuthField()).isDisplayed());
        assertTrue(driver.findElement(authorizationPage.getPasswordAuthField()).isDisplayed());
        userApi.removeUserApi(user);
    }
        @Test
        @DisplayName("Редирект на конструктор по нажатию на лого")
        void redirectToConstructorByLogoTest(){
            User user = UserFactory.validUser();
            userApi.createUserApi(user);
            header.clickAccountButton();
            authorizationPage.waitForAuthPageVisible();
            authorizationPage.authorization(user);
            header.clickLogo();
            homePage.waitCreateBurgerHeader();
            assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        }
    @Test
    @DisplayName("Редирект на конструктор по нажатию на кнопку Конструктор в хэдере")
    void redirectToConstructorByConstructorButtonTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        header.clickConstructorButton();
        homePage.waitCreateBurgerHeader();
        assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
        userApi.removeUserApi(user);
    }
    @Test
    @DisplayName("Редирект на табу Булки")
    void redirectBunTabTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitSauceTab();
        homePage.clickSauceTab();
        homePage.waitBunTab();
        homePage.clickBunTab();
        assertEquals("Булки", driver.findElement(homePage.getBunTitle()).getText());
        assertTrue(driver.findElement(homePage.getBunOne()).isDisplayed());
        userApi.removeUserApi(user);
    }
    @Test
    @DisplayName("Редирект на табу Соусы")
    void redirectSauceTabTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitSauceTab();
        homePage.clickSauceTab();
        assertEquals("Соусы", driver.findElement(homePage.getSauceTitle()).getText());
        assertTrue(driver.findElement(homePage.getSauceOne()).isDisplayed());
        userApi.removeUserApi(user);
    }
    @Test
    @DisplayName("Редирект на табу Начинки")
    void redirectFillingsTabTest(){
        User user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitFillingsTab();
        homePage.clickFillingsTab();
        assertEquals("Начинки", driver.findElement(homePage.getFillingsTitle()).getText());
        assertTrue(driver.findElement(homePage.getFillingOne()).isDisplayed());
        userApi.removeUserApi(user);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }
}
