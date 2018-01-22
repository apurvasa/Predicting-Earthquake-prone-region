/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;



import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;


public class PSO extends JPanel
{ private static final int KAPPA=1;
    private static final double PHIL=2.05;
    private static final double PHIL2=2.05;
    private static final double PHI=PHIL+PHIL2;
    private static final double CHI=2*KAPPA/Math.abs(2-PHI-Math.sqrt(Math.pow(PHI, 2)-4*PHI));
    private static final int MAX_EPOCHS = 800;
    private static final int MAX_PARTICLES = 30;
    private static final int V_MAX = 10;             // Maximum velocity change allowed. may change to inertia and coefficents
    private static final int V_MIN = 0;
    Particle gBest=null;
  static int width, height;
    private static ArrayList<Particle> particles = new ArrayList<Particle>();
    int delay=100;
 
 //   private static ArrayList<Particle> particles = new ArrayList<Particle>();
    private static int getRandomNumber(int low, int high)
    {
        return (int)((high - low) * new Random().nextDouble() + low);
    }
    
     public static void initialize()
    {
        for(int i = 0; i < MAX_PARTICLES; i++)
        {
            Particle newParticle = new Particle();
          
                newParticle.setX(getRandomNumber(100, Particle.maxX ));
                newParticle.setY(getRandomNumber(100, Particle.maxY ));
                newParticle.setCost(CostFunction.costFunction());
                newParticle.setBestX(0);
                newParticle.setBestY(0);
                newParticle.setBestCost(0);
            particles.add(newParticle);
        } // i
        
    }

//Create a timer with delay 1000 ms
private Timer timer = new Timer(delay, new TimerListener());


public PSO()
{
   
initialize();
timer.start();
}

private class TimerListener implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
   
        for(int i = 0; i < MAX_PARTICLES; i++)
                {
                    System.out.println("x:"+particles.get(i).getX()+"y:"+particles.get(i).getY());
                            
                } 
        Particle  gBest = particles.get(0);//should be minimum so 1st element
        Particle gBestTest = null;
         int epoch = 0;
             while(epoch < MAX_EPOCHS){
                gBestTest = maximum();
             //   System.out.println("epoch number: " + epoch);
             //   System.out.println("gbestest"+gBestTest.getCost());
               
                if(gBestTest.getCost()> gBest.getCost()){
             //       System.out.println("pso.PSOFunction.PSOAlgorithm()");
                    gBest = gBestTest;
                }
               updateVelocity(gBest);
               updateParticles(gBest);
                
                
                System.out.println("best value:"+gBest.getCost());
                System.out.println("---------------------------------------------------------");
                epoch += 1;
                
                
                
                timer.setDelay(delay);
               
            }
            for(int i = 0; i < MAX_PARTICLES; i++)
                {
                    System.out.println("x:"+particles.get(i).getX()+"y:"+particles.get(i).getY());
                            
                } 

       
repaint();
}

}  protected void paintComponent(Graphics g)
{
super.paintComponent(g);
resize(10000, 10000);
        setBackground(BLUE);
       
        int i = 0;
        for (Particle p : particles) {

            // set current color
            // draw filled oval using current x and y coordinates and diameter
           // double[] loc = p.getPosition();

            int x=(int)p.getX();
            int y=(int)p.getY();
            System.out.println("X Coords:\t" + x);
            System.out.println("Y Coords:\t" + y);
            g.setColor(WHITE);
            g.fillOval(x, y, 10, 10);
            i++;

        }
}

      
 
public void suspend()
{
timer.stop();
}

public void resume()
{
timer.start();
}

public void setDelay(int delay)
{
this.delay = delay;
timer.setDelay(delay);
}

public static Particle maximum() //returns particle vith high cost
    {
        int winner = 0;
            
            for(int i = 0; i < MAX_PARTICLES; i++)
            {
                if(i != winner){             // Avoid self-comparison.
                     if(particles.get(winner).getCost()< particles.get(i).getCost()){
                        winner = i;
                        
                    }
                }
            }

        System.out.println("maximum"+particles.get(winner).getCost());
        return particles.get(winner);
    }
     
    
 
   
     public static void updateVelocity(Particle gbest){
        
        double bestResults = 0;
        double w=CHI;//0.7
        double  C1=CHI*PHIL; //2
        double C2=CHI*PHIL2;//2

       

       for(int i = 0; i <MAX_PARTICLES; i++)
        {
            Random g=new  Random();
            Particle par=particles.get(i);
            bestResults = par.getCost();
//            double lx = particles.get(i).getX();
//            double ly = particles.get(i).getX();
//            double vx = particles.get(i).getmVelocityX();
//            double vy = particles.get(i).getmVelocityY();
//            double pBestX = particles.get(i).getBestX();
//            double pBestY = particles.get(i).getBestY();
//            double gBestX = gbest.getX();
//            double gBestY = gbest.getY();
 
//            double newVelX = (w * vx) + (g.nextDouble() * C1) * (pBestX - lx) + (g.nextDouble() * C2) * (gBestX - lx);
//            double newVelY = (w * vy) + (g.nextDouble() * C1) * (pBestY - ly) + (g.nextDouble() * C2) * (gBestY - ly);
            
            
            double newVelX = w*par.getmVelocityX()+ (g.nextDouble()*C1) * (par.getBestX() - par.getX()) + (g.nextDouble()*C2)*(gbest.getX() - par.getX());
            double newVelY=w*par.getmVelocityY()+((g.nextDouble()*C1)*(par.getBestY()-par.getY()))+((g.nextDouble()*C2)*(gbest.getY()-par.getY()));
            if(newVelX > 10){
                newVelX=V_MAX;
            }
            else if(newVelX<0){
                newVelX=V_MIN;    
           }
            if(newVelY > 10){
                newVelY=V_MAX;
            }
            else if(newVelY<0){
                newVelY=V_MIN;    
           }
            par.setmVelocityX(newVelX);
            par.setmVelocityY(newVelY);
       }
    }
     
     public static void updateParticles(Particle gPar){
        for(int i=0;i<MAX_PARTICLES;i++){
            Particle par=particles.get(i);
            if(par!=gPar){
                double newLocX=par.getX()+par.getmVelocityX();
                double newLocY=par.getY()+par.getmVelocityY();
                if(newLocX>500){
                    newLocX=500;
                }
                else if(newLocX<100){
                    newLocX=100;
                }
                if(newLocY>500){
                    newLocY=500;
                }
                else if(newLocY<100){
                    newLocY=100;
                }
                par.setX(newLocX);
                par.setY(newLocY);
               
               double cost=CostFunction.costFunction();
                if(par.getCost()<cost){
                par.setCost(cost);
            }
            
           if(par.getBestCost()<par.getCost()){
                par.setBestCost(par.getCost());
                par.setBestX(par.getX());
                par.setBestY(par.getY());
                
               
           }
        }
        }
    }

}