package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class VentanaCrearReserva extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textFieldInicio;
	private JTextField textFieldFinal;
	private JTextField textFieldNoches;
	private JTextField textFieldNombre;
	private JTextField textFieldDocumento;
	private JTextField textFieldCorreo;
	private JTextField textFieldTelefono;

	public VentanaCrearReserva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 439);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel.add(btnAceptar);
		
		JPanel panel_1 = new JPanel();
		panelPrincipal.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFechai = new JLabel("Fecha de incio:");
		panel_1.add(lblFechai);
		
		textFieldInicio = new JTextField();
		panel_1.add(textFieldInicio);
		textFieldInicio.setColumns(10);
		
		JLabel lblFechaf = new JLabel("Fecha final:");
		panel_1.add(lblFechaf);
		
		textFieldFinal = new JTextField();
		panel_1.add(textFieldFinal);
		textFieldFinal.setColumns(10);
		
		JLabel lblNoches = new JLabel("Numero de noches:");
		panel_1.add(lblNoches);
		
		textFieldNoches = new JTextField();
		panel_1.add(textFieldNoches);
		textFieldNoches.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre huesped principal:");
		panel_1.add(lblNombre);
		
		textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDoc = new JLabel("Documento:");
		panel_1.add(lblDoc);
		
		textFieldDocumento = new JTextField();
		panel_1.add(textFieldDocumento);
		textFieldDocumento.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		panel_1.add(lblCorreo);
		
		textFieldCorreo = new JTextField();
		panel_1.add(textFieldCorreo);
		textFieldCorreo.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		panel_1.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		panel_1.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"hab 1", "hab 2 ", "hab 3", "hab 4"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panel_2.add(list);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("Añadir");
		panel_3.add(btnAdd);
		
		JButton btnRemove = new JButton("Eliminar");
		panel_3.add(btnRemove);
	}

}
