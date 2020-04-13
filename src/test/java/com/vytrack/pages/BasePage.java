package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {


    @FindBy(className = "oro-subtitle")
    public WebElement subTitle;

    @FindBy(className = "loader-mask shown")
    public WebElement loadMask;

    public BasePage(){
            PageFactory.initElements(Driver.get(),this);
    }

    public void navigateTo(String moduleName, String subModuleName) {
        Actions actions = new Actions(Driver.get());
        String moduleLocator = "//*[normalize-space()='" + moduleName + "' and @class='title title-level-1']";
        String subModuleLocator = "//*[normalize-space()='" + subModuleName + "' and @class='title title-level-2']";

        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));

        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));
        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));

        waitUntilLoaderMaskDisappear();

        BrowserUtils.clickWithWait(module); //if click is not working well
        WebElement subModule = Driver.get().findElement(By.xpath(subModuleLocator));
        if (!subModule.isDisplayed()) {
            actions.doubleClick(module).doubleClick().build().perform();
            try {
                wait.until(ExpectedConditions.visibilityOf(subModule));
            } catch (Exception ex) {
                ex.printStackTrace();
                BrowserUtils.clickWithJS(module);
            }
        }
        BrowserUtils.clickWithWait(subModule); //if click is not working well
        //it waits until page is loaded and ajax calls are done
        BrowserUtils.waitForPageToLoad(10);
    }
    public String getSubtitle(){
        return subTitle.getText();
    }
    public boolean WaitUntilLoadMaskDisappear(){
    WebDriverWait wait=new WebDriverWait(Driver.get(),10);
    try {
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        return true;
    }catch (NoSuchElementException e){
        e.printStackTrace();
        return true;
    }catch (WebDriverException e){
        e.printStackTrace();
    }
    return false;
    }
    public boolean waitUntilLoaderMaskDisappear() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
            return true;
        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }

}
