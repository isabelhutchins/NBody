import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NBody {
	
	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		
		double radius = readRadius(pfile);
		Planet[] planets = readPlanets(pfile);
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		 
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		for (int i=0; i<planets.length; i++){
			planets[i].draw();
		}
		
		double time = 0;
		
		while(time!=T){
			double [] xForces = new double [5];
			double [] yForces = new double [5];
			
			for (int j=0; j<planets.length; j++){
				xForces[j]=planets[j].calcNetForceExertedByX(planets);
				yForces[j]=planets[j].calcNetForceExertedByY(planets);	
			}
			
			for (int k=0; k<planets.length; k++){
				planets[k].update(dt, xForces[k], yForces[k]);
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for (int k=0; k<planets.length; k++){
				planets[k].draw();
			}
			
			StdDraw.show(10);
			time+=dt;
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                      planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
		}
		
	}
	
	public static double readRadius(String fname){
		double radius = 0;
		try {
			Scanner s = new Scanner (new File(fname));
			s.nextDouble();
			radius = s.nextDouble();
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		return radius;
	}
	
	public static Planet[] readPlanets(String fname){
		Planet [] planets = new Planet [5]; 
		double xPos; double yPos; double xVel; double yVel; double mass; String file;
		try {
			Scanner s = new Scanner (new File(fname));
			s.nextDouble();
			s.nextDouble();
			for (int i=0; i<5; i++){
			xPos = s.nextDouble();
			yPos = s.nextDouble();
			xVel = s.nextDouble();
			yVel = s.nextDouble();
			mass = s.nextDouble();
			file = s.next();
			s.nextLine();
			Planet p = new Planet(xPos, yPos, xVel, yVel, mass, file);
			planets[i]=p;
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.err.printf("File not found!");
			e.printStackTrace();
		}
		return planets;
	}
	
	
	
	
	
}
