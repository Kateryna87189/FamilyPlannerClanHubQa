package family_planner.uiTests;

import family_planner.coreUiTests.TestUIBase;
import family_planner.page.DashboardPage;
import family_planner.page.HomePage;
import family_planner.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestUIBase {

    @Test
    public void successfulLoginTest() {
        String email = "katranchik21@gmail.com";
        String password = "Password@1";

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        DashboardPage dashboardPage = loginPage.loginAs(email, password);
    }

    @Test
    public void invalidEmailTest() {

        String email = "katranchik21@.com";
        String password = "Password@1";

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();

        // Assert
        String errorMessage = loginPage.getInvalidEmailErrorMessage();
        Assert.assertTrue(errorMessage.contains("Please enter a valid email"),
                errorMessage);
    }


    @Test
    public void incorrectPasswordTest() {
        String email = "katranchik21@gmail.com";
        String password = "Password1@23"; //

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();

        String errorMessage = loginPage.getIncorrectPasswordTest();
        Assert.assertTrue(errorMessage.contains("Incorrect password. Please try again."),
                errorMessage);
    }

    @Test
    public void emptyPasswordFieldTest() {
        String email = "katranchik21@gmail.com";

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.enterEmail(email)
                .clickLoginButton();

        String errorMessage = loginPage.getEmptyPasswordFieldErrorMessage();
        Assert.assertTrue(errorMessage.contains("Password required"),
                errorMessage);
    }

    @Test
    public void emptyEmailFieldTest() {
        String password = "Password@1";

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.enterPassword(password)
                .clickLoginButton();

        String errorMessage = loginPage.getEmptyEmailFieldErrorMessage();
        Assert.assertTrue(errorMessage.contains("Email is required"),
                errorMessage);
    }

    @Test
    public void unregisteredUserTest() {
        String email = "usw999+2j804gyg7o78k@spam4.me"; // Незареєстрований користувач
        String password = "SomePassword123@";

        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();

        String errorMessage = loginPage.getInvalidUserErrorMessage();
        Assert.assertTrue(errorMessage.contains("Incorrect password. Please try again."),
                errorMessage);
    }


}
//    @Test
//    public void emptyFieldsTest() {
//        HomePage homePage = new HomePage(driver, wait);
//        LoginPage loginPage = homePage.clickLoginButton();
//        loginPage.clickLoginButton();
//
//        String errorMessage = loginPage.getErrorMessage();
//        Assert.assertTrue(errorMessage.contains("required"),
//                "Should show validation message for empty fields");
//    }