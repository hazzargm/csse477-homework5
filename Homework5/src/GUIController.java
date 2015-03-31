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
	
	private Plugin currentPlugin;
	
	public GUIController() {
		execPanel = new ExecutionPanel();
		listPanel = new ListingPanel();
		statPanel = new StatusPanel();
		
		this.add(listPanel);
		this.add(statPanel);
		this.add(execPanel);
				
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void launchPlugin(Plugin p) {
		
		// Status for "Killing" the current plugin
		statPanel.log(currentPlugin.getName(), false);
		
		//TODO: Other GUI killing stuffs
		
		// Plugin switch
		currentPlugin = p;
		
		// Status for "Launching" the current plugin
		statPanel.log(p.getName(), true);
		
		//TODO: Other GUI launching stuffs
	}
	
	public ListingPanel getListingPanel() {
		return listPanel;
	}

}
