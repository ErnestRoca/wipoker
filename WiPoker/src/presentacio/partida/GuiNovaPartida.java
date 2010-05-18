/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import java.awt.event.ActionEvent;
import presentacio.dades.GuiMenuDades;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import presentacio.GuiTaulell;

/**
 *
 * @author wida45787385
 */
public class GuiNovaPartida {

    protected JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbUnirsePartida;
    private JButton jbContraMaquina;
    private JButton jbCrearPartida;
    private JButton jbTornar;
    private JLabel jlBarra;
    private ControladoraGui gui;

    public GuiNovaPartida() throws InterruptedException {
        iniciarComponents();
        comprovarLogin();
    }

    public GuiNovaPartida(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
        comprovarLogin();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Nova Partida");
        jFrame.setLayout(null);
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

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbCrearPartida = new JButton("Crear nova partida internet");
        jbCrearPartida.setMnemonic('C');
        jbCrearPartida.setCursor(cursor);
        jbCrearPartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrearPartida.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrearPartida.setBounds(40, 135, 260, 40);
        jbCrearPartida.setIconTextGap(-260);
        jbCrearPartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrearPartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrearPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrearPartida);

        jbUnirsePartida = new JButton("Unir-se a partida a internet");
        jbUnirsePartida.setMnemonic('U');
        jbUnirsePartida.setCursor(cursor);
        jbUnirsePartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbUnirsePartida.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbUnirsePartida.setBounds(40, 225, 260, 40);
        jbUnirsePartida.setIconTextGap(-260);
        jbUnirsePartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbUnirsePartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbUnirsePartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbUnirsePartida);

        jbContraMaquina = new JButton("Jugar contra la màquina");
        jbContraMaquina.setMnemonic('J');
        jbContraMaquina.setCursor(cursor);
        jbContraMaquina.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbContraMaquina.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbContraMaquina.setBounds(40, 315, 260, 40);
        jbContraMaquina.setIconTextGap(-260);
        jbContraMaquina.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbContraMaquina.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbContraMaquina.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbContraMaquina);

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

        jlBarra = new JLabel("Menú Principal/Nova Partida");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);

        jbCrearPartida.addActionListener(new ActionListener() {
            private GuiTaulell taulell;

            public void actionPerformed(ActionEvent e) {
                taulell = new GuiTaulell(gui);
                jFrame.setVisible(false);
                taulell.getjFrame().setLocation(taulell.getjFrame().getLocation());
                taulell.getjFrame().setVisible(true);
            }
        });

        jbUnirsePartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    GuiUnirsePartida gt = new GuiUnirsePartida(gui);
                    jFrame.setVisible(false);
                    gt.getjFrame().setLocation(jFrame.getLocation());
                    gt.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiNovaPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbContraMaquina.addActionListener(new ActionListener() {
            private GuiTaulell taulell;

            public void actionPerformed(ActionEvent e) {
                taulell = new GuiTaulell();
                jFrame.setVisible(false);
                taulell.getjFrame().setLocation(taulell.getjFrame().getLocation());
                taulell.getjFrame().setVisible(true);
                //taulell.setControladoraGui(gui);
            }
        });

        jbTornar.addActionListener(new ActionListener() {
            private GuiMenu menu;

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    menu = new GuiMenu(gui);
                    menu.getjFrame().setLocation(jFrame.getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiNovaPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void comprovarLogin() {
        if (!gui.isLogin()) {
            jbCrearPartida.setEnabled(false);
            jbUnirsePartida.setEnabled(false);
        }
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

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiNovaPartida();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


}
