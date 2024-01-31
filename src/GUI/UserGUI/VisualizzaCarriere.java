package GUI.UserGUI;

import Controller.ControllerUtente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class VisualizzaCarriere {

    public JFrame frame;
    public final JFrame frameChiamante;
    private JPanel panel;
    public ControllerUtente controller;
    private JPanel panelTabella;
    private JTable tableGiocatori;
    private JButton buttonIndietro;
    private JScrollPane scrollPaneTabella;
    private JButton visualizzaInformazioniCompleteGiocatoreButton;
    private JScrollPane scrollPane;


    public VisualizzaCarriere(JFrame frameChiamante, ControllerUtente controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        tableGiocatori.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Codice Fiscale", "Nome", "Cognome",
                "Data Di Nascita", "Eta", "Piede", "Squadra Attuale", "Ruolo Principale", "Caratteristiche Giocatore", "Partite Giocate",
                "Goal Effettuati", "Goal Subiti", "Ammonizioni", "Espulsioni"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableGiocatori.setModel(tableModel);

        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
        ArrayList<Integer> listaEta = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaRuoli = new ArrayList<>();
        ArrayList<String> listaCaratteristiche = new ArrayList<>();
        ArrayList<Integer> listaPartiteGiocate = new ArrayList<>();
        ArrayList<Integer> listaGolEffettuati = new ArrayList<>();
        ArrayList<Integer> listaGolSubiti = new ArrayList<>();
        ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
        ArrayList<Integer> listaEspulsioni = new ArrayList<>();

        controller.visualizzaCarriere(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            tableModel.addRow(new Object[]{listaCodFisc.get(i), listaNomi.get(i), listaCognomi.get(i), listaDateNascita.get(i), listaEta.get(i),
                    listaPiedi.get(i), listaNomiSquadra.get(i), listaRuoli.get(i), listaCaratteristiche.get(i), listaPartiteGiocate.get(i),
                    listaGolEffettuati.get(i), listaGolSubiti.get(i), listaAmmonizioni.get(i),  listaEspulsioni.get(i)});
        }

        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        visualizzaInformazioniCompleteGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codFisc = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                VisualizzaGiocatore finestraVisualizzaGiocatore = new VisualizzaGiocatore(frame, controller, codFisc);
                finestraVisualizzaGiocatore.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
