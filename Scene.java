import java.awt.Color;

public class Scene {
	public SolidObject [] solidObjects;
	public Scene() {
		solidObjects=initializeArray();
	}

	public SolidObject[] initializeArray() {
		//WTH is holding this program from allowing x coordinate to be 100; it should not be offscreen
		//thought: missing an abs val somewhere; eliminated poss i think
		Sphere s1 = new Sphere (new Vec3(500, 600, 500), 50, new Vec3 (25, 55, 71));
		//Sphere s2 = new Sphere (new Vec3(500, 400, 800), 200, new Vec3 (90, 60, 160));
		//QuadricSurface s2 = new QuadricSurface (1, 1, 1, 0, 0, 0, -1000, -800, -1600, 1010000, new Vec3 (90, 60, 160));
		Sphere1 s2 = new Sphere1 (new Vec3(500, 400, 800), 200, new Vec3 (90, 60, 160));
		//Ellipsoid1 s3 = new Ellipsoid1(new Vec3(100, 400, 600), 100, 50, 100, new Vec3(25, 100, 200));
		Ellipsoid s3 = new Ellipsoid (new Vec3(100, 400, 600), 100, 50, 100, new Vec3(25, 100, 200));
		/*SolidObject [] objs = new SolidObject[400];
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
					objs[i*20 +j] = new Cube(new Vec3(100 + j*20, 100 + i*20, ((i+j)*20)), 20, new Vec3(50, 100, 200));
			}
		}*/
		Cube s4 = new Cube (new Vec3(500, 110, 300),100,  new Vec3(50, 100, 200));
		Cone s5 = new Cone(new Vec3(500, 100, 600), 200, 100, new Vec3 (200, 100, 50));
		//Cube s6 = new Cube (new Vec3(250, 150, 50), 500, new Vec3 (100, 100, 100));
		SolidObject [] s = {s1, s2, s3, s4, s5};
		//SolidObject [] s = {s4};
		
		//SolidObject [] s= {s1, s5};
		//SolidObject [] s = {s1, s4};
		//SolidObject [] s = {s2};
		return s;
		//return objs;
		
	}

	public SolidObject[] getSolidObjects() {
		return solidObjects;
	}
	
	public void setSolidObjects(SolidObject[] solidObjects) {
		this.solidObjects = solidObjects;
	}
	
}
