import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteCampoTreinamento {

	private WebDriver driver;

	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1000, 700));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		System.getProperty("user.dir");
	}

	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void testeTextField() {

		driver.findElement(By.id("elementosForm:nome")).sendKeys("teste escrita");
		Assert.assertEquals("teste escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}

	@Test
	public void deveInteragirComTextArea() {

		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste escrita textArea\nOsasco\nmunhoz");
		Assert.assertEquals("teste escrita textArea\nOsasco\nmunhoz", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}

	@Test
	public void deveInteragirComRadioButton() {

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void deveInteragirComCombo() {

		WebElement combo = driver.findElement(By.id("elementosForm:escolaridade"));
		Select escolaridade = new Select(combo);
		//			escolaridade.selectByIndex(3);
		//			escolaridade.selectByValue("superior");
		escolaridade.selectByVisibleText("Superior");

		Assert.assertEquals("Superior", escolaridade.getFirstSelectedOption().getText());			

	}

	@Test
	public void deveVerificarComboBox() {

		WebElement combo = driver.findElement(By.id("elementosForm:escolaridade"));
		Select escolaridade = new Select(combo);

		List<WebElement> options = escolaridade.getOptions();
		Assert.assertEquals(8,options.size());

		boolean encontrou = false;

		for(WebElement option :options) {
			if(option.getText().equals("Mestrado")) {
				encontrou=true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {

		WebElement combo = driver.findElement(By.id("elementosForm:esportes"));
		Select escolaridade = new Select(combo);
		escolaridade.selectByVisibleText("Natacao");
		escolaridade.selectByVisibleText("Corrida");
		escolaridade.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectedOptions = escolaridade.getAllSelectedOptions();		
		Assert.assertEquals(3,allSelectedOptions.size());

		escolaridade.deselectByVisibleText("Corrida");
		allSelectedOptions = escolaridade.getAllSelectedOptions();		
		Assert.assertEquals(2, allSelectedOptions.size());
			
	}

	@Test
	public void deveInteragirComBotoes() {

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();

		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));		
	}

	@Test
	//	@Ignore
	public void deveInteragirComLinks() {

		WebElement link = driver.findElement(By.linkText("Voltar"));
		link.click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());	
			
	}

	@Test
	public void deveBuscarTextosNaTelaPagina() {

		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		//verificando se o texto esta na tela pelo body pegando geral

		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				driver.findElement(By.className("facilAchar")).getText());
		
	}
}
