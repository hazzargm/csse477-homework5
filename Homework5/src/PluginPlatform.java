import javax.swing.WindowConstants;


public class PluginPlatform {
	private PluginLoader loader;
	private GUIController gui;
	
	public PluginPlatform() {
		loader = new PluginLoader();
		gui = new GUIController();
	}
	
	public void run() {
		gui.setVisible(true);
		loader.loadPlugins();
	}
}
