import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AcmeSwitch extends Device {
	ArrayList<Device> devices = new ArrayList<Device>();
	public AcmeSwitch() {
		super("ENL");
	}

	@Override
	public void update(boolean isOn) {
		for (Device device: devices) {
			if(isOn == true){
				device.turnOn();
				this.turnOn();
			}

			else{
				device.turnOff();
				this.turnOff();
			}
		}
	}

	@Override
	public void attach(Device device) throws IncompatibleProtocolException {
		if(!device.getProtocol().equals(this.getProtocol())) {
			throw new IncompatibleProtocolException();
		}
		devices.add(device);
		device.update(this.isOn());
	}

	public JComponent addToContainer(Container container, int x, int y) {
		JToggleButton tglbtnNewToggleButton = new JToggleButton("ACME Switch");
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnNewToggleButton.gridx = x;
		gbc_tglbtnNewToggleButton.gridy = y;
		container.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		return tglbtnNewToggleButton;
	}
}