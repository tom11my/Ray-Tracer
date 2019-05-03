
public abstract class SolidObject {
	//must deal with cases including if there is more than one intersection
	public abstract Intersection findIntersection(Ray r);
	public Vec3 center;
}
