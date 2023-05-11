package GUI;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Persistencia.Hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class panelRooms extends JPanel implements ActionListener {
	
	JButton btnCargar, btnAdd, btnRemove, btnSearch;

	public panelRooms() {
		
		btnCargar = new JButton("Cargar");
		add(btnCargar);
		btnCargar.addActionListener(this);
		
		btnAdd = new JButton("Add");
		add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnRemove = new JButton("Remove");
		add(btnRemove);
		btnRemove.addActionListener(this);
		
		btnSearch = new JButton("Search");
		add(btnSearch);
		btnSearch.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==btnAdd) {
			VentanaAddRoom ventanaAdd = new VentanaAddRoom();
			ventanaAdd.setVisible(true);
		} 
		else if (e.getSource()==btnRemove) {
			VentanaRemoveRoom ventanaRemove = new VentanaRemoveRoom();
			ventanaRemove.setVisible(true);
		}
		else if (e.getSource()==btnSearch) {
			VentanaSearchRoom ventanaSearch = new VentanaSearchRoom();
			ventanaSearch.setVisible(true);
		}
		else if (e.getSource()==btnCargar) {
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
					Hotel.getInstance().actualizarHabs(file);
					JOptionPane.showMessageDialog(null, "Rooms were successfully loaded.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
}
