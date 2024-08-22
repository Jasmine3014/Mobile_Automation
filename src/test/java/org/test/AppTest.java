package org.test;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {
	
	public AndroidDriver driver;
	
	@BeforeTest
	public AndroidDriver statApplication() throws MalformedURLException {
		File f = new File("src/test/resources");
		File general_store_apk = new File(f,"General-Store.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:deviceName", "local");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
        desiredCapabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.SplashActivity");
        desiredCapabilities.setCapability("appium:app",  general_store_apk.getAbsolutePath());
        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
        
      
	}
	@AfterTest
	public void quiteApplication() {
//	    driver.removeApp("com.androidsample.generalstore");
		driver.quit();
	}
	@Test
    public void testGeneraleStoreAPK() throws  InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
//		driver.findElement(By.xpath("//*[@text='Algeria']")).click();
		WebElement Colombia_opt = driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Colombia\"))"));
		Colombia_opt.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jasmine");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(By.xpath("//*[@text='Air Jordan 1 Mid SE']/following-sibling::android.widget.LinearLayout/*[@text='ADD TO CART']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        driver.findElement(By.className("android.widget.CheckBox")).click();
        Thread.sleep(5000);
    
    }

}