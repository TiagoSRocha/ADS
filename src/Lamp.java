import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;

public abstract class Lamp extends Device  {

    protected Lamp(String protocol) {
        super(protocol);
    }

    @Override
    public void update(boolean on) {
        if (on) {
            this.turnOn();
        } else {
            this.turnOff();
        }
    }

    public JComponent addToContainer(Container container, int x, int y) {
        JLabel lblLamp1 = new JLabel("Lamp1");
        GridBagConstraints gbc_lblLamp1 = new GridBagConstraints();
        gbc_lblLamp1.insets = new Insets(0, 0, 5, 0);
        gbc_lblLamp1.gridx = x;
        gbc_lblLamp1.gridy = y;
        container.add(lblLamp1, gbc_lblLamp1);
        return lblLamp1;
    }

}