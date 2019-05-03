import java.awt.Color;

public class Sphere extends SolidObject{
	private Vec3 loc;
	private float radius;
	private Vec3 origCol = new Vec3 (25, 55, 71);
	//private Color origCol = new Color (255, 99, 71);
	public Sphere (Vec3 loc, float radius) {
		this.loc = loc;
		this.radius = radius;
	}
	
	public Sphere () {
		//rewrite to fit viewing field
		loc = new Vec3 ((int) Math.random()*500, 50 + (int)Math.random()*500, 50 + (int) Math.random()*500);
		radius = (float) Math.random()*50;
	}
	
	public Intersection findIntersection(Ray r) {
		//GOAL 1: find parameter t and thus POI
		//GOAL 2: store info including color/intensity
	
		float t =0;
		//under the assumption that direction vector is normalized
		float a = 1;
		//b=2d(a-c) where d is direction, a is starting point for ray, and c is center of sphere
		float b = r.getDirection().scaledBy(2.0f).dot(r.getStartingPt().minus(loc));
		//System.out.println("start is " +r.getDirection());
		//System.out.println("b is " + b);
		//c=(a-c)^2 -R^2
		float c = r.getStartingPt().minus(loc).mag2() - (float)Math.pow(radius,2);
		//discriminant, b^2-4ac allows me to break into 3 cases
		float d = (float) (Math.pow(b, 2) - 4*a*c);
		//Case 1: d<0 so there are no solutions
		if(d<0) {
			return new Intersection (-1, -1, -1, new Vec3(0, 0, 0), 1);
		}
		
		//Case 2: d = 0, so there is 1 solution
		if(d==0)
			t = quadraticFormula1(a, b, c, d);
		
		//Case 3: d > 0 so there are 2 solutions
		//important assumption: both values of t > 0
		//motivation: because a smaller t value is the closest to camera always for sphere
		if(d>0) {
			t = (float)Math.min(quadraticFormula1(a, b, c, d), quadraticFormula2(a, b, c, d));
			//System.out.println("t = " + t);
		}
		
		//GOAL 1 ACHIEVED:
		Vec3 POI = r.findPoint(t);
		System.out.println(POI);
		//use normal to determine float value for intensity
		//(p-q)/|p-q|
		Vec3 surfaceNormal = POI.minus(this.loc).normalized();
		
		// ray direction is already normalized
		//how directly is the ray hitting the surface?
		float intensity = Math.abs(surfaceNormal.dot(r.getDirection()));
		
		//System.out.println(r.getDirection() + " and " + surfaceNormal);
		//System.out.println(intensity);
		//color must  be same color as sphere
		Vec3 colIntersect = origCol;
		
		//1- intensity random mod
		return new Intersection(POI, colIntersect, 1-intensity);
	}
	
	private float quadraticFormula1(float a, float b, float c, float d) {
		//+ instead of -
		return (-b + (float)Math.sqrt((double)d))/(2*a);
	}
	
	private float quadraticFormula2(float a, float b, float c, float d) {
		//- instead of +
		return (-b - (float)Math.sqrt((double)d))/(2*a);
	}
	
	
}