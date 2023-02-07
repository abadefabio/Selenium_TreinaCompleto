package br.fr.abade.test;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chromedriver.driver","C:\\Users\\fabade\\eclipse-workspace\\SeleniumProjetoAC\\chromedriver.exe");
		//	WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
//		driver.manage().window().setPosition(new Point(100, 100));
		driver.manage().window().setSize(new Dimension(1000, 700));
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		
		driver.quit();
	}
}
