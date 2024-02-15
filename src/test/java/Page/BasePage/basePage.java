package Page.BasePage;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
public class basePage {
    private final Wait<WebDriver> wait;
    WebDriver driver;
    private static final int WAIT_TIMEOUT=10;
    private static final int POLLING = 10;
    JavascriptExecutor js;

    protected basePage(WebDriver driver){
        this.driver=driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,WAIT_TIMEOUT), this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected boolean isVisible(WebElement webElement) throws NoSuchElementException{
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e){
            System.out.println("Exception:" +webElement);
            return false;
        }
    }

    protected void waitUntilElementIsVisible(WebElement element){
        try{
            await().atMost(WAIT_TIMEOUT, SECONDS).until(()->isVisible(element));
        }catch (ConditionTimeoutException e){
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento despues de 30 segundos\nElemento: %s", element));
        }
    }

    protected void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException ignored){
        }
    }

    protected boolean isVisible(By element) throws Exception {
        try {
            return getDriver().findElement(element).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("El elemento no esta visible en la pagina web");
            //throw new NoSuchElementException("");
            return false;
        } catch (Exception e) {
            System.out.println("El elemento no esta visible: " +element);
            return false;
        }
    }

    protected void moveToElement (WebElement element){
       try {
           new Actions(driver)
                   .moveToElement(element)
                   .perform();
       }catch (NoSuchElementException e){
           System.out.println("No se encuentra el elemento");
       }
    }

    public void scrollDownToElement(WebElement element) {
        try{
            if(js!=null) js=(JavascriptExecutor) driver;
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }catch (Exception e){
            System.out.println("Problemas en el scroll down element");
        }

    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}