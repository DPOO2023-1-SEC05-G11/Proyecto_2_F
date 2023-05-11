package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;

public class VentanaAddRemoveServicio extends JFrame {

    private JPanel panelPrincipal, panelBotones;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public VentanaAddRemoveServicio(Set<String> servicios) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add/Remove Servicio");

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(panelPrincipal);

        panelBotones = new JPanel();

        listModel = new DefaultListModel<>();

        for (String servicio : servicios) {
            listModel.addElement(servicio.substring(0, 1).toUpperCase() + servicio.substring(1));
        }

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = JOptionPane.showInputDialog(null, "Entre el nombre del nuevo servicio:");
                if (userInput != null && !userInput.isEmpty()) {
                    if (listModel.contains(userInput)) {
                        JOptionPane.showMessageDialog(null, "El servicio ya existe.");
                    } else {
                        int choice = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to add the servicio:\n" + userInput + "?",
                            "Confirm Remove", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            Hotel.getInstance().addEmptyServicio(userInput.toLowerCase());
                            Hotel.getInstance().salvarNuevoServicio(userInput.toLowerCase());
                            listModel.addElement(userInput.substring(0, 1).toUpperCase() + userInput.substring(1));
                            dispose();
                        }
                    }
                }
            }
        });
        
        panelBotones.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedServicio = list.getSelectedValue();
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to remove the servicio:\n" + selectedServicio + "?",
                            "Confirm Remove", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        Hotel.getInstance().removerServs(listModel.get(selectedIndex).toLowerCase());
                        listModel.remove(selectedIndex);
                        dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un servicio.", "Nada Seleccionada", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panelBotones.add(btnRemove);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }
}