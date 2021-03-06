package br.pages;

import br.assets.DriverFactory;
import br.assets.Properties;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.assets.BasePage;

public class TasksPage extends BasePage {

	public void acessarPagina() {
		DriverFactory.getDriver().get("http://"+Properties.LOCALHOST+":9555/tasks");
	}
	
	public void acessarPaginaProd() {
		DriverFactory.getDriver().get("http://"+Properties.LOCALHOST+":9999/tasks");	
	}
	
	public void clicarBotaoAdicionarTask() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(),20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("addTodo")));
		clicarBotao("addTodo");
	}
	
	public void clicarBotaoRemoverTask(String task) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(),20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='todoTable']//td[.='"+task+"']/..//a[.='Remove']")));
		clicarBotaoByXpath("//table[@id='todoTable']//td[.='"+task+"']/..//a[.='Remove']");
	}
	
	public void preencherDescricaoTask(String Descricao) {
		escreverTexto("task", Descricao);
	}

	public void preencherDataTask(String Data) {
		escreverTexto("dueDate", Data);
	}
	
	public void clicarBotaoSalvarTask() {
		clicarBotao("saveButton");
	}
	
	public String obterStatusInsercaoTask() {
		return obterTexto("message");
	}
	
	public String obterVersaoBuild() {
		return obterTexto("version");
	}
	
	public void fecharPagina() {
		DriverFactory.killDriver();
	}
}
