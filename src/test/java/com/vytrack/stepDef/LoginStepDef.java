package com.vytrack.stepDef;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginStepDef {
    private static Logger logger = Logger.getLogger(LoginStepDef.class);
    LoginPage loginPage = new LoginPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        logger.info("I am on te login page");
        Driver.get().get(ConfigurationReader.getProperty("url"));
    }


    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        logger.info("Login as a store manager");
        String userName = ConfigurationReader.getProperty("userName");
        String password = ConfigurationReader.getProperty("password");
        loginPage.login(userName, password);
    }
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        loginPage.WaitUntilLoadMaskDisappear();
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.visibilityOf(loginPage.subTitle));
        String actual=loginPage.subTitle.getText();
        Assert.assertEquals(string,actual);

    }


}
