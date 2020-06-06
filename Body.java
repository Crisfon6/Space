/******************************************************************************
 *  Compilation:  javac Body.java
 *  Execution:    java Body
 *  Dependencies: Vector.java StdDraw.java
 *
 *  Implementation of a 2D Body with a position, velocity and mass.
 *
 *
 ******************************************************************************/
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
public class Body {
    private Vector r;           // position
    private Vector v;           // velocity
    private final double mass;  // mass
    private final String planet ;
    

    public Body(Vector r, Vector v, double mass,String planet) {
        this.r = r;
        this.v = v;
        this.mass = mass;
        this.planet = planet;
       
    }

    public void move(Vector f, double dt) {
        Vector a = f.scale(1/mass);
        v = v.plus(a.scale(dt));
        r = r.plus(v.scale(dt));
    }

    public Vector forceFrom(Body b) {
        Body a = this;
        double G = 6.67e-11;
        
        Vector delta = b.r.minus(a.r);
        double dist = delta.magnitude();
        double magnitude = (G * a.mass * b.mass) / (dist * dist);
        return delta.direction().scale(magnitude);
    }

    public void draw()  throws IOException{
    	
    	/*try {
        BufferedImage img = null;
        File f = new File("pin.gif");
        img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        img = ImageIO.read(f);
    	}catch (IOException e) {
			// TODO: handle exception
    		System.out.println(e);
		}*/
    	
        //String path = "earth.gif";
    	// StdDraw.picture(0, 0, "starfield.jpg",1,1);
       StdDraw.picture(r.cartesian(0), r.cartesian(1), planet);
        /*
       int n = 10;
       double[] coor ={ 0.1, 0.2, 0.3, 0.2 };
     
        //System.out.println(coor);
        for (int i = 0; i < coor.length-1; i++) {
            StdDraw.picture(coor[i], coor[i], "pin.gif");
          }
        
        */
        //StdDraw.setPenRadius(0.025);
        //
       //StdDraw.point(r.cartesian(0), r.cartesian(1));
       //StdDraw.picture(r.cartesian(0), r.cartesian(1));
    }

    // this method is only needed if you want to change the size of the bodies
    public void draw(double penRadius) {
        StdDraw.setPenRadius(penRadius);
         StdDraw.point(r.cartesian(0), r.cartesian(1));
    }

} 

