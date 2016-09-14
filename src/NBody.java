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
		Planet[] planets = readPlanets(pfile);
		double radius = 0.0;
	
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
		File f = new File(fname);
		Scanner s;
		try {
			s = new Scanner (f);
			s.nextDouble();
			radius = s.nextDouble();	
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		return radius;
	}
	
	
	public static Planet[] readPlanets(String fname){
		Planet [] planets = new Planet [5]; 
		double xPos; double yPos; double xVel; double yVel; double mass; String file;
		File f = new File(fname);
		Scanner s;
		try {
			s = new Scanner (f);
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
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		return planets;
	}
	
	
	
	
	
}
