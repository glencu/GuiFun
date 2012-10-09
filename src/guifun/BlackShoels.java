/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guifun;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author akg023
 */
public class BlackShoels {
    
    
   
    static double callPrice(double S, double K, double T , double r, double sigma , double q)
    {
        double result = 0.0;
        double d1,d2=0;
        NormalDistribution nd = new NormalDistribution(0, 1);     
        d1 = (Math.log(S/K) + ( (r-q) + (Math.pow(sigma ,2)/2))*T)/(sigma*(Math.pow(T, 0.5)));
        d2 = d1 - sigma* Math.pow(T, 0.5);
        result = S*Math.exp(-q*T)* nd.cumulativeProbability( d1 ) - K *Math.exp(-r*T)* nd.cumulativeProbability( d2 );
 
        
        return result;
    }
   
    static double putPrice(double S, double K, double T , double r, double sigma , double q)
    {
        double result = 0.0;
        double d1,d2=0;
        NormalDistribution nd = new NormalDistribution(0, 1);   
        d1 = (Math.log(S/K) + ( (r-q) + (Math.pow(sigma ,2)/2))*T)/(sigma*(Math.pow(T, 0.5)));
        d2 = d1 - sigma* Math.pow(T, 0.5);
        result = -1*S*Math.exp(-q*T)* nd.cumulativeProbability( -1 * d1 ) + K *Math.exp(-r*T)* nd.cumulativeProbability( -1 * d2 );
           
        return result;
    }
     
    static double putcallParity(double S, double K, double T , double r, double sigma , double q)
    {
      double result = 0.0;
      
      result = BlackShoels.callPrice(S, K, T, r, sigma, q) - S*Math.exp(-1*q*T)-K*Math.exp(-r*T);
      
      return result;
      
    }
    
    static double getTimeFractionInDays(Calendar startDate , int month, int year)
    {
       double result=0.0;
       long tmp,tmp1,tmp2;
       Calendar cal = Calendar.getInstance();
       Calendar thirdFriday = Calendar.getInstance();
      
     
       thirdFriday.set(GregorianCalendar.DAY_OF_WEEK,Calendar.FRIDAY);
       thirdFriday.set(GregorianCalendar.DAY_OF_WEEK_IN_MONTH, -2);
       thirdFriday.set(GregorianCalendar.MONTH,month-1);
       thirdFriday.set(GregorianCalendar.YEAR,year);
       System.out.println(thirdFriday);
       
       tmp1 = thirdFriday.getTimeInMillis();
       tmp2 = cal.getTimeInMillis();
       tmp = thirdFriday.getTimeInMillis() - cal.getTimeInMillis();
       result = tmp / (1000*60*60*24);
           
       return result/365;
    
    }
    
}


class MonthMapping{

   public static final Map<String, Integer> MY_MAP = createMap();

    private static Map<String, Integer> createMap() {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("C", Calendar.MARCH);
        result.put("F", Calendar.JUNE);
        result.put("I", Calendar.SEPTEMBER);
        result.put("L", Calendar.DECEMBER);
        
        result.put("O", Calendar.MARCH);
        result.put("R", Calendar.JUNE);
        result.put("U", Calendar.SEPTEMBER);
        result.put("X", Calendar.DECEMBER);
        
        
        return Collections.unmodifiableMap(result);
    }

}    


class StringOptionParser
{
  int strikePrice=-1;
  int month=-1;
  int year=-1;
  String optionType=null;
  
  public  StringOptionParser(String option)
  {
       optionType = new String(option.substring(0, 4));
       System.out.println(optionType);
       month = MonthMapping.MY_MAP.get(option.substring(4, 5));
       System.out.println(month);
       year = 2010 + Integer.parseInt(option.substring(5, 6));
       System.out.println(year);
       strikePrice = Integer.parseInt(option.substring(6, 9))*10;
       System.out.println(strikePrice);
  
  }



}