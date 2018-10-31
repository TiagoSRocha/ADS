
public class Hot {
	public static void main(String args[]) throws IncompatibleProtocolException {
		System.out.println("Hello House of Things!");
		
		Lamp lamp1 = new AcmeLamp();
		Switch switch1 = new AcmeSwitch();
		//Switch switch1 = new DoeSwitch(); // Incompatible protocols not yet supported

		System.out.println(String.valueOf(lamp1.isOn()));
		System.out.println(String.valueOf(switch1.isOn()));
				
		switch1.attach(lamp1);
		switch1.turnOn();
		
		System.out.println(String.valueOf(lamp1.isOn()));
		System.out.println(String.valueOf(switch1.isOn()));
		
		
		Lamp lamp2 = new AcmeLamp();
		lamp2.turnOn();
		Switch switch2 = new AcmeSwitch();
		//Switch switch2 = new DoeSwitch(); // Incompatible protocols not yet supported
		switch2.attach(lamp2);

		System.out.println(String.valueOf(lamp2.isOn()));
		System.out.println(String.valueOf(switch2.isOn()));
	
		switch2.turnOn();

		System.out.println(String.valueOf(lamp2.isOn()));
		System.out.println(String.valueOf(switch2.isOn()));
	}
}
