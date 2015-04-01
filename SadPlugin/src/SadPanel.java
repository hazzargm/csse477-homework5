import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class SadPanel extends JPanel {

	private static final int x = 50;
	private static final int y = 50;
	private static final int width = 300;
	private static final int height = 350;
	
	public SadPanel() {
		this.setBounds(x, y, width, height);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double face = new Ellipse2D.Double(0, 0, width, height);
		g2.setColor(Color.BLUE);
		g2.fill(face);
		g2.setColor(Color.BLACK);
		Ellipse2D.Double leftEye = new Ellipse2D.Double(75, 75, 50, 50);
		g2.fill(leftEye);
		Ellipse2D.Double rightEye = new Ellipse2D.Double(175, 75, 50, 50);
		g2.fill(rightEye);
		Arc2D.Double mouth = new Arc2D.Double(50, 175, 200, 100, 0, 180, Arc2D.PIE);
		g2.fill(mouth);
		
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SadPanel panel = new SadPanel();
		frame.add(panel);
		frame.setSize(width + 50, height + 50);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
