package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestContext;

public class CucumberHooks {
    Logger log = LogManager.getLogger(CucumberHooks.class);

    @Before(order = 1)
    public void BeforeTests(){
        TestContext.INSTANCE.purge();
        log.info("======Global before hook=======");

        TestContext.INSTANCE.initBaseUrl();
        log.info("=====We are running our tests on: " + TestContext.INSTANCE.getBaseUrl());
    }

    @After
    public void AfterTests(){
        log.info("Global after hook");

    }
}
