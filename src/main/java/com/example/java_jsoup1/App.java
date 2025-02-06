package com.example.java_jsoup1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.File;
import java.util.List;
public class App 
{
    public static void main( String[] args )
    {
    	JSONObject json = new JSONObject();
    	JSONArray array = new JSONArray();
    	JSONObject item = new JSONObject();   
    	
    	String url1 = "https://inc.moci.gov.kw/INC/WebPages/ExtractorLicnDataQR.aspx?l=Ap0Znq1v7DjXuFZjBrxH5w==";
    	String url2 = "https://inc.moci.gov.kw/INC/WebPages/ExtractorLicnDataQR.aspx?l=778HInexX/a/d+Keh5zGiw==";
    	String url3 = "https://inc.moci.gov.kw/INC/WebPages/ExtractorLicnDataQR.aspx?l=RdLaduvvXDbmvuCnjRhNQg==";

    	//System.setProperty("webdriver.chrome.driver","C:\\Users\\vinay\\Downloads\\chromedriver_win32\\chromedriver.exe");
    	// setting up the web driver
    	//WebDriver driver = new ChromeDriver();
    	
    	
    	System.setProperty("webdriver.edge.driver","C:\\Users\\vinay\\Downloads\\edgedriver_win32_132\\msedgedriver.exe");
    	WebDriver driver = new EdgeDriver();
    	
    	// navigating to the website
    	driver.get(url3);
    	List<WebElement> elements = driver.findElements(By.tagName("input"));
    	String tmp = "";
    	for (WebElement element : elements) {
    		tmp = element.getAttribute("id");
    		if(tmp != "" && tmp != null &&  !"__VIEWSTATEGENERATOR".equalsIgnoreCase(tmp) && !"__VIEWSTATE".equalsIgnoreCase(tmp) && !"__EVENTVALIDATION".equalsIgnoreCase(tmp))
    		{
    			item.put(removeJunk(tmp), element.getAttribute("value"));
    		}
    		tmp = "";    		
    	}
    	List<WebElement> telements = driver.findElements(By.tagName("table"));
    	for (WebElement element : telements) {
    		tmp = element.getAttribute("id");
    		if("COMPANY_DTL_gvOwners".equalsIgnoreCase(tmp))
    		{
    			List<WebElement> tdelements = element.findElements(By.tagName("td"));
    			int k = 0;
    			JSONArray array2 = new JSONArray();
    			JSONObject partner = new JSONObject(); 
    			for (WebElement tdelement : tdelements) {
    				k++;
    				 
    				if(k==1)
    				{
    					partner.put("m", tdelement.getText());
    				}
    				if(k==2)
    				{
    					partner.put("p_rname", tdelement.getText());
    				}
    				if(k==3)
    				{
    					partner.put("p_nationality", tdelement.getText());
    				}
    				if(k==4)
    				{
    					partner.put("p_share", tdelement.getText());
    				}
    				if(k==5)
    				{
    					partner.put("p_attribute", tdelement.getText());
    				}
    				if(k==6)
    				{
    					partner.put("p_gen_powers", tdelement.getText());
    				}
    				if(k==7)
    				{
    					partner.put("p_open_nbk_acc", tdelement.getText());
    				}
    				if(k==8)
    				{
    					partner.put("p_rt_of_mgmt", tdelement.getText());
    					array2.put(partner); 
    					k=0;
    					partner = new JSONObject();
    				}
    				  				
    				
    			}  
    			item.put("partners", array2);
    		}
    		   	
    	}
    	array.put(item);
    	json.put("data", array); 

    	// closing the web driver
    	driver.close();
    	System.out.print(json.toString());
    }
    
    public static String removeJunk(String val){
	    String _val = val;
	    if(val.contains("txtCOMPANY_NAME_BRANCHES"))
	    {
	    	_val = "txtCOMPANY_NAME_BRANCHES";
	    }
	    else if(val.contains("txtLICN_CIVIL_ID_BRANCHES"))
	    {
	    	_val = "txtLICN_CIVIL_ID_BRANCHES";
	    }
	    else if(val.contains("txtLICN_COMM_EDATE_BRANCHES"))
	    {
	    	_val = "txtLICN_COMM_EDATE_BRANCHES";
	    }
	    else if(val.contains("txtCOMM_BOOK_NO_BRANCHES"))
	    {
	    	_val = "txtCOMM_BOOK_NO_BRANCHES";
	    }
	    else if(val.contains("txtLICN_COMM_NO_BRANCHES"))
	    {
	    	_val = "txtLICN_COMM_NO_BRANCHES";
	    }
	    else
	    {
	    	
	    }
	    return _val;
    }
}
