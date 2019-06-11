
public abstract class SolidObject {
	//must deal with cases including if there is more than one intersection
	public abstract Intersection findIntersection(Ray r, LightSource light);
	//public abstract int calculateLighting(Vec3 point, LightSource l);
	//future note: array of light sources
	public Vec3 center;
	public Vec3 col;
	public SolidObject (Vec3 center, Vec3 col) {
		this.center = center;
		this.col =col;
	}
	public float quadraticFormula1(float a, float b, float c, float d) {
		//+ instead of -
		return (-b + (float)Math.sqrt((double)d))/(2*a);
	}
	
	public float quadraticFormula2(float a, float b, float c, float d) {
		//- instead of +
		return (-b - (float)Math.sqrt((double)d))/(2*a);
	}
	public int calculateLighting(Vec3 POI, LightSource light) {
		//Blinn-Phong model
		//not really sure what to choose
		//System.out.println(POI);
		Vec3 ambientColor = col.scaledBy(1/1000f);
		float ambientIntensity = 0.5f;
		
		Vec3 diffuseColor = this.col.normalized();
		Vec3 surfaceNormal = POI.minus(this.center).normalized();
		Vec3 lightHit = light.getLocation().minus(POI).normalized();
		float diffuseIntensity = Math.max(lightHit.dot(surfaceNormal), 0);
		
		Vec3 specularColor = new Vec3(1.0f, 1.0f, 1.0f);
		//not sure if I should use camera or light source location for half angle
		Vec3 halfAngle = (Main.c.getLocation().minus(POI).plus(light.getLocation().minus(POI))).normalized();
		//Vec3 halfAngle = l.getLocation().minus(POI).plus(lightHit).normalized();
		float specularIntensity = (float) Math.pow(Math.max(0,  halfAngle.dot(surfaceNormal)), 70);
		
		//System.out.println(specularIntensity);
		Vec3 finalCol = ambientColor.scaledBy(255).plus(diffuseColor.scaledBy(diffuseIntensity*255)).plus(specularColor.scaledBy(255*specularIntensity));
		return finalCol.convertToColor().getRGB();
	}

	public Vec3 getCenter() {
		return center;
	}

	public void setCenter(Vec3 center) {
		this.center = center;
	}

	public Vec3 getCol() {
		return col;
	}

	public void setCol(Vec3 col) {
		this.col = col;
	}
}
