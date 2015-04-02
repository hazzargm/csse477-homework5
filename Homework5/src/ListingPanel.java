import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ListingPanel extends JPanel {
	
	private static final int x = 0;
	private static final int y = 0;
	private static final int height = 400;
	private static final int width = 150;
		
	Map<String, JLabel> plugins;
	GUIController gui;
	
	public ListingPanel(GUIController gui) {
		super();
		this.gui = gui;
		plugins = new HashMap<String, JLabel>();
		this.setBounds(x, y, width, height);
		this.setBackground(Color.GRAY);
	}
	
	public void addPlugin(String pluginName) {
		JLabel pLabel = new JLabel(pluginName);
		pLabel.setForeground(Color.GREEN);
		pLabel.addMouseListener(new PluginClickListener(this, pluginName));
		pLabel.setPreferredSize(new Dimension(150, 25));
		plugins.put(pluginName, pLabel);
		this.add(plugins.get(pluginName));
		this.revalidate();
		this.repaint();
	}
	
	public void removePlugin(String pluginName) {
		this.remove(plugins.remove(pluginName));
		this.revalidate();
		this.repaint();
	}
	
	public void pluginClicked(String pluginName) {
		gui.launchPlugin(pluginName);
	}
	
}
