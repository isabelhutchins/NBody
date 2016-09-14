

public class Planet {
public double myXPos;
public double myYPos;
public double myXVel;
public double myYVel;
public double myMass;
public String myFileName;

	public Planet(double xP, double yP, double xV,
            double yV, double m, String img){
		myXPos=xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName=img;
	}
	
	public Planet(Planet p){
		myXPos=p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName=p.myFileName;
	}
	
	
	public double calcDistance(Planet p){
		double deltaX = this.myXPos - p.myXPos;
		double deltaY = this.myYPos - p.myYPos;
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}
	
	public double calcForceExertedBy(Planet p){
		double distanceSqr = p.calcDistance(this) * p.calcDistance(this);
		double G = 6.67e-11;
		return G * this.myMass * p.myMass / distanceSqr;
	}
	
	public double calcForceExertedByX(Planet p){
		double deltaX = p.myXPos - this.myXPos;
		double totalForce = p.calcForceExertedBy(this);
		double distance = p.calcDistance(this);
		return (totalForce * deltaX)/distance;
	}
	
	public double calcForceExertedByY(Planet p){
		double deltaY = p.myYPos - this.myYPos;
		double totalForce = p.calcForceExertedBy(this);;
		double distance = p.calcDistance(this);
		return (totalForce * deltaY)/distance;
	}
	
	public double calcNetForceExertedByX(Planet[]planets){
		double netForceX=0;
		for (int i=0; i<planets.length; i++){
			if (!planets[i].equals(this)){
				netForceX+=this.calcForceExertedByX(planets[i]);
			}
		}
		return netForceX;
	}
	
	public double calcNetForceExertedByY(Planet[]planets){
		double netForceY=0;
		for (int i=0; i<planets.length; i++){
			if (!planets[i].equals(this)){
				netForceY+=this.calcForceExertedByY(planets[i]);
			}
		}
		return netForceY;
	}
	

	public void update(double time, double xForce, double yForce){
		double accelX = xForce/this.myMass;
		double accelY = yForce/this.myMass;
		this.myXVel = this.myXVel + time * accelX;
		this.myYVel = this.myYVel + time * accelY;
		this.myXPos = this.myXPos + time * this.myXVel;
		this.myYPos = this.myYPos + time * this.myYVel;
	}
	
	
}
