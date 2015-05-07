package bureau;

import java.lang.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WidgetCalculatrice extends Widget implements Serializable{
    private JPanel panneau;
    private final String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "=", "C", "+", "-"};
    private JButton[] tab_button = new JButton[tab_string.length];
    private JLabel ecran = new JLabel();
    private final Dimension dim = new Dimension(50,40);
    private final Dimension dim2 = new Dimension(50,31);
    private double chiffre1;
    private boolean clicOperateur = false, update = false;
    private String operateur = "";

    public WidgetCalculatrice(boolean statut, String nom){
        super(statut, nom);
        this.initCalculatrice();
        panneau = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
        this.setContentPane(panneau);
    }

    private void initCalculatrice() {
        Font police = new Font("Arial", Font.BOLD, 20);
        ecran = new JLabel("0");
        ecran.setFont(police);
        ecran.setHorizontalAlignment(JLabel.RIGHT);
        ecran.setPreferredSize(new Dimension(220, 20));
        JPanel operateur = new JPanel();
        operateur.setPreferredSize(new Dimension(55, 225));
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel panEcran = new JPanel();
        panEcran.setPreferredSize(new Dimension(220, 30));

        for(int i = 0; i < tab_string.length; i++){
            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);
            switch(i){
                case 11 : //=
                    tab_button[i].addActionListener(new EgalListener());
                    chiffre.add(tab_button[i]);
                    break;
                case 12 : //C
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    operateur.add(tab_button[i]);
                    break;
                case 13 : //+
                    tab_button[i].addActionListener(new PlusListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 14 : //-
                    tab_button[i].addActionListener(new MoinsListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                default : // tous les autres boutons => des chiffres
                    chiffre.add(tab_button[i]);
                    tab_button[i].addActionListener(new ChiffreListener());
                    break;
            }
        }
        panEcran.add(ecran);
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        panneau.add(panEcran, BorderLayout.NORTH);
        panneau.add(chiffre, BorderLayout.CENTER);
        panneau.add(operateur, BorderLayout.EAST);
    }

    private void calcul() {
        if(operateur.equals("+")){
            chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
        if(operateur.equals("-")){
            chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
    }

    class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton)e.getSource()).getText();
            if(update){
                update = false;
            }
            else {
                if(!ecran.getText().equals("0"))
                    str = ecran.getText() + str;
            }
            ecran.setText(str);
        }
    }

    class EgalListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            calcul();
            update = true;
            clicOperateur = false;
        }
    }

    class PlusListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "+";
            update = true;
        }
    }

    class MoinsListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "-";
            update = true;
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            ecran.setText("");
        }
    }
}
