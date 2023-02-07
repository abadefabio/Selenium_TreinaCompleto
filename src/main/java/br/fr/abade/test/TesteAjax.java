package br.fr.abade.test;
import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.fr.abade.core.DSL;
import br.fr.abade.core.DriverFactory;



public class TesteAjax {

//	private WebDriver driver;
	private DSL dsl;
	
		

	@Before
	public void inicializar() {
		
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=bcbe2");
		System.getProperty("user.dir");
		dsl = new DSL();
	}

	@After
	public void finalizar() throws InterruptedException {
		Thread.sleep(1500);
		DriverFactory.getDriver().quit();
	}
	
	@Test
	public void testeAjax() {
		
		dsl.escrever("j_idt343:name", "TestFR");
		dsl.clicarBotao("j_idt343:j_idt347");
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(25));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "TestFR"));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("TestFR", dsl.obterTexto("j_idt343:display"));
		
	}
}
