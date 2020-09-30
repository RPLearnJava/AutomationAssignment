package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {
    private WebDriver driver;

    private By txtUserName=By.name("username");
    private By txtLogin=By.className("login-btn");

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void Login(String userName) throws InterruptedException {
        driver.findElement(txtUserName).sendKeys(userName);
        driver.findElement(txtLogin).click();
        Thread.sleep(4000);
    }
}
