package br.assets;

public class Properties {
	
	public enum Browsers{
		CHROME,
		FIREFOX, 
		IE
	}
	
	public enum TipoExecucao{
		LOCAL,
		GRID,
		CLOUD
	}
	
	public static Browsers BROWSER = Browsers.CHROME;
	public static TipoExecucao TIPOEXECUCAO = TipoExecucao.GRID;
	public static String LOCALHOST = "http://192.168.1.102";
}
