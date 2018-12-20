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
	JLabel lblLockSensor, lblLockSensor1, lblLockSensor2;
	JLabel lblHome;
	JComboBox combo1;
	
	AcmeSwitch acmeswitch;
	DoeSwitch doeswitch;
	Lamp lamp1;
	Lamp lamp2;
	TempSensor tempsensor;
	Heater heater;
	ProtocolTranslator protocol;
	LockSensor locksensor;
	Lock lock, lock1, lock2;
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
		lock1 = new Lock(false);
		lock2 = new Lock(false);
		brain.attach(locksensor,tempsensor,doeswitch,acmeswitch);


		brain.tempsensor.attach(heater);
		brain.locksensor.attach(lock);
		brain.locksensor.attach(lock1);
		brain.locksensor.attach(lock2);

		protocol = new ProtocolTranslator("DEFAULT");
		System.out.println(lamp1.getProtocol());
		protocol.interchangeProtocol(doeswitch.getProtocol(), lamp1);
		protocol.interchangeProtocol(acmeswitch.getProtocol(), lamp2);
		System.out.println(lamp1.getProtocol());
		brain.doeswitch.attach(lamp1);
		brain.acmeswitch.attach(lamp2);
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
		b2.setActionCommand("lock");


		JButton b1 = new JButton("ACME Lamp");
		b1.setMnemonic(KeyEvent.VK_D);
		b1.setActionCommand("acme");


		JButton b3 = new JButton("DOE Lamp");
		b3.setMnemonic(KeyEvent.VK_E);
		b3.setActionCommand("doe");

		JButton b4 = new JButton("Set Temperature");
		b4.setActionCommand("temp");

		JButton b5= new JButton("Home");
		b5.setActionCommand("home");





		 combo1 = new JComboBox(
				new String[]{"Lock 1", "Lock 2", "Lock 3"});

		b1.addActionListener(this);
		b3.addActionListener(this);
		b2.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);




		frame.getContentPane().add(b1);
		frame.getContentPane().add(b3);
		frame.getContentPane().add(b4);
		frame.getContentPane().add(b2);
		frame.getContentPane().add(combo1);
		frame.getContentPane().add(b5);


		lblLamp1 = (JLabel) lamp1.addToContainer(frame.getContentPane(), 1, 2);
		lblLamp2 = (JLabel) lamp2.addToContainer(frame.getContentPane(), 0, 2);
		lblLockSensor = (JLabel) brain.locksensor.addToContainer(frame.getContentPane(),0,3);
		lblLockSensor1 = (JLabel) brain.locksensor.addToContainer(frame.getContentPane(),1,3);
		lblLockSensor2 = (JLabel) brain.locksensor.addToContainer(frame.getContentPane(),2,3);
		lblTempSensor = (JLabel) brain.tempsensor.addToContainer(frame.getContentPane(), 0, 4);
		lblHome = (JLabel) brain.locksensor.addToContainer(frame.getContentPane(), 2, 4);
	}


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
			case "home":
				if(brain.getAtHome())
					brain.setAtHome(false);
				else
					brain.setAtHome(true);
				break;
			case "doe":
				if(brain.doeswitch.isOn())
					brain.doeswitch.update(false);
				else
					brain.doeswitch.update(true);
				break;
			case "lock":
				int i = combo1.getSelectedIndex();
				if(brain.locksensor.getLock(i) == false)
					brain.locksensor.update(true, i);
				else
					brain.locksensor.update(false,i);
				break;
			case "temp":
				String input = JOptionPane.showInputDialog(null, "Desired Temperature:");
				if(input != null)
				{
					float temperature = Float.parseFloat(input);
					brain.tempsensor.update(temperature);
				}
				break;
		}

		if(lamp1.isOn())
			lblLamp1.setText("On");
		else
			lblLamp1.setText("Off");

		if(lamp2.isOn())
			lblLamp2.setText("On");
		else
			lblLamp2.setText("Off");

		if(brain.locksensor.getLock(0))
			lblLockSensor.setText("Lock1: Locked");
		else
			lblLockSensor.setText("Lock1: Unlocked");



		if(brain.locksensor.getLock(1))
			lblLockSensor1.setText("Lock2: Locked");
		else
			lblLockSensor1.setText("Lock2: Unlocked");



		if(brain.locksensor.getLock(2))
			lblLockSensor2.setText("Lock3: Locked");
		else
			lblLockSensor2.setText("Lock3: Unlocked");

		lblTempSensor.setText("Current Temperature: " + tempsensor.getTemperature() + "ºC");

		if(brain.getAtHome())
			lblHome.setText("I'm home.");
		else{
			lblHome.setText("Nobody's Home. Everything's Locked.");
			brain.locksensor.update(true, 0);
			brain.locksensor.update(true, 1);
			brain.locksensor.update(true, 2);
			lblLockSensor.setText("Lock1: Locked");
			lblLockSensor1.setText("Lock2: Locked");
			lblLockSensor2.setText("Lock3: Locked");
		}




	}

}
