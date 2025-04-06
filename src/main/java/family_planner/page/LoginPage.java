package family_planner.page;

import family_planner.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {



    @FindBy(id = "email-input")
    private WebElement emailField;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    @FindBy(id = "password-input")
    private WebElement passwordField;

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    @FindBy(id = "login-btn")
    private WebElement loginButton;

    public DashboardPage clickLoginButton() {
        click(loginButton);
        return new DashboardPage(driver, wait);
    }

    public DashboardPage loginAs(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();
    }

    @FindBy(xpath = "//span[contains(text(),'Please enter a valid email')]")
    private WebElement invalidEmailErrorMessage;

    public String getInvalidEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(invalidEmailErrorMessage));
        return invalidEmailErrorMessage.getText();
    }

    @FindBy(xpath = " //div[contains(text(),'Incorrect password. Please try again.')]")
    private WebElement invalidPasswordErrorMessage;

    public String getIncorrectPasswordTest() {
        wait.until(ExpectedConditions.visibilityOf(invalidPasswordErrorMessage));
        return invalidPasswordErrorMessage.getText();
    }

    @FindBy(xpath = "//span[contains(text(),'Password required')]")
    private WebElement emptyPasswordFieldErrorMessage;

    public String getEmptyPasswordFieldErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(emptyPasswordFieldErrorMessage));
        return emptyPasswordFieldErrorMessage.getText();
    }

    @FindBy(xpath = "//span[contains(text(),'Email is required')]")
    private WebElement emptyEmailFieldErrorMessage;

    public String getEmptyEmailFieldErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(emptyEmailFieldErrorMessage));
        return emptyEmailFieldErrorMessage.getText();
    }

    @FindBy(xpath = "//div[contains(text(),'Incorrect password. Please try again.')]")
    private WebElement invalidUserErrorMessage;

    public String getInvalidUserErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(invalidUserErrorMessage));
        return invalidUserErrorMessage.getText();
    }

    public String getLoginErrorMessage() {
        String errorMessage = getInvalidEmailErrorMessage();
        if (errorMessage.isEmpty()) {
            errorMessage = getIncorrectPasswordTest();
        }
        if (errorMessage.isEmpty()) {
            errorMessage = getEmptyPasswordFieldErrorMessage();
        }
        if (errorMessage.isEmpty()) {
            errorMessage = getEmptyEmailFieldErrorMessage();
        }
        return errorMessage;
    }

    public String getIncorrectPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(invalidPasswordErrorMessage));
        return invalidPasswordErrorMessage.getText();
    }
}

