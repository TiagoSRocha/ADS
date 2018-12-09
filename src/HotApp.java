import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.*;
import java.awt.event.*;

public class HotApp implements ActionListener {

	private JFrame frame;
	JLabel lblLamp1;
	JLabel lblLamp2;
	JLabel lblSensor;
	
	Switch switch1;
	Lamp lamp1;
	Lamp lamp2;
	Sensor sensor;
	Heater heater;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotApp window = new HotApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IncompatibleProtocolException 
	 */
	public HotApp() throws IncompatibleProtocolException {
		lamp1 = new AcmeLamp();
		lamp2 = new AcmeLamp();
		switch1 = new AcmeSwitch();
		//switch1 = new DoeSwitch(); // Incompatible protocols not yet supported
		heater = new Heater();
		sensor = new Sensor(20);
		switch1.attach(lamp1);
		switch1.attach(lamp2);
		sensor.attach(heater);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);




		JButton b1 = new JButton("Disable Lamp");
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setActionCommand("disable");


		JButton b3 = new JButton("Enable Lamp");
		b3.setMnemonic(KeyEvent.VK_E);
		b3.setActionCommand("enable");

		b1.addActionListener(this);
		b3.addActionListener(this);

		frame.getContentPane().add(b1);
		frame.getContentPane().add(b3);
		//JToggleButton tglbtnNewToggleButton = (JToggleButton) switch1.addToContainer(frame.getContentPane(), 0, 1);
		//tglbtnNewToggleButton.addItemListener(this);
		lblLamp1 = (JLabel) lamp1.addToContainer(frame.getContentPane(), 1, 2);
		lblLamp2 = (JLabel) lamp2.addToContainer(frame.getContentPane(), 0, 2);
		lblSensor = (JLabel) sensor.addToContainer(frame.getContentPane(), 3, 3);
	}

	/*@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			switch1.turnOn();
	    } else if(e.getStateChange()==ItemEvent.DESELECTED){
	    		switch1.turnOff();
	    }
		
		lblLamp1.setText(String.valueOf(lamp1.isOn()));
		lblLamp2.setText(String.valueOf(lamp2.isOn()));
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("disable".equals(e.getActionCommand())) {
			switch1.turnOff();
		} else {
			switch1.turnOn();
		}
		lblLamp1.setText(String.valueOf(lamp1.isOn()));
		lblLamp2.setText(String.valueOf(lamp2.isOn()));

	}

}
