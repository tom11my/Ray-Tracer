import java.awt.Color;

public class Sphere1 extends QuadricSurface{
	public Sphere1 (Vec3 loc, float radius, Vec3 col) {
		super(1, 1, 1, 0, 0, 0, -2*loc.getX(), -2*loc.getY(), -2*loc.getZ(), loc.getX()*loc.getX() + loc.getY()*loc.getY() + loc.getZ()*loc.getZ() - radius*radius, col);	
	}
}