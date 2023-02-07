package br.fr.abade.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.fr.abade.core.BasePage;
import br.fr.abade.core.DSL;
import br.fr.abade.core.DriverFactory;

public class CampoDeTreinamentoPage extends BasePage{
	
		
	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}
	
	public void setSobreNome(String sobrenome) {
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void SetComidaPizza() {
		dsl.clicarRadio("elementosForm:comidafavorita:2");
	}
	
	public void SetComidaCarne() {
		dsl.clicarRadio("elementosForm:comidafavorita:0");
	}
	public void SetComidaVegetariano() {
		dsl.clicarRadio("elementosForm:comidafavorita:3");
	}
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void SetEsporte(String... valores) {
		for(String valor:valores)
		dsl.selecionarCombo("elementosForm:esportes",valor);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro() {
//		return dsl.obterTexto("descNome");
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobreNomeCadastro() {
//		return dsl.obterTexto("descSobreNome");
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro() {
//		return dsl.obterTexto("descSexo");
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro() {
//		return dsl.obterTexto("descComida");
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCadastro() {
//		return dsl.obterTexto("descEscolaridade");
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsportesCadastro() {
//		return dsl.obterTexto("descEsportes");
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	
	}
}
