import java.awt.Color;

public class Sphere extends SolidObject{
	//private Vec3 loc;
	private float radius;
	
	//sick color:  = new Vec3 (25, 55, 71);
	//private Color origCol = new Color (255, 99, 71);
	public Sphere (Vec3 loc, float radius, Vec3 col) {
		super(loc, col);
		
		this.radius = radius;
	}
	
	public Sphere () {
		super(new Vec3 ((int) Math.random()*500, 50 + (int)Math.random()*500, 50 + (int) Math.random()*500),	new Vec3((int)Math.random()*255, (int)Math.random()*255, (int) Math.random()*255) );
		radius = (float) Math.random()*50;
	}
	
	public Intersection findIntersection(Ray r, LightSource light) {
		//find parameter t and thus POI
	
		float t =0;
		//under the assumption that direction vector is normalized
		float a = 1;
		//b=2d(a-c) where d is direction, a is starting point for ray, and c is center of sphere
		float b = r.getDirection().scaledBy(2.0f).dot(r.getStartingPt().minus(super.getCenter()));
		//System.out.println("start is " +r.getDirection());
		//System.out.println("b is " + b);
		//c=(a-c)^2 -R^2
		float c = r.getStartingPt().minus(super.getCenter()).mag2() - (float)Math.pow(radius,2);
		//discriminant, b^2-4ac allows me to break into 3 cases
		float d = (float) (Math.pow(b, 2) - 4*a*c);
		//Case 1: d<0 so there are no solutions
		if(d<0) {
			//technically irrelevant given structure in Main for findClosestIntersection method
			return new Intersection (new Vec3(-1, -1, -1), Color.BLACK.getRGB());
		}
		//System.out.println("b " + b + "; a " +a + "; c "+ c);
		//Case 2: d = 0, so there is 1 solution
		if(d==0)
			t = super.quadraticFormula1(a, b, c, d);
		
		//Case 3: d > 0 so there are 2 solutions
		//important assumption: both values of t > 0
		//motivation: because a smaller t value is the closest to camera always for sphere
		if(d>0) {
			t = (float)Math.min(super.quadraticFormula1(a, b, c, d), super.quadraticFormula2(a, b, c, d));
			//System.out.println("t = " + t);
		}
		Vec3 POI = r.findPoint(t);

		//determine color at POI
		
		//Vec3 lightHit = POI.minus(light.getLocation()).normalized();
	
		int col = super.calculateLighting(POI, light);
		//col must be of type int
		return new Intersection (POI, col);
	}
	
	
	
	
}