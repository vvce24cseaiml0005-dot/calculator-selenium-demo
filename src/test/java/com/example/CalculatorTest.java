package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class CalculatorTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox",
                "--disable-dev-shm-usage", "--window-size=1280,800");
        driver = new ChromeDriver(options);
        String url = Paths.get("webpage", "calculator.html")
                .toAbsolutePath().toUri().toString();
        driver.get(url);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String calculate(String a, String b, String buttonId) {
        driver.findElement(By.id("num1")).clear();
        driver.findElement(By.id("num1")).sendKeys(a);
        driver.findElement(By.id("num2")).clear();
        driver.findElement(By.id("num2")).sendKeys(b);
        driver.findElement(By.id(buttonId)).click();
        return driver.findElement(By.id("result")).getText();
    }

    @Test
    void testAddition() {
        assertEquals("8", calculate("5", "3", "add"));
    }

    @Test
    void testSubtraction() {
        assertEquals("6", calculate("10", "4", "subtract"));
    }

    @Test
    void testMultiplication() {
        assertEquals("42", calculate("6", "7", "multiply"));
    }

    @Test
    void testDivision() {
        assertEquals("2.5", calculate("10", "4", "divide"));
    }

    @Test
    void testDivisionByZero() {
        assertEquals("Cannot divide by zero", calculate("10", "0", "divide"));
    }
}