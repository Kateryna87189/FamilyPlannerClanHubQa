package family_planner.core;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Ініціалізуємо wait тут
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    public void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }
    private void scrollToElement(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitForPageScrollToFinish();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }
    public void typeWithJs(WebElement element, String text, int x, int y) {
        if (text != null) {
            js.executeScript("window.scrollBy("+x+","+y+")");
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }
    public void clickWithJs(WebElement element, int x, int y){
        js.executeScript("window.scrollBy("+x+","+y+")");
        click(element);
    }
    public void scrollTo(int y){
        js.executeScript("window.scrollBy(0,"+y+")");
    }
    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public String takeScreenshot() {

        // Capture screenshot
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(
                    isTextPresent, "Expected text: + ["+text+"], actual in element: " +
                            "["+element.getText()+"] in ["+element.getTagName()+"]");
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "Text not found in element: [" + element.getTagName() + "], expected text: [" + text + "], " +
                            "was text: [" + element.getText() + "]");
        }
    }
    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected void waitForPageScrollToFinish() {

        //* wait.until() всегда выполняется минимум два раза:
        //= Первый вызов при старте ожидания.
        //= Второй вызов для проверки условия выхода из do-while
        wait.until(driver -> { //это лямбда-выражение, которое принимает driver как аргумент и выполняет код внутри {}.
            double beforeScroll, afterScroll;
            // int count = 0; // Инициализируем счётчик
            do {
                beforeScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
                // System.out.println(beforeScroll);
                pause(100); // Ждём короткий промежуток времени
                afterScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
                // System.out.println(afterScroll);
                //System.out.println((++count) + "-я попытка подождать окончание скролла страницы.");
            } while (beforeScroll != afterScroll); // Если скролл ещё идёт, повторяем

            return true;
        });
    }
    // Допоміжний метод для перевірки наявності елемента
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
