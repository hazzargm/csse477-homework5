import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private PluginPlatform platform;
		
	public GUIController(PluginPlatform platform) {
		this.platform = platform;
		execPanel = new ExecutionPanel();
		listPanel = new ListingPanel(this);
		statPanel = new StatusPanel();
		
		this.add(listPanel);
		this.add(statPanel);
		this.add(execPanel);
				
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void launchPlugin(String pluginName) {
		platform.launchPlugin(pluginName);
	}
	
	public void startPlugin(Plugin p) {
		
		// Status for "Killing" the current plugin
		if (currentPlugin != null) {
			statPanel.log(currentPlugin.getName(), false);
		}
		//TODO: Other GUI killing stuffs
		
		execPanel.add(p.getPanel());
		
		// Plugin switch
		currentPlugin = p;
		
		// Status for "Launching" the current plugin
		statPanel.log(p.getName(), true);
		
		//TODO: Other GUI launching stuffs
		this.revalidate();
		this.repaint();
	}
	
	public void addPlugin(String pluginName) {
		listPanel.addPlugin(pluginName);
		this.revalidate();
		this.repaint();
	}
	
	public void removePlugin(String pluginName) {
		listPanel.removePlugin(pluginName);
		this.revalidate();
		this.repaint();
	}

}
