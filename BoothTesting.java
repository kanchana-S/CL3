/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class BoothTesting {
public static void main(String args[]) throws InterruptedException 
{
    String URL="http://localhost:8084/Booths/";
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kanchana\\Downloads\\chromedriver.exe");
    WebDriver driver= new ChromeDriver();
    
   // System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
    //WebDriver driver = new FirefoxDriver();
    driver.get(URL);
    
    int num1[]={10,1000,-7,-8,-4,3,6,15,11,12,-1};
    int num2[]={-7,111-8,-4,3,6,15,1,13,1,12,10};
    
    System.out.println("-------------------------------------------------");
    for(int i=0;i<10;i++){
        System.out.println("Test Case: "+(i+1));
        System.out.println("Num1: "+num1[i]);
        System.out.println("Num2: "+num2[i]);
        driver.get(URL);
        driver.findElement(By.name("number1")).sendKeys(num1[i]+"");
        driver.findElement(By.name("number2")).sendKeys(num2[i]+"");
        Thread.sleep(1000);
        driver.findElement(By.name("submit")).click(); 
        System.out.println("Successfull Test Case");
        System.out.println("-------------------------------------------------");
        
        Thread.sleep(2000);
    }
    
  Thread.sleep(5000);
   
 driver.close();
}
}
