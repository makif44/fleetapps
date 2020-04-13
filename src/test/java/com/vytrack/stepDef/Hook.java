package com.vytrack.stepDef;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Hook {
    private static Logger logger= Logger.getLogger(Hook.class);
@Before
    public void setup(){
    logger.info("#######################");
    logger.info("Test setup");
//    Dimension d = new Dimension(620,500);
//    Driver.get().manage().window().setSize(d);

    //    ChromeOptions options=new ChromeOptions();
//    options.addArguments("startmaximized");
    Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

}
@After
    public void tearDown(Scenario scenario){
    if (scenario.isFailed()){
        logger.error("Test failed");
        byte[] screenshot=((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot,"image/png");
    }else{
    logger.info("Cleanup!");
    logger.info("Test completed!");
    }
    logger.info("#######################");
    Driver.close();
}

}
