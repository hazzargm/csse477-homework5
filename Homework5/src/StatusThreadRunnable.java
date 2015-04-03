import java.io.ByteArrayOutputStream;

public class StatusThreadRunnable implements Runnable {
	
	private ByteArrayOutputStream out;
	private GUIController gui;
	private Plugin plugin;
	
	public StatusThreadRunnable(Plugin plugin, ByteArrayOutputStream out, GUIController gui) {
		this.out = out;
		this.gui = gui;
		this.plugin = plugin;
	}

	@Override
	public void run() {
		while(true) {
			if(out != null && !out.toString().equalsIgnoreCase("")) {
				gui.addStatus(plugin.getName() + ": " + out.toString());
				out.reset();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}	

}
