package tasks.prod.healthcheck;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pages.TasksPage;

public class HealthCheckIT {
	
	private TasksPage tasksPage;
	
	@Before
	public void setup() {
		tasksPage = new TasksPage();
		tasksPage.acessarPaginaProd();
	}
	
	@Test
	public void realizarHealth() {
		Assert.assertTrue(tasksPage.obterVersaoBuild().contains("build"));
	}
	
	@After
	public void finalizar() {		
		tasksPage.fecharPagina();
	}

}
