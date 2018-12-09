
public class DoeSwitch extends Switch {
	private double intensity;
	public DoeSwitch() {
		super("DOE");
		intensity = 0.6;
	}

	@Override
	public void update(boolean on) {
	//Em primeiro lugar acho qe esta fun�ao deveria atualizar o valor de ON. E notificar toda a gente disso.

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
