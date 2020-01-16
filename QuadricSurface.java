import java.awt.Color;

public class QuadricSurface extends SolidObject {
	float A, B, C, D, E, F, G, H, I, J;

	//Ax^2 + By^2 + Cz^2 + Dxy + Exz + Fyz + Gx + Hy + Iz + J
	//as of now only works for a sphere because the Vec3 center is only for sphere
	public QuadricSurface(float a, float b, float c, float d, float e, float f, float g, float h, float i, float j,  Vec3 col) {
		super(new Vec3((float)-g/2, (float)-h/2, (float)-i/2), col);
		A=a;
		B=b;
		C=c;
		D=d;
		E=e;
		F=f;
		G=g;
		H=h;
		I=i;
		J=j;
		
		//sphere specific
		//Vec3 center = new Vec3((float)-G/2, (float)-H/2, (float)-I/2);
		
		
	}
	
	@Override
	public Intersection findIntersection(Ray r, LightSource light) {
		float t = 0;
		Vec3 d = r.getDirection();
		Vec3 s = r.getStartingPt();
		//set up to clean up calculations
		float dx = d.getX();
		float dy = d.getY();
		float dz = d.getZ();
		
		float sx = s.getX();
		float sy = s.getY();
		float sz = s.getZ();
		//at^2 + bt + c = 0
		float a = A*dx*dx + B*dy*dy + C*dz*dz + D*dx*dy + E*dx*dz +F*dy*dz;
		
		float b = 2*A*sx*dx + 2*B*sy*dy + 2*C*sz*dz + D*sx*dy + D*dx*sy + 
				E*sx*dz + E*dx*sz + F*sy*dz + F*dy*sz + G*dx + H*dy + I*dz;
		float c = A*sx*sx + B*sy*sy + C*sz*sz + D*sx*sy + E*sx*sz + F*sy*sz +G*sx + H*sy + I*sz +J;
		float det = (float) (Math.pow(b, 2) - 4*a*c);
		if(det<0) {
			//technically irrelevant given structure in Main for findClosestIntersection method
			return new Intersection (new Vec3(-1, -1, -1), Color.BLACK.getRGB());
		}
		//System.out.println("b " + b + "; a " +a + "; c "+ c);
		//Case 2: d = 0, so there is 1 solution
		if(det==0)
			t = super.quadraticFormula1(a, b, c, det);
		
		//Case 3: d > 0 so there are 2 solutions
		//important assumption: both values of t > 0
		//motivation: because a smaller t value is the closest to camera always for sphere
		if(det>0) {
			t = (float)Math.min(super.quadraticFormula1(a, b, c, det), super.quadraticFormula2(a, b, c, det));
			//System.out.println("t = " + t);
		}
		
		Vec3 POI = r.findPoint(t);
		
		int col = super.calculateLighting(POI, light);
		return new Intersection (POI, col);
	}
	public float getA() {
		return A;
	}

	public void setA(float a) {
		A = a;
	}

	public float getB() {
		return B;
	}

	public void setB(float b) {
		B = b;
	}

	public float getC() {
		return C;
	}

	public void setC(float c) {
		C = c;
	}

	public float getD() {
		return D;
	}

	public void setD(float d) {
		D = d;
	}

	public float getE() {
		return E;
	}

	public void setE(float e) {
		E = e;
	}

	public float getF() {
		return F;
	}

	public void setF(float f) {
		F = f;
	}

	public float getG() {
		return G;
	}

	public void setG(float g) {
		G = g;
	}

	public float getH() {
		return H;
	}

	public void setH(float h) {
		H = h;
	}

	public float getI() {
		return I;
	}

	public void setI(float i) {
		I = i;
	}

	public float getJ() {
		return J;
	}

	public void setJ(float j) {
		J = j;
	}

	
}
