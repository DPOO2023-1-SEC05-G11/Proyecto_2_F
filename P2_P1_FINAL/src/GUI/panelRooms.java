package GUI;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class panelRooms extends JPanel implements ActionListener {
	
	JButton btnCargar, btnAdd, btnUpdate, btnRemove, btnSearch;

	public panelRooms() {
		
		btnCargar = new JButton("Cargar");
		add(btnCargar);
		btnCargar.addActionListener(this);
		
		btnAdd = new JButton("Add");
		add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		add(btnUpdate);
		btnUpdate.addActionListener(this);
		
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
		else if (e.getSource()==btnCargar || e.getSource()==btnUpdate) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File (fileChooser.getSelectedFile().getAbsolutePath());
			}
			
		}
		
		
	}

}
