
public class Ray {
	//parameter where t>0
	private float t;
	//direction vector
	private Vec3 d;
	//starting point
	private Vec3 a;
	public Ray (Vec3 start, Vec3 direction) {
		//should be normalized
		d=direction;
		a=start;
	}
	
	public float getParameter() {
		return t;
	}
	public void setParameter(float t) {
		this.t = t;
	}
	public Vec3 getDirection() {
		return d;
	}
	public void setDirection(Vec3 d) {
		this.d = d;
	}
	public Vec3 getStartingPt() {
		return a;
	}
	public void setStartingPt(Vec3 a) {
		this.a = a;
	}
	public Vec3 findPoint(float t) {
		return d.scaledBy(t).plus(a);
		//t should always be positive
	}
	public String toString() {
		return "R -- dir: " +d + " start: " + a +" t: " +t;
	}
}
