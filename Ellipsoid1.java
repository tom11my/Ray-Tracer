//is an ellipsoid based on the generalized quadric surface
//this class functions without proper lighting, because the generalized quadricSurface does not have a formula for finding the center of all quadric shapes given the constants
public class Ellipsoid1 extends QuadricSurface{
	float a, b, c;
	//constants to define the lengths of major axes
	public Ellipsoid1 (Vec3 loc, float a, float b, float c, Vec3 col) {
		super(b*b*c*c, a*a*c*c, a*a*b*b, 0, 0, 0, -2*loc.getX()*b*b*c*c, -2*loc.getY()*a*a*c*c, -2*loc.getZ()*a*a*b*b, b*b*c*c*loc.getX()*loc.getX()+a*a*c*c*loc.getY()*loc.getY()+a*a*b*b*loc.getZ()*loc.getZ()-a*a*b*b*c*c, col);	
		this.a = a;
		this.b = b;
		this.c = c;
	}
}