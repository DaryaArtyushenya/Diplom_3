package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private By accountButton = By.xpath("//p[text()=\"Личный Кабинет\"]/..");

    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }

    private By constructorButton = By.xpath("//p[text()='Конструктор']/..");
    private By logo = By.className("AppHeader_header__logo__2D0X2");

    public void clickLogo(){
        driver.findElement(logo).click();
    }
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
}
