import java.util.ArrayList;

public class DoeSwitch extends Device {
	private double intensity;
	ArrayList<Device> devices = new ArrayList<Device>();
	public DoeSwitch() {
		super("DOE");
		intensity = 0.6;
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

	public void adjustIntensity(double value){
		if(value < 0.5)
		{
			this.turnOff();
			this.onChange(this.isOn());
		}
		else
		{
			intensity = value;
			System.out.println("Lamp adjusted to " + this.intensity);
		}

	}

}
