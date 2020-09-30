package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Array;
import java.util.List;

public class CheckHomePage {
    @FindBy(xpath = "//a[text()='My Account']")
    private WebElement txt_WelcomeMsg;
    public CheckHomePage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
    public String isWelcomeMessagePresent()
    {
        String result;
        try {
            if (txt_WelcomeMsg.isDisplayed()==true)
                result="Login Passed";
            else{
                result= "Login Failed";
            }
            return result;
        }
        catch(Exception e)
        {
            return ("Login Process Error");
        }
    }
}
