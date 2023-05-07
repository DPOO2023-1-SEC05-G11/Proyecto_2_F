package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaRemoveRoom extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textField;

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
		
		JButton btnAceptar = new JButton("Aceptar");
		panelAceptar.add(btnAceptar);
	}

}
