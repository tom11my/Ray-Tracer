import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Display {
	private JFrame window;
	
	public Display (BufferedImage img) {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		JLabel label = new JLabel (new ImageIcon(img));
		window.add(label);
		window.pack();
		window.setVisible(true);
	}
}
