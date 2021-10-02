package br.assets;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.assets.Properties.TipoExecucao;

public class DriverFactory {

	//private static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {

		WebDriver driver = null;	
		Capabilities cap = null;
		
		
		if (Properties.TIPOEXECUCAO == TipoExecucao.LOCAL) {
			switch (Properties.BROWSER) {
			case FIREFOX: driver = new FirefoxDriver(); break; 
			case CHROME:  driver = new ChromeDriver(); break;
			case IE: driver = new InternetExplorerDriver();
			}
		}
		
		
		if (Properties.TIPOEXECUCAO == TipoExecucao.GRID) {
			switch (Properties.BROWSER) {
			case FIREFOX: cap = DesiredCapabilities.firefox(); break;
			case CHROME:  cap = DesiredCapabilities.chrome(); break;
			case IE: cap = DesiredCapabilities.internetExplorer(); 
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://"+Properties.LOCALHOST+":4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				System.err.println ("Falha na conex�o com o GRID");
				e.printStackTrace();
			}
		}
		
		MutableCapabilities sauceOptions = new MutableCapabilities();

		
		if (Properties.TIPOEXECUCAO == TipoExecucao.CLOUD) {
			switch (Properties.BROWSER) {
			case FIREFOX: cap = DesiredCapabilities.firefox(); break;
			case CHROME:  cap = DesiredCapabilities.chrome(); break;
			case IE: cap = DesiredCapabilities.internetExplorer();
				((MutableCapabilities) cap).setCapability("platformName", "Windows 10");
				((MutableCapabilities) cap).setCapability("browserVersion", "latest");
				((MutableCapabilities) cap).setCapability("sauce:options", sauceOptions);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("https://AndersonPereira:2d634f49-c8a4-409b-a1cb-cf20e5f1ea03@ondemand.us-west-1.saucelabs.com:443/wd/hub"),cap);
			} catch (MalformedURLException e) {
				System.err.println ("Falha na conex�o com o CLOUD");
				e.printStackTrace();
			}
		}

		driver.manage().window().maximize();
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		
		if (driver != null) {
			driver.quit();
		    driver = null;
		}
		
		if (threadDriver != null)
			threadDriver.remove();
	}

}
