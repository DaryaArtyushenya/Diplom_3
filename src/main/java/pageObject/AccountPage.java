package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.lang.model.element.Element;
import java.util.List;

public class AccountPage {
    private WebDriver driver;
    private By accountList = By.cssSelector(".Account_list__3KQQf.mb-20");
    private By profileTab = By.xpath("//a[@href=\"/account/profile\"]");
    private By orderHistoryTab = By.xpath("//a[@href=\"/account/order-history\"]");
    private By logoutButton = By.xpath("//button[text() =\"Выход\"]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getProfileTab() {
        return profileTab;
    }

    public By getOrderHistoryTab() {
        return orderHistoryTab;
    }

    public By getLogoutButton() {
        return logoutButton;
    }

    private By nameAccountPage = By.xpath("//label[text() = \"Имя\"]/following-sibling::input");
    private By emailAccountPage = By.xpath("//label[text() = \"Логин\"]/following-sibling::input");
    private By passwordAccountPage = By.xpath("//label[text() = \"Пароль\"]/following-sibling::input");

    public By getNameAccountPage() {
        return nameAccountPage;
    }

    public By getEmailAccountPage() {
        return emailAccountPage;
    }


    public void clickLogout(){
        driver.findElement(logoutButton).click();
    }
}
