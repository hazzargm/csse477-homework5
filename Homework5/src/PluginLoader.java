import java.io.File;
import java.util.ArrayList;


public class PluginLoader {
	
	public PluginLoader() {
		File dir = new File("PluginFolder");
		File[] filesList = dir.listFiles();
		String files = "";
		for (File file : filesList) {
			if(file.isFile()) {
				files = files + file.getName() + "\n"; 
			}
		}
		System.out.println(dir.getAbsolutePath());
	}
	
	public ArrayList<Object> loadPlugins() {
		return null;
	}

}
