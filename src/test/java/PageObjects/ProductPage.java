package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

public class ProductPage {
    private WebDriver driver;
    private By chosenProduct = By.xpath("//div[contains(text(),'2480SM02')]");
    private By addToCartButton =By.xpath("//span[@class='ic-pdp-add-cart']");

    public ProductPage(WebDriver driver){
        this.driver=driver;
    }

    public void addProduct() throws InterruptedException {
        driver.findElement(chosenProduct).click();
        Thread.sleep(4000);
        //Product details are opened in new tab
        // Store all currently open tabs in tabs
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        // Switch to newly opened Tab
        driver.switchTo().window(tabs.get(1));
        driver.findElement(addToCartButton).click();
        Thread.sleep(6000);
    }
}
