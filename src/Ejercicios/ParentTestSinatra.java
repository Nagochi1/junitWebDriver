package Ejercicios;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class ParentTestSinatra {
	
	protected WebDriver driver;
				
		String usuario="frank";
		String pwd="sinatra";
		String titulo="Moon River";
		int duracion= 120;
		String fecha="05/19/2018";
		String letra="Moon river, wider than a mile I'm crossin' you in style some day Old dream maker, you heartbreaker";

		
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\test_automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.get("http://songs-by-sinatra.herokuapp.com/");	
	}
	
	protected void logIn(String user, String pass) {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.id("username")).sendKeys(usuario);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
					
		if (driver.findElement(By.xpath("//a[@href='/logout']")).isDisplayed())
			   System.out.println("Login exitoso");
			  else {
			   System.out.println("Login no exitoso");
			   System.exit(-1);
		}
	}
	
	protected void agregarCancion(String titulo1, int duracion1, String fecha1, String letra1) {
		driver.findElement(By.xpath("//a[@href='/songs']")).click();
		driver.findElement(By.xpath("//a[@href='/songs/new']")).click();
		driver.findElement(By.id("title")).sendKeys(titulo);
		driver.findElement(By.id("length")).sendKeys(duracion + "");//se castea el campo
		driver.findElement(By.id("released_on")).sendKeys(fecha);
		driver.findElement(By.id("lyrics")).sendKeys(letra);
		driver.findElement(By.xpath("//input[@value='Save Song']")).click();
			
	}
	
	protected void existeCancion(String titulo1, int duracion1, String fecha1, String letra1) {
		
		WebElement tituloCancionLbl = driver.findElement(By.xpath("//h1[text()='"+titulo+"']"));
		if(tituloCancionLbl.isDisplayed()) {
			System.out.println("La canción "+ tituloCancionLbl.getText() +" existe");
		}
		
		WebElement fechaCancionLbl = driver.findElement(By.xpath("//p[contains(text(),'Release Date')]"));
		if(fechaCancionLbl.isDisplayed()) {
			System.out.println("La fecha de la canción agregada "+ fechaCancionLbl.getText() +" existe");
			//05/19/2018 
			String date = transformDate(fecha);
			if(fechaCancionLbl.getText().contains(date)) {
				System.out.println("La canción muestra la fecha correcta");
			}else
				{
					System.out.println("La canción muestra la fecha incorrecta");
				}
		}
		
		WebElement duracionCancionLbl = driver.findElement(By.xpath("//p[contains(text(),'Length')]"));
		if(duracionCancionLbl.isDisplayed()) {
			System.out.println("La duración de la canción agregada "+ duracionCancionLbl.getText() +" existe");}
			String duracionTransformada = transformarDuracion(duracion);
			if(duracionCancionLbl.getText().contains(duracionTransformada)) {
				System.out.println("La duración que muestra es la correcta");
			}else
				{
					System.out.println("La duración que muestra no es la correcta");
				}
				
		
		
		WebElement letraCancionLbl = driver.findElement(By.xpath("//pre"));
		if(letraCancionLbl.isDisplayed()) {
			System.out.println("La letra de la canción agregada "+ letraCancionLbl.getText() +" existe");
		}
		
		WebElement borrarCancionBtn = driver.findElement(By.xpath("//input[@value='delete this song']"));
		if(borrarCancionBtn.isDisplayed()) {
			System.out.println("El botón Delete this song existe");
		}
		
		WebElement editarCancionLnk = driver.findElement(By.xpath("//a[text()='edit this song']"));
		if(editarCancionLnk.isDisplayed()) {
			System.out.println("El link Edit this song existe");
		}
	}
	
	protected static String transformarDuracion(int duracion) {
		String duracionTransformada = "";
		int numMinutos = duracion/60;
		int numSegundos = duracion%60;
		duracionTransformada = numMinutos + " minutos " + numSegundos + " segundos";
		return duracionTransformada;
	}

	
	protected static String transformDate(String date) {
		//05/19/2018
		String[] elementosFecha = date.split("/");
		String fecha = elementosFecha[1] +" ";
		String[] meses = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Nov", "Dec"};
		int mes = Integer.parseInt(elementosFecha[0]);
		fecha = fecha + meses[mes] + " ";
		fecha = fecha + elementosFecha[2];
					
		return fecha;
	}
	
	
	protected void eliminarCancion(String titulo1) {
		driver.findElement(By.xpath("//a[@href='/songs']")).click();
		driver.findElement(By.xpath("//a[@href='/songs/70']")).click();
		driver.findElement(By.xpath("//input[@value='delete this song']")).click();
	
	}

		
	@After
	public void tearDown() {
		//driver.quit();
	}
}