import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TempSensor extends Device {
    protected double temperature;
    protected double targetTemperature;


    protected TempSensor(double targetTemperature){
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
        if(!device.getProtocol().contains(this.getProtocol())) {
            throw new IncompatibleProtocolException();
        }
        devices.add(device);
        device.update(this.isOn());
    }


    //overload
	public void notifyObservers(boolean value, double temp) {

        this.temperature = temp;
        if(value)
            this.turnOn();
        else
            this.turnOff();
	}


    public void update(double targetTemperature) {

        if (targetTemperature > this.getTemperature()) {
            {
                for (Device device: devices) {
                    device.turnOn();
                    notifyObservers(true, targetTemperature);

                }
            }
        } else {
            for (Device device: devices) {
                device.turnOff();
                notifyObservers(false, targetTemperature);
            }
        }

    }



    public JComponent addToContainer(Container container, int x, int y) {


        JLabel lblSensor = new JLabel("Current Temperature " + this.getTemperature() + "ºC" );
        GridBagConstraints gbc_lblSensor = new GridBagConstraints();
        gbc_lblSensor.insets = new Insets(5, 5, 5, 5);
        gbc_lblSensor.gridx = x;
        gbc_lblSensor.gridy = y;
        container.add(lblSensor, gbc_lblSensor);
        return lblSensor;
    }

}

/*

 */