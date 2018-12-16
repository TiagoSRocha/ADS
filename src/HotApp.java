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
	JLabel lblTempSensor;
	JLabel lblLockSensor;
	
	AcmeSwitch acmeswitch;
	DoeSwitch doeswitch;
	Lamp lamp1;
	Lamp lamp2;
	TempSensor tempsensor;
	Heater heater;
	ProtocolTranslator protocol;
	LockSensor locksensor;
	Lock lock;
	Brain brain;
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
		brain =  Brain.getBrain();
		lamp1 = new AcmeLamp();
		lamp2 = new DoeLamp();
		acmeswitch = new AcmeSwitch();
		doeswitch = new DoeSwitch();
		lock = new Lock(false);
		locksensor = new LockSensor(false);
		heater = new Heater();
		tempsensor = new TempSensor(20);

		brain.attach(locksensor,tempsensor,doeswitch,acmeswitch);
		//brain.acmeswitch.attach(lamp1);

		brain.tempsensor.attach(heater);
		brain.locksensor.attach(lock);


		protocol = new ProtocolTranslator("DEFAULT");
		System.out.println(lamp1.getProtocol());
		brain.doeswitch.attach(lamp1);
		protocol.interchangeProtocol(doeswitch.getProtocol(), lamp1);
		System.out.println(lamp1.getProtocol());
		brain.doeswitch.attach(lamp1);
		brain.doeswitch.attach(lamp2);
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





		JButton b2 = new JButton("Lock");
		//b3.setMnemonic(KeyEvent.VK_E);
		b2.setActionCommand("lock");


		JButton b1 = new JButton("ACME Lamp");
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setActionCommand("acme");


		JButton b3 = new JButton("DOE Lamp");
		b3.setMnemonic(KeyEvent.VK_E);
		b3.setActionCommand("doe");

		JButton b4 = new JButton("Set Temperature");
		b4.setActionCommand("temp");

		b1.addActionListener(this);
		b3.addActionListener(this);
		b2.addActionListener(this);
		b4.addActionListener(this);



		frame.getContentPane().add(b1);
		frame.getContentPane().add(b3);
		frame.getContentPane().add(b2);
		frame.getContentPane().add(b4);
		//JToggleButton tglbtnNewToggleButton = (JToggleButton) switch1.addToContainer(frame.getContentPane(), 0, 1);
		//tglbtnNewToggleButton.addItemListener(this);
		lblLamp1 = (JLabel) lamp1.addToContainer(frame.getContentPane(), 0, 2);
		lblLamp2 = (JLabel) lamp2.addToContainer(frame.getContentPane(), 1, 2);
		lblLockSensor = (JLabel) brain.locksensor.addToContainer(frame.getContentPane(),2,2);
		lblTempSensor = (JLabel) brain.tempsensor.addToContainer(frame.getContentPane(), 0, 3);
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



	//Lock nao esta a alternar bem. Corrigir.

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand())
		{
			case "acme":
				if(brain.acmeswitch.isOn())
					brain.acmeswitch.update(false);
				else
					brain.acmeswitch.update(true);
				break;
			case "doe":
				if(brain.doeswitch.isOn())
					brain.doeswitch.update(false);
				else
					brain.doeswitch.update(true);
				break;
			case "lock":
				if(brain.locksensor.getLock() == false)
					brain.locksensor.update(true);
				else
					brain.locksensor.update(false);
				break;
			case "temp":
				String input = JOptionPane.showInputDialog(null, "Desired Temperature:");
				float temperature = Float.parseFloat(input);
				brain.tempsensor.update(temperature);
				break;

		}
		lblLamp1.setText(String.valueOf(lamp1.isOn()));
		lblLamp2.setText(String.valueOf(lamp2.isOn()));
		lblLockSensor.setText("Current Lock: " + brain.locksensor.getLock());
		lblTempSensor.setText("Current Temperature: " + tempsensor.getTemperature());




	}

}
