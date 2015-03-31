import javax.swing.JPanel;


public class Plugin {
	private String name;
	private JPanel panel;

	public Plugin(String name, JPanel panel) {
		this.name = name;
		this.panel = panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel; 
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
}
