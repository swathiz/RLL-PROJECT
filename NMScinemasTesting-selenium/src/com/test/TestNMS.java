package com.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class TestNMS {
	private static String url = "http://localhost:4200";

	public static void main(String[] args) throws InterruptedException, UnhandledAlertException, NoAlertPresentException {

		WebDriver webdriver = null;

		System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver_win32\\chromedriver.exe");
		webdriver = new ChromeDriver();

//		adminLogin(webdriver,"adminDashboard","admin","password"); 
//		
//		updateMovie(webdriver);
//		addMovie(webdriver, "adminDashboard"); 
//		deleteMovie(webdriver,"adminDashboard"); 
//
//		ManageUser(webdriver,"manageCustomer");
//		managePurchase(webdriver,"managePurchase");
		
		register(webdriver); 
		
		search(webdriver); 
		filter(webdriver); 
		
		addtocart(webdriver); 
		
//		emptyCart(webdriver); 
    	checkout(webdriver); 
	
		orderSummery(webdriver,"ordersummary","xyz@gmail.com","123"); 
		printOrder(webdriver,"orderSummary"); 
		bookMore(webdriver,"orderSummary"); 
		
//		webdriver.quit();
	}

	private static void updateMovie(WebDriver webdriver) throws InterruptedException, UnhandledAlertException, NoAlertPresentException{
//			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			webdriver.manage().timeouts ().implicitlyWait (Duration.ofMinutes (5));
			webdriver.manage().window().maximize();
			webdriver.findElement(By.xpath("//tbody/tr[1]/td[10]/button[1]")).click();
			Thread.sleep(1000);
			webdriver.findElement(By.xpath("//input[@id='actualPrice']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"400");
			Thread.sleep(1000);
			webdriver.findElement(By.xpath("(//button[@class='btn btn-primary ng-star-inserted'])[1]")).click();
			webdriver.switchTo().alert().accept();
			System.out.println("updated successfully");
	}

	@SuppressWarnings("deprecation")
	private static void adminLogin(WebDriver webdriver, String route, String adminuname, String adminpswrd) throws InterruptedException {
	webdriver.get(url + "/" + route);
	webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	webdriver.manage().window().maximize();
	webdriver.findElement(By.xpath("//input[@name='username']")).sendKeys(adminuname);
	Thread.sleep(1000);
	webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys(adminpswrd);
	Thread.sleep(1000);
	webdriver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(2000);
	System.out.println("admin login successfull");
	
	}
	
	private static void ManageUser(WebDriver webdriver, String route) throws InterruptedException,UnhandledAlertException, NoAlertPresentException{ 
		webdriver.get(url + "/" + route);
		webdriver.manage (). window () .maximize ();
//		webdriver.manage().timeouts ().implicitlyWait (Duration.ofMinutes (5));
		webdriver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		webdriver.findElement(By.xpath("//button[@type='submit']")).click();
		webdriver.findElement(By.cssSelector("button[routerLink='/manageCustomer']")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.cssSelector("button[class='btn btn-primary btn-sm']")).click();//view active orders
		Thread.sleep(2000);
		webdriver.findElement(By.cssSelector("button[class='btn btn-sm btn-danger']")).click(); //close
		Thread.sleep(2000);
		webdriver.findElement(By.name("searchKey")).sendKeys("xyz");
		Thread.sleep(1000);
		webdriver.findElement(By.cssSelector("button[class='btn btn-primary btn-sm']")).click();
		Thread.sleep(1000);
		webdriver.findElement(By.cssSelector("button[class='btn btn-sm btn-danger']")).click();
		System.out.println("ManageUser successful");
//		webdriver.findElement(By.cssSelector("button[class='btn btn-danger btn-sm']")).click();//delete order	
//		webdriver.switchTo().alert().accept();
//		System.out.println(webdriver.switchTo().alert().getText());
		}

	public static void orderSummery(WebDriver webdriver, String route,String username, String Password) throws InterruptedException,UnhandledAlertException, NoAlertPresentException{

		webdriver.get(url + "/" + route);
		webdriver.manage().window().maximize();

		webdriver.findElement(By.xpath("//button[@id='custLogin']")).click(); 
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-header[1]/header[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]")).sendKeys(username);
		webdriver.findElement(By.xpath("(//input[@id='defaultForm-pass'])[1]")).sendKeys(Password);
		webdriver.findElement(By.xpath("(//button[@id='loginSubmit'])[1]")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//button[normalize-space()='Previous Orders']")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("(//button[normalize-space()='Close'])[1]")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-header[1]/header[1]/div[1]/button[1]")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//button[normalize-space()='Previous Orders']")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("(//button[normalize-space()='Close'])[1]")).click();
		Thread.sleep(2000);
		System.out.println("orderSummary successful");

	}

	public static void printOrder(WebDriver webdriver, String route){
		webdriver.get(url + "/" + route);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-order-summary[1]/div[1]/div[1]/div[1]/div[1]/div[2]/button[1]")).click();	
		System.out.println("ManageUser successful");
		}

	public static void bookMore(WebDriver webdriver, String route){
		webdriver.get(url + "/" + route);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.xpath("(//button[normalize-space()='Book More'])[1]")).click();
		System.out.println("print order successfull");
	}
	
	public static void managePurchase(WebDriver webdriver, String route) throws InterruptedException,UnhandledAlertException, NoAlertPresentException{
		webdriver.get(url + "/" + route);
//		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		webdriver.findElement(By.xpath("//button[@type='submit']")).click();
		webdriver.findElement(By.cssSelector("button[routerLink='/managePurchase']")).click();
		Thread.sleep(2000);
//		webdriver.findElement(By.xpath("//button[@class='btn bthHead active']")).click();
		webdriver.findElement(By.xpath("//button[normalize-space()='Manage Purchases']")).click();
		webdriver.findElement(By.xpath("//input[@id='searchKey']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"RRR");
		System.out.println("managePurchase successfull");
}
	
	public static void checkout(WebDriver driver) throws InterruptedException,UnhandledAlertException, NoAlertPresentException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.xpath("(//button[normalize-space()='CheckOut'])[1]")).click();
		driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys("MAX");
		driver.findElement(By.xpath("(//input[@id='cr_no'])[1]")).sendKeys("1234 0000 9999 4567");
		driver.findElement(By.name("exp")).sendKeys("09/28");
		driver.findElement(By.name("cvv")).sendKeys("800");
		Thread.sleep(3000);
		driver.findElement(By.id("pay")).click();
		System.out.println("checkout successfull");
	}
	
	
	public static void emptyCart(WebDriver webdriver) throws InterruptedException {
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("(//button[@id='del_item'])[1]")).click();
		System.out.println("It will show the shop more page");
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("(//button[normalize-space()='Shop Now'])[1]")).click();
	}

	public static void  register(WebDriver webdriver) throws InterruptedException,UnhandledAlertException, NoAlertPresentException {
		webdriver.get(url);
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.id("custRegister")).click();
		webdriver.findElement(By.id("orangeForm-Remail")).sendKeys("adam@gmail.com");
		webdriver.findElement(By.id("orangeForm-Rpass")).sendKeys("adam123");
		webdriver.findElement(By.id("orangeForm-name")).sendKeys("Adam Smith");
		webdriver.findElement(By.id("orangeForm-contact")).sendKeys("9765452677");
		webdriver.findElement(By.id("orangeForm-address")).sendKeys("abcdef");
		Thread.sleep(3000);
		webdriver.findElement(By.xpath("//*[@id=\"registerModal\"]/div/div/div[2]/button")).click();
		Thread.sleep(3000);
		webdriver.findElement(By.xpath("/html/body/app-root/app-header/header/div[1]/div/div[2]/button[2]")).click(); //logout
		System.out.println("register successfull");
	}
	
	private static void search(WebDriver webdriver) {
		webdriver.get(url);
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.xpath("//input[@placeholder='Search Product']")).sendKeys("RRR");
//		webdriver.findElement(By.xpath("//input[@placeholder='Search Product']")).sendKeys("Drama");
		System.out.println("search tested successfully");
	}
	
	public static void filter(WebDriver webdriver) {
		webdriver.get(url);
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.findElement(By.xpath("//a[@id='dropdownMenuLink']")).click();
//		webdriver.findElement(By.xpath("//a[@id='allProducts']")).click();
		webdriver.findElement(By.xpath("//a[@id='chinese']")).click();
//		webdriver.findElement(By.xpath("//a[@id='indian']")).click();
//		webdriver.findElement(By.xpath("//a[@id='Mexican']")).click();
//		webdriver.findElement(By.xpath("//a[@id='Italian']")).click();
		System.out.println("filter tested successfully");
	}
	public static void addtocart(WebDriver webdriver) throws InterruptedException,UnhandledAlertException, NoAlertPresentException{
		webdriver.get(url);
		webdriver.manage().window().maximize();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(By.xpath("//button[@id='custLogin']")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//input[@name='email']")).sendKeys("xyz@gmail.com");
		webdriver.findElement(By.xpath("//input[@name='password']")).sendKeys("123");
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//div[@class='row']//div[1]//button[1]")).click();
		Thread.sleep(2000);
		webdriver.findElement(By.xpath("//button[@class='btn cart']")).click();
//		webdriver.findElement(By.xpath("//button[normalize-space()='Previous Orders']")).click();
//		Thread.sleep(2000);
//		webdriver.findElement(By.xpath("//button[normalize-space()='Close']")).click();
//		Thread.sleep(2000);
//		webdriver.findElement(By.xpath("//button[normalize-space()='Logout']")).click();
		System.out.println("addtocart tested successfully");
	}
	
	public static void addMovie(WebDriver driver, String route) throws InterruptedException,UnhandledAlertException, NoAlertPresentException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url + "/" + route);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("(//button[normalize-space()='Add movies'])[1]")).click();
		driver.findElement(By.id("name")).sendKeys("ponniyine selvan");
		driver.findElement(By.id("desc")).sendKeys("Kalki Krishnamurthy's 1955 novel, Ponniyin Selvan, Vikram, Aishwarya Rai Bachchan, Jayam Ravi, Karthi, Trisha, Jayaram, Aishwarya Lekshmi, Sobhita Dhulipala, Prabhu, R. Sarathkumar, Vikram Prabhu, Prakash Raj, Rahman, R. Parthiban, A. R. Rahman");
		driver.findElement(By.id("duration")).sendKeys("2hr:50min");
		Thread.sleep(1000);
		driver.findElement(By.id("category")).sendKeys("historical fiction");
//		driver.findElement(By.xpath("//*[@id='category']")).selectByValue("Historical");
		driver.findElement(By.id("actualPrice")).sendKeys("200");
		driver.findElement(By.id("discount")).sendKeys("0");
		driver.findElement(By.id("yes")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("imagepath")).sendKeys("./assets/images/ps.jpg");
		driver.findElement(By.xpath("(//button[normalize-space()='Add'])[1]")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		System.out.println("movie added successfully");		
	} 
	
	private static void deleteMovie(WebDriver driver, String route) throws InterruptedException,UnhandledAlertException, NoAlertPresentException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url + "/" + route);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/app-admin-dashboard[1]/div[2]/table[1]/tbody[1]/tr[1]/td[10]/button[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

		System.out.println("movie deleted successfully");
	
	}



}

