import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList; // import the ArrayList class
/******************************************************************************
 *  Compilation:  javac Universe.java
 *  Execution:    java Universe dt < input.txt
 *  Dependencies: Body.java Vector.java StdIn.java StdDraw.java
 *  Datafiles:    https://introcs.cs.princeton.edu/java/34nbody/2body.txt
 *                https://introcs.cs.princeton.edu/java/34nbody/3body.txt
 *                https://introcs.cs.princeton.edu/java/34nbody/4body.txt
 *                https://introcs.cs.princeton.edu/java/34nbody/2bodyTiny.txt
 *
 *  This data-driven program simulates motion in the universe defined
 *  by the standard input stream, increasing time at the rate on the
 *  command line.
 *
 *  %  java Universe 25000 < 4body.txt
 *
 *
 ******************************************************************************/

import java.util.Scanner;

public class Universe {
    private final int n;             // number of bodies
    private final Body[] bodies;     // array of n bodies

    // read universe from standard input
    public Universe() {
    	
        // number of bodies
        n = StdIn.readInt(); 
        double radius = StdIn.readDouble(); 
       
        
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        StdDraw.setXscale(0.0,1.0);
        StdDraw.setYscale(0.0,1.0);
        // read in the n bodies
        bodies = new Body[n]; 
        for (int i = 0; i < n; i++) { 
            double rx   = StdIn.readDouble(); 
            
            double ry   = StdIn.readDouble();

            double vx   = StdIn.readDouble();

            double vy   = StdIn.readDouble();
            
            double mass = StdIn.readDouble(); 
            
            String planet = StdIn.readString();
            
           // System.out.println(path_img);
            double[] position = { rx, ry }; 
            double[] velocity = { vx, vy }; 
            Vector r = new Vector(position); 
            Vector v = new Vector(velocity); 
            bodies[i] = new Body(r, v, mass,planet); 
        } 
        //System.out.println(bodies);
         // the set scale for drawing on screen
         
        StdDraw.setXscale(-radius, +radius); 
        StdDraw.setYscale(-radius, +radius);
       // String song = StdIn.readLine();
       // System.out.println(song);
    } 
    

    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        Vector[] f = new Vector[n];
        for (int i = 0; i < n; i++) {
            f[i] = new Vector(new double[2]);
        }
        
	

        // compute the forces
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    f[i] = f[i].plus(bodies[i].forceFrom(bodies[j]));
                }
            }
        }
      
        // move the bodies
        for (int i = 0; i < n; i++) {
            bodies[i].move(f[i], dt);
        }
    }

    // draw the n bodies
    public void draw() throws IOException {
        for (int i = 0; i < n; i++) {
            bodies[i].draw();
        }
    } 

    
    public static double harmonic(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }
    // client to simulate a universe
    public static void main(String[] args) throws IOException {
        Universe newton = new Universe();
     
        double dt = Double.parseDouble(args[1]);
        int T = Integer.parseInt(args[0]);
        
        StdDraw.enableDoubleBuffering();
        //StdDraw.setScale(-10.00e10,10.00e10);
        StdDraw.setScale(-18.05E10 ,18.05E10 );
        File file = new File("2001.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        ArrayList<Integer> song = new ArrayList<Integer>();
        int pos=0;
       while((string = br.readLine()) != null) {
    	   String[] v = string.split(" Tempo ");
    	   System.out.println(v[0]);
    	   song.add(Integer.parseInt(v[0]));
    	  
    	   song.add(Integer.parseInt(v[1]));
    	 
    	 
       }
       System.out.println(song.size());
        
        for(int i =0;i<song.size()-1;i++) {
        	
      
            StdDraw.clear(); 
            newton.increaseTime(dt);
            
            
           //StdDraw.clear(Color.DARK_GRAY);
          // StdAudio.play(song[i]);
           StdDraw.picture(0,0, "starfield.jpg", 37.05E10, 37.05E10);
            newton.draw(); 
            StdDraw.show();
            StdDraw.pause(T);
        } 
    } 
}
