import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class tempSensor extends Device {
    protected double temperature;
    protected double targetTemperature;


    protected tempSensor(double targetTemperature){
        super("HEAT");
        this.temperature = 20.0;
        this.targetTemperature = targetTemperature;
    }

    protected double getTemperature(){
        return this.temperature;
    }



    ArrayList<Device> devices = new ArrayList<Device>();


    @Override
    public void attach(Device device) throws IncompatibleProtocolException {
        if(!device.getProtocol().equals(this.getProtocol())) {
            throw new IncompatibleProtocolException();
        }
        devices.add(device);
        device.update(this.isOn());
    }

    /*
    @Override - nao sei se fara sentido uma funcao destas aqui
	public void notifyObservers() {
		for (Device device: devices) {
			device.update(this.isOn());
		}
	}
     */

    public void update(double targetTemperature) {

        if (targetTemperature > this.getTemperature()) {
            {
                for (Device device: devices) {
                    device.turnOn();
                    this.temperature = targetTemperature;

                }
            }
        } else {
            for (Device device: devices) {
                device.turnOff();
                this.temperature = targetTemperature;
            }
        }

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

/*

 */