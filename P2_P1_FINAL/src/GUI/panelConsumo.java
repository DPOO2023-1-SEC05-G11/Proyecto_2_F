package GUI;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.FlowLayout;

public class panelConsumo extends JPanel {
	private JTextField textHuesped;
	private JTextField textField;
	
	public panelConsumo() {
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelIzq = new JPanel();
		add(panelIzq);
		panelIzq.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panelIzq.add(panel);
		
		JLabel lblHuesped = new JLabel("Huesped: ");
		panel.add(lblHuesped);
		
		textHuesped = new JTextField();
		panel.add(textHuesped);
		textHuesped.setColumns(10);
		
		JPanel panelHuesPrinc = new JPanel();
		panelIzq.add(panelHuesPrinc);
		
		JLabel lblPrincipal = new JLabel("Huesped principal:");
		panelHuesPrinc.add(lblPrincipal);
		
		textField = new JTextField();
		panelHuesPrinc.add(textField);
		textField.setColumns(10);
		
		JPanel panelTipo = new JPanel();
		panelIzq.add(panelTipo);
		
		JLabel lblPrincipal_1 = new JLabel("Tipo: ");
		panelTipo.add(lblPrincipal_1);
		
		ButtonGroup grupoTipo = new ButtonGroup();
		
		JRadioButton individual = new JRadioButton("Individual", false);
		panelTipo.add(individual);
		grupoTipo.add(individual);
		JRadioButton habitacion = new JRadioButton("Habitacion", false);
		panelTipo.add(habitacion);
		grupoTipo.add(habitacion);
		JRadioButton grupo = new JRadioButton("Individual", false);
		panelTipo.add(grupo);
		grupoTipo.add(grupo);
		
		JPanel panelPagado = new JPanel();
		panelIzq.add(panelPagado);
		
		JLabel lblPagado = new JLabel("Ya Pagado:");
		panelPagado.add(lblPagado);
		
		ButtonGroup grupoPagado = new ButtonGroup();
		JRadioButton btnSi = new JRadioButton("SI", false);
		panelPagado.add(btnSi);
		grupoPagado.add(btnSi);
		JRadioButton btnNo = new JRadioButton("NO", false);
		panelPagado.add(btnNo);
		grupoPagado.add(btnNo);
		
		JPanel panelInferior = new JPanel();
		panelIzq.add(panelInferior);
		
		JButton btnCancel = new JButton("Cancelar");
		panelInferior.add(btnCancel);
		
		JButton btnFacturar = new JButton("Facturar");
		panelInferior.add(btnFacturar);
		
		JPanel panelDer = new JPanel();
		add(panelDer);
		panelDer.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelServ = new JPanel();
		panelDer.add(panelServ);
		panelServ.setLayout(new GridLayout(0, 2, 0, 0));
		
		JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Restaurante", "Spa", "Guia"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panelServ.add(list);
		
		JPanel panel_2 = new JPanel();
		panelServ.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblServ = new JLabel("Servicio seleccionado: ");
		panel_2.add(lblServ);
		lblServ.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_2.add(lblNewLabel);
		
		JPanel panelListaProd = new JPanel();
		panelDer.add(panelListaProd);
		panelListaProd.setLayout(new GridLayout(0, 2, 0, 0));
		
		JList listProd = new JList();
		listProd.setModel(new AbstractListModel() {
			String[] values = new String[] {"Huevo", "Arroz", "etc"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		panelListaProd.add(listProd);
		
		JPanel panel_1 = new JPanel();
		panelListaProd.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("Add");
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setHorizontalAlignment(SwingConstants.LEADING);
		panel_1.add(btnRemove);
		
		

	}

}
