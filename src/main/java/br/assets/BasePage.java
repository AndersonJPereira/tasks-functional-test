package br.assets;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	
	public void escreverTexto(String idElemento, String texto) {
		DriverFactory.getDriver().findElement(By.id(idElemento)).clear();
		DriverFactory.getDriver().findElement(By.id(idElemento)).sendKeys(texto);
	}
	
	public void escreverTexto(By idElemento, String texto) {
		DriverFactory.getDriver().findElement(idElemento).clear();
		DriverFactory.getDriver().findElement(idElemento).sendKeys(texto);
	}
	
	public void escreverTextoPrompt(String texto) {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(texto);
		alert.accept();
	}

	public void selecionarCheckBoxRadionButton(String idElemento) {

		DriverFactory.getDriver().findElement(By.id(idElemento)).click();

	}
	public void selecionarComboBoxPorTextoVisivel(String idElemento, String itemSelecionado) {
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		selecao.selectByVisibleText(itemSelecionado);
	}

	public void selecionarComboBoxPorIndex(String idElemento, int itemSelecionado) {
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		selecao.selectByIndex(itemSelecionado);
	}

	public void selecionarComboBoxPorValor(String idElemento, String itemSelecionado) {
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		selecao.selectByValue(itemSelecionado);
	}
	
	public void clicarBotao(String idElemento) {
		DriverFactory.getDriver().findElement(By.id(idElemento)).click();
	}
	
	public void clicarBotaoByXpath(String idElemento) {
		DriverFactory.getDriver().findElement(By.xpath(idElemento)).click();
	}

	public void entrarNoFrame(String idFrame) {
		DriverFactory.getDriver().switchTo().frame(idFrame);
	}

	public void entrarNoPopUp(String idElemento) {
		DriverFactory.getDriver().switchTo().window(idElemento);
	}
	
	public void clicarLink(String texto) {
		DriverFactory.getDriver().findElement(By.linkText(texto)).click();
	}
	
	public void sairDoFrame() {
		DriverFactory.getDriver().switchTo().defaultContent();
	}
	
	public void sairDoPopUp() {
		DriverFactory.getDriver().close();
		DriverFactory.getDriver().switchTo().window("");
	}
	
	public boolean obterSelecaoCheckBoxRadioButton(String idElemento) {
		return DriverFactory.getDriver().findElement(By.id(idElemento)).isSelected();
	}

	public String obterTextoAlertEAceita() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String msg = alert.getText();
		alert.accept();
		return msg;
	}
	
	public String obterTextoAlertENega() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String msg = alert.getText();
		alert.dismiss();
		return msg;
	}

	public String obterTextoCadastro(String idElemento) {
		return DriverFactory.getDriver().findElement(By.id(idElemento)).findElement(By.tagName("span")).getText();
	}
	
	public String obterTextoCampo(String idElemento) {
		return obterTextoCampo(By.id(idElemento));
	}
	
	public String obterTextoCampo(By idElemento) {
		return DriverFactory.getDriver().findElement(idElemento).getAttribute("value");
	}

	public String obterTexto(String idElemento) {
		return obterTexto(By.id(idElemento));
	}
	
	public String obterTexto(By idElemento) {
		return DriverFactory.getDriver().findElement(idElemento).getText();
	}
	
	public String obterTexto(By idElemento1, By idElemento2) {
		return DriverFactory.getDriver().findElement(idElemento1).findElement(idElemento2).getText();
	}

	public String obterTextoByXPath(String idElemento) {
		return DriverFactory.getDriver().findElement(By.xpath(idElemento)).getText();
	}
	
	
	public String obterSelecaoComboBox(String idElemento) {
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		return selecao.getFirstSelectedOption().getText();
	}

	public int obterNumeroOpcoesDisponiveisCombobox(String idElemento) {
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		List <WebElement> lista = selecao.getOptions();
		return lista.size();
	}
	
	public int obterNumeroOpcoesDisponiveisComboBoxMultiplasEscolhas (String idElemento) {
		return obterNumeroOpcoesDisponiveisCombobox(idElemento);
	}
	
	public int obterNumeroOpcoesSelecionadasComboBoxMultiplasEscolhas(String idElemento) {

		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		List <WebElement> lista = selecao.getAllSelectedOptions();
		return lista.size();

	}

	public boolean verificarOpcaoExistenteComboBox(String idElemento, String texto) {

		boolean encontrou = false;
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		for (WebElement iteracao:selecao.getOptions()) {
			if (iteracao.getText().equals(texto)) {
				encontrou = true;
				break;
			}
		}
		return encontrou;
	} 
	
	public List<String> verificarOpcaoSelecionadaComboBoxMultiplasEscolhas(String idElemento) {
		
		List<String> valores = new ArrayList<String>();
		Select selecao = new Select (DriverFactory.getDriver().findElement(By.id(idElemento)));
		
		for (WebElement iteracao:selecao.getAllSelectedOptions())
			valores.add(iteracao.getText());
		
		return valores;
	} 

	
	public Object interagirJavaScript(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	   //js.executeScript("alert('testando js via selenium')");
	   //js.executeScript("document.getElementById('elementosForm:nome').value = 'Teste Java Script'");
		//WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		return js.executeScript(cmd,param);
	}
	
	public void interagirScroll(String idElemento) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(idElemento));
		interagirJavaScript("window.scrollBy(0, arguments[0])",element.getLocation().y);
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[@id='"+idTabela+"']"));
		int idColuna = retornarIndiceColuna(colunaBusca, tabela);
		int idLinha  = retornarIndiceLinha(valor, tabela, idColuna);
		int idColunaBotao = retornarIndiceColuna(colunaBotao, tabela);
		tabela.findElement(By.xpath(".//tr["+ idLinha +"]/td["+ idColunaBotao+"]//input")).click();
	}
	
		
	public int retornarIndiceLinha(String linhaBusca, WebElement tabela, int idColuna) {
			List <WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
			
			int idLinha = -1;
			
			for (int i=0; i< linhas.size(); ++i) {
				if (linhas.get(i).getText().equals(linhaBusca)) {
					idLinha=i+1;
					break;
				}   
			}
			
			return idLinha;
	}	
		
	public int retornarIndiceColuna(String colunaBusca, WebElement tabela) {
		List <WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		
		int idColuna = -1;
		
		for (int i=0; i< colunas.size(); ++i) {
			if (colunas.get(i).getText().equals(colunaBusca)) {
				idColuna=i+1;
				break;
			}   
		}
		
		return idColuna;
	}
	
	public String getTituloTela() {
		return DriverFactory.getDriver().getTitle();
	}

}
