import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	@Test
	public void deveRealizarCadastroCompleto() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			System.getProperty("user.dir");
			
			driver.findElement(By.id("elementosForm:nome")).sendKeys("teste FR");
			driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("system");
			driver.findElement(By.id("elementosForm:sexo")).click();
			driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
			
			new Select(driver.findElement(By.id("elementosForm:escolaridade")))
					.selectByVisibleText("Mestrado");
			new Select(driver.findElement(By.id("elementosForm:esportes")))
			.selectByVisibleText("Natacao");
			driver.findElement(By.id("elementosForm:cadastrar")).click();		
			
			
			Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
			Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("teste FR"));
			Assert.assertEquals("Sobrenome: system", driver.findElement(By.id("descSobrenome")).getText());
			Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descsexo")).getText());
			Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
			Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
			Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
			
			driver.quit();
		}
	@Test
	public void deveValidarNomeObrigatorio() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			
			driver.findElement(By.id("elementosForm:cadastrar")).click();
		
			Alert alerta = driver.switchTo().alert();
			Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
			alerta.accept();
			
			driver.quit();
	}
	
	@Test
	public void deveValidarSobreNomeObrigatorio() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			driver.findElement(By.id("elementosForm:nome")).sendKeys("FR");
			driver.findElement(By.id("elementosForm:cadastrar")).click();
		
			Alert alerta = driver.switchTo().alert();
			Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
			alerta.accept();
			
			driver.quit();
	}
	
	@Test
	public void deveValidarRadioSexoObrigatorio() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			driver.findElement(By.id("elementosForm:nome")).sendKeys("FR");
			driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("SYSTEM");
			driver.findElement(By.id("elementosForm:cadastrar")).click();
		
			Alert alerta = driver.switchTo().alert();
			Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
			alerta.accept();			
			
			driver.quit();
	}
	
	@Test
	public void deveValidarComidaFavoritaObrigatorio() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			driver.findElement(By.id("elementosForm:nome")).sendKeys("FR");
			driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("SYSTEM");
			driver.findElement(By.id("elementosForm:sexo:0")).click();
			driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
			driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
			driver.findElement(By.id("elementosForm:cadastrar")).click();
		
			Alert alerta = driver.switchTo().alert();
			Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
			alerta.accept();			
			
			driver.quit();
	}
}
