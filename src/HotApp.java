import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class HotApp implements ItemListener {

	private JFrame frame;
	JLabel lblLamp1;
	JLabel lblLamp2;
	
	Switch switch1;
	Lamp lamp1;
	Lamp lamp2; 

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
		
		switch1.attach(lamp1);
		switch1.attach(lamp2);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
				
		JToggleButton tglbtnNewToggleButton = (JToggleButton) switch1.addToContainer(frame.getContentPane(), 0, 1);
		tglbtnNewToggleButton.addItemListener(this);
		lblLamp1 = (JLabel) lamp1.addToContainer(frame.getContentPane(), 1, 1);
		lblLamp2 = (JLabel) lamp2.addToContainer(frame.getContentPane(), 1, 2);		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			switch1.turnOn();
	    } else if(e.getStateChange()==ItemEvent.DESELECTED){
	    		switch1.turnOff();
	    }
		
		lblLamp1.setText(String.valueOf(lamp1.isOn()));
		lblLamp2.setText(String.valueOf(lamp2.isOn()));
	}

}
