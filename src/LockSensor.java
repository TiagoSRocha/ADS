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

    protected boolean getLock(){
            return this.islocked;
            }



    @Override
    public void attach(Device device) throws IncompatibleProtocolException {
        if(!device.getProtocol().contains(this.getProtocol())) {
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
      for (Device device: devices) {
            if(islocked == true)
                device.turnOn();
            else
                device.turnOff();

      this.islocked = islocked;
     }
}



public JComponent addToContainer(Container container, int x, int y) {

        //String input = JOptionPane.showInputDialog(null, "Desired Temperature:");
        //float temperature = Float.parseFloat(input);
        //update(true);
        JLabel lblSensor = new JLabel("Current Lock: " + this.getLock());
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