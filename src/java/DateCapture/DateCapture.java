import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver

/* This is a simple sample programme to test Date picker Functionality
 * As Now a days we have many sites with Date pipcker widget - its very 
 * hard to select particular date ( future and past both )using selenium 
 * web-driver. 
 * So I have just wrote a programe to put/fill the date field with date
 * using the selenium Javascript Executor to automate date selection
 * functionality.
 */

public class DateCapture {
	
	public void pickDate(WebElement webElem, String dateFrom, WebDriver webDrv,String xpath){ // Function Date pick
		JavascriptExecutor javaExc = (JavascriptExecutor)webDrv;	
		String tagName = webElem.getTagName(); // capturing tagname of an element
		
		// Checking whether the Date input field ( Element) has an ID or not
		if(!webElem.getAttribute("id").trim().equals("")){	 
			try{
				//Checking if the date input field is under span tag or not, if not then get Element Id and fill the date input field with particular date.
				if((!tagName.trim().equalsIgnoreCase("span"))){	
					javaExc.executeScript("document.getElementById('"+webElem.getAttribute("Id")+"').value = '"+dateFrom+"';");	
				}
				else if(tagName.trim().equalsIgnoreCase("span")){ 	// if the date input field is under Span tag			
					webElem.click();
					javaExc.executeScript("document.getElementById('"+webElem.getAttribute("Id")+"').innerHTML = '"+dateFrom+"';");
				}
			}catch(Exception e){
				System.out.println("Throws Exception" + e.getCause()+ "\n"); // Catch the excception.			
				
			}
		}else{ // if date input field (Element) has an ID.
			try{		
				//Checking if the date input field is span tag or not if not then get Element Id and fill the date with particular date.
				if((!tagName.trim().equalsIgnoreCase("span"))){
					javaExc.executeScript("document.getElementsByClassName('"+webElem.getAttribute("class")+"')[0].removeAttribute('readonly','readonly');");
					javaExc.executeScript("document.getElementsByClassName('"+webElem.getAttribute("class")+"')[0].value= '"+dateFrom+"';");
				}else if(tagName.trim().equalsIgnoreCase("span")){ // if the date input field is under Span tag
					webElem.click();
					javaExc.executeScript("document.getElementsByClassName('"+webElem.getAttribute("class")+"')[0].innerHTML = '"+dateFrom+"';");
				}	
						
				}					
			catch(Exception e){
				System.out.println("Throws Exception" + e.getCause()+ "\n"); // Catch the excception.
			}
		}
	}
	
  public static void main(String[] args) throws Exception {
		try {
			
			System.setProperty("webdriver.chrome.driver","C:\\src\\chromedriver.exe");// path of webdriver..
			WebDriver webDriver = new ChromeDriver();
			DateCapture dc = new DateCapture();		
		        webDriver.get("http://spicejet.com/");	// Site address which needs to be tested using selenium			
			String date= "20/06";	// Date to be placed in Date picker text field 
			String xPath = "//*[@id='ctl00_mainContent_view_date1']";
			WebElement we = webDriver.findElement(By.xpath("//*[@id='ctl00_mainContent_view_date1']")); //find element using xpath of the Date input field							
			dc.pickDate(we,date,webDriver, xPath); // calling pickDate Function 
			Thread.sleep(3000); // wait for site to load completely...
			
		}
		catch(Exception e ){
			System.out.println("Something Went Wrong : -" + e.getMessage());
			e.printStackTrace();
		}
	}

}

