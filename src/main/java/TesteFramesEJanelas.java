import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TesteFramesEJanelas {

	@Test
	public void deveInteragirComFrames() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			driver.switchTo().frame("frame1");
			driver.findElement(By.id("frameButton")).click();
			Alert alerta = driver.switchTo().alert();
			
			String msg = alerta.getText();			
			Assert.assertEquals("Frame OK!", msg);
			alerta.accept();
			
			driver.switchTo().defaultContent();
			driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			
			driver.findElement(By.id("buttonPopUpEasy")).click();
			driver.switchTo().window("Popup");
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
			driver.close();
						
			driver.switchTo().window("");
			driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
	
			driver.quit();
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1000, 700));
			driver.get("file:///"+ System.getProperty("user.dir") + "/src/main/resources/componentes.html");

			
			driver.findElement(By.id("buttonPopUpHard")).click();
			System.out.println(driver.getWindowHandle());
			System.out.println(driver.getWindowHandles());
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
			driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
			driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
						
			driver.quit();
	}
	
}