package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AccountPage {
    private WebDriver driver;
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

    @Step("Клик на Логаут")
    public void clickLogout(){
        driver.findElement(logoutButton).click();
    }
    public void waitForLogoutButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }
    public void waitForProfileButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTab));
    }

    }
