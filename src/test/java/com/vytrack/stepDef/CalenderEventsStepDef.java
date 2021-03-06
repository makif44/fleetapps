package com.vytrack.stepDef;

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class CalenderEventsStepDef {
    LoginPage loginPage=new LoginPage();
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

    @Then("user navigates to {string} then to {string}")
    public void user_navigates_to_then_to(String module, String submodule) {
        loginPage.navigateTo(module, submodule);
    }
    @Then("user verifies that column names are displayed")
    public void user_verifies_that_column_names_are_displayed(List<String> dataTable) {
        System.out.println(dataTable);
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.wait(3);
        System.out.println(calendarEventsPage.getColumnNames());
        Assert.assertEquals(dataTable, calendarEventsPage.getColumnNames());
    }

}
