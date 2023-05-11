package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Habitacion;

import java.awt.BorderLayout;
import javax.swing.JLabel;

public class VentanaShowRoom extends JFrame {

	private JPanel panelPrincipal;

	public VentanaShowRoom(Habitacion hab) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel, BorderLayout.NORTH);
		
		JLabel lblId = new JLabel("ID: "+hab.getId());
		panel.add(lblId);
		
		JLabel IdChangeable = new JLabel("0");
		panel.add(IdChangeable);
		
		JLabel lblTarifa = new JLabel("Tarifa: "+hab.getTarifa());
		panel.add(lblTarifa);
		
		JLabel tarifaChangeable = new JLabel("0");
		panel.add(tarifaChangeable);
		
		JLabel lblEspacioNinos = new JLabel("Espacio ninos: "+hab.getEspacioNinos());
		panel.add(lblEspacioNinos);

		JLabel lblEspacioAdultos = new JLabel("Espacio adultos: "+hab.getEspacioAdultos());
		panel.add(lblEspacioAdultos);

		JPanel panel_1 = new JPanel();
		panelPrincipal.add(panel_1, BorderLayout.CENTER);
	}

}
