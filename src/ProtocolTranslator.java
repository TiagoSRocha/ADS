
public class ProtocolTranslator extends Device {
		
	public ProtocolTranslator(String toProtocol) {
		super(toProtocol);
	}

	/*public bolean interchangeProtocol(string currentProtocol, Switch switch){


		try {
			Method m = Device.class.getDeclaredMethod("setProtocol");
			m.setAccessible(true); //bypasses the private modifier
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch(currentProtocol)
		{
			case "ENL":
				m.invoke(currentProtocol + " DOE");
				break;
			case "DOE":
				m.invoke(currentProtocol + "ENL");
				break;
			default:
				System.out.println("no match");
		}
	}*/
	// TODO: to be completed
}
