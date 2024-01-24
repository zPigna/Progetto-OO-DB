package GUI;

import Controller.ControllerAdmin;
import Controller.ControllerUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {

    public static JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;
    private JPanel panelGiocatori;
    private JPanel panelSquadre;
    private JTextField textField1;
    private JButton invioButton;


    public Home(JFrame frameChiamante, ControllerAdmin controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
