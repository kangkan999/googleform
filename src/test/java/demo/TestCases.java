package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */

     @BeforeTest
     public void startBrowser()
     {
         System.setProperty("java.util.logging.config.file", "logging.properties");
 
         // NOT NEEDED FOR SELENIUM MANAGER
         // WebDriverManager.chromedriver().timeout(30).setup();
 
         ChromeOptions options = new ChromeOptions();
         LoggingPreferences logs = new LoggingPreferences();
 
         logs.enable(LogType.BROWSER, Level.ALL);
         logs.enable(LogType.DRIVER, Level.ALL);
         options.setCapability("goog:loggingPrefs", logs);
         options.addArguments("--remote-allow-origins=*");
 
         System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
 
         driver = new ChromeDriver(options);
 
         driver.manage().window().maximize();

         driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
     }

     @Test
     public void testCase01() throws InterruptedException {
         System.out.println("Starting Test Case 01");
     
         // Create a WebDriverWait instance
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         Wrappers wrappers = new Wrappers(driver);
     
         // Step 1: Fill in the "Name" field
         By nameFieldLocator = By.xpath("//input[@type='text']");
         wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldLocator));
         System.out.println("Filling the Name field.");
         wrappers.type(nameFieldLocator, "Crio Learner");
     
         // Step 2: Answer a text question
         By questionFieldLocator = By.xpath("//textarea[contains(@class,'tL9Q4c')]");
         String epochAnswer = "I want to be the best QA Engineer! " + Instant.now().getEpochSecond();
         wrappers.type(questionFieldLocator, epochAnswer);
         System.out.println("Entered answer: " + epochAnswer);
     
         // Step 3: Select a radio button for "6-10 years" of experience
         By radioButtonLocator = By.xpath("//div[@id='i22']//div[@class='AB7Lab Id5V1']");
         wrappers.click(radioButtonLocator);
         System.out.println("Selected radio button for '6 - 10 years' experience.");
     
         // Step 4: Select checkboxes for skills
         By javaCheckbox = By.xpath("//div[@id='i34']//div[@class='uHMk6b fsHoPb']");
         By seleniumCheckbox = By.xpath("//div[@id='i37']//div[@class='uHMk6b fsHoPb']");
         By testngCheckbox = By.xpath("//div[@id='i43']//div[@class='uHMk6b fsHoPb']");
         wrappers.clickCheckbox(javaCheckbox);
         wrappers.clickCheckbox(seleniumCheckbox);
         wrappers.clickCheckbox(testngCheckbox);
         System.out.println("Selected checkboxes: Java, Selenium, TestNG.");
     
         // Step 5: Select "Mr" from a dropdown menu
         driver.findElement(By.xpath("//body/div[@class='Uc2NEf']/div[@class='teQAzf']/form[@id='mG61Hd']/div[@class='RH5hzf RLS9Fe']/div[@class='lrKTG']/div[@role='list']/div[@role='listitem']/div/div[@class='geS5n']/div[@class='vQES8d']/div[@role='listbox']/div[@role='presentation']/div[2]")).click();
         Thread.sleep(2000);
         By dropdownLocator = By.xpath("(//span[normalize-space()='Mr'])[2]");
         wrappers.selectByVisibleText(dropdownLocator, "Mr");
         System.out.println("Selected 'Mr' from the dropdown.");
     
         // Step 6: Input a date (current date minus 7 days)
         By dateFieldLocator = By.xpath("//input[@type='date']");
         String formattedDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
         wrappers.type(dateFieldLocator, formattedDate);
         System.out.println("Entered date: " + formattedDate);
     
         // Step 7: Enter a time and submit the form
         By hourElement = By.xpath("//input[@aria-label='Hour']");
         By miElement = By.xpath("//input[@aria-label='Minute']");
         By submitButtonLocator = By.xpath("//span[contains(text(),'Submit')]");
         wrappers.typeInt(hourElement, 07);
         wrappers.typeInt(miElement, 30);
         wrappers.click(submitButtonLocator);
         System.out.println("Entered time: " + " and clicked Submit.");
     
         // Step 8: Verify the success message
         By successMessageLocator = By.xpath("//div[@class='vHW8K']");
         wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
         WebElement successMessageElement = driver.findElement(successMessageLocator);
         String successMessage = successMessageElement.getText();
         System.out.println("Success message: " + successMessage);
     
         System.out.println("Test Case 01 completed successfully.");
     }


     @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}