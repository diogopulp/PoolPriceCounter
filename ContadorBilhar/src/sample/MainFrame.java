package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by diogosantos on 08/03/2018.
 */
public class MainFrame extends JFrame {

    private MainPanel mainPanel;

    public MainFrame(String title) {
        super(title);

        // Set layout manager
        setLayout(new FlowLayout());

        // Create Swing component
        JButton start = new JButton("Iniciar");
        JButton stop = new JButton("Parar");

        mainPanel = new MainPanel();

        // Add Swing components to content pane
        Container c = getContentPane();

        c.add(start);
        c.add(stop);
        c.add(mainPanel);

        // Add behaviour
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.startClock();

            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.stopClock();

            }
        });

    }
}
