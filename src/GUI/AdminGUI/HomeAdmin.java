package GUI.AdminGUI;

import Controller.ControllerAdmin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin {
    public static JFrame frame;
    private final JFrame frameChiamante;

    public ControllerAdmin controller;
    private JPanel panel;
    private JPanel panelGiocatori;
    private JButton visualizzaGiocatoriButton;
    private JPanel panelCampionati;
    private JButton visualizzaCampionatiButton;
    private JPanel panelSquadre;
    private JButton visualizzaSquadreButton;
    private JButton indietroButton;

    public HomeAdmin(JFrame frameChiamante, ControllerAdmin controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        visualizzaGiocatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaGiocatoriAdmin finestraVisualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frame, controller);
                finestraVisualizzaGiocatoriAdmin.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
