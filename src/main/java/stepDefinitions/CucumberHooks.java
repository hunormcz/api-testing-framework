package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.Logger;
import utils.TestContext;

public class CucumberHooks {

    @Before(order = 1)
    public void BeforeTests(){
        TestContext.INSTANCE.purge();
        Logger.log("======Global before hook=======");

        TestContext.INSTANCE.initBaseUrl();
        Logger.log("=====We are running our tests on: " + TestContext.INSTANCE.getBaseUrl());
    }

    @After
    public void AfterTests(){
        Logger.log("Global before hook");

    }
}
