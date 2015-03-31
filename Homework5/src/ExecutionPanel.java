import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class ExecutionPanel extends JPanel {
	
	private static final int x = 150;
	private static final int y = 0;
	private static final int height = 400;
	private static final int width = 450;
	
	public ExecutionPanel() {
		super();
		this.setBounds(x, y, width, height);
		this.setBackground(Color.RED);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}

}
