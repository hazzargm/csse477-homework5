import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class PluginClickListener implements MouseListener {

	private ListingPanel listPanel;
	private String pluginName;

	public PluginClickListener(ListingPanel listPanel, String pluginName) {
		this.listPanel = listPanel;
		this.pluginName = pluginName;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		listPanel.pluginClicked(pluginName);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
