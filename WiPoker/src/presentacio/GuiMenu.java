/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import presentacio.dades.GuiMenuDades;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;
import presentacio.jabber.GuiMenuJabber;
import presentacio.partida.GuiNovaPartida;

/**
 *
 * @author wida45787385
 */
public class GuiMenu {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbPartida;
    private JButton jbDades;
    private JButton jbSortir;
    private JButton jbJabber;
    private GuiMenuJabber jabber;
    private GuiMenuDades dades;
    private GuiNovaPartida partida;

    public GuiMenu() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("WiPoker");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbJabber = new JButton("Jabber");
        jbJabber.setCursor(cursor);
        jbJabber.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbJabber.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbJabber.setBounds(40, 135, 260, 40);
        jbJabber.setIconTextGap(-260);
        jbJabber.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbJabber.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbJabber.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbJabber);

        jbPartida = new JButton("Jugar");
        jbPartida.setCursor(cursor);
        jbPartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbPartida.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbPartida.setBounds(40, 245, 260, 40);
        jbPartida.setIconTextGap(-260);
        jbPartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbPartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbPartida);

        jbDades = new JButton("Dades jugador");
        jbDades.setCursor(cursor);
        jbDades.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbDades.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbDades.setIconTextGap(-260);
        jbDades.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbDades.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbDades.setHorizontalTextPosition(SwingConstants.CENTER);
        jbDades.setBounds(40, 355, 260, 40);
        jpFons.add(jbDades);

        jbSortir = new JButton("Sortir");
        jbSortir.setMnemonic('S');
        jbSortir.setCursor(cursor);
        jbSortir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbSortir.setIconTextGap(-260);
        jbSortir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbSortir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbSortir.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbSortir.setHorizontalTextPosition(SwingConstants.CENTER);
        jbSortir.setBounds(40, 465, 260, 40);
        jpFons.add(jbSortir);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbJabber.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    jabber = new GuiMenuJabber();
                    jabber.getjFrame().setLocation(jFrame.getLocation());
                    jabber.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        jbPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    partida = new GuiNovaPartida();
                    partida.getjFrame().setLocation(jFrame.getLocation());
                    partida.getjFrame().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });


        jbDades.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    dades = new GuiMenuDades();
                    dades.getjFrame().setLocation(jFrame.getLocation());
                    dades.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbSortir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiMenu();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
