package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;


public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     private ChromeDriver driver;

    // Constructor to initialize the driver
    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }

    /**
     * Wrapper to find an element
     * @param locator The locator of the element (By object)
     * @return The WebElement
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Wrapper to type text into an input field
     * @param locator The locator of the field
     * @param text The text to type
     */
    
        
     public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void typeInt(By locator, int number) {
        driver.findElement(locator).sendKeys(String.valueOf(number));
    }

    /**
     * Wrapper to click on an element
     * @param locator The locator of the element
     */
    public void click(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }

    /**
     * Wrapper to wait for an element to be visible
     * @param locator The locator of the element
     * @param timeoutInSeconds The timeout duration
     * @return The WebElement after it becomes visible
     */
    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wrapper to handle alerts
     * @param accept True to accept the alert, false to dismiss
     */
    public void handleAlert(boolean accept) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    /**
     * Wrapper to get text from an element
     * @param locator The locator of the element
     * @return The text of the element
     */
    public String getText(By locator) {
        WebElement element = findElement(locator);
        return element.getText();
    }

    /**
     * Wrapper to check if an element is displayed
     * @param locator The locator of the element
     * @return True if displayed, false otherwise
     */
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
 * Wrapper to select a checkbox (if not already selected)
 * @param locator The locator of the checkbox
 */
public void clickCheckbox(By locator) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!element.isSelected()) { // Check if it's already selected
            element.click(); // Click to select
        }
    } catch (Exception e) {
        System.err.println("Error while selecting the checkbox: " + e.getMessage());
    }
}

/**
 * Wrapper to select a value from a dropdown by visible text
 * @param locator The locator of the dropdown
 * @param visibleText The visible text to select
 */
public void selectByVisibleText(By locator, String visibleText) {
    try {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //Select select = new Select(dropdown);
        //select.selectByVisibleText(visibleText);
        driver.findElement(locator).click();
        
    } catch (Exception e) {
        System.err.println("Error while selecting from the dropdown: " + e.getMessage());
    }
}



    
}



