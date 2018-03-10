package com.artinrayan.foodi.web.automationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by asus on 1/22/2018.
 */
public class RadioButtons {

    public static void main(String[] args) {

// TODO Auto-generated method stub

        System.setProperty("webdriver.chrome.driver","D:\\Java\\Selenium\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver=new ChromeDriver();

//WebDriver driver=new FirefoxDriver();

        driver.get("http://www.echoecho.com/htmlforms10.htm");

//driver.findElement(By.xpath("//input[@value='Milk']")).click();

        int count =driver.findElements(By.xpath("//input[@name='group1']")).size();

        for(int i=0;i<count;i++)

        {

//driver.findElements(By.xpath("//input[@name='group1']")).get(i).click();

            String text=driver.findElements(By.xpath("//input[@name='group1']")).get(i).getAttribute("value");

            if(text.equals("Cheese"))

            {

                driver.findElements(By.xpath("//input[@name='group1']")).get(i).click();

            }

        }

    }
}
