/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import controladora.ControladoraPartidaLocal;
import domini.Jugador;
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
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import presentacio.GuiTaulell;
import presentacio.dades.GuiMenuDades;

/**
 *
 * @author ula
 */
public class GuiNovaPartidaOffline {

    private ControladoraGui gui;
    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlAlias;
    private JTextField jtfAlias;
    private JLabel jlFInicials;
    private JTextField jtfFInicials;
    private JButton jbCrear;
    private JButton jbTornar;
    private JButton jbContraMaquina;
    private JButton jbCrearPartida;
    private JButton jbUnirsePartida;

    //constructor de pruebas
    public GuiNovaPartidaOffline() throws InterruptedException {
        iniciarComponents();
    }

    public GuiNovaPartidaOffline(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        //jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Nova Partida Offline");
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

        jlAlias = new JLabel();
        jlAlias.setBounds(58, 130, 340, 104);
        jlAlias.setText("Alias ");
        jlAlias.setForeground(Color.red);
        jlAlias.setLayout(null);
        jpFons.add(jlAlias);

        jtfAlias = new JTextField(20);
        jtfAlias.setBounds(170, 170, 120, 24);
        jpFons.add(jtfAlias);

        jlFInicials = new JLabel();
        jlFInicials.setBounds(74, 210, 340, 104);
        jlFInicials.setText("Fitxes Inicials ");
        jlFInicials.setForeground(Color.red);
        jlFInicials.setLayout(null);
        jpFons.add(jlFInicials);

        jtfFInicials = new JTextField(20);
        jtfFInicials.setBounds(170, 250, 120, 24);
        jpFons.add(jtfFInicials);

        jbCrear = new JButton("CREAR");
        jbCrear.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrear.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrear.setLayout(null);
        jbCrear.setBounds(100, 350, 120, 24);
        jbCrear.setIconTextGap(-260);
        jbCrear.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrear.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrear.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrear);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setLayout(null);
        jbTornar.setBounds(100, 420, 120, 24);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);


        jbCrear.addActionListener(new ActionListener() {

            private GuiTaulell taulell;

            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                gui.setCp(new ControladoraPartidaLocal(gui, jtfAlias.getText(), Integer.parseInt(jtfFInicials.getText())));
                taulell = new GuiTaulell(gui);
                taulell.getjFrame().setLocation(taulell.getjFrame().getLocation());
                new Runnable() {

                    public void run() {
                        taulell.getjFrame().setVisible(true);
                    }
                };
                
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            private GuiLoginJabberPartida menu;

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    menu = new GuiLoginJabberPartida(gui);
                    menu.getjFrame().setLocation(jFrame.getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JButton getJbContraMaquina() {
        return jbContraMaquina;
    }

    public void setJbContraMaquina(JButton jbContraMaquina) {
        this.jbContraMaquina = jbContraMaquina;
    }

    public JButton getJbCrearPartida() {
        return jbCrearPartida;
    }

    public void setJbCrearPartida(JButton jbCrearPartida) {
        this.jbCrearPartida = jbCrearPartida;
    }

    public JButton getJbTornar() {
        return jbTornar;
    }

    public void setJbTornar(JButton jbTornar) {
        this.jbTornar = jbTornar;
    }

    public JButton getJbUnirsePartida() {
        return jbUnirsePartida;
    }

    public void setJbUnirsePartida(JButton jbUnirsePartida) {
        this.jbUnirsePartida = jbUnirsePartida;
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
                    new GuiNovaPartidaOffline();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
