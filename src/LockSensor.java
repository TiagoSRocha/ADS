import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LockSensor extends Device {
    protected boolean islocked;
    protected double targetLock;
    ArrayList<Device> devices = new ArrayList<Device>();


    protected LockSensor(boolean islocked){
            super("LOCK");
            this.islocked = islocked;

            }

    protected boolean getLock(int index){
            return devices.get(index).isOn();
            }



    @Override
    public void attach(Device device) throws IncompatibleProtocolException {
        if(!device.getProtocol().contains(this.getProtocol())) {
            throw new IncompatibleProtocolException();
            }
        devices.add(device);
        device.update(this.isOn());
    }


    //Overload
	public void notifyObservers(boolean value) {
		if(value)
		    this.turnOn();
		else
		    this.turnOff();
	}





public void update(boolean islocked, int index) {

            if(islocked == true)
                devices.get(index).turnOn();
            else
                devices.get(index).turnOff();

      this.islocked = islocked;
      notifyObservers(islocked);

}



public JComponent addToContainer(Container container, int x, int y) {

        //String input = JOptionPane.showInputDialog(null, "Desired Temperature:");
        //float temperature = Float.parseFloat(input);
        //update(true);
        JLabel lblSensor = new JLabel("Locked");
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