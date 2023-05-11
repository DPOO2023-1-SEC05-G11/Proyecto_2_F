package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Habitacion;
import Persistencia.Hotel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaSearchRoom extends JFrame implements ActionListener{

	private JPanel panelPrincipal;
	private JTextField textFieldId;
	private JButton btnAceptar;

	public VentanaSearchRoom() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 343, 155);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelId = new JPanel();
		panelPrincipal.add(panelId);
		
		JLabel lbId = new JLabel("ID: ");
		panelId.add(lbId);
		
		textFieldId = new JTextField();
		panelId.add(textFieldId);
		textFieldId.setColumns(10);
		
		JPanel panelAceptar = new JPanel();
		panelPrincipal.add(panelAceptar);
		
		btnAceptar = new JButton("Aceptar");
		panelAceptar.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);
		btnAceptar.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			if (e.getSource()==btnAceptar) {
				int id = Integer.parseInt(textFieldId.getText());
				Habitacion hab = Hotel.getInstance().buscarHabs1(id);
				if (hab==null) {
					JOptionPane.showMessageDialog(null, "La habitacion no existe");
				} else {
					VentanaShowRoom ventana = new VentanaShowRoom(hab);
					ventana.setVisible(true);
					dispose();
				}
			
			}
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null, "Por favor entre un numero!");
		}
	}
}