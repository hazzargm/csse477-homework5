import java.io.File;
import java.util.ArrayList;


public class PluginLoader {
	private ListingPanel listPanel;
	
	public PluginLoader(ListingPanel listPanel) {
		ArrayList<String> pluginNames = fetchPlugins();
		System.out.println(pluginNames);
		this.listPanel = listPanel;
	}
	
	public ArrayList<Object> loadPlugins() {
		return null;
	}
	
	public ArrayList<String> fetchPlugins() {
		ArrayList<String> pluginNames = new ArrayList<String>();
		File dir = new File(System.getProperty("user.dir") + "/PluginFolder");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
			if(file.isFile()) {
				pluginNames.add(file.getName());
				System.out.println(file);
			}
		}
		return pluginNames;
	}

}
