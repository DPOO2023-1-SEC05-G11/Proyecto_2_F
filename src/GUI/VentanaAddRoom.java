package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Modelo.Cama;
import Persistencia.Hotel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.AbstractListModel;

public class VentanaAddRoom extends JFrame implements ActionListener {

	private JPanel panelPrincipal;
	private JTextField textId;
	private JTextField textTarifa;
	private JPanel panelOp;
	protected JButton btnAddCama, btnCancel, btnDone;
	protected JRadioButton btnDoble, btnEstandar, btnSuite;
	protected JCheckBox btnVista, btnCocina, btnBalcon;
	protected JList<String> list;
	protected DefaultListModel<String> listaa;
	
	
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
		
		// GRUPO DE BOTONES #############
		
		ButtonGroup roomTypeButtonGroup = new ButtonGroup();
		btnDoble = new JRadioButton("Doble", false);
		panelTipos.add(btnDoble);
		roomTypeButtonGroup.add(btnDoble); 
		btnEstandar = new JRadioButton("Estandar", false);
		panelTipos.add(btnEstandar);
		roomTypeButtonGroup.add(btnEstandar);
		btnSuite = new JRadioButton("Suite", false);
		panelTipos.add(btnSuite);
		roomTypeButtonGroup.add(btnSuite);
		
		JPanel panelCama = new JPanel();
		panelOp.add(panelCama);
		JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
		panelCama.add(lblCaracteristicas);
		
		
		// CHECK BOXES #####################
		
		btnBalcon = new JCheckBox("Balcon");
		panelCama.add(btnBalcon);
		
		btnVista = new JCheckBox("Vista");
		panelCama.add(btnVista);
		
		btnCocina = new JCheckBox("Cocina");
		panelCama.add(btnCocina);
		
		JPanel panelCarac = new JPanel();
		panelOp.add(panelCarac);
		
		panelCarac.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblcama = new JLabel("Cama:");
		panelCarac.add(lblcama);
		
		// J LIST ##########
	
		list = new JList<String>();
		listaa = new DefaultListModel<String>();

		JScrollPane scrollPaneLista = new JScrollPane(list);
		scrollPaneLista.setPreferredSize(new Dimension(200, 50));
		
		panelCarac.add(scrollPaneLista);
		
		btnAddCama = new JButton("Añadir cama");
		btnAddCama.addActionListener(this);
		panelCarac.add(btnAddCama);
		
		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		panelInferior.add(btnCancel);
		btnCancel.addActionListener(this);
		
		btnDone = new JButton("Aceptar");
		panelInferior.add(btnDone);
		btnDone.addActionListener(this);
		list.setModel(listaa);

		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tipo = "";
		ArrayList<Integer> listaNums = new ArrayList<>();
		if (e.getSource()==btnAddCama) {
			dialogCama dialogo = new dialogCama(VentanaAddRoom.this);
			dialogo.setVisible(true);
		} else if (e.getSource()==btnDone) {
			
			try {
				int id;
				id = Integer.parseInt(textId.getText());
				
				if (btnEstandar.isSelected()) {
					tipo = "estandar";
				} else if (btnSuite.isSelected()){
					tipo = "suite";
				} else if (btnDoble.isSelected()) {
					tipo = "doble";
				}
				Boolean vista = btnVista.isSelected();
				Boolean balcon = btnBalcon.isSelected();
				Boolean cocina = btnCocina.isSelected();
				Double tarifa = Double.parseDouble(textTarifa.getText());
				
				for (int i =0; i<listaa.getSize();i++) {
					if (listaa.getElementAt(i).equals("")||listaa.getElementAt(i)==null) {
						listaNums.add(0);
					} else if (listaa.getElementAt(i).equals("King")) {
						listaNums.add(1);
					} else if (listaa.getElementAt(i).equals("Queen")) {
						listaNums.add(2);
					} else if (listaa.getElementAt(i).equals("Doble")) {
						listaNums.add(3);
					} else if (listaa.getElementAt(i).equals("Sencilla")) {
						listaNums.add(4);
					} else if (listaa.getElementAt(i).equals("Niños")) {
						listaNums.add(5);
					}
				}
				ArrayList<Cama> listaCamas = new ArrayList<>();
				for (int num: listaNums ) {
					Cama camaa = new Cama(num);
					listaCamas.add(camaa);
				}
				
				Hotel.getInstance().anadirHabs1(id, tipo, balcon, vista, cocina, tarifa, false, listaCamas);
				JOptionPane.showMessageDialog(null, "Se añadio correctamente");	
				
			} catch (NumberFormatException e1) {
				System.err.println("Exception occurred: " + e1);
				JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid numeric value.");
			} catch (Exception e1) {
				System.err.println("Exception occurred: " + e1);
				JOptionPane.showMessageDialog(null, "An error occurred. Please check the input.");
			}

			
			
			
		}else if(e.getSource()==btnCancel) {
			dispose();
		}
	}
	public void addLista(String element) {
		listaa.addElement(element);
	}	
	public void setId(String id) {
		textId.setText(id);
	}
	public void setTarifa(String tarifa) {
		textTarifa.setText(tarifa);
	}
	
	

}