package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;
import Persistencia.Hotel;

import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;



public class VentanaLogin extends JFrame implements ActionListener {
	
	JPanel panelPrincipal;
	JTextField textFieldUsuario, textFieldContraseña;
	JButton btnNewButton_1;
	String tipo;

	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 459, 302);
		setLocationRelativeTo(null);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.setBounds(181, 158, 85, 21);
		panelPrincipal.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		getRootPane().setDefaultButton(btnNewButton_1);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(138, 83, 189, 19);
		panelPrincipal.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(138, 112, 189, 19);
		panelPrincipal.add(textFieldContraseña);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(54, 86, 45, 13);
		panelPrincipal.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(54, 115, 74, 13);
		panelPrincipal.add(lblPassword);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnNewButton_1) {
			String usuario = textFieldUsuario.getText();
			String contra = textFieldContraseña.getText();
			tipo = Hotel.getInstance().acceso1(usuario, contra);
			if (tipo==null) {
				JOptionPane.showMessageDialog(null, "Incorrecto");
				textFieldUsuario.setText("");
				textFieldContraseña.setText("");
			} else if (tipo.equals("empleado")) {
				VentanaEmpleado ventana = new VentanaEmpleado();
				ventana.setVisible(true);
				dispose();
			} else if (tipo.equals("admin")) {
				VentanaAdmin ventana = new VentanaAdmin();
				ventana.setVisible(true);
				dispose();
				}
			
		}
	}
}
