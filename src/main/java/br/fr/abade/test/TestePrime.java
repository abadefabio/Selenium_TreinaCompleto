package br.fr.abade.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import br.fr.abade.core.DSL;
import br.fr.abade.core.DriverFactory;

public class TestePrime {

//	private WebDriver driver;
	private DSL dsl;
		
		

	@Before
	public void inicializar() {
//		driver = new ChromeDriver();
//		driver.manage().window().setSize(new Dimension(1000, 700));
//		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=87b4d");
		System.getProperty("user.dir");
		dsl = new DSL();
	}

	@After
	public void finalizar() throws InterruptedException {
		Thread.sleep(1500);
		//driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt344:console:0']/../..//span")); 
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='Option3']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:2"));
		
	}
	
	@Test
	public void deveInteragirComComboPrime() {
		dsl.clicarRadio(By.xpath("//*[@id='j_idt343:option_input']/../..//span"));
		dsl.clicarRadio(By.xpath("//*[@id='j_idt343:option_items']//option[@value='Option2']"));
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt343:console_label"));
	}

	
}
