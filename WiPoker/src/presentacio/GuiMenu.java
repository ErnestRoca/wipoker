/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import controladora.ControladoraGui;
import presentacio.dades.GuiMenuDades;
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
    private JLabel jlBarra;
    private JLabel jlBarra2;
    private ControladoraGui gui;

    public GuiMenu() throws InterruptedException {
        iniciarComponents();
        gui = new ControladoraGui();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("WiPoker");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.GREEN);
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

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbJabber = new JButton("Jabber");
        jbJabber.setMnemonic('a');
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
        jbPartida.setMnemonic('J');
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
        jbDades.setMnemonic('D');
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

        jlBarra = new JLabel("Voleu posar alguna cosa aqui?¿?¿?");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jlBarra2 = new JLabel("b.1.3");
        jlBarra2.setForeground(Color.white);
        jlBarra2.setBounds(300, 578, 340, 30);
        jpFons.add(jlBarra2);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbJabber.addActionListener(new ActionListener() {

            private GuiMenuJabber jabber;

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    jabber = new GuiMenuJabber(gui);
                    jabber.getjFrame().setLocation(jFrame.getLocation());
                    jabber.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        jbPartida.addActionListener(new ActionListener() {

            private GuiNovaPartida partida;

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    partida = new GuiNovaPartida(gui);
                    partida.getjFrame().setLocation(jFrame.getLocation());
                    partida.getjFrame().setVisible(true);
                    //partida.setControladoraGui(gui);
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });


        jbDades.addActionListener(new ActionListener() {

            private GuiMenuDades dades;

            public void actionPerformed(ActionEvent event) {
                //
                try {
                    jFrame.setVisible(false);
                    dades = new GuiMenuDades();
                    dades.getjFrame().setLocation(jFrame.getLocation());
                    dades.getjFrame().setVisible(true);
                    dades.setControladoraGui(gui);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenu.class.getName()).log(Level.SEVERE, null, ex);
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

    public ControladoraGui getControladoraGui() {
        return gui;
    }

    public void setControladoraGui(ControladoraGui gui) {
        this.gui = gui;
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
