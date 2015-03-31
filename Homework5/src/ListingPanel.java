import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class ListingPanel extends JPanel {
	
	private static final int x = 0;
	private static final int y = 0;
	private static final int height = 400;
	private static final int width = 150;
	
	public ListingPanel() {
		super();
		this.setBounds(x, y, width, height);
		this.setBackground(Color.GREEN);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}

}
