import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;		
		

	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1000, 700));
		driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		System.getProperty("user.dir");
		dsl = new DSL(driver);
		page = new CampoDeTreinamentoPage(driver);
	}

	@After
	public void finalizar() throws InterruptedException {
		Thread.sleep(1500);
		//driver.quit();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("FRSystem");
		page.setSobreNome("Silva");
		page.setSexoMasculino();
		page.SetComidaPizza();
		page.setEscolaridade("Mestrado");
		page.SetEsporte("Natacao");
//		page.SetEsporte("Natacao","Karate");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		Assert.assertEquals("FRSystem", page.obterNomeCadastro());
		Assert.assertEquals("Silva", page.obterSobreNomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
	}
	
	@Test
	public void deveRealizarCadastroComSucessoOld() {
		page.setNome("FRSystem");
		page.setSobreNome("Silva");
		page.setSexoMasculino();
		page.SetComidaPizza();
		page.setEscolaridade("Mestrado");
		page.SetEsporte("Natacao");
//		page.SetEsporte("Natacao","Karate");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("FRSystem"));
		Assert.assertEquals("Sobrenome: Silva", page.obterSobreNomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsportesCadastro());
	}

	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("nomequalquer");
		page.setSobreNome("sobrenome qualquer");
		page.setSexoFeminino();
		page.SetComidaPizza();
		page.SetEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceitar());
	}
	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "teste escrita");
		Assert.assertEquals("teste escrita", dsl.obterValorCampo("elementosForm:nome"));
		
		////old
//		driver.findElement(By.id("elementosForm:nome")).sendKeys("teste escrita");
//		Assert.assertEquals("teste escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes","teste escrita textArea\nOsasco\nmunhoz");
		Assert.assertEquals("teste escrita textArea\nOsasco\nmunhoz", dsl.obterValorCampo("elementosForm:sugestoes"));

//		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste escrita textArea\nOsasco\nmunhoz");
//		Assert.assertEquals("teste escrita textArea\nOsasco\nmunhoz", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	
	}

	@Test
	public void deveInteragirComRadioButton() {

		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

//		driver.findElement(By.id("elementosForm:sexo:0")).click();
//		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarBotao("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
//		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
//		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void deveInteragirComCombo() {

		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
//		WebElement combo = driver.findElement(By.id("elementosForm:escolaridade"));
//		Select escolaridade = new Select(combo);
//		//			escolaridade.selectByIndex(3);
//		//			escolaridade.selectByValue("superior");
//		escolaridade.selectByVisibleText("Superior");
//		Assert.assertEquals("Superior", escolaridade.getFirstSelectedOption().getText());	
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));	

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
	
	@Test
	public void testeTestfieldDuplo() {
		dsl.escrever("elementosForm:nome", "teste escrita");
		Assert.assertEquals("teste escrita", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "teste escrita fr");
		Assert.assertEquals("teste escrita fr", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via java script'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px green");
		
	}
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");		
		dsl.clicarBotao("framebutton");
		String msg = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Francisco", "Botao", "elementosForm:tableUsuarios");
//		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
//		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
}
