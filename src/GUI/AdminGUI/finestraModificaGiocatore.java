package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class finestraModificaGiocatore {
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;
    private JTextField textFieldNome;
    private JLabel labelNome;
    private JTextField textFieldCognome;
    private JLabel labelCognome;
    private JTextField textFieldCodiceFiscale;
    private JLabel labelCodiceFiscale;
    private JLabel labelPiede;
    private JComboBox comboBoxPiede;
    private JComboBox boxGiornoDoB;
    private JComboBox boxAnnoDoB;
    private JComboBox boxMeseDoB;
    private JButton indietroButton;
    private JButton invioButton;
    private JComboBox boxGiornoDoR;
    private JComboBox boxAnnoDoR;
    private JComboBox boxMeseDoR;

    public finestraModificaGiocatore(JFrame frameChiamante, ControllerAdmin controller, String codFisc) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        boxGiornoDoB.addItem("");
        boxMeseDoR.addItem("");
        boxAnnoDoR.addItem("");
        comboBoxPiede.addItem("Dx");
        comboBoxPiede.addItem("Sx");
        comboBoxPiede.addItem("Am");
        for(int i = 1; i < 32; i++){
            boxGiornoDoB.addItem(i);
            boxGiornoDoR.addItem(i);
        }
        for(int i = 1900; i < LocalDate.now().getYear(); i++){
            boxAnnoDoB.addItem(i);
        }
        for(int i = 1; i < 13; i++){
            boxMeseDoB.addItem(i);
        }

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
