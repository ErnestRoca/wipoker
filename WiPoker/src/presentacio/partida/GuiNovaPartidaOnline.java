/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import presentacio.GuiMenu;
import presentacio.GuiTaulell;
import presentacio.dades.GuiMenuDades;

/**
 *
 * @author ula
 */
public class GuiNovaPartidaOnline {

    protected JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbUnirsePartida;
    private JButton jbContraMaquina;
    private JButton jbCrearPartida;
    private JButton jbTornar;
    private ControladoraGui gui;
    private JLabel jlNom;
    private JTextField jtfNom;
    private JLabel jlMaxJ;
    private JTextField jtfMAxJ;
    private JLabel jlFInicials;
    private JTextField jtfFInicials;
    private JButton jbCrear;
    private ButtonGroup bp;
    private JRadioButton jrbOnline;
    private JRadioButton jrbUnir;

    public GuiNovaPartidaOnline() throws InterruptedException {
        gui = new ControladoraGui();
        iniciarComponents();
        //gui.comprovarLogin(this);
    }

    public GuiNovaPartidaOnline(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
        //gui.comprovarLogin(this);
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        //jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Partida Online");
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


        jrbOnline = new JRadioButton("Crear partida Online");
        jrbOnline.setBounds(11, 123, 154, 20);
        jrbOnline.setSelected(true);
        jrbOnline.setBackground(Color.BLACK);
        jrbOnline.setForeground(Color.red);

        jrbUnir = new JRadioButton("Unir-se a partida");
        jrbUnir.setBounds(177, 123, 144, 20);
        jrbUnir.setBackground(Color.BLACK);
        jrbUnir.setForeground(Color.red);

        bp = new ButtonGroup();
        bp.add(jrbOnline);
        bp.add(jrbUnir);
        jpFons.add(jrbOnline);
        jpFons.add(jrbUnir);

        jlNom = new JLabel();
        jlNom.setBounds(80, 140, 340, 144);
        jlNom.setText("Nom Partida ");
        jlNom.setForeground(Color.red);
        jlNom.setLayout(null);
        jpFons.add(jlNom);

        jtfNom = new JTextField(20);
        jtfNom.setBounds(170, 198, 120, 24);
        jpFons.add(jtfNom);

        jlMaxJ = new JLabel();
        jlMaxJ.setBounds(58, 240, 340, 104);
        jlMaxJ.setText("Maxim Jugadors ");
        jlMaxJ.setForeground(Color.red);
        jlMaxJ.setLayout(null);
        jpFons.add(jlMaxJ);

        jtfMAxJ = new JTextField(20);
        jtfMAxJ.setBounds(170, 280, 120, 24);
        jpFons.add(jtfMAxJ);

        jlFInicials = new JLabel();
        jlFInicials.setBounds(74, 330, 340, 104);
        jlFInicials.setText("Fitxes Inicials ");
        jlFInicials.setForeground(Color.red);
        jlFInicials.setLayout(null);
        jpFons.add(jlFInicials);

        jtfFInicials = new JTextField(20);
        jtfFInicials.setBounds(170, 370, 120, 24);
        jpFons.add(jtfFInicials);

//        jlDNI = new JLabel();
//        jlDNI.setBounds(120, 310, 340, 104);
//        jlDNI.setText("DNI ");
//        jlDNI.setForeground(Color.red);
//        jlDNI.setLayout(null);
//        jpFons.add(jlDNI);
//
//        jtfDNI = new JTextField(20);
//        jtfDNI.setBounds(170, 350, 120, 24);
//        jpFons.add(jtfDNI);


        jbCrear = new JButton("CREAR");
        jbCrear.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrear.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrear.setLayout(null);
        jbCrear.setBounds(100, 450, 120, 24);
        jbCrear.setIconTextGap(-260);
        jbCrear.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrear.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrear.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrear);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setLayout(null);
        jbTornar.setBounds(100, 510, 120, 24);
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
                taulell = new GuiTaulell(gui);
                jFrame.dispose();
                taulell.getjFrame().setLocation(taulell.getjFrame().getLocation());
                taulell.getjFrame().setVisible(true);
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

        jrbOnline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jtfNom.setEnabled(true);
                jtfMAxJ.setEnabled(true);
                jtfMAxJ.setBackground(Color.WHITE);
                jtfFInicials.setEnabled(true);
                jtfFInicials.setBackground(Color.WHITE);
                jbCrear.setText("CREAR");
            }
        });

        jrbUnir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                jtfNom.setEnabled(true);
                jtfMAxJ.setEnabled(false);
                jtfMAxJ.setBackground(Color.DARK_GRAY);
                jtfFInicials.setEnabled(false);
                jtfFInicials.setBackground(Color.DARK_GRAY);
                jbCrear.setText("UNIR-SE");
            }
        });
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("hola");
        Object source = actionEvent.getSource();
        if (source == jrbOnline) {
            jtfNom.setEnabled(true);
            jtfMAxJ.setEnabled(true);
            jtfFInicials.setEnabled(true);
            jbCrear.setText("CREAR");
        } else if (source == jrbUnir) {
            jtfNom.setEnabled(true);
            jtfMAxJ.setEnabled(false);
            jtfFInicials.setEnabled(false);
            jbCrear.setText("UNIR-SE");
        }
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
                    new GuiNovaPartidaOnline();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}