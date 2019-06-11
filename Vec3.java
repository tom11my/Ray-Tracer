
import java.awt.Color;

	public class Vec3 {
		public final float x, y, z;
		public Vec3 (float x, float y, float z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}

		public static Vec3 sphereCoord(float t, float p, float r) {
			float sint=(float)Math.sin(t);
			float cost=(float)Math.cos(t);
			float sinp=(float)Math.sin(p);
			float cosp=(float)Math.cos(p);
			return new Vec3(r*sint*cosp, r*sinp, r*cost*cosp);
		}
		public Vec3 plus(Vec3 v) {
			return new Vec3(x+v.x, y+v.y, z+v.z);
		}
		public Vec3 scaledBy(float f) {
			return new Vec3(f*x, f*y, f*z);
		}
		public Vec3 minus(Vec3 v) {
			return this.plus(v.scaledBy(-1f));
		}
		public float dot(Vec3 v) {
			return x*v.x +y*v.y+z*v.z;
		}
		public Vec3 cross(Vec3 v) {
			return new Vec3(y*v.z-z*v.y, x*v.z-z*v.x, x*v.y-y*v.x);
		}
		public Vec3 times(Vec3 v){
			return new Vec3(x*v.x,y*v.y,z*v.z);
		}
		
		public float mag2(){
			return dot(this);
		}
		
		public float mag(){
			return (float) Math.sqrt(mag2());
		}
		
		public Vec3 normalized(){
			return this.scaledBy(1f/mag());
		}
		
		public Vec3 toNegative() {
			return new Vec3(-x, -y, -z);
		}
		public Color convertToColor(float intensity){
			//could be reason for weird outline - 1f
			return new Color(Math.max(0,Math.min(x/255,1f)),Math.max(0,Math.min(y/255,1f)),Math.max(0,Math.min(z/255,1f)), Math.max(0, intensity));
		}
		public Color convertToColor() {
			return new Color(Math.max(0,Math.min(x/255,1f)),Math.max(0,Math.min(y/255,1f)),Math.max(0,Math.min(z/255,1f)));
		}
		public String toString() {
			return "V -- x - " + x + "; y - " + y + "; z " + z;
		}
		
		public static final Vec3 zero = new Vec3(0,0,0);
		public static final Vec3 i = new Vec3(1,0,0);
		public static final Vec3 j = new Vec3(0,1,0);
		public static final Vec3 k = new Vec3(0,0,1);
		public static final float epsilon = 0.1f;
	}


