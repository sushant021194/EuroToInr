package home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.asm.Advice.Return;

public class EuroToInr {
	public static String flag;
	static WebDriver driver;
	static Double currentValue;
	public static void main(String[] args) throws InterruptedException 
	{

		// TODO Auto-generated method stub
		Double expectedValue=80.00;
		String driverpath="Common/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
		driver=new ChromeDriver();
		driver.get("https://transferwise.com/gb/currency-converter/eur-to-inr-rate");
		Double currentValue=Double.parseDouble(driver.findElement(By.id("cc-amount-to")).getAttribute("value"));
		System.out.println(currentValue);

		if(currentValue >= expectedValue)
		{ 	getFlag();
			if(flag.equals("True")) 
			{
				loginWay2Sms();
				sendSms();
			}
		}
		else
		{
			setFlag("True");
		}

		driver.close();	
	}
	public static void sendSms()
	{
		driver.findElement(By.xpath("//span[@class='d-table-cell']/input[@class='input-field']")).sendKeys("8291919985");
		driver.findElement(By.xpath("//textarea[@id='messageEng']")).sendKeys("Euro to Inr rate increased to "+currentValue);
		driver.findElement(By.id("sendButton")).click();
		System.out.println("sms sent");
		setFlag("False");
	}
	public static void loginWay2Sms() throws InterruptedException
	{
		driver.get("https://www.sms4india.com/send-sms");
		driver.findElement(By.id("mobileNo")).sendKeys("8291919985");
		driver.findElement(By.id("password")).sendKeys("1994");
		driver.findElement(By.xpath("//div[@class=\"mdw-ft align-right\"]/button[@class=\"btn-theme-sm btn-ls text-uppercase\"]")).click();
		System.out.println("login succesful");
		Thread.sleep(3000);
	}

	public static void getFlag()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("Common/flag.txt")); 

			String f;
			while ((f = br.readLine()) != null) 

				flag=f;
			System.out.println(flag); 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setFlag(String arg)
	{
		try {
			FileWriter writer = new FileWriter("Common/flag.txt", false);

			writer.write(arg);
			System.out.println("append complete");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


