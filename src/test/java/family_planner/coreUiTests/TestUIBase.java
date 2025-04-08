package family_planner.coreUiTests;

import family_planner.core.AplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestUIBase {
    protected final AplicationManager app = new AplicationManager();
    Logger logger = LoggerFactory.getLogger(TestUIBase.class);

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Test is started: ["+method.getName()+"]");
        app.init();
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {

        if (result.isSuccess()){
            logger.info("Test is PASSED: [" + method.getName() +"]");
        }else {
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + app.getBasePage().takeScreenshot() + "]");
        }
        app.stop();
    }
}
