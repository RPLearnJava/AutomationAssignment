package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


import static org.openqa.selenium.By.*;

public class PasswordPage {
    private WebDriver driver;
    private By txtPassword= new ByXPath("//input[@type='password']");
    private By txtSubmit = new ByXPath("//input[@type='submit']");

    public PasswordPage(WebDriver driver){
        this.driver=driver;
    }

    public void PasswordPage(String password) throws InterruptedException {
        driver.findElement((By) txtPassword).sendKeys(password);
        Thread.sleep(20000);
        driver.findElement((By) txtSubmit).click();
        Thread.sleep(3000);
    }
}