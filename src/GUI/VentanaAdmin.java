package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Component;



public class VentanaAdmin extends JFrame {
	private JTabbedPane pestañas;
	private panelRooms panel1;
	private panelServices panel2;
	
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
		pestañas.add("Services", panel2);
		pestañas.add("Rooms", panel1);
		getContentPane().add(pestañas);


		
	}

}
