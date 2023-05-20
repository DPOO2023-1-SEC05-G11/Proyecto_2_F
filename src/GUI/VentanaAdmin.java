package GUI;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class VentanaAdmin extends JFrame {
	private JTabbedPane pestañas;
	private panelRooms panel1;
	private panelServices panel2;
	private panelOcupacion panel3;
	
	public VentanaAdmin() {
		setLocationRelativeTo(null);
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		pestañas = new JTabbedPane();
		//pestañas.add("")
		panel1 = new panelRooms();
		panel2= new panelServices();
		panel3 = new panelOcupacion();
		pestañas.add("Services", panel2);
		pestañas.add("Rooms", panel1);
		pestañas.add("Ocupacion", panel3);
		getContentPane().add(pestañas);


		
	}

}
