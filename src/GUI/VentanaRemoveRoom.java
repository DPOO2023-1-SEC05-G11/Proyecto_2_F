package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaRemoveRoom extends JFrame implements ActionListener{

	private JPanel panelPrincipal;
	private JTextField textField;
	private JButton btnAceptar;
	
	public VentanaRemoveRoom() {
		setTitle("Remove Room");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 287, 147);
		setLocationRelativeTo(null);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelId = new JPanel();
		panelPrincipal.add(panelId);
		
		JLabel lbID = new JLabel("ID: ");
		panelId.add(lbID);
		
		textField = new JTextField();
		panelId.add(textField);
		textField.setColumns(10);
		
		JPanel panelAceptar = new JPanel();
		panelPrincipal.add(panelAceptar);
		
		btnAceptar = new JButton("Aceptar");
		panelAceptar.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);
		btnAceptar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAceptar) {
			try{
				int id = Integer.parseInt(textField.getText());
				int done = Hotel.getInstance().removerHabs1(id);
				if (done==1) {
					dispose();
					JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente!");
				} else if (done==0) {
					JOptionPane.showMessageDialog(null, "No existe esa habitacion");
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "Por favor entre un numero!");
			}
		}
		
	}

}