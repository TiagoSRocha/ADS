import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JToggleButton;

public abstract class Switch extends Device {

	protected Switch(String protocol) {
		super(protocol);
	}

	ArrayList<Device> devices = new ArrayList<Device>();
	
	@Override
	public void attach(Device device) throws IncompatibleProtocolException {
		if(!device.getProtocol().equals(this.getProtocol())) {
			throw new IncompatibleProtocolException();
		}
		devices.add(device);
		device.update(this.isOn());
	}

	@Override
	public void notifyObservers() {
		for (Device device: devices) {
			device.update(this.isOn());
		}
	}
	
	@Override
	public void onChange(boolean on) {		
		this.notifyObservers();
	}
	
	public JComponent addToContainer(Container container, int x, int y) {
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Switch");
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnNewToggleButton.gridx = x;
		gbc_tglbtnNewToggleButton.gridy = y;
		container.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);		
		return tglbtnNewToggleButton;
	}	
}
