import javax.swing.WindowConstants;


public class PluginPlatform {
	private PluginLoader loader;
	private GUIController gui;
	
	public PluginPlatform() {
		gui = new GUIController();
		loader = new PluginLoader(gui.getListingPanel());
	}
	
	public void run() {
		gui.setVisible(true);
		loader.loadPlugins();
	}
}
