package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class VentanaShowRoom extends JFrame {

	private JPanel panelPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaShowRoom frame = new VentanaShowRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaShowRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel, BorderLayout.NORTH);
		
		JLabel lblId = new JLabel("ID: ");
		panel.add(lblId);
		
		JLabel IdChangeable = new JLabel("0");
		panel.add(IdChangeable);
		
		JLabel lblTarifa = new JLabel("Tarifa:");
		panel.add(lblTarifa);
		
		JLabel tarifaChangeable = new JLabel("0");
		panel.add(tarifaChangeable);
		
		JPanel panel_1 = new JPanel();
		panelPrincipal.add(panel_1, BorderLayout.CENTER);
	}

}
