import java.awt.Color;
import java.awt.image.BufferedImage;

public class Main {
	public static Camera c = new Camera();
	public static void main(String[] args) {
		System.out.println();
		
		Scene s = new Scene();
		LightSource l = new LightSource( new Vec3 (0, 800, -100));
		final int width = 600;
		//represented on y-axis
		final int height = 600;
		//represented on x-axis
		final int z = 300+ (int) c.getLocation().z;
		//how far flat screen is from camera(directly)
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//run through individual pixels of image
		for(int i=0; i<width;i++) {
			for(int j=0; j<height; j++) {
				//i represents column # and j row #
				//shoot ray from camera to specific pixel
				Vec3 direction = new Vec3(i, j, z).minus(c.getLocation()).normalized();
				Ray r = new Ray(c.getLocation(), direction);
				//System.out.println(r);
				//find intersections, specifically closest intersection
				//AND: determine color given intersections and lighting
				Intersection POI = findClosestIntersection(r, s, l);
				//System.out.println(POI);
				//System.out.println(POI.getColor().convertToColor(POI.getIntensity()).getRGB());
				//System.out.println(POI.getColor().convertToColor(POI.getIntensity()).getRGB());
				//img.setRGB(i,  j,  POI.getColor().convertToColor(POI.getIntensity()).getRGB());
				img.setRGB(i,  j,  POI.getFinalCol());
				//somehow scale by intensity in line above
				
			}
		}
		Display d = new Display(img);
	}
	
	public Ray constructRay(Camera c, Vec3 point) {
		return new Ray (c.getLocation(), point);
	}
	public static Intersection findClosestIntersection(Ray r, Scene s, LightSource l) {
		//test for intersection in each object in scene
		Vec3 color = new Vec3(0, 0, 0);
		Intersection closest = new Intersection(new Vec3(0, 0, 0), Color.black.getRGB());
		for(SolidObject obj: s.getSolidObjects()) {
			Intersection tempClose = obj.findIntersection(r, l);
			//works because they are on same ray line
			//feels a bit arbitrary though
			if(tempClose.getX()>closest.getX()) {
				closest=tempClose;
			}
		}
	return closest;
	}
	

}
