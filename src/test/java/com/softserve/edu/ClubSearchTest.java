package com.softserve.edu;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ClubSearchTest {


    private static final String BASE_URL
            = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/paging/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(BASE_URL);
    }

    @AfterEach
    public void afterEach() {
        // logout; clear cache; delete cookie; delete session;
        // Save Screen;
    }

    @Test
    public void checkClubSearch() {
        // TODO
    }

}
