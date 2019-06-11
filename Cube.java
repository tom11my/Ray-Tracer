
public class Cube extends SolidObject {
	float sideLength;
	Vec3 surfaceNormal;
	public Cube (Vec3 center, float sideLength, Vec3 col) {
		super(center, col);
		this.sideLength = sideLength;
		//super.setCenter(center);
		//super.setCol(col);
	}
	@Override
	public Intersection findIntersection(Ray r, LightSource light) {
		//CASE 1: when the x portion is half the side length
		Vec3 dir = r.getDirection();
		Vec3 start = r.getStartingPt();
		Vec3 POI;
		//minimum of t values for which t=(+/-L/2 +a-ax)/dx where ax is the x component of start and dx is x component of dir
		float t1 = Math.min((sideLength/2+super.getCenter().getX()-start.getX())/dir.getX(),(-sideLength/2+super.getCenter().getX()-start.getX())/dir.getX());
		if(Math.abs(dir.getY()*t1+start.getY()-super.getCenter().getY())<sideLength/2 &&
				Math.abs(dir.getZ()*t1+start.getZ()-super.getCenter().getZ())<sideLength/2) {
			POI = r.getStartingPt().plus(r.getDirection().scaledBy(t1));
			surfaceNormal = new Vec3(-1, 0, 0);
			return new Intersection(POI, calculateLighting(POI, light));
			//return new Intersection(POI,100);
		}
		
		float t2 = Math.min((sideLength/2+super.getCenter().getY()-start.getY())/dir.getY(),(-sideLength/2+super.getCenter().getY()-start.getY())/dir.getY());
		if(Math.abs(dir.getZ()*t2+start.getZ()-super.getCenter().getZ())<sideLength/2 &&
				Math.abs(dir.getX()*t2+start.getX()-super.getCenter().getX())<sideLength/2) {
			POI = r.getStartingPt().plus(r.getDirection().scaledBy(t2));
			surfaceNormal = new Vec3(0, -1, 0);
			return new Intersection(POI, calculateLighting(POI, light));
			//return new Intersection(POI,100);
		}	
		float t3 = Math.min((sideLength/2+super.getCenter().getZ()-start.getZ())/dir.getZ(),(-sideLength/2+super.getCenter().getZ()-start.getZ())/dir.getZ());
		if(Math.abs(dir.getX()*t3+start.getX()-super.getCenter().getX())<sideLength/2 &&
				Math.abs(dir.getY()*t3+start.getY()-super.getCenter().getY())<sideLength/2) {
			POI = r.getStartingPt().plus(r.getDirection().scaledBy(t3));
			surfaceNormal = new Vec3(0, 0, -1);
			return new Intersection(POI, calculateLighting(POI, light));
			//return new Intersection(POI,100);
		}	
		return new Intersection(new Vec3(0, 0, 0), 0);
	}
	@Override
	public int calculateLighting(Vec3 POI, LightSource light) {
		//Blinn-Phong model
		//not really sure what to choose
		Vec3 ambientColor = col.scaledBy(1/1000f);
		float ambientIntensity = 0.5f;
		
		Vec3 diffuseColor = this.col.normalized();
		//surfaceNormal = POI.minus(this.center).normalized();
		//surfaceNormal = new Vec3(0, 0, 0);
		Vec3 lightHit = light.getLocation().minus(POI).normalized();
		float diffuseIntensity = Math.max(lightHit.dot(surfaceNormal), 0);
		
		Vec3 specularColor = new Vec3(1.0f, 1.0f, 1.0f);
		//not sure if I should use camera or light source location for half angle
		Vec3 halfAngle = (Main.c.getLocation().minus(POI).plus(light.getLocation().minus(POI))).normalized();
		//Vec3 halfAngle = l.getLocation().minus(POI).plus(lightHit).normalized();
		float specularIntensity = (float) Math.pow(Math.max(0,  halfAngle.dot(surfaceNormal)), 60);
		
		//System.out.println(specularIntensity);
		Vec3 finalCol = ambientColor.scaledBy(255).plus(diffuseColor.scaledBy(diffuseIntensity*255)).plus(specularColor.scaledBy(255*specularIntensity));
		return finalCol.convertToColor().getRGB();
	}
}
