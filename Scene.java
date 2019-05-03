
public class Scene {
	public SolidObject [] solidObjects;
	public Scene() {
		solidObjects=initializeArray();
	}

	public SolidObject[] initializeArray() {
		//WTH is holding this program from allowing x coordinate to be 100; it should not be offscreen
		//thought: missing an abs val somewhere; eliminated poss i think
		Sphere s1 = new Sphere (new Vec3(200, 600, 500), 100);
		Sphere s2 = new Sphere (new Vec3(400, 300, 800), 300);
		SolidObject [] s = {s1, s2};
		//SolidObject [] s = {s2};
		return s;
		
	}

	public SolidObject[] getSolidObjects() {
		return solidObjects;
	}
	
	public void setSolidObjects(SolidObject[] solidObjects) {
		this.solidObjects = solidObjects;
	}
	
}
