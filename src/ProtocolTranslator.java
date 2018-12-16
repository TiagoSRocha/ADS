
public class ProtocolTranslator extends Device {

	public java.lang.reflect.Method method;
	public ProtocolTranslator(String toProtocol) {
		super(toProtocol);
	}

	//tou a usar reflexion aqui pa usar o metodo do Pai
	public boolean interchangeProtocol(String requiredProtocol, Device D) {
		String help = D.getProtocol() + "," + requiredProtocol;
		try {
			if(D.getProtocol().contains(requiredProtocol))
				return true;
			else
			{
				method = Device.class.getDeclaredMethod("setProtocol",requiredProtocol.getClass());
				method.setAccessible(true); //bypasses the private modifier
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
