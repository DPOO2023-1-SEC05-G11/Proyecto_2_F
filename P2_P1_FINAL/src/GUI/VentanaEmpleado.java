package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class VentanaEmpleado extends JFrame {

	private JPanel panelPrincipal;
	private panelReservas panel1;
	private panelConsumo panel2;
	private JTabbedPane pestañas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleado frame = new VentanaEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 500);
		setLocationRelativeTo(null);
		
		pestañas = new JTabbedPane();
		
		panel2 = new panelConsumo();
		panel1 = new panelReservas();
		pestañas.add("Reservas", panel1);
		pestañas.add("Nuevo Consumo",panel2);
		
		
		getContentPane().add(pestañas);
		

}
}
