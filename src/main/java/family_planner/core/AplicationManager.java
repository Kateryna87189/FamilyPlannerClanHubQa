package family_planner.core;

import family_planner.page.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage basePage;

//    public BasePage getBasePage() {
//        return basePage;
//    }
    public void init () {
        System.err.close();
        //driver = new ChromeDriver();

        String browser = System.getProperty("browser", "edge");
        // Проверяет значение переменной browser и в зависимости от результата инициализирует нужный драйвер
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: // Это резервный сценарий на случай, если значение browser не совпадает ни с одним из указанных случаев
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }

        driver.get("https://clanhub-sd6ys.ondigitalocean.app/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        pause (50);
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); //ojudanie zagryzki stranicu

        wait = new WebDriverWait(driver, Duration.ofSeconds(900));
        basePage = new BasePage(driver, wait);
    }
    public void stop () {
        driver.quit();
    }

    public HomePage getHomePage() {
        return new HomePage(driver, wait);
    }

    }
