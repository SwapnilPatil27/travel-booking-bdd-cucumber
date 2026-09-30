package com.travel.driver;

import com.travel.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static void initDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();

        WebDriver driver;
        switch (browser) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (ConfigReader.headless()) options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (ConfigReader.headless()) options.addArguments("--headless=new");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.timeout()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
