
public abstract class SolidObject {
	//must deal with cases including if there is more than one intersection
	public abstract Intersection findIntersection(Ray r);
	public abstract int calculateLighting(Vec3 point, LightSource l);
	//future note: array of light sources
	public Vec3 center;
	
}
