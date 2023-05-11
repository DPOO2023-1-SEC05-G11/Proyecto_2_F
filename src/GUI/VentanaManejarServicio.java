package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Modelo.Servicio;
import Persistencia.Hotel;

public class VentanaManejarServicio extends JFrame {

    private JPanel panelPrincipal;
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public VentanaManejarServicio(Servicio servicio) {
        HashMap<String, Integer> map = servicio.getMap();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(servicio.getName().toUpperCase());

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPrincipal.setLayout(new BorderLayout(0, 0));
        setContentPane(panelPrincipal);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        for (String key : map.keySet()) {
            int value = map.get(key);
            String entry = "Nombre: " + key + ", Precio: $COP " + value;
            listModel.addElement(entry);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "Entre el nombre del artículo:");
                String priceStr = JOptionPane.showInputDialog(null, "Entre el precio del artículo:");

                int precio = 0;
                try {
                    precio = Integer.parseInt(priceStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entre un numero valido por favor.");
                    return; // Exit the method if price is invalid
                }

                Hotel.getInstance().agregarOpcionServicio(servicio.getName(), name, precio);
                listModel.addElement("Nombre: " + name + ", Precio: $COP " + precio);
            }
        });

        buttonPanel.add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedEntry = list.getSelectedValue();
                if (selectedEntry != null) {
                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            "Está seguro de que quiere remover este artículo?",
                            "Seguro?",
                            JOptionPane.YES_NO_OPTION);
                    
                    if (choice == JOptionPane.YES_OPTION) {
                        int nameStartIndex = selectedEntry.indexOf("Nombre: ") + "Nombre: ".length();
                        int nameEndIndex = selectedEntry.indexOf(", Precio: ");
                        String nombreOpcion = selectedEntry.substring(nameStartIndex, nameEndIndex);
                        
                        Hotel.getInstance().removerOpcionServicio(servicio.getName(), nombreOpcion);
                        listModel.removeElement(selectedEntry);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an item to remove.");
                }
            }
        });



        buttonPanel.add(btnRemove);

        panelPrincipal.add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }
}
