package GUI;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Persistencia.Hotel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.FlowLayout;
import java.awt.Frame;

public class panelConsumo extends JPanel {
	private JTextField textHuesped;
	private JTextField textField;
	private static double precioTotal = 0.0;
	private static DefaultListModel<String> listModel;
    private static JLabel lblPrecioTotal;
	
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
		
		JList<String> list = new JList<>();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);

		Set<String> servicios = Hotel.getInstance().getServicios().keySet();
		String[] values = servicios.toArray(new String[servicios.size()]);

		list.setModel(new AbstractListModel<String>() {
			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index].substring(0, 1).toUpperCase() + values[index].substring(1); 
			}
		});

		JScrollPane scrollPaneList = new JScrollPane(list);

		panelServ.add(scrollPaneList);

		

		
		JPanel panel_2 = new JPanel();
		panelServ.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblServ = new JLabel("Servicio seleccionado: ");
		panel_2.add(lblServ);
		lblServ.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblServSeleccionado = new JLabel("\n Ningun seleccion");
		panel_2.add(lblServSeleccionado);

		

		JLabel lblPrecioTotal = new JLabel("Precio Total: $COP "+precioTotal);
		panel_2.add(lblPrecioTotal);
		lblPrecioTotal.setHorizontalAlignment(SwingConstants.RIGHT);

		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				String selectedItem = list.getSelectedValue();
				lblServSeleccionado.setText(selectedItem);
			}
		});


		JPanel panelListaProd = new JPanel();
		panelDer.add(panelListaProd);
		panelListaProd.setLayout(new GridLayout(0, 2, 0, 0));
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> listProd = new JList<>(listModel);

		JScrollPane scrollPaneProd = new JScrollPane(listProd);
		panelListaProd.add(scrollPaneProd);
		
		JPanel panel_1 = new JPanel();
		panelListaProd.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("Add Producto");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{

				

				String service = lblServSeleccionado.getText().toLowerCase();
				
				//precioTotal += 
				addProdDialog(panelConsumo.this, Hotel.getInstance().getServicios().get(service).getMap());

				}catch(Exception ex){
					System.out.println("An error occurred while executing crearReserva: " + ex.getMessage());
					JOptionPane.showMessageDialog(null, "Seleccione un tipo de servicio.", "Error!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove Producto");
		btnRemove.setHorizontalAlignment(SwingConstants.LEADING);
		panel_1.add(btnRemove);
		

	}


	private static void addProdDialog(JPanel parentPanel, HashMap<String, Integer> hashMap) {
		double precio = 0.0;

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Custom Dialog", true);
        dialog.setPreferredSize(new Dimension(300, 200));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel2);

		for (Map.Entry<String,Integer> entry : hashMap.entrySet())
		{
			listModel2.addElement(entry.getKey() + ", $COP: " + entry.getValue());
		}

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				String selectedItem = list.getSelectedValue();
				if (selectedItem == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una opcion!", "Nada Seleccionada!", JOptionPane.WARNING_MESSAGE);
				} else {
					String nombre = selectedItem.split(",")[0].trim();
					int precio = hashMap.get(nombre);
		
					// Add the selected item to the listProd
					listModel.addElement(selectedItem);
		
					// Update the total price
					precioTotal += precio;
					lblPrecioTotal.setText("Precio Total: $COP " + precioTotal);
		
					dialog.dispose();
				}
			}
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(contentPane);
        dialog.pack();
        dialog.setLocationRelativeTo(parentPanel);
        dialog.setVisible(true);
	}

}
