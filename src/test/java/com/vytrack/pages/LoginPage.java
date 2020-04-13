package com.vytrack.pages;

import com.vytrack.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id="prependedInput")
    public WebElement userInputBox;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInputBox;

    @FindBy(id = "_submit")
    public WebElement submitButton;

    public void login(String userName,String password){
        userInputBox.sendKeys(userName);
        passwordInputBox.sendKeys(password, Keys.ENTER);
        //submitButton.click();
    }
}
