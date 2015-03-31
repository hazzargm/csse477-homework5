import java.io.File;
import java.io.IOException;


public class PluginPlatform {
	private PluginLoader loader;
	private GUIController gui;
	
	public PluginPlatform() {
		gui = new GUIController();
		File pluginDir = new File(System.getProperty("user.dir") + "/PluginFolder");
		try {
			loader = new PluginLoader(gui.getListingPanel(), pluginDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		gui.setVisible(true);
		loader.loadAllPlugins();
		try {
			loader.watchPlugins();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
