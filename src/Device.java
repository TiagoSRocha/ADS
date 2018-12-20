import java.awt.Container;

import javax.swing.JComponent;

public abstract class Device {
	private boolean on;
	private String protocol;
	
	public void attach(Device device) throws IncompatibleProtocolException {};
	public void notifyObservers() {};
	public void update(boolean on) {};
	protected void onChange(boolean on) {};
	protected JComponent addToContainer(Container container, int x, int y) { return null; }
	protected Device(String protocol) {
		this.setProtocol(protocol);
	}
	
	public void turnOn() {
		this.on = true;
		this.onChange(true);
	}

	public void turnOff() {
		this.on = false;
		this.onChange(false);
	}

	public boolean isOn() {
		return this.on;
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	private void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
