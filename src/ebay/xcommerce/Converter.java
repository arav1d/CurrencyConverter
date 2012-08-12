package ebay.xcommerce;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;




/**
 * @author Administrator
 *
 */
public class Converter
{

public Converter()
{		// TODO Auto-generated constructor stub
}
	

public static double getConversionFactor(String fromCountry,String toCountry)
{
try 
{
double conversionFactor=0.0;	
String tempLine="",from="",to="";
boolean flg1=false;boolean flg2=false;
String url1="https://raw.github.com/currencybot/open-exchange-rates/master/latest.json";
URL url=new URL(url1);
BufferedReader ratesReader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		while ((tempLine = ratesReader.readLine()) != null) {
			if(tempLine.contains(toCountry))
			{
				String temp[]=tempLine.split(" |,");				
				to=temp[1];
				flg1=true;
			}
			else if(tempLine.contains(fromCountry))
			{
				String temp[]=tempLine.split(" |,");				
				from=temp[1];
				flg2=true;
			}
			if(flg1&&flg2)
			{			
			conversionFactor=Double.parseDouble(to)/Double.parseDouble(from);
			flg1=false;flg2=false;
			return conversionFactor;
			}
		}
		
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
		
	}

	/*
	 * replace
	 * String url1="file:///C:/Users/Administrator/Downloads/latest.json";
	 * with
	 * String url1="https://raw.github.com/currencybot/open-exchange-rates/master/latest.json";
	 * */
	
	public static double getConversionFactor(String toCountry)
	{
		try {
		if(toCountry.equals("USD"))
			return 1;
		String tempLine="";
		String url1="https://raw.github.com/currencybot/open-exchange-rates/master/latest.json";
		URL url=new URL(url1);
		BufferedReader ratesReader = new BufferedReader(new InputStreamReader(url.openStream()));
		while ((tempLine = ratesReader.readLine()) != null) {
			if(tempLine.contains(toCountry))
			{
				String temp[]=tempLine.split(" |,");
				return Double.parseDouble(temp[1]);
			}
			
		}		
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String ip=null;
		String toCurrencyCode=null;
		System.out.println("Enter the ip address (ex:117.193.178.8) :\n");
		Scanner in=new Scanner(System.in);
		ip=in.nextLine();
		
		long startTime = System.nanoTime();//start of the runtime of the java program
		toCurrencyCode=CountryIP.getCountry(ip); //getting the currency code using IP
		long endTime = System.nanoTime();//end of runtime of java program
		System.out.println("Took "+(endTime - startTime) + " ns to find the country's currency code alone");
		
		
		System.out.println("For the currency code "+toCurrencyCode+" the conversion factor is "+getConversionFactor(toCurrencyCode));
		endTime = System.nanoTime();//end of runtime of java program
		System.out.println("Took "+(endTime - startTime) + " ns total time to find the country's currency code and determine its value together");
		
		startTime = System.nanoTime();//start time for currency-currency conversion
		System.out.println("From PKR to INR = "+getConversionFactor("PKR","INR"));
		endTime = System.nanoTime();//end of runtime of java program
		System.out.println("Took "+(endTime - startTime) + " ns to return the conversion factor for currency to currency conversion");
		

	}

}
