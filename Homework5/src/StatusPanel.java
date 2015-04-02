import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	
	private static final int x = 0;
	private static final int y = 400;
	private static final int height = 222;
	private static final int width = 600;
	
	private ArrayList<String> statusList;
	private JScrollPane statusPane;

	public StatusPanel() {
		this.setBounds(x, y, width, height);
		this.setBackground(Color.BLACK);
		statusList = new ArrayList<String>();
		statusList.add("Welcome to the Donkey Punters, LLC Plugin Platform!");
		statusPane = new JScrollPane();
		refreshStatusPane();
		
		this.setVisible(true);
	}

	private void refreshStatusPane() {
		if(!statusPane.equals(new JScrollPane())) {
			this.remove(statusPane);
		}
		Vector<String> iVec = new Vector<String>(statusList);
		JList<?> items = new JList<String>(iVec);
		statusPane = new JScrollPane(items,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		statusPane.setVisible(true);
		statusPane.setPreferredSize(this.getSize());
		statusPane.setBounds(0, 400, 600, 200);
		this.add(statusPane);
		this.revalidate();
		this.repaint();
	}
	
	public void log(String pluginName, boolean start) {
		String status = "";
		if(start) {
			status = pluginName + " has started executing.";
		} else {
			status = pluginName + " has terminated.";
		}
		statusList.add(statusList.size(), status);
		refreshStatusPane();
		
	}
	
}
