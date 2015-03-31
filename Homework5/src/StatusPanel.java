import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class StatusPanel extends JPanel {
	
	private static final int x = 0;
	private static final int y = 400;
	private static final int height = 222;
	private static final int width = 600;
	
//	private ArrayList<String> statuses;

	public StatusPanel() {
		this.setBounds(x, y, width, height);
		this.setBackground(Color.BLACK);
		ArrayList<String> i = new ArrayList<String>();
		for(int j = 0; j < 40; j ++)
			i.add("test " + j + "");
		Vector iVec = new Vector(i);
		JList items = new JList(iVec);
		JScrollPane sp = new JScrollPane(items);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setVisible(true);
		sp.setPreferredSize(this.getSize());
		sp.setBounds(0, 400, 600, 200);
		this.add(sp);
		this.setVisible(true);
	}
	
	public void log(String pluginName, boolean start) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}
}
