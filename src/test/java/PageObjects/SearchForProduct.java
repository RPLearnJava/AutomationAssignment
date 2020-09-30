package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchForProduct {
    private WebDriver driver;
    private By searchBox=By.name("searchVal");
    private By searchButton = new By.ByXPath("//span[@class='ic-search']");

    public SearchForProduct(WebDriver driver){
        this.driver=driver;
    }

    public void SearchForProduct(String searchWord) throws InterruptedException {
        driver.findElement((By) searchBox).sendKeys(searchWord);
        Thread.sleep(4000);
        driver.findElement((By) searchButton).click();
        Thread.sleep(4000);
    }
}