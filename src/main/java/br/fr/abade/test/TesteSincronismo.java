package br.fr.abade.test;

import br.fr.abade.core.*;

import static br.fr.abade.core.DriverFactory.getDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
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
import br.fr.abade.page.CampoDeTreinamentoPage;



public class TesteSincronismo {

//	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;		
		

	@Before
	public void inicializar() {
		
		getDriver().get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		System.getProperty("user.dir");
		dsl = new DSL();
		page = new CampoDeTreinamentoPage();
	}

	@After
	public void finalizar() throws InterruptedException {
		Thread.sleep(1500);
		//driver.quit();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu certo?");
	}
	

	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		getDriver().manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(25));
//		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
	}
}
