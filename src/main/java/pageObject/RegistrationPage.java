package pageObject;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.bouncycastle.cms.RecipientId.password;

public class RegistrationPage {
    private WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By registerButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private By nameRegisterField = By.xpath("//label[text()=\"Имя\"]/following-sibling::input");
    private By emailRegisterField = By.xpath("//label[text()=\"Email\"]/following-sibling::input");
    private By passwordRegisterField = By.xpath("//label[text()=\"Пароль\"]/following-sibling::input");
    private By passwordErrorMessage = By.cssSelector(".input__error.text_type_main-default");
    private By loginButtonOnRegisterPage = By.className("Auth_link__1fOlj");

    public By getPasswordErrorMessage() {
        return passwordErrorMessage;
    }
    @Step("Ввод данных для регистрации")
    public void regData(User user){
        driver.findElement(nameRegisterField).sendKeys(user.getName());
        driver.findElement(emailRegisterField).sendKeys(user.getEmail());
        driver.findElement(passwordRegisterField).sendKeys(user.getPassword());

    }
    @Step("Клик по кнопке регистрации")
    public void clickRegButton(){
        driver.findElement(registerButton).click();
    }


    @Step("Клик на кнопку Войти на странице регистрации")
    public void clickLoginButtonOnRegisterPage(){
        driver.findElement(loginButtonOnRegisterPage).click();
    }

}
