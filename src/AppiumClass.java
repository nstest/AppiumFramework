import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppiumClass {

	//Download the iOS test app from the link https://github.com/appium/ios-uicatalog and put in the Desktop. "~/Desktop/UICatalog.app"
	static IOSDriver<IOSElement> driver = null;
	static String type = "safariBrowser"; 
	public static void main(String[] args) throws MalformedURLException {

		initialize();
		if(type.equalsIgnoreCase("safariBrowser")) {
			enterEmail();
			printHeadingText();
			
		} else if(type.equalsIgnoreCase("iosApp")) {
			actionSheets();
			activityIndicators();
			alertViews();
			datePicker();
			pickerView();
			steppers();
		}

		quit();
		
	}
	
	private static void enterEmail() {
		driver.findElement(By.id("identifierId")).sendKeys("santhoshmaddy@gmail.com");
		driver.findElement(By.xpath("//div[@id='identifierNext']/div[2]")).click();
		
	}
	
	private static void printHeadingText() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileIdentifier")));
		
		System.out.println(driver.findElement(By.id("headingText")).getText());
		System.out.println(driver.findElement(By.id("profileIdentifier")).getText());
	}
	

	private static void datePicker() {
		driver.findElementByAccessibilityId("Date Picker").click();
		
		driver.findElementsByClassName("XCUIElementTypePickerWheel").get(0).sendKeys("Fri Nov 10");
		
 		driver.findElementsByClassName("XCUIElementTypePickerWheel").get(1).sendKeys("10");
		driver.findElementsByClassName("XCUIElementTypePickerWheel").get(2).sendKeys("46");
		driver.findElementsByClassName("XCUIElementTypePickerWheel").get(3).sendKeys("PM");
		
		System.out.println(driver.findElementByClassName("XCUIElementTypeStaticText").getText());
		
		driver.navigate().back();
		
	}


	private static void activityIndicators() {
		driver.findElementByAccessibilityId("Activity Indicators").click();
		
		if(driver.findElementsByXPath("//XCUIElementTypeActivityIndicator[@name='In progress']").get(0).isDisplayed())
			System.out.println("GRAY Activity Indicator present");
		else 
			System.out.println("GRAY Activity Indicator is not present");
		
		if(driver.findElementsByXPath("//XCUIElementTypeActivityIndicator[@name='In progress']").get(1).isDisplayed())
			System.out.println("TINTED Activity Indicator present");
		else 
			System.out.println("TINTED Activity Indicator is not present");
		
		driver.navigate().back();
		
	}


	/**
	 * @author ssundresh
	 * This method performs all the actions under the Action Sheets
	 */
	private static void actionSheets() {
		
		driver.findElementByAccessibilityId("Action Sheets").click();
		
		driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Okay / Cancel']").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@name='OK']").click();
		
		driver.findElementByAccessibilityId("Other").click();
		driver.findElementByAccessibilityId("Safe Choice").click();
		
		//Navigate back to homepage after performing all the actions under Action Sheets
		driver.navigate().back();
		
	}


	private static void pickerView() {

		System.out.println("Click on Picker View");
		driver.findElementByAccessibilityId("Picker View").click();
		driver.findElementByAccessibilityId("Red color component value").sendKeys("80");
		driver.findElementByAccessibilityId("Green color component value").sendKeys("220");
		driver.findElementByAccessibilityId("Blue color component value").sendKeys("130");
		System.out.println("Ending Picker View");
		
		driver.navigate().back();
		
	}

	private static void steppers() {
		driver.findElementByAccessibilityId("Steppers").click();
		driver.findElementByXPath("//XCUIElementTypeButton[@name='Increment']").click();
		driver.findElementsByXPath("//XCUIElementTypeButton[@name='Increment']").get(2).click();
		
		driver.findElementByAccessibilityId("Increment").click();
		driver.findElementByAccessibilityId("Increment").click();
		System.out.println("Default Value: " + driver.findElementsByClassName("XCUIElementTypeStaticText").get(0).getText());
		System.out.println("Tinted Value: " + driver.findElementsByClassName("XCUIElementTypeStaticText").get(1).getText());
		System.out.println("Custom Value: " + driver.findElementsByClassName("XCUIElementTypeStaticText").get(2).getText());
		
		driver.findElementByAccessibilityId("Decrement").click();
		System.out.println("Default Value: " + driver.findElementsByClassName("XCUIElementTypeStaticText").get(0).getText());
		
	}

	private static void quit() {
		driver.quit();
	}

	public static IOSDriver<IOSElement> initialize() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if(type.equals("iosApp")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			capabilities.setCapability(MobileCapabilityType.APP, "~/Desktop/UICatalog.app");
			
		} else if(type.equals("safariBrowser")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.1");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			capabilities.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS, true);
		}
		driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.gmail.com");
		return driver;
	}
	
	public static void alertViews() {
		driver.findElementByAccessibilityId("Alert Views").click();
		driver.findElementByXPath("//XCUIElementTypeStaticText[@value='Text Entry']").click();
		driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("A Short Message");
		driver.findElementByName("OK").click();
		
		driver.navigate().back();
	}

}
