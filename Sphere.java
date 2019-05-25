import java.awt.Color;

public class Sphere extends SolidObject{
	private Vec3 loc;
	private float radius;
	private Vec3 origCol;
	//sick color:  = new Vec3 (25, 55, 71);
	//private Color origCol = new Color (255, 99, 71);
	public Sphere (Vec3 loc, float radius, Vec3 col) {
		this.loc = loc;
		this.radius = radius;
		origCol=col;
	}
	
	public Sphere () {
		//rewrite to fit viewing field
		loc = new Vec3 ((int) Math.random()*500, 50 + (int)Math.random()*500, 50 + (int) Math.random()*500);
		radius = (float) Math.random()*50;
	}
	
	public Intersection findIntersection(Ray r) {
		//find parameter t and thus POI
	
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
			//technically irrelevant given structure in Main for findClosestIntersection method
			return new Intersection (new Vec3(-1, -1, -1), Color.BLACK.getRGB());
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
		Vec3 POI = r.findPoint(t);

		//determine color at POI
		LightSource l = new LightSource( new Vec3 (100, 800, -200));
		Vec3 lightHit = POI.minus(l.getLocation()).normalized();
	
		int col = calculateLighting(POI, l);
		//col must be of type int
		return new Intersection (POI, col);
	}
	public int calculateLighting(Vec3 POI, LightSource l) {
		//Blinn-Phong model
		//not really sure what to choose
		Vec3 ambientColor = new Vec3(0.1f, 0.1f, 0.0f);
		float ambientIntensity = 0.5f;
		
		Vec3 diffuseColor = origCol.normalized();
		Vec3 surfaceNormal = POI.minus(this.loc).normalized();
		Vec3 lightHit = l.getLocation().minus(POI).normalized();
		float diffuseIntensity = Math.max(lightHit.dot(surfaceNormal), 0);
		
		Vec3 specularColor = new Vec3(1.0f, 1.0f, 1.0f);
		//not sure if I should use camera or light source location for half angle
		Vec3 halfAngle = (Main.c.getLocation().minus(POI).plus(l.getLocation().minus(POI))).normalized();
		//Vec3 halfAngle = l.getLocation().minus(POI).plus(lightHit).normalized();
		System.out.println(halfAngle.dot(surfaceNormal));
		float specularIntensity = (float) Math.pow(Math.max(0,  halfAngle.dot(surfaceNormal)), 25);
		
		//System.out.println(specularIntensity);
		Vec3 finalCol = ambientColor.scaledBy(255).plus(diffuseColor.scaledBy(diffuseIntensity*255)).plus(specularColor.scaledBy(255*specularIntensity));
		return finalCol.convertToColor().getRGB();
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