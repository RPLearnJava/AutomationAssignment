package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewCart {
    private WebDriver driver;
    private By goToCart =By.xpath("//div[@class='btn-cart']");

    public ViewCart(WebDriver driver){
        this.driver=driver;
    }

    public void ViewCart() throws InterruptedException {

        driver.findElement((By) goToCart).click();
        Thread.sleep(8000);
    }

}
