package com.softserve.edu.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SimpleTest {

    // @Test
    public void checkGoogle() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Selenium download");
        searchElement.submit();
        Thread.sleep(2000); // For Presentation
        driver.findElement(By.xpath("//h3[text()='Install a Selenium library']")).click();      // Error
        Thread.sleep(8000); // For Presentation
        //
        driver.quit();
    }

    // Runtime Error
    // @Test
    public void checkSpeak1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        WebElement searchElement = driver.findElement(By
                .cssSelector("input.ant-select-selection-search-input"));
        searchElement.sendKeys("Школа");
        WebElement searchClub = driver.findElement(By
                .xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]"));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        searchClub.click();
        driver.quit();
    }

    // Thread.sleep()
    // @Test
    public void checkSpeak2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        WebElement searchElement = driver.findElement(By
                .cssSelector("input.ant-select-selection-search-input"));
        searchElement.sendKeys("Школа");
        Thread.sleep(2000); // For Presentation
        WebElement searchClub = driver.findElement(By
                .xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]"));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        Thread.sleep(2000); // For Presentation
        searchClub.click();
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Implicit Waits
    // @Test
    public void checkSpeak3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Setting timeout searching of web element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300)); // 300 by default
        //driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30)); // 30 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30)); // 30 by default
        driver.manage().window().maximize();
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        WebElement searchElement = driver.findElement(By
                .cssSelector("input.ant-select-selection-search-input"));
        searchElement.sendKeys("Школа");
        WebElement searchClub = driver.findElement(By
                .xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]"));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        searchClub.click();
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Explicit Waits
    // @Test
    public void checkSpeak4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        WebElement searchElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input.ant-select-selection-search-input")));
        searchElement.sendKeys("Школа");
        WebElement searchClub = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]")));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        searchClub.click();
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Implicit/Explicit Waits. elementToBeClickable()
    //@Test
    public void checkSpeak5() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().window().maximize();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        WebElement searchElement = driver.findElement(By
                .cssSelector("input.ant-select-selection-search-input"));
        searchElement.sendKeys("Школа");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebElement searchClub = driverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]")));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        searchClub.click();
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Custom Waits
    // @Test
    public void checkSpeak6() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        driverWait.until(new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver driver) {
                                System.out.println("driver.getTitle() = " + driver.getTitle());
                                return driver.getTitle().contains("Навчай українською");
                            }
                        }
        );
        WebElement searchElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input.ant-select-selection-search-input")));
        searchElement.sendKeys("Школа");
        WebElement searchClub = driverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'ant-card')]//div[contains(text(),'Dream Team')]")));
        System.out.println("searchClub = " + searchClub.getText());
        Assertions.assertEquals("Школа танців Dream Team", searchClub.getText());
        searchClub.click();
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // FluentWait
    //@Test
    public void checkSpeak7() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Wait<WebDriver> driverWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NoSuchElementException.class)
                .ignoring(NullPointerException.class)
                .ignoring(TimeoutException.class);
        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/");
        driverWait.until(new ExpectedCondition<Boolean>() {
                             public Boolean apply(WebDriver driver) {
                                 System.out.println("driver.getTitle() = " + driver.getTitle());
                                 return driver.getTitle().contains("Навчай українською");
                             }
                         }
        );
        WebElement searchElement = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input.ant-select-selection-search-input")));
        searchElement.sendKeys("Школа");
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Ajax test
    //@Test
    public void checkAjax1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.w3schools.com/xml/ajax_intro.asp");
        WebElement firstText = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#demo > h2")));
        //        By.xpath("//div[@id='demo']/h2")));
        WebElement changeButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div#demo > button")));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView(true);", firstText);
        Actions action = new Actions(driver);
        action.moveToElement(changeButton).perform();
        Thread.sleep(2000); // For Presentation
        changeButton.click();
        driverWait.until(ExpectedConditions.stalenessOf(firstText));
        //driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#demo > h2")));
        //driverWait.until(ExpectedConditions
        //        .invisibilityOfElementLocated(By.cssSelector("div#demo > h2")));
        //driverWait.until(ExpectedConditions
        //        .invisibilityOfElementWithText(By.cssSelector("div#demo > h2"), "Let AJAX change this text")));
        WebElement nextParagraf = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div#demo > h1 + p")));
        Assertions.assertEquals("AJAX is not a programming language.", nextParagraf.getText());
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }

    // Ajax2 test
    @Test
    public void checkAjax2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.w3schools.com/xml/ajax_intro.asp");
        WebElement firstText = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div#demo > h2")));
        //        By.xpath("//div[@id='demo']/h2")));
        WebElement changeButton = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div#demo > button")));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);", firstText);
        Actions action = new Actions(driver);
        action.moveToElement(changeButton).perform();
        Thread.sleep(2000); // For Presentation
        changeButton.click();
        //driverWait.until(ExpectedConditions.stalenessOf(firstText));
        driverWait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.cssSelector("div#demo > h2")));
        WebElement nextParagraf = driverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div#demo > h1 + p")));
        Assertions.assertEquals("AJAX is not a programming language.", nextParagraf.getText());
        Thread.sleep(8000); // For Presentation
        driver.quit();
    }
}
