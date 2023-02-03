import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)	
	public String [] esportes;
	@Parameter(value=5)
	public String msg;


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
		driver.quit();
	}

	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(), new String[] {},"Nome eh obrigatorio"},
			{"FRsytem","","",Arrays.asList(), new String[] {},"Sobrenome eh obrigatorio"},
			{"FRsytem","Silva","",Arrays.asList(), new String[] {},"Sexo eh obrigatorio"},
			{"FRsytem","Silva","Masculino",Arrays.asList("Carne","Vegetariano"), new String[] {},"Tem certeza que voce eh vegetariano?"},
			{"FRsytem","Silva","Masculino",Arrays.asList("Carne"), new String[] {"Karate","O que eh esporte?"},"Voce faz esporte ou nao?"}
		});
	}

	@Test
	public void deveValidarRegras() {

		page.setNome(nome);
		page.setSobreNome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}else if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}

		if(comidas.contains("Pizza"))page.SetComidaPizza();
		if(comidas.contains("Carne"))page.SetComidaCarne();
		if(comidas.contains("Vegetariano"))page.SetComidaVegetariano();

		page.SetEsporte(esportes);
		page.cadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceitar());
	}
}
