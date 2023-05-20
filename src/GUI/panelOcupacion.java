package GUI;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Persistencia.Hotel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class panelOcupacion extends JSplitPane  {

    private static final int ROWS = 7;
    private static final int COLUMNS = 52;
    private static final int LABEL_FREQUENCY = 9;
    private JButton clickedButton = null;


    public panelOcupacion() {
        super(JSplitPane.VERTICAL_SPLIT);

        JPanel mainPanel = new JPanel(new BorderLayout());

        int numSmallPanels = COLUMNS / LABEL_FREQUENCY;

        

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel topLabelPanel = new JPanel(new GridLayout(1, numSmallPanels + 1));
        JPanel sideLabelPanel = new JPanel(new GridLayout(ROWS, 1));
        JPanel westPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLUMNS));

        JPanel emptyWrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
        JPanel emptyJPanel = new JPanel(new GridLayout(1, numSmallPanels + 1));

        JLabel emptyLabel = new JLabel("     ");
        emptyLabel.setFont(emptyLabel.getFont().deriveFont(Font.BOLD, 10)); 
        emptyJPanel.add(emptyLabel);

        emptyWrapperPanel.add(emptyJPanel);

        centerPanel.add(topLabelPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        westPanel.add(emptyWrapperPanel, BorderLayout.NORTH); 
        westPanel.add(sideLabelPanel, BorderLayout.CENTER);

        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        for (int row = 0; row < ROWS; row++) {
            JLabel dayLabel = new JLabel(daysOfWeek[row].toString());
            sideLabelPanel.add(dayLabel);
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate mondayOfCurrentWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate startDate = mondayOfCurrentWeek.minusWeeks(8);
        LocalDate endDate = currentDate.plusWeeks(43);

        Month currentMonth = currentDate.getMonth();
        Month firstLabelMonth = currentMonth.minus(1); 
        int monthIndex = firstLabelMonth.getValue() - 1;

        for (int col = 0; col < COLUMNS; col++) {
            if (col % LABEL_FREQUENCY == 0) {
                
                monthIndex = monthIndex % 12;
                String monthName = Month.values()[monthIndex].toString();
                JLabel label = new JLabel(monthName);
                label.setFont(label.getFont().deriveFont(Font.BOLD, 10));
                label.setVerticalAlignment(SwingConstants.BOTTOM);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalTextPosition(SwingConstants.BOTTOM);
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                monthIndex += 2;
                topLabelPanel.add(label);
            }
        }

        Integer[] ocupacionHabs = Hotel.getInstance().ocupacionHabs();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int daysToAdd = (col * ROWS) + row;
                LocalDate date = startDate.plusDays(daysToAdd);
                JButton button;
                
                int habsOcupadas = ocupacionHabs[daysToAdd];
                Color buttonColor = getColourForOcupacion(habsOcupadas);
                if (date.equals(currentDate) && col == 8 && row == currentDate.getDayOfWeek().getValue() - 1) {
                    button = createButton(buttonColor, date, habsOcupadas, true);
                } else {
                    button = createButton(buttonColor, date, habsOcupadas, false);
                }

                buttonPanel.add(button);
            }
        }

        



        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setTopComponent(mainPanel);
        setBottomComponent(new JPanel());


    }

    private Color getColourForOcupacion(int ocupacion) {
        if (ocupacion == 0) {
            return Color.WHITE;
        } else if (ocupacion <= 2) {
            return new Color(230, 255, 230); // Very light green
        } else if (ocupacion <= 5) {
            return new Color(210, 255, 210); // Slightly darker light green
        } else if (ocupacion <= 10) {
            return new Color(190, 255, 190); // Slightly darker green
        } else if (ocupacion <= 15) {
            return new Color(170, 255, 170); // Slightly darker green
        } else if (ocupacion <= 20) {
            return new Color(150, 255, 150); // Slightly darker green
        } else if (ocupacion <= 30) {
            return new Color(130, 255, 130); // Slightly darker green
        } else if (ocupacion <= 40) {
            return new Color(110, 255, 110); // Even darker green
        } else if (ocupacion <= 50) {
            return new Color(90, 255, 90); // Even darker green
        } else if (ocupacion <= 65) {
            return new Color(70, 255, 70); // Quite dark green
        } else if (ocupacion <= 80) {
            return new Color(50, 255, 50); // Darker green
        } else if (ocupacion <= 100) {
            return new Color(30, 255, 30); // Very dark green
        } else {
            return Color.BLACK; // Default color for unexpected values
        }
    }
    

    private JButton createButton(Color colour, LocalDate date, int habsOcupadas, Boolean today) {
        JButton button = new JButton();

        button.putClientProperty("habitaciones ocupadas", habsOcupadas);
        button.putClientProperty("date", date);

        button.setBackground(colour);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(20, 20));

        button.setBorder(new Border() {
            private final int BORDER_THICKNESS = 2;
        
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                if (today)
                {
                    g.setColor(Color.RED);
                }else{
                    g.setColor(new Color(255, 230, 230));
                }
                g.fillRect(x, y, width, BORDER_THICKNESS); 
                g.fillRect(x, y, BORDER_THICKNESS, height);
                g.fillRect(x + width - BORDER_THICKNESS, y, BORDER_THICKNESS, height); 
                g.fillRect(x, y + height - BORDER_THICKNESS, width, BORDER_THICKNESS); 
            }
        
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS);
            }
        
            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedButton = (JButton) e.getSource();
        
                updateBottomPanel();
        
                System.out.println(date);
            }
        });
        

        

        button.setOpaque(true);

    
        return button;
    }
    
    
    private void updateBottomPanel() {
        JPanel bottomPanel = (JPanel) getBottomComponent();
        bottomPanel.removeAll();

        JLabel label = new JLabel();
    
        if (clickedButton != null) {
            LocalDate date = (LocalDate) clickedButton.getClientProperty("date");
            int ocupacion = (int) clickedButton.getClientProperty("habitaciones ocupadas");
    
            if (ocupacion == 1)
            {
                label = new JLabel(date.toString() + ": " + ocupacion + " habitacion ocupada.");
            }else{
                label = new JLabel(date.toString() + ": " + ocupacion + " habitaciones ocupadas.");
            }
            
            bottomPanel.add(label);
        }
    
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
    
    
    
    

    private Color getRandomColor(Random random) {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900, 400);
    }
}
