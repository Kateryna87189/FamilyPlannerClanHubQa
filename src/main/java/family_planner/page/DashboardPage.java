package family_planner.page;

import family_planner.core.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(className = "_userInfo_sl5gc_19")
    private WebElement userProfile;

    public boolean isUserDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(userProfile));
            return userProfile.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @FindBy(xpath = "//button[contains(text(),'Add new task')]")
    private WebElement addNewTaskButton;

    public TaskPage clickAddNewTaskButton() {
        click(addNewTaskButton);
        return new TaskPage(driver, wait);
    }

}
