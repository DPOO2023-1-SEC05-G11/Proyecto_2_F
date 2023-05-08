package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

public class dialogCama extends JDialog implements ActionListener{

	private final JPanel panelPrincipal = new JPanel();
	JButton okButton, cancelButton;
	JComboBox<String> comboBox;
	VentanaAddRoom ventanaPrin;
	
	public dialogCama(VentanaAddRoom parentFrame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Añadir Cama");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		setSize(200,200);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		ventanaPrin = parentFrame;
		{
			JLabel lbl1 = new JLabel("Seleccione tipo");
			panelPrincipal.add(lbl1);
		}
		{
			comboBox = new JComboBox<>();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"King", "Queen", "Niños", "Doble"}));
			panelPrincipal.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okButton) {
			String cama = comboBox.getItemAt(comboBox.getSelectedIndex());
			ventanaPrin.addLista(cama);
		}
		
	}

}