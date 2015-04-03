import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class PluginPlatform {
	private PluginLoader loader;
	private GUIController gui;
	private Thread statThread;
	
	public PluginPlatform() {
		gui = new GUIController(this);
		File pluginDir = new File(System.getProperty("user.dir") + "/PluginFolder");
		try {
			loader = new PluginLoader(gui, pluginDir);
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
	
	public void launchPlugin(String pluginName) {
		loader.launchPlugin(pluginName);
	}

	public void monitorPluginForStatus(Plugin plugin, ByteArrayOutputStream out) {
		statThread = new Thread(new StatusThreadRunnable(plugin, out, gui));
		statThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void killStatThread() {
		if(statThread != null)
			statThread.stop();
	}
	
}
