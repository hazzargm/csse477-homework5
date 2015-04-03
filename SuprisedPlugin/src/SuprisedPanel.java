import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class SuprisedPanel extends JPanel {

	private static final int x = 50;
	private static final int y = 50;
	private static final int width = 300;
	private static final int height = 350;
	private ByteArrayOutputStream out;

	public SuprisedPanel() {
		this.setBounds(x, y, width, height);
		this.out = new ByteArrayOutputStream();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double face = new Ellipse2D.Double(0, 0, width, height);
		g2.setColor(Color.PINK);
		g2.fill(face);
		g2.setColor(Color.BLACK);
		Ellipse2D.Double leftEye = new Ellipse2D.Double(75, 75, 50, 50);
		g2.fill(leftEye);
		Ellipse2D.Double rightEye = new Ellipse2D.Double(175, 75, 50, 50);
		g2.fill(rightEye);
		Arc2D.Double mouth = new Arc2D.Double(50, 175, 200, 100, 0, 360, Arc2D.PIE);
		g2.fill(mouth);
		String s = "I am suprised we actually got this working!";
		try {
			out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	public ByteArrayOutputStream getStream() {
		return out;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		SuprisedPanel panel= new SuprisedPanel();
		frame.add(panel);
		frame.setSize(width + 50, height + 50);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
