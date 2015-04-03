import java.io.ByteArrayOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class GUIController extends JFrame{
	
	private static final int WIDTH = 605;
	private static final int HEIGHT = 650;
	
	private ExecutionPanel execPanel;
	private ListingPanel listPanel;
	private StatusPanel statPanel;
	
	private Plugin currentPlugin;
	private PluginPlatform platform;
		
	public GUIController(PluginPlatform platform) {
		this.platform = platform;
		currentPlugin = new Plugin("null", null);
		
		execPanel = new ExecutionPanel();
		listPanel = new ListingPanel(this);
		statPanel = new StatusPanel();
		
		this.add(listPanel);
		this.add(statPanel);
		this.add(execPanel);
		this.add(new JPanel());
				
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void launchPlugin(String pluginName) {
		if (!currentPlugin.getName().equalsIgnoreCase(pluginName)) {
			platform.launchPlugin(pluginName);
		}
	}
	
	public void startPlugin(Plugin p, ByteArrayOutputStream out) {
		
		// Status for "Killing" the current plugin
		if (currentPlugin.getPanel() != null) {
			statPanel.log(currentPlugin.getName(), false);
		}
		platform.killStatThread();
		execPanel.removeAll();
		
		// Plugin switch
		currentPlugin = p;
		
		// Status for "Launching" the current plugin
		execPanel.add(currentPlugin.getPanel());

		statPanel.log(currentPlugin.getName(), true);
		
		this.revalidate();
		this.repaint();
		
		this.platform.monitorPluginForStatus(currentPlugin, out);
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
	
	public void addStatus(String status) {
		statPanel.addStatus(status);
	}

}
