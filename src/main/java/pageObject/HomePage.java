package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButtonHomePage = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    private By createBurgerHeader = By.xpath("//h1[text()='Соберите бургер']");
    private By bunTab = By.xpath("//span[text() = 'Булки']/..");
    private By sauceTab = By.xpath("//span[text() = 'Соусы']/..");
    private By fillingsTab = By.xpath("//span[text() = 'Начинки']/..");
    private By bunTitle = By.xpath("//h2[text()='Булки']");
    private By bunOne = By.xpath("//img[@alt='Флюоресцентная булка R2-D3']");
    private By sauceTitle = By.xpath("//h2[text()='Соусы']");
    private By sauceOne = By.xpath("//img[@alt='Соус Spicy-X']");
    private By fillingsTitle = By.xpath("//h2[text()='Начинки']");
    private By fillingOne = By.xpath("//img[@alt='Соус Spicy-X']");


    public By getBunTitle() {
        return bunTitle;
    }

    public By getBunOne() {
        return bunOne;
    }

    public By getSauceTitle() {
        return sauceTitle;
    }

    public By getSauceOne() {
        return sauceOne;
    }

    public By getFillingsTitle() {
        return fillingsTitle;
    }

    public By getFillingOne() {
        return fillingOne;
    }

    public By getCreateBurgerHeader() {
        return createBurgerHeader;
    }
    @Step("Клик на кнопку логина на Домашней странице")
    public void clickLoginButtonHomePage(){
        driver.findElement(loginButtonHomePage).click();
    }
    @Step("Клик на табу Булки")
    public void clickBunTab(){
        driver.findElement(bunTab).click();
    }
    @Step("Клик на табу Соусы")
    public void clickSauceTab(){
        driver.findElement(sauceTab).click();
    }
    @Step("Клик на табу Начинки")
    public void clickFillingsTab(){
        driver.findElement(fillingsTab).click();
    }

    public void waitCreateBurgerHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerHeader));
    }
    public void waitSauceTab(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
    }
    public void waitBunTab(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunTab));
    }
    public void waitFillingsTab(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
    }

}

