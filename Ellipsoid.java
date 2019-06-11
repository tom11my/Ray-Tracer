import java.awt.Color;

public class Ellipsoid extends SolidObject{
	float a;
	float b;
	float c;
	//Vec3 col;
	//Vec3 center;
	public Ellipsoid(Vec3 center, float a, float b, float c, Vec3 col) {
		super(center, col);
		
		this.a=a;
		this.b=b;
		this.c=c;
		
	}
	@Override
	public Intersection findIntersection(Ray r, LightSource light) {
		//find parameter t and thus POI
		Vec3 dir = r.getDirection();
		Vec3 start = r.getStartingPt();
		//under the assumption that direction vector is normalized
		float t=0;
		float a2b2 = (float)Math.pow(a*b, 2);
		float a2c2 = (float)Math.pow(a*c,  2);
		float b2c2 = (float)Math.pow(b*c,  2);
		//lt^2+mt+n=0
		float l = (float)(Math.pow(dir.getX(), 2)*b2c2 +Math.pow(dir.getY(),2)*a2c2 +Math.pow(dir.getZ(),2)*a2b2);
		
		float m = (float)((start.getX()-this.center.getX())*2*dir.getX()*b2c2 + 
				(start.getY()-super.getCenter().getY())*2*dir.getY()*a2c2 +
				(start.getZ()-super.getCenter().getZ())*2*dir.getZ()*a2b2);
	
		float n = (float)(Math.pow(start.getX()-this.center.getX(), 2)*b2c2+
				Math.pow(start.getY()-super.getCenter().getY(),2)*a2c2 +
				Math.pow(start.getZ()-super.getCenter().getZ(), 2)*a2b2 - 
				a2b2*Math.pow(this.c, 2));
		//discriminant, b^2-4ac allows me to break into 3 cases
		float d = (float) (Math.pow(m, 2) - 4*l*n);
		//Case 1: d<0 so there are no solutions
		if(d<0) {
			//technically irrelevant given structure in Main for findClosestIntersection method
			return new Intersection (new Vec3(-1, -1, -1), Color.BLACK.getRGB());
		}
		
		//Case 2: d = 0, so there is 1 solution
		if(d==0)
			t = super.quadraticFormula1(l, m, n, d);
		
		//Case 3: d > 0 so there are 2 solutions
		//important assumption: both values of t > 0
		//motivation: because a smaller t value is the closest to camera always for sphere
		if(d>0) {
			t = (float)Math.min(super.quadraticFormula1(l, m, n, d), super.quadraticFormula2(l, m, n, d));
			//System.out.println("t = " + t);
		}
		Vec3 POI = r.findPoint(t);
		//determine color at POI
		
		Vec3 lightHit = POI.minus(light.getLocation()).normalized();
	
		int col = calculateLighting(POI, light);
		//col must be of type int
		return new Intersection (POI, col);
	}



}
