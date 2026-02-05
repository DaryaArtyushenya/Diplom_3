package pageObject;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {
    private WebDriver driver;
    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }
    private By emailAuthField = By.xpath("//label[text()=\"Email\"]/following-sibling::input");

    public By getEmailAuthField() {
        return emailAuthField;
    }

    private By passwordAuthField = By.xpath("//label[text()=\"Пароль\"]/following-sibling::input");

    public By getPasswordAuthField() {
        return passwordAuthField;
    }

    private By authBlock = By.className("Auth_login__3hAey");
    private By loginButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");

    public void waitForAuthPageVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(authBlock));
    }
    @Step("Шаг авторизация пользователя")
    public void authorization(User user){
        driver.findElement(emailAuthField).sendKeys(user.getEmail());
        driver.findElement(passwordAuthField).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();

    }
}
