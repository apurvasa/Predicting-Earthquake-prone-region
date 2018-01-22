/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Apurva Sawant
 */
public class Particle {
   // private int[] data=new int[4]; //x, y, f1, f2
    private double x=0;
    private double y=0;
    private double bestCost=0;
    private double bestX = 0;
    private double bestY = 0;
    private double mVelocityX = 0.0;
    private double mVelocityY = 0.0;
    private double cost;
    private boolean active=true;
    
    
     static int maxX=500;
     static int maxY=500;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

     
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBestCost() {
        return bestCost;
    }

    public void setBestCost(double bestCost) {
        this.bestCost = bestCost;
    }

    public double getBestX() {
        return bestX;
    }

    public void setBestX(double bestX) {
        this.bestX = bestX;
    }
    
    public double getBestY() {
        return bestX;
    }

    public void setBestY(double bestY) {
        this.bestY = bestY;
    }

    public double getmVelocityX() {
        return mVelocityX;
    }

    public void setmVelocityX(double mVelocity) {
        this.mVelocityX = mVelocity;
    }

    public double getmVelocityY() {
        return mVelocityY;
    }

    public void setmVelocityY(double mVelocityY) {
        this.mVelocityY = mVelocityY;
    }

        
}
