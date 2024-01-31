package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;

public class VisualizzaGiocatoriAdmin {
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;

    public VisualizzaGiocatoriAdmin(JFrame frameChiamante, ControllerAdmin controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;
    }
}
