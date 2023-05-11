package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;
import Modelo.*;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;

public class VentanaManejarReserva extends JFrame {

    private JPanel panelPrincipal;
    private JTextField textFieldInicio;
    private JTextField textFieldNoches;
    private JTextField textFieldNombre;
    private JTextField textFieldDocumento;
    private JTextField textFieldCorreo;
    private JTextField textFieldTelefono;

    public VentanaManejarReserva(ReservaEstadia reserva) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 439);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout(0, 0));

        JPanel bottomPanel = new JPanel();
        panelPrincipal.add(bottomPanel, BorderLayout.SOUTH);

        JButton checkInButton = new JButton("Check In");
        bottomPanel.add(checkInButton);

        JButton checkOutButton = new JButton("Check Out");
        bottomPanel.add(checkOutButton);

        JButton cancelReservaButton = new JButton("Cancelar Reserva");
        bottomPanel.add(cancelReservaButton);

        JPanel panel_1 = new JPanel();
        panelPrincipal.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblFechai = new JLabel("Fecha de incio:");
        panel_1.add(lblFechai);

        textFieldInicio = new JTextField(reserva.getFechaFinal().toString());
        textFieldInicio.setEditable(false);
        panel_1.add(textFieldInicio);
        textFieldInicio.setColumns(10);

        JLabel lblNoches = new JLabel("Numero de noches:");
        panel_1.add(lblNoches);

        textFieldNoches = new JTextField(Integer.toString(reserva.getDuracion()));
        textFieldNoches.setEditable(false);
        panel_1.add(textFieldNoches);
        textFieldNoches.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre huesped principal:");
        panel_1.add(lblNombre);

        textFieldNombre = new JTextField(reserva.getHuespedPrincipal().getNombre());
        textFieldNombre.setEditable(false);
        panel_1.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblDoc = new JLabel("Documento:");
        panel_1.add(lblDoc);

        textFieldDocumento = new JTextField(reserva.getHuespedPrincipal().getDocumento());
        textFieldDocumento.setEditable(false);
        panel_1.add(textFieldDocumento);
        textFieldDocumento.setColumns(10);

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> habsSeleccionadasList = new JList<>(listModel);
        habsSeleccionadasList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        JScrollPane scrollPane = new JScrollPane(habsSeleccionadasList);
        scrollPane.setPreferredSize(new Dimension(250, 500));

        for (Habitacion hab : reserva.getHabitaciones()) {
            listModel.addElement(Integer.toString(hab.getId()));
        }

        habsSeleccionadasList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        int selectedRoomID = Integer.parseInt(habsSeleccionadasList.getSelectedValue());
                        new VentanaShowRoom(Hotel.getInstance().buscarHabs1(selectedRoomID)).setVisible(true);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid room ID");
                    }
                }
            }
        });

        panel_2.add(scrollPane);

       
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (reserva.isCheckedIn())
                {
                    JOptionPane.showMessageDialog(null, "Reservation already checked in", "Checked in!", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    ArrayList<Huesped> huespedes = new ArrayList<Huesped>();

                    JFrame frame = new JFrame("Check-in Window");
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(400, 300);
            
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
            
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    JList<String> list = new JList<>(listModel);
            
                    JScrollPane scrollPane = new JScrollPane(list);
                    panel.add(scrollPane, BorderLayout.CENTER);
            
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            
                    JLabel label1 = new JLabel("Nombre:");
                    JTextField textField1 = new JTextField(10);
            
                    JLabel label2 = new JLabel("Documento:");
                    JTextField textField2 = new JTextField(10);
            
                    JLabel label3 = new JLabel("Correo:");
                    JTextField textField3 = new JTextField(10);
            
                    JLabel label4 = new JLabel("Telefono:");
                    JTextField textField4 = new JTextField(10);
            
                    JButton addButton = new JButton("Add");
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Huesped huesped = Hotel.getInstance().crearHuesped(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText());
                            huespedes.add(huesped);

                            listModel.addElement(huesped.toString());

                            textField1.setText("");
                            textField2.setText("");
                            textField3.setText("");
                            textField4.setText("");
                        }
                    });
                    frame.getRootPane().setDefaultButton(addButton);
            
                    JButton removeButton = new JButton("Remove");
                    removeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedIndex = list.getSelectedIndex();
                            if (selectedIndex != -1) {
                                
                                for (Huesped huesped : huespedes)
                                {
                                    if (huesped.toString().equals(listModel.elementAt(selectedIndex)))
                                    {
                                        huespedes.remove(huesped);
                                    }
                                }
                                listModel.remove(selectedIndex);
                            }
                        }
                    });
            
                    buttonPanel.add(label1);
                    buttonPanel.add(textField1);
                    buttonPanel.add(label2);
                    buttonPanel.add(textField2);
                    buttonPanel.add(label3);
                    buttonPanel.add(textField3);
                    buttonPanel.add(label4);
                    buttonPanel.add(textField4);
                    buttonPanel.add(addButton);
                    buttonPanel.add(removeButton);
            
                    panel.add(buttonPanel, BorderLayout.EAST);



                    JPanel panel3 = new JPanel();
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

                    JButton checkInButton = new JButton("Check In");
                    checkInButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Hotel.getInstance().checkIn(reserva, huespedes);
                            frame.dispose();
                            dispose();
                            
                        }
                    });
            
                    JButton cancelCheckIn = new JButton("Cancel");
                    cancelCheckIn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        }
                    });


                    panel3.add(checkInButton);
                    panel3.add(cancelCheckIn);

                    panel.add(panel3, BorderLayout.SOUTH);

                    frame.setContentPane(panel);
                    frame.setVisible(true);

                    frame.pack();

                    String message = "Por favor entre las informaciones de todos los huespedes del grupo";
                    String title = "Huespedes";
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                    }

                

            }
        });
        

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!reserva.isCheckedIn())
                {
                    JOptionPane.showMessageDialog(null, "Reservation must be checked in before it can be checked out.", "Not checked in!", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to check out?", "Confirm Check Out",

                    JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        
                        try {
                            Hotel.getInstance().checkOut(reserva);
                            dispose();
                        } catch (Exception ex) {
                            System.out.println("An error occurred while executing: " + ex.getMessage());
                            ex.printStackTrace();
                        }

                        dispose();
                    }
                }   
            }
        });

        cancelReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reserva.isCheckedIn())
                {
                    JOptionPane.showMessageDialog(null, "Reservation already checked in, it is too late to cancel!", "Checked in!", JOptionPane.INFORMATION_MESSAGE);
                }else{

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the reservation?", "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {

                        try {
                            Hotel.getInstance().cancelarReserva(reserva);
                            dispose();
                        } catch (Exception ex) {
                            System.out.println("An error occurred while executing: " + ex.getMessage());
                            ex.printStackTrace();
                        }

                        dispose();
                    }
                }
            }
        });

        // pack();

    }

}
