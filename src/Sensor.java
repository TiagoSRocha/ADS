import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sensor extends Device {

    protected double temperature;
    protected double targetTemperature;
    ArrayList<Device> devices = new ArrayList<Device>();
    protected Sensor(float temperature) {
        super("heat");
        this.temperature = 20.0;
        this.targetTemperature = temperature;

    }

    public void attach(Device device) {
        devices.add(device);
        String result = update(this.targetTemperature);

    }

    /*
    @Override - nao sei se fara sentido uma funcao destas aqui
	public void notifyObservers() {
		for (Device device: devices) {
			device.update(this.isOn());
		}
	}
     */

    protected double getTemperature(){
        return this.temperature;
    }

    //Overload method -- arranjar melhor forma
    public String update(double targetTemperature) {
        if (targetTemperature > this.getTemperature()) {
            {
                for (Device device: devices) {
                    device.turnOn();
                    this.temperature = targetTemperature;
                    return "Heater on.";
                }
            }
        } else {
            for (Device device: devices) {
                device.turnOff();
                this.temperature = targetTemperature;
            }
        }
        return "Heater off.";
    }

    public JComponent addToContainer(Container container, int x, int y) {

        String input = JOptionPane.showInputDialog(null, "Desired Temperature:");
        float temperature = Float.parseFloat(input);
        update(temperature);
        JLabel lblSensor = new JLabel("Current Temperature " + this.getTemperature() + "ºC" );
        GridBagConstraints gbc_lblSensor = new GridBagConstraints();
        gbc_lblSensor.insets = new Insets(0, 0, 5, 0);
        gbc_lblSensor.gridx = x;
        gbc_lblSensor.gridy = y;
        container.add(lblSensor, gbc_lblSensor);
        return lblSensor;
    }
}