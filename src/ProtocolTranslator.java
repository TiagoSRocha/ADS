
public class ProtocolTranslator extends Device {

	public java.lang.reflect.Method method;
	public ProtocolTranslator(String toProtocol) {
		super(toProtocol);
	}


	public boolean interchangeProtocol(String requiredProtocol, Device D) {
		String help = D.getProtocol() + "," + requiredProtocol;
		try {
			if(D.getProtocol().contains(requiredProtocol))
				return true;
			else
			{
				method = Device.class.getDeclaredMethod("setProtocol",requiredProtocol.getClass());
				method.setAccessible(true);
				method.invoke(D, help);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// TODO: to be completed
}
