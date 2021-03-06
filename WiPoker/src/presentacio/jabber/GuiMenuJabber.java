/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

import controladora.ControladoraGui;
import controladora.jabber.GestioUsuaris;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import presentacio.GuiMenu;

/**
 *
 * @author wida45787385
 */
public class GuiMenuJabber {

    private ControladoraGui gui;
    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbCrearCompte;
    private JButton jbTornar;
    private JLabel jlBarra;

    public GuiMenuJabber() throws InterruptedException {
        iniciarComponents();
    }

    public GuiMenuJabber(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Usuaris");
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setLayout(null);
        jpFons.setBounds(0, 0, 340, 950);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setOpaque(false);

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbCrearCompte = new JButton("Crea el teu compte Jabber");
        jbCrearCompte.setMnemonic('C');
        jbCrearCompte.setCursor(cursor);
        jbCrearCompte.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrearCompte.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrearCompte.setBounds(40, 225, 260, 40);
        jbCrearCompte.setIconTextGap(-260);
        jbCrearCompte.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrearCompte.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrearCompte.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrearCompte);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setMnemonic('r');
        jbTornar.setCursor(cursor);
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setBounds(40, 405, 260, 40);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jlBarra = new JLabel("Menú Principal/Jabber");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbCrearCompte.addActionListener(new ActionListener() {

            private GuiCrearCompteJabber crear;

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    crear = new GuiCrearCompteJabber(gui);
                    crear.getjFrame().setLocation(jFrame.getLocation());
                    crear.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            private GuiMenu menu;

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    menu = new GuiMenu(gui);
                    menu.getjFrame().setLocation(jFrame.getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new GuiMenuJabber();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
