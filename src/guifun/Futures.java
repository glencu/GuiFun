/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guifun;

/**
 *
 * @author Bobasek
 */
public class Futures {
    
    static double theoreticalContractPrice(double baseInstr , double riskFreeIR, int n)
    {   
        double result = -1.0;
        
        result = baseInstr * (1+riskFreeIR * (n/365));
        return result;
    }
    
}
