package task.functionaltest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pages.TasksPage;

public class TasksFunctionalTest {
	
	private static TasksPage tasksPage;

	@Before
	public void setup() {
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
	
	@Test
	public void naoDeveInserirTaskComDescricaoVazia() {
		LocalDate data = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tasksPage.clicarBotaoAdicionarTask();
		tasksPage.preencherDescricaoTask("");
		tasksPage.preencherDataTask(data.format(formatter));
		tasksPage.clicarBotaoSalvarTask();
		Assert.assertEquals("Fill the task description", tasksPage.obterStatusInsercaoTask());
	}
	
	@Test
	public void naoDeveInserirTaskComDataPassada() {
		LocalDate data = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tasksPage.clicarBotaoAdicionarTask();
		tasksPage.preencherDescricaoTask("Estudo Jenkins");
		tasksPage.preencherDataTask(data.format(formatter));
		tasksPage.clicarBotaoSalvarTask();
		Assert.assertEquals("Due date must not be in past", tasksPage.obterStatusInsercaoTask());
	}
	
	@Test
	public void naoDeveInserirTaskSemData() {
		tasksPage.clicarBotaoAdicionarTask();
		tasksPage.preencherDescricaoTask("Estudo Jenkins");
		tasksPage.preencherDataTask("");
		tasksPage.clicarBotaoSalvarTask();
		Assert.assertEquals("Fill the due date", tasksPage.obterStatusInsercaoTask());
	}


	@After
	public void finalizar() {		
		tasksPage.fecharPagina();
	}
}
