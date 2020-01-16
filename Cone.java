import java.awt.Color;

public class Cone extends SolidObject{
	private float radius;
	private float height;
	
	public Cone(Vec3 center, float radius, float height, Vec3 col) {
		super(center, col);
		this.radius = radius;
		this.height = height;
		
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	@Override
	public Intersection findIntersection(Ray r, LightSource light) {
		
		Vec3 d = r.getDirection();		
		float A = r.getStartingPt().getX() - center.getX();
		float B = r.getStartingPt().getZ() - center.getZ();
		float D = height - r.getStartingPt().getY() + center.getY();
				
		float tan = (radius/height)*(radius/height);
				
		float a = (d.getX()*d.getX()) + (d.getZ()*d.getZ()) - (tan*(d.getY()*d.getY()));
		float b = (2*A*d.getX()) + (2*B*d.getZ()) +(2*tan*D*d.getY());
		float c = (A*A) + (B*B) - (tan*(D*D));
		
		float dis = b*b - 4*a*c;
		if(dis < 0 || Math.abs(dis)<0.001)
			return new Intersection(new Vec3(-1, -1, -1),100);
		
		float t1 = (float)(-b + Math.sqrt(dis))/(2*a);
		float t2 = (float)(-b - Math.sqrt(dis))/(2*a);
		float t;
		
		if(t2>t1)
			t = t1;
		else t = t2;
		
		
		Vec3 POI = r.findPoint(t);
		float yLoc = POI.getY();
		
		int col = calculateLighting(POI, light);
		if((yLoc > center.getY()) && (yLoc < center.getY() + height))
			return new Intersection (POI, col);
		
				
				
		return new Intersection(new Vec3(-1, -1, -1),100);
	}
	
}
