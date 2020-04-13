package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/activities",
        glue="com/vytrack/stepDef",
        dryRun = false,
        tags = "@calendar_events",
        plugin = {"html:target/default-cucumber-reports",
                "json:target/cucumber.json",
        }

)
public class CucumberRunner {

}
