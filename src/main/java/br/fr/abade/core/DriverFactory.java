package br.fr.abade.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {}  // construtor
	
	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Propriedades.browser) {
			case FIREFOX: driver = new FirefoxDriver(); break;
			case CHROME: driver = new ChromeDriver(); break;
			case EDGE: driver = new EdgeDriver(); break;
			}			
			driver.manage().window().setSize(new Dimension(1000, 700));
		}		
		return driver;		
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
