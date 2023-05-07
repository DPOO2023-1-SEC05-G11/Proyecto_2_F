package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class panelServices extends JPanel {

	public panelServices() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAñadir = new JButton("Add");
		add(btnAñadir);
		
		JButton btnManage = new JButton("Manage");
		add(btnManage);
		
	}

}
