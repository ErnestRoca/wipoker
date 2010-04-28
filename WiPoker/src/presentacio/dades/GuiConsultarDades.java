/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.dades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author wida47716705
 */
public class GuiConsultarDades {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlBuscador;
    private JTextField jtfAlies;
    private JButton jbBuscar;
    private JLabel jlNom;
    private JLabel jlAlies;
    private JLabel jlEdat;
    private JLabel jlPartides;
    private JLabel jlPuntuacio;
    private JTextField jtfNom;
    private JTextField jtfAlies2;
    private JTextField jtfEdat;
    private JTextField jtfPartides;
    private JTextField jtfPuntuacio;
    private JButton jbTornar;

    private GuiMenuDades menu;



    public GuiConsultarDades() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Consultar dades del jugador");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setLayout(null);
        jpFons.setOpaque(false);
        jpFons.setBounds(0, 0, 340, 950);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setLayout(null);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setLayout(null);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setOpaque(false);

        jlBuscador = new JLabel();
        jlBuscador.setBounds(53, 80, 340, 104);
        jlBuscador.setText("Introduir Alies ");
        jlBuscador.setForeground(Color.red);
        jlBuscador.setLayout(null);
        jpFons.add(jlBuscador);

        jtfAlies = new JTextField(20);
        jtfAlies.setBounds(170, 120, 120, 24);
        jpFons.add(jtfAlies);

        jbBuscar = new JButton("Buscar");
        jbBuscar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbBuscar.setBorder(null);
        jbBuscar.setLayout(null);
        jbBuscar.setBounds(120, 160, 80, 24);
        jbBuscar.setIconTextGap(-260);
        jbBuscar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbBuscar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbBuscar);

        jlNom = new JLabel();
        jlNom.setBounds(60, 170, 340, 104);
        jlNom.setText("Nom Complet ");
        jlNom.setForeground(Color.red);
        jlNom.setLayout(null);
        jpFons.add(jlNom);

        jtfNom = new JTextField(20);
        jtfNom.setBounds(170, 210, 120, 24);
        jtfNom.setEditable(false);
        jpFons.add(jtfNom);

        jlAlies = new JLabel();
        jlAlies.setBounds(115, 240, 340, 104);
        jlAlies.setText("Alies ");
        jlAlies.setForeground(Color.red);
        jlAlies.setLayout(null);
        jpFons.add(jlAlies);

        jtfAlies2 = new JTextField(20);
        jtfAlies2.setBounds(170, 280, 120, 24);
        jtfAlies2.setEditable(false);
        jpFons.add(jtfAlies2);

        jlEdat = new JLabel();
        jlEdat.setBounds(115, 310, 340, 104);
        jlEdat.setText("Edat ");
        jlEdat.setForeground(Color.red);
        jlEdat.setLayout(null);
        jpFons.add(jlEdat);

        jtfEdat = new JTextField(20);
        jtfEdat.setBounds(170, 350, 120, 24);
        jtfEdat.setEditable(false);
        jpFons.add(jtfEdat);

        jlPartides = new JLabel();
        jlPartides.setBounds(26, 380, 340, 104);
        jlPartides.setText("Partides guanyades ");
        jlPartides.setForeground(Color.red);
        jlPartides.setLayout(null);
        jpFons.add(jlPartides);

        jtfPartides = new JTextField(20);
        jtfPartides.setBounds(170, 420, 120, 24);
        jtfPartides.setEditable(false);
        jpFons.add(jtfPartides);

        jlPuntuacio = new JLabel();
        jlPuntuacio.setBounds(85, 450, 340, 104);
        jlPuntuacio.setText("Puntuacio ");
        jlPuntuacio.setForeground(Color.red);
        jlPuntuacio.setLayout(null);
        jpFons.add(jlPuntuacio);

        jtfPuntuacio = new JTextField(20);
        jtfPuntuacio.setBounds(170, 490, 120, 24);
        jtfPuntuacio.setEditable(false);
        jpFons.add(jtfPuntuacio);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(null);
        jbTornar.setLayout(null);
        jbTornar.setBounds(100, 540, 120, 24);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jpFons.add(jlImatgeFons);


        jbTornar.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    menu = new GuiMenuDades();
                    menu.getjFrame().setLocation(jFrame.getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
    }


    public JFrame getjFrame() {
        return jFrame;
    }



    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiConsultarDades();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiConsultarDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
