package GUI;

import Controller.ControllerUtente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaSquadre {


    public JFrame frame;
    public final JFrame frameChiamante;
    public ControllerUtente controller;
    private JPanel panel;
    private JTable tabellaSquadre;
    private JTable tabellaTrofei = new JTable();
    private JPanel panelTabella;
    private JScrollPane scrollPaneTabella;
    private JButton indietroButton;
    private JButton buttonVisualizzaTrofei;


    public VisualizzaSquadre(JFrame frameChiamante, ControllerUtente controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        tabellaSquadre.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Nazionalita"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabellaSquadre.setModel(tableModel);

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();

        controller.visualizzaSquadre(listaNomi, listaNazionalita);

        for(int i = 0; i < listaNomi.size(); i++){
            tableModel.addRow(new Object[]{listaNomi.get(i), listaNazionalita.get(i)});
        }



        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        buttonVisualizzaTrofei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeSquadraSelezionata = tableModel.getValueAt(tabellaSquadre.getSelectedRow(), 0).toString();
                String nazionalitaSquadraSelezionata = tableModel.getValueAt(tabellaSquadre.getSelectedRow(), 1).toString();

                tabellaTrofei.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                DefaultTableModel tableModelTrofei = new DefaultTableModel(new Object[][]{}, new String[]{"Nome Trofeo", "Anno di vittoria", "Merito di vittoria"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tabellaTrofei.setModel(tableModelTrofei);

                ArrayList<String> listaNomiTrofeo = new ArrayList<>();
                ArrayList<String> listaAnniTrofeo = new ArrayList<>();
                ArrayList<String> listaMeritiTrofeo = new ArrayList<>();

                controller.visualizzaTrofeiSquadra(nomeSquadraSelezionata, nazionalitaSquadraSelezionata, listaNomiTrofeo, listaAnniTrofeo, listaMeritiTrofeo);

                for(int i = 0; i < listaNomiTrofeo.size(); i++){
                    tableModelTrofei.addRow(new Object[]{listaNomiTrofeo.get(i), listaAnniTrofeo.get(i), listaMeritiTrofeo.get(i)});
                }

                JPanel panelTabellaTrofei = new JPanel();
                panelTabellaTrofei.add(new JScrollPane(tabellaTrofei));

                JDialog dialog = new JDialog();
                dialog.setTitle("Trofei vinti da: " + nomeSquadraSelezionata);
                dialog.setLayout(new GridLayout());
                dialog.add(tabellaTrofei);
                dialog.setSize(400, 300);
                dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialog.setLocationRelativeTo(null);
                dialog.setResizable(true);
                dialog.setVisible(true);

            }
        });
    }
}
