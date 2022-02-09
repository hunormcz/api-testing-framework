package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    public final static TestContext INSTANCE = new TestContext();
    private Map<String, Object> testContext;
    private String baseUrl;
    //Logger log = LoggerFactory.getLogger(EmployeeServiceHelper.class);
    private static Logger log = LogManager.getLogger();

    private TestContext() {
        testContext = new HashMap<>();
    }

    public void add(String objectKey, Object objectValue) {
        if (this.testContext.containsKey(objectKey)) {
            log.info("Replacing key: %s value on testContext.", objectKey);
            this.testContext.remove(objectKey);
        }
        log.info("Adding key: %s to TestContext", objectKey);
        this.testContext.put(objectKey, objectValue);
    }

    public Object get(String objectKey) {
        return this.testContext.get(objectKey);
    }

    public void purge() {
        log.info("Purge TestContext.");
        this.testContext.clear();
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public void initBaseUrl(){
        this.baseUrl = ConfigManager.INSTANCE.getBaseUrl();
    }
}
