import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/*Author : Archana Panchal 
 * This is a simple sample programme to test Date picker Functionality
 * Now a days we have many sites with Date pipcker widget - its very 
 * hard to select particular date ( future and past both )using selenium 
 * web-driver. 
 * So I have just wrote a programe to put/fill the date field with date
 * using the selenium Javascript Executor to automate date selection
 * functionality.
 */

public class DateCapture {
	
	public void pickDate(WebElement we1, String dt1, WebDriver wd,String xpath){ // Function Date pick
		JavascriptExecutor js = (JavascriptExecutor)wd;	
		String tagname = we1.getTagName(); // capturing tagname of an element
		
		if(!we1.getAttribute("id").trim().equals("")){	 // Checking whether the Date input field ( Element) has an ID or not
			try{
				//Checking if the date input field is under span tag or not if not then get Element Id and fill the date with particular date.
				if((!tagname.trim().equalsIgnoreCase("span"))){	
					js.executeScript("document.getElementById('"+we1.getAttribute("Id")+"').value = '"+dt1+"';");	
				}
				else if(tagname.trim().equalsIgnoreCase("span")){ 	// if the date input field is under Span tag			
					we1.click();
					js.executeScript("document.getElementById('"+we1.getAttribute("Id")+"').innerHTML = '"+dt1+"';");
				}
			}catch(Exception e){
				System.out.println("Throws Exception" + e.getCause()+ "\n"); // Catch the excception.
				
				
			}
		}else{ // if date input field (Element) has an ID.
			try{		
				//Checking if the date input field is span tag or not if not then get Element Id and fill the date with particular date.
				if((!tagname.trim().equalsIgnoreCase("span"))){
					js.executeScript("document.getElementsByClassName('"+we1.getAttribute("class")+"')[0].removeAttribute('readonly','readonly');");
					js.executeScript("document.getElementsByClassName('"+we1.getAttribute("class")+"')[0].value= '"+dt1+"';");
				}else if(tagname.trim().equalsIgnoreCase("span")){ // if the date input field is under Span tag
						we1.click();
						js.executeScript("document.getElementsByClassName('"+we1.getAttribute("class")+"')[0].innerHTML = '"+dt1+"';");
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
