package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	/**
	 * This method is used to intialize the WebDriver
	 * 
	 * @param browserName
	 * @return this will return driver reference
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("browser name is : " + browserName);
		
		highlight = prop.getProperty("highlight");
		
		optionsManager = new  OptionsManager(prop);

		// cross-browser logic
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.chromedriver().setup();
		//	driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
		//	driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("please pass the right browser: " + browserName);
		}

		// pre-conditions
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

	//	openUrl(prop.getProperty("url"));
		
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return getDriver();

	}

	/**
	 * this method will return a thread local copy of the webdriver
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to intialise the Properties
	 * @return the Properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream fip = null;
		
		String envName = System.getProperty("env"); //qa//dev//stage//uat//production
		
		
		if(envName==null) {
			System.out.println("Running on PRODUCTION env");
			try {
				fip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("Running on environment: " + envName);
			try {
			switch (envName.toLowerCase()) {
			case "qa":
				fip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "dev":
				fip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				fip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				fip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
				
			default:
				System.out.println("please pass the right environment");
				break;
			}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
		}
		
		try {
			prop.load(fip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * 
	 */
     public String getScreenshot() {
    	 File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
    	 String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
    	 File destination = new File(path);
    	 
    	 try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
    	 return path;
     }
     
     /**
      * launch url
      * @param url
      */
     public void openUrl(String url) {
    	 try {
    	 if(url==null) {
    		 	throw new Exception("url is null");
    	 	}
    	 }  catch(Exception e) {
    		   
    	   }
    	 getDriver().get(url);
    	 
     }
     
     
     public void openUrl(URL url) {
    	 try {
    	 if(url==null) {
    		 	throw new Exception("url is null");
    	 	}
    	 }  catch(Exception e) {
    		   
    	   }
    	 getDriver().navigate().to(url);
    	 
     }
     
     
     
     public void openUrl(String baseurl, String path) {
    	 try {
    	 if(baseurl==null) {
    		 	throw new Exception("baseurl is null");
    	 	}
    	 }  catch(Exception e) {
    		   
    	   }
    	 //http://amazon.com/accpage/users.html
    	 getDriver().get(baseurl+"/"+path);
    	 
     }
     
     
     
     public void openUrl(String baseurl, String path, String queryParameter) {
    	 try {
    	 if(baseurl==null) {
    		 	throw new Exception("baseurl is null");
    	 	}
    	 }  catch(Exception e) {
    		   
    	   }
    	 //http://amazon.com/accpage/users.html
    	 getDriver().get(baseurl+"/"+path+"?"+queryParameter);
    	 
     }
}
