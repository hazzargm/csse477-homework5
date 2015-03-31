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
	
	private ArrayList<String> statusList;
	private JScrollPane statusPane;
	
//	private ArrayList<String> statuses;

	public StatusPanel() {
		this.setBounds(x, y, width, height);
		this.setBackground(Color.BLACK);
		statusList = new ArrayList<String>();
		
		
		
		createScrollPane();
		this.setVisible(true);
		this.log("plug 1", true);
	}

private void createScrollPane() {
	for(int j = 0; j < 40; j ++)
		statusList.add("test " + j + "");
	Vector iVec = new Vector(statusList);
	JList items = new JList(iVec);
	JScrollPane statusPane = new JScrollPane(items);
	statusPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	statusPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	statusPane.setVisible(true);
	statusPane.setPreferredSize(this.getSize());
	statusPane.setBounds(0, 400, 600, 200);
	this.add(statusPane);
}
	
	public void log(String pluginName, boolean start) {
		String status = "";
		if(start) {
			status = pluginName + "has started executing.";
		} else {
			status = pluginName + "has terminated.";
		}
		statusList.add(status);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}
}
