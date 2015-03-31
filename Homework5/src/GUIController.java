import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


public class GUIController extends JFrame{
	
	private static final int WIDTH = 615;
	private static final int HEIGHT = 660;
	
	private ExecutionPanel execPanel;
	private ListingPanel listPanel;
	private StatusPanel statPanel;
	
	public GUIController() {
		execPanel = new ExecutionPanel();
		listPanel = new ListingPanel();
		statPanel = new StatusPanel();
		
		this.add(statPanel);
		this.add(execPanel);
		this.add(listPanel);
		
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void launchPlugin() {
		
	}
	
	public ListingPanel getListingPanel() {
		return listPanel;
	}

}
