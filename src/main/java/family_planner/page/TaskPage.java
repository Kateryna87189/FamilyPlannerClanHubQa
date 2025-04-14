package family_planner.page;

import family_planner.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

public class TaskPage extends BasePage {

    public TaskPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//input[contains(@placeholder,'Enter task name')]")
    private WebElement taskName;

    @FindBy(xpath = "//input[@type='date']")
    private WebElement taskDeadline;

    @FindBy(xpath = "//textarea[contains(@placeholder,'Enter task description')]")
    private WebElement taskDescription;

    @FindBy(xpath = "//img[@alt='save']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(.,'Task is acsesseful')]")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(),'Please enter task name')]")
    private WebElement nameRequiredError;

    @FindBy(xpath = "//p[normalize-space(text())='Description cannot exceed 100 characters']")
    private WebElement descriptionTooLongError;

    @FindBy(xpath = "//p[contains(text(),'Deadline cannot be in the past')]")
    private WebElement pastDateError;

    @FindBy(xpath = "//p[contains(text(),'Please select deadline')]")
    private WebElement invalidDateError;

    @FindBy(xpath = "//p[contains(text(),'Task name cannot exceed 30 characters')]")
    private WebElement nameTooLongError;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    private WebElement popUpButton;

    public TaskPage createTask(String name, String deadline, String description) {
        type(taskName, name);
        // type(taskDeadline, deadline);
        Actions actions = new Actions(driver);
        actions.moveToElement(taskDeadline);
        actions.click();
        actions.sendKeys(deadline).build().perform();

        type(taskDescription, description);
        click(submitButton);
       // click(popUpButton);
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementPresent(successMessage);
    }

    public boolean isNameRequiredErrorDisplayed() {
        return isElementPresent(nameRequiredError);
    }

    public boolean isDescriptionTooLongErrorDisplayed() {
        return isElementPresent(descriptionTooLongError);
    }

    public boolean isPastDateErrorDisplayed() {
        //click(popUpButton);
        return isElementPresent(pastDateError);
    }

    public boolean isInvalidDateErrorDisplayed() {
        return isElementPresent(invalidDateError);
    }

    public boolean isNameTooLongErrorDisplayed() {
        return isElementPresent(nameTooLongError);
    }

    private boolean isElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



}