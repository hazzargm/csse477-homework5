import java.io.File;
import java.util.ArrayList;


public class PluginLoader {
	
	public PluginLoader() {
		ArrayList<String> pluginNames =fetchPlugins();
		System.out.println(pluginNames);
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
