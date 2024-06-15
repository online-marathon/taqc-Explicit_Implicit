package com.softserve.edu.example;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjaxTest {
    private static final String BASE_URL
            = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/paging/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final Long IMPLICITLY_WAIT_FIVE_SECONDS = 5L;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;

    // Overload
    private static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    private static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void afterAll() {
        presentationSleep(); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
            // driver.close();
        }
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
        closePopup();
    }

    @AfterEach
    public void afterEach() {
        presentationSleep(); // For Presentation ONLY
        // logout; clear cache; delete cookie; delete session;
        // Save Screen;
    }

    private void closePopup() {
        presentationSleep(); // For Presentation ONLY
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_FIVE_SECONDS));
        long timeStart = System.currentTimeMillis();
        List<WebElement> footerButton = driver
                .findElements(By.xpath("//footer[contains(@class,'cookie')]//button"));
        System.out.println("footerButton.size() = " + footerButton.size());
        if (footerButton.size() > 0) {
            footerButton.get(0).click();
            presentationSleep(); // For Presentation ONLY
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
    }

    //@Test
    public void checkPage() {
        String expectedText = "Use Paging with Other Data Processing Plugins";
        WebElement position = driver.findElement(By
                .id("use-paging-with-other-data-processing-plugins"));
        Assertions.assertTrue(position.getText().contains(expectedText));
    }

    // Wrong Test
    //@Test
    public void checkIframeAjaxWrong() {
        WebElement position = driver
                .findElement(By.id("use-paging-with-other-data-processing-plugins"));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        presentationSleep(2); // For Presentation ONLY
        driver.switchTo().frame(driver
                .findElement(By.xpath("//div[contains(@data-options,'remote-paging')]//iframe")));
        // Search First Element
        WebElement tdNevadaFirstData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaFirstData.getText() = " + tdNevadaFirstData.getText());
        String removeText = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[3]")).getText();
        System.out.println("removeText = " + removeText);
        // Goto to Page 2
        driver.findElement(By.xpath("//button[text()='2']")).click();
        // Search Second Element
        WebElement tdNevadaSecondData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaSecondData.getText() = " + tdNevadaSecondData.getText());
        Assertions.assertEquals("2013/12/07", tdNevadaSecondData.getText());
    }

    // Thread Sleep
    //@Test
    public void checkIframeAjaxThreadSleep() {
        WebElement position = driver
                .findElement(By.id("use-paging-with-other-data-processing-plugins"));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        presentationSleep(2); // For Presentation ONLY
        driver.switchTo().frame(driver
                .findElement(By.xpath("//div[contains(@data-options,'remote-paging')]//iframe")));
        // Search First Element
        WebElement tdNevadaFirstData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaFirstData.getText() = " + tdNevadaFirstData.getText());
        String removeText = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[3]")).getText();
        System.out.println("removeText = " + removeText);
        // Goto to Page 2
        driver.findElement(By.xpath("//button[text()='2']")).click();
        presentationSleep(4); // DO NOT USE
        // Search Second Element
        WebElement tdNevadaSecondData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaSecondData.getText() = " + tdNevadaSecondData.getText());
        Assertions.assertEquals("2013/12/07", tdNevadaSecondData.getText());
    }

    // Explicit Waits
    @Test
    public void checkIframeAjaxExplicit() {
        // Move to Element
        WebElement position = driver
                .findElement(By.id("use-paging-with-other-data-processing-plugins"));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        presentationSleep(2); // For Presentation ONLY
        //
        // Switch To iFrame
        driver.switchTo().frame(driver
                .findElement(By.xpath("//div[contains(@data-options,'remote-paging')]//iframe")));
        //
        // Search First Element
        WebElement tdNevadaFirstData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaFirstData.getText() = " + tdNevadaFirstData.getText());
        String removeText = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[3]")).getText();
        System.out.println("removeText = " + removeText);
        //
        // Goto to Page 2
        driver.findElement(By.xpath("//button[text()='2']")).click();
        //
        // Turn off Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //
        //(new WebDriverWait(driver, Duration.ofSeconds(10)))
        //        .until(ExpectedConditions.stalenessOf(tdNevadaFirstData));
        //
        // Explicit Wait
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.
                invisibilityOfElementLocated(By.xpath("//td[text()='" + removeText + "']")));
        //
        // Turn on Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        //
        // Search Second Element
        WebElement tdNevadaSecondData = driver
                .findElement(By.xpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaSecondData.getText() = " + tdNevadaSecondData.getText());
        Assertions.assertEquals("2013/12/07", tdNevadaSecondData.getText());
    }

}