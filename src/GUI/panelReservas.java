package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class panelReservas extends JPanel implements ActionListener{
	
	JButton btnReservas, btnInfo;

	public panelReservas() {
		
		btnReservas = new JButton("Crear");
		add(btnReservas);
		btnReservas.addActionListener(this);
		btnInfo = new JButton("Info");
		add(btnInfo);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btnReservas) {
			VentanaCrearReserva crearReserva = new VentanaCrearReserva();
			crearReserva.setVisible(true);
		}
	}

}
