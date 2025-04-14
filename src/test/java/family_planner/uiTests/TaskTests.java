package family_planner.uiTests;

import family_planner.coreUiTests.TestUIBase;
import family_planner.page.DashboardPage;
import family_planner.page.HomePage;
import family_planner.page.LoginPage;
import family_planner.page.TaskPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TaskTests extends TestUIBase {

    private void loginToApp() {
        HomePage homePage = new HomePage(app.driver, app.wait);
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.loginAs("katranchik21@gmail.com", "Password@1");
    }

    @Test
    public void testCreateValidTask() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();
        assertTrue(app.driver.getCurrentUrl().contains("/addtask"), "Should be on task creation page");
        taskPage.createTask("Test task", "10-04-2025", "Should be on task creation page");
    }


    @Test
    public void testCreateTaskWithoutName() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();
        taskPage.createTask("", "20-04-2025", "Task without name");
        assertTrue(taskPage.isNameRequiredErrorDisplayed(), "Name required error should be displayed");
    }

    @Test
    public void testTooLongDescription() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();

        String longDescription = "Lorem ipsum dolor sit amet".repeat(100);
        taskPage.createTask("test task!", "13-04-2025", longDescription);

        Assert.assertTrue(taskPage.isDescriptionTooLongErrorDisplayed(), "Description too long error should be displayed");
    }

    @Test
    public void testPastDeadline() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();

        taskPage.createTask("test task!", "24-10-2024", "Description");
        assertTrue(taskPage.isPastDateErrorDisplayed(), "Deadline cannot be in the past");
    }

    @Test
    public void testUnavailableDate() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();

        taskPage.createTask("Тестове завдання", "31-02-2025", "Опис");
        assertTrue(taskPage.isInvalidDateErrorDisplayed(), "Invalid date error should be displayed");
    }

    @Test
    public void testTooLongTaskName() {
        loginToApp();
        DashboardPage dashboardPage = new DashboardPage(app.driver, app.wait);
        TaskPage taskPage = dashboardPage.clickAddNewTaskButton();

        String longTaskName = "Task".repeat(50);
        taskPage.createTask(longTaskName, "10-04-2025", "");

        assertTrue(taskPage.isNameTooLongErrorDisplayed(), "Task name cannot exceed 30 characters");
    }
}