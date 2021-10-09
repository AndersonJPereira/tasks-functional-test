package br.assets;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Properties {

	public enum Browsers {
		CHROME, FIREFOX, IE
	}

	public enum TipoExecucao {
		LOCAL, GRID, CLOUD
	}

	public static Browsers BROWSER = Browsers.CHROME;
	public static TipoExecucao TIPOEXECUCAO = TipoExecucao.GRID;
	public static String LOCALHOST;

	// declarar variavel com possibilidade de lancar excecao
	static {
		try {
			LOCALHOST = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}