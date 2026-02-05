package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private By accountButton = By.xpath("//p[text()=\"Личный Кабинет\"]/..");

    @Step("Клик на Личный Кабинет в хэдере")
    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }

    private By constructorButton = By.xpath("//p[text()='Конструктор']/..");
    private By logo = By.className("AppHeader_header__logo__2D0X2");

    @Step("Клик на лого в хэдере")
    public void clickLogo(){
        driver.findElement(logo).click();
    }
    @Step("Клик на кнопку Конструктор в хэдере")
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
}
