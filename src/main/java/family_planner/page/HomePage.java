package family_planner.page;
import family_planner.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(id="login-btn")
    private WebElement loginButton;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    
    public LoginPage clickLoginButton() {
        click(loginButton);
        return new LoginPage(driver, wait);
    }


}
