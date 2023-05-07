package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;

public class VentanaAddRoom extends JFrame implements ActionListener {

	private JPanel panelPrincipal;
	private JTextField textId;
	private JTextField textTarifa;
	private JPanel panelOp;
	protected JButton btnAddCama, btnCancel, btnDone;

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAddRoom frame = new VentanaAddRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public VentanaAddRoom() {
		setTitle("Añadir Habitacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		panelSuperior.add(lblId);
		
		textId = new JTextField();
		panelSuperior.add(textId);
		textId.setColumns(10);
		
		JLabel lblTarifa = new JLabel("Tarifa:");
		lblTarifa.setHorizontalAlignment(SwingConstants.LEFT);
		panelSuperior.add(lblTarifa);
		
		textTarifa = new JTextField();
		panelSuperior.add(textTarifa);
		textTarifa.setColumns(10);
		
		panelOp = new JPanel();
		panelPrincipal.add(panelOp, BorderLayout.CENTER);
		panelOp.setLayout(new GridLayout(0,1, 0, 0));
		
		JPanel panelTipos = new JPanel();
		panelOp.add(panelTipos);
		
		JLabel lblTipo = new JLabel("Tipo:");
		panelTipos.add(lblTipo);
		JRadioButton btnDoble = new JRadioButton("Doble", false);
		panelTipos.add(btnDoble);
		
		// GRUPO DE BOTONES #############
		
		ButtonGroup roomTypeButtonGroup = new ButtonGroup();
		roomTypeButtonGroup.add(btnDoble); 
		JRadioButton btnEstandar = new JRadioButton("Estandar", false);
		panelTipos.add(btnEstandar);
		roomTypeButtonGroup.add(btnEstandar);
		JRadioButton btnSuite = new JRadioButton("Suite", false);
		panelTipos.add(btnSuite);
		roomTypeButtonGroup.add(btnSuite);
		
		JPanel panelCama = new JPanel();
		panelOp.add(panelCama);
		JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
		panelCama.add(lblCaracteristicas);
		
		
		// CHECK BOXES #####################
		
		JCheckBox btnBalcon = new JCheckBox("Balcon");
		panelCama.add(btnBalcon);
		
		JCheckBox btnVista = new JCheckBox("Vista");
		panelCama.add(btnVista);
		
		JCheckBox btnCocina = new JCheckBox("Cocina");
		panelCama.add(btnCocina);
		
		JPanel panelCarac = new JPanel();
		panelOp.add(panelCarac);
		
		panelCarac.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblcama = new JLabel("Cama:");
		panelCarac.add(lblcama);
		
		// J LIST ##########
	
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"fasfas", "safasg", "dsafadsf"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		panelCarac.add(list);
		
		btnAddCama = new JButton("Añadir cama");
		btnAddCama.addActionListener(this);
		panelCarac.add(btnAddCama);
		
		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		panelInferior.add(btnCancel);
		
		btnDone = new JButton("Aceptar");
		panelInferior.add(btnDone);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAddCama) {
			dialogCama dialogo = new dialogCama();
			dialogo.setVisible(true);
		} 
		
	}
	public void setId(String id) {
		textId.setText(id);
	}
	public void setTarifa(String tarifa) {
		textTarifa.setText(tarifa);
	}
	
	

}
