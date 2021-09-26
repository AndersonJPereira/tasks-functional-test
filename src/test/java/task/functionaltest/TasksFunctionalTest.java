package task.functionaltest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.pages.TasksPage;

public class TasksFunctionalTest {
	
	private static TasksPage tasksPage;

	@BeforeClass
	public static void setup() {
		tasksPage = new TasksPage();
		tasksPage.acessarPagina();
	}
	
	@Test
	public void deveInserirTaskComSucesso() {
		LocalDate data = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tasksPage.clicarBotaoAdicionarTask();
		tasksPage.preencherDescricaoTask("Estudar Jenkins");
		tasksPage.preencherDataTask(data.format(formatter));
		tasksPage.clicarBotaoSalvarTask();
		Assert.assertEquals("Success!", tasksPage.obterStatusInsercaoTask());
	}

	@AfterClass
	public static void finalizar() {		
		tasksPage.fecharPagina();
	}
}
