import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class lockSensor extends Device {
    protected boolean islocked;
    protected double targetLock;
    ArrayList<Device> devices = new ArrayList<Device>();


    protected lockSensor(boolean islocked){
            super("LOCK");
            this.islocked = islocked;

            }

    protected boolean getLock(){
            return this.islocked;
            }



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


    //ver esta funçao logica de adapar a fechadura

public void update(boolean islocked) {

        if (islocked != this.getLock()) {
        {
        for (Device device: devices) {
        device.turnOn();
        this.islocked = islocked;
        }
        }
        } else {
        for (Device device: devices) {
        device.turnOff();
        this.islocked = islocked;
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