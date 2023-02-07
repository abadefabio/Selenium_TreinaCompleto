package br.fr.abade.core;

import static br.fr.abade.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class DSL {
	
	
	/*********** TextField e textArea ************/
	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
		
	}
	public void escrever(String id_campo, String texto) {
		escrever(By.id(id_campo), texto);		
	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/************ Radio e check *************/
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}
	
	/********** Combo **********/
	public void selecionarCombo(By by, String valor) {
		WebElement elemento = DriverFactory.getDriver().findElement(By.xpath(valor));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	public void selecionarCombo(String id, String valor) {
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valor);
	}
	public String obterValorCombo(String id) {
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id){
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> options = combo.getOptions();

		return options.size();
	}
	
	public boolean verificaOpcaoCombo(String id, String item){
		WebElement elemento = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(elemento);
		List<WebElement> options = combo.getOptions();
		for(WebElement opcao: options) {
			if(opcao.getText().equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//option[@value='"+valor+"']"));
	}
	
	/**************** Botoes **************/
	public void clicarBotao(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
	}
	/*********** Link *************/
	public void clicarLink(String link) {
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}
	
	/************ textos *************/
	public String obterTexto(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));				
	}
	
	/*********** Alertas **********/
	public String alertaObterTexto() {
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		return alerta.getText();				
	}
	
	public String alertaObterTextoEAceitar() {
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.accept();
		return valor;				
	}
	
	public String alertaObterTextoENegar() {
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		String valor = alerta.getText();
		alerta.dismiss();
		return valor;				
	}
	
	public void alertaEscrever(String valor) {
		Alert alerta = DriverFactory.getDriver().switchTo().alert();
		alerta.sendKeys(valor);
		alerta.accept();				
	}
	/************ Frames e Janelas ************/
	
	public void entrarFrame(String id) {
		DriverFactory.getDriver().switchTo().frame(id);
	}
	
	public void sairFrame(String id) {
		DriverFactory.getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		DriverFactory.getDriver().switchTo().window(id);
	}
	
	/********** JS **********************/
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		return js.executeScript(cmd, param);		
	}
	
	/********** tabela **********************/
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String xpath) {
		//procurar coluna do registro da table
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada 
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i=0;i<linhas.size();i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i=0;i<colunas.size();i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
