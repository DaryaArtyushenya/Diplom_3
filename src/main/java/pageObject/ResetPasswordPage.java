package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private By loginOnResetPasswordPage = By.className("Auth_link__1fOlj");
        @Step("Клик на Войти на странице восстановления пароля")
    public void clickLoginButtonOnResetPage(){
        driver.findElement(loginOnResetPasswordPage).click();
    }
}
