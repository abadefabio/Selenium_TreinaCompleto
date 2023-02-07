package br.fr.abade.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.fr.abade.core.DriverFactory;
import br.fr.abade.test.TesteCadastro;
import br.fr.abade.test.TesteCampoTreinamento;
import br.fr.abade.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
//	TesteCadastro.class,
	TesteCampoTreinamento.class,
	TesteRegrasCadastro.class
})
public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
}
