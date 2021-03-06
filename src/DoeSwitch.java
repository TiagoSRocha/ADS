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
				notifyObservers(true);
			}
			else{
				device.turnOff();
				notifyObservers(false);
			}
		}
	}

	//Overload
	public void notifyObservers(boolean value) {
		if(value)
			this.turnOn();
		else
			this.turnOff();
	}



	@Override
	public void attach(Device device) throws IncompatibleProtocolException {
		if(!device.getProtocol().contains(this.getProtocol())) {
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
