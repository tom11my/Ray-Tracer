import java.awt.Color;

public class Intersection extends Vec3{
	public Vec3 col;
	public float intensity;
	public int finalCol;
	//somehow include normal or dot product
	/*public Intersection(float x, float y, float z) {
		super(x, y, z);
	}*/
	public Intersection(float x, float y, float z, Vec3 col, float i) {
		super(x, y, z);
		this.col=col;
		intensity = i;
	}
	public Intersection(Vec3 v, Vec3 col, float i) {
		super(v.x, v.y, v.z);
		this.col = col;
		this.intensity = i;
		
	}
	public Intersection (Vec3 v, int color) {
		super(v.x, v.y, v.z);
		finalCol = color;
	}
	public Vec3 getColor() {
		return col;
	}
	public Color getActualColor(float intensity) {
		return col.convertToColor(intensity);
	}
	public int getFinalCol() {
		return finalCol;
	}
	public void setColor(Vec3 col) {
		this.col = col;
	}
	public float getIntensity() {
		return intensity;
	}
	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	public String toString() {
		return "I -- x - " + x + "; y - " + y + "; z " + z;
	}
}
