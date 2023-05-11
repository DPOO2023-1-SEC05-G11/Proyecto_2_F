package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Persistencia.Hotel;

public class panelReservas extends JPanel implements ActionListener{
	
	JButton btnCrearReservas, btnManejarReservas;

	public panelReservas() {
		
		btnCrearReservas = new JButton("Crear Reserva");
		add(btnCrearReservas);
		btnCrearReservas.addActionListener(this);
		
		btnManejarReservas = new JButton("Manejar Reserva");
		add(btnManejarReservas);
		btnManejarReservas.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btnCrearReservas) {
			VentanaCrearReserva crearReserva = new VentanaCrearReserva();
			crearReserva.setVisible(true);
		}else if (e.getSource() == btnManejarReservas) {
			String userInput = JOptionPane.showInputDialog(null, "Entre el ID del huesped principal de la reserva:");

			try {
				if (userInput != null && !userInput.isEmpty()) {
					VentanaManejarReserva manejarReserva = new VentanaManejarReserva(Hotel.getInstance().buscarReserva(userInput));
					manejarReserva.setVisible(true);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
			}
		}
	}
}
