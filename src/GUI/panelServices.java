package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Modelo.Servicio;
import Persistencia.Hotel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

public class panelServices extends JPanel implements ActionListener{

	JButton btnCargar, btnAddRemove, btnSearch, btnManejar;

	public panelServices() {
		
		btnCargar = new JButton("Cargar");
		add(btnCargar);
		btnCargar.addActionListener(this);
		
		btnAddRemove = new JButton("Add/Remove");
		add(btnAddRemove);
		btnAddRemove.addActionListener(this);

		btnManejar = new JButton("Manejar");
		add(btnManejar);
		btnManejar.addActionListener(this);
		
		btnSearch = new JButton("Search");
		add(btnSearch);
		btnSearch.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btnAddRemove) {
			
			
			VentanaAddRemoveServicio VentanaAddRemoveServicio = new VentanaAddRemoveServicio(Hotel.getInstance().getServicios().keySet());
			VentanaAddRemoveServicio.setVisible(true);

		}else if (e.getSource() == btnSearch) {
			try {
				String userInput = JOptionPane.showInputDialog(null, "Entre el nombre del servicio que estás buscando:");
				if (userInput != null) {
					userInput = userInput.toLowerCase().trim();
					if (!userInput.isEmpty()) {
						try {
							showServicioDialog(Hotel.getInstance().getServicios().get(userInput));
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(this, "El servicio entrado no existe!", "Servicio Inexistente", JOptionPane.ERROR_MESSAGE);
						}
					}
				} 
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Invalid input.");
				System.out.println("An error occurred while executing crearReserva: " + e1.getMessage());
			}
		}
		else if (e.getSource()==btnCargar) {
			String servicioAActualizar = showListInputDialog(Hotel.getInstance().getServicios().keySet());

			if (servicioAActualizar != null)
			{
				JFileChooser fileChooser = new JFileChooser() {
				@Override
				public void approveSelection() {
					File selectedFile = getSelectedFile();
					if (selectedFile != null && !selectedFile.getName().toLowerCase().endsWith(".txt")) {
						JOptionPane.showMessageDialog(this, "Please select a .txt file.", "Invalid File", JOptionPane.ERROR_MESSAGE);
						return;
					}
					super.approveSelection();
				}
				};
				fileChooser.setCurrentDirectory(new File("."));
			
				javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt");
				fileChooser.setFileFilter(filter);
			
				int response = fileChooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the data?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						Hotel.getInstance().cargarServicioDesdeDocumento(servicioAActualizar, file);
						JOptionPane.showMessageDialog(null, "El servicio se actualizó.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

			
		}else if (e.getSource()==btnManejar) {
			String servicioAManejar = showListInputDialog(Hotel.getInstance().getServicios().keySet());

			if (servicioAManejar != null)
			{
				VentanaManejarServicio VentanaManejarServicio = new VentanaManejarServicio(Hotel.getInstance().getServicios().get(servicioAManejar));
				VentanaManejarServicio.setVisible(true);
			}

		}
	}

	private String showListInputDialog(Set<String> items) {
		List<String> servicios = new ArrayList<>();
		for (String item : items) {
			String servicio = item.substring(0, 1).toUpperCase() + item.substring(1);
			servicios.add(servicio);
		}
	
		JList<String> list = new JList<>(servicios.toArray(new String[0]));
		JScrollPane scrollPane = new JScrollPane(list);
	
		int result = JOptionPane.showOptionDialog(
				null,
				scrollPane,
				"Elige un servicio o añade otro servicio primero.",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
	
		if (result == JOptionPane.OK_OPTION) {
			String selectedValue = list.getSelectedValue();
			if (selectedValue != null) {
				return selectedValue.toLowerCase();
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, selecciona una opción.");
				return showListInputDialog(items);
			}
		} else {
			return null;
		}
	}

	public static void showServicioDialog(Servicio servicio) {
		HashMap<String, Integer> map = servicio.getMap();
	
		JDialog dialog = new JDialog();
		dialog.setTitle(servicio.getName().toUpperCase());
		dialog.setModal(true);
	
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		dialog.getContentPane().add(panelPrincipal);
	
		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
	
		for (String key : map.keySet()) {
			int value = map.get(key);
			String entry = "Nombre: " + key + ", Precio: $COP " + value;
			listModel.addElement(entry);
		}
	
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	

}
