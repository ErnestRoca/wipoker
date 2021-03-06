/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.dades;

import controladora.ControladoraGui;
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
public class GuiMenuDades {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlRuta;
    private JButton jbAfegir;
    private JButton jbEditar;
    private JButton jbEliminar;
    private JButton jbConsultar;
    private JButton jbTornar;
    private GuiConsultarDades consultar;
    private GuiAfegirJugador afegir;
    private GuiEditarJugador editar;
    private GuiEliminarJugador eliminar;
    private GuiMenu menu;
    private ControladoraGui gui;

    public GuiMenuDades() throws InterruptedException {
        iniciarComponents();
    }

    public GuiMenuDades(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }



    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Dades del jugador");
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

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbConsultar = new JButton("Consultar dades jugador");
        jbConsultar.setCursor(cursor);
        jbConsultar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbConsultar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbConsultar.setLayout(null);
        jbConsultar.setBounds(40, 135, 260, 40);
        jbConsultar.setIconTextGap(-260);
        jbConsultar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbConsultar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbConsultar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbConsultar);

        jbAfegir = new JButton("Afegir nou jugador");
        jbAfegir.setCursor(cursor);
        jbAfegir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbAfegir.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbAfegir.setLayout(null);
        jbAfegir.setBounds(40, 225, 260, 40);
        jbAfegir.setIconTextGap(-260);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbAfegir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbAfegir.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbAfegir);

        jbEditar = new JButton("Editar un jugador");
        jbEditar.setCursor(cursor);
        jbEditar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEditar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbEditar.setLayout(null);
        jbEditar.setBounds(40, 315, 260, 40);
        jbEditar.setIconTextGap(-260);
        jbEditar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEditar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbEditar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbEditar);

        jbEliminar = new JButton("Eliminir dades jugador");
        jbEliminar.setCursor(cursor);
        jbEliminar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEliminar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbEliminar.setLayout(null);
        jbEliminar.setBounds(40, 405, 260, 40);
        jbEliminar.setIconTextGap(-260);
        jbEliminar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEliminar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbEliminar);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setCursor(cursor);
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setLayout(null);
        jbTornar.setBounds(40, 495, 260, 40);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jlRuta = new JLabel("Menu Principal/MenuDades ... Part no implementada");
        jlRuta.setForeground(Color.white);
        jlRuta.setBounds(2, 578, 340, 30);
        jpFons.add(jlRuta);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbConsultar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    consultar = new GuiConsultarDades(gui);
                    consultar.getjFrame().setLocation(jFrame.getLocation());
                    consultar.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbAfegir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    afegir = new GuiAfegirJugador(gui);
                    afegir.getjFrame().setLocation(jFrame.getLocation());
                    afegir.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbEditar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    editar = new GuiEditarJugador(gui);
                    editar.getjFrame().setLocation(jFrame.getLocation());
                    editar.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbEliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    eliminar = new GuiEliminarJugador(gui);
                    eliminar.getjFrame().setLocation(jFrame.getLocation());
                    eliminar.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.dispose();
                    menu = new GuiMenu(gui);
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

            @Override
            public void run() {
                try {
                    new GuiMenuDades();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
