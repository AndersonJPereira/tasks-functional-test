package br.pages;

import br.assets.DriverFactory;
import br.assets.Properties;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Driver;

import br.assets.BasePage;

public class TasksPage extends BasePage {

	public void acessarPagina() {
		DriverFactory.getDriver().get("http://"+Properties.LOCALHOST+":8001/tasks");	
	}
	
	public void clicarBotaoAdicionarTask() {
		clicarBotao("addTodo");
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
	
	public void fecharPagina() {
		DriverFactory.killDriver();
	}
}
