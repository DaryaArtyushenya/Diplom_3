package tests;

import api.UserApi;
import factory.UserFactory;
import io.restassured.RestAssured;
import model.User;
import org.junit.jupiter.api.*;
import pageObject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransitionTests extends BaseTest{
    @BeforeAll
    static void uriSetUp(){
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";
    }
    private User user;
    RegistrationPage registrationPage;
    AuthorizationPage authorizationPage;
    Header header;
    UserApi userApi = new UserApi();
    AccountPage accountPage;
    HomePage homePage;
    @BeforeEach
    void setUp() {
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
        user = UserFactory.validUser();
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
        user = UserFactory.validUser();
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
    }
        @Test
        @DisplayName("Редирект на конструктор по нажатию на лого")
        void redirectToConstructorByLogoTest(){
            user = UserFactory.validUser();
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
        user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        header.clickConstructorButton();
        homePage.waitCreateBurgerHeader();
        assertTrue(driver.findElement(homePage.getCreateBurgerHeader()).isDisplayed());
    }
    @Test
    @DisplayName("Редирект на табу Булки")
    void redirectBunTabTest(){
        user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitBunTab();
        assertTrue((driver.findElement(homePage.getBunTab())).getAttribute("class").contains(
                         "tab_tab_type_current"));
        assertTrue(driver.findElement(homePage.getBunOne()).isDisplayed());
    }
    @Test
    @DisplayName("Редирект на табу Соусы")
    void redirectSauceTabTest(){
        user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitSauceTab();
        homePage.clickSauceTab();
        homePage.waitSauceTab();
        assertTrue((driver.findElement(homePage.getSauceTab())).getAttribute("class").contains(
                "tab_tab_type_current"));
        assertTrue(driver.findElement(homePage.getSauceOne()).isDisplayed());
    }
    @Test
    @DisplayName("Редирект на табу Начинки")
    void redirectFillingsTabTest(){
        user = UserFactory.validUser();
        userApi.createUserApi(user);
        header.clickAccountButton();
        authorizationPage.waitForAuthPageVisible();
        authorizationPage.authorization(user);
        homePage.waitFillingsTab();
        homePage.clickFillingsTab();
        homePage.waitFillingsTab();
        System.out.println(driver.findElement(homePage.getFillingsTab()).getAttribute("class"));
        assertTrue((driver.findElement(homePage.getFillingsTab())).getAttribute("class").contains(
                "tab_tab_type_current"));
        assertTrue(driver.findElement(homePage.getFillingOne()).isDisplayed());
    }

    @AfterEach
    void removeUser(){
        if(user!=null){
            userApi.removeUserApi(user);
        }
    }
}
