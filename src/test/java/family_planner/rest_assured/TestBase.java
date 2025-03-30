package family_planner.rest_assured;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public final String loginDto = "/auth/sign-in";


    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://clanhub-sd6ys.ondigitalocean.app/";
        RestAssured.basePath = "api";
        //RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
