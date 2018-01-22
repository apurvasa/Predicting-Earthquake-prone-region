/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.Random;

/**
 *
 * @author Apurva Sawant
 */
public class CostFunction {
    
    double temprature;
    double stress;
    double pressure;
    static double mint=40;
    static double mins=40;
    static double minp=50;
    static double maxt=70;
    static double maxs=80;
    static double maxp=75;
    public static double costFunction(){
        double cost=0.3*getRandomNumber(mint, maxt) + 0.6*getRandomNumber(mins, maxs)+ 0.3*getRandomNumber(minp, minp);
        return cost;
    }
    private static double getRandomNumber(double low, double high)
    {
        return (int)((high - low) * new Random().nextDouble() + low);
    }
}
