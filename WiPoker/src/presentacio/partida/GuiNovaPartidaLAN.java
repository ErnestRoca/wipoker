    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import controladora.ControladoraPartidaOnline;
import domini.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import presentacio.GuiTaulell;
import sockets.Client;
import sockets.Servidor;

/**
 *
 * @author wida45787385
 */
public class GuiNovaPartidaLAN extends javax.swing.JDialog {

    private ControladoraGui gui;
    //
    //
    private JLabel jlTitol;
    //
    private JLabel jlIP;
    private JTextField jtfIP;
    //
    private JLabel jlMaxJ;
    private JTextField jtfMAxJ;
    //
    private JLabel jlFInicials;
    private JTextField jtfFInicials;
    //
    private JLabel jlPort;
    private JTextField jtfPort;
    private JButton jbTornar;
    private JButton jbCrearUnir;
    private JButton jbRefresca;
    //
    private ButtonGroup bp;
    private JRadioButton jrbOnline;
    private JRadioButton jrbUnir;
    //
    private JLabel jlBarra;
    private JPanelGlobal jPanelGlobal;
    private JComboBox jcbServidors;

    public GuiNovaPartidaLAN(ControladoraGui gui, Frame owner, boolean modal) throws InterruptedException {
        super(owner, "Partida lan", modal);
        this.gui = gui;
        iniciarComponents();
        crearEscoltadors();
        setVisible(true);
    }

    /** Crea objectes crear els components de la UI. */
    private void iniciarComponents() {
        crearJFrame();
        crearJPanels();
        crearControls();
    }

    private void crearJFrame() {
        final int w = 338;
        final int h = 629;
        setSize(w, h);
        setTitle("Partida lan");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
    }

    //Mètode per introduir una imatge de fons.
    public class JPanelGlobal extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg"));
            g.drawImage(imatgeFons.getImage(), 0, 104, 333, 499, null);
            setOpaque(true);
        }
    }

    private void crearJPanels() {
        jPanelGlobal = new JPanelGlobal();
        jPanelGlobal.setLayout(null);
        jPanelGlobal.setBounds(0, 0, 338, 629);
        getContentPane().add(jPanelGlobal);
    }

    private void crearControls() {
        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jPanelGlobal.add(jlTitol);

        jrbOnline = new JRadioButton("Crear partida Online");
        jrbOnline.setFocusPainted(false);
        jrbOnline.setOpaque(false);
        jrbOnline.setBounds(11, 123, 154, 20);
        jrbOnline.setSelected(true);
        jrbOnline.setBackground(Color.BLACK);
        jrbOnline.setForeground(Color.red);

        jrbUnir = new JRadioButton("Unir-se a partida");
        jrbUnir.setFocusPainted(false);
        jrbUnir.setOpaque(false);
        jrbUnir.setBounds(177, 123, 144, 20);
        jrbUnir.setBackground(Color.BLACK);
        jrbUnir.setForeground(Color.red);

        bp = new ButtonGroup();
        bp.add(jrbOnline);
        bp.add(jrbUnir);
        jPanelGlobal.add(jrbOnline);
        jPanelGlobal.add(jrbUnir);

        jlIP = new JLabel();
        jlIP.setBounds(80, 220, 340, 24);
        jlIP.setText("IP ");
        jlIP.setForeground(Color.red);
        jlIP.setLayout(null);
        jPanelGlobal.add(jlIP);

        jtfIP = new JTextField("IP");
        jtfIP.setBounds(170, 220, 120, 24);
        jPanelGlobal.add(jtfIP);

        jlMaxJ = new JLabel();
        jlMaxJ.setBounds(58, 280, 340, 24);
        jlMaxJ.setText("Maxim Jugadors ");
        jlMaxJ.setForeground(Color.red);
        jlMaxJ.setLayout(null);
        jPanelGlobal.add(jlMaxJ);

        jtfMAxJ = new JTextField(20);
        jtfMAxJ.setBounds(170, 280, 120, 24);
        jPanelGlobal.add(jtfMAxJ);

        jlFInicials = new JLabel();
        jlFInicials.setBounds(74, 340, 340, 24);
        jlFInicials.setText("Fitxes Inicials ");
        jlFInicials.setForeground(Color.red);
        jlFInicials.setLayout(null);
        jPanelGlobal.add(jlFInicials);

        jtfFInicials = new JTextField(20);
        jtfFInicials.setBounds(170, 340, 120, 24);
        jPanelGlobal.add(jtfFInicials);

        jlPort = new JLabel();
        jlPort.setBounds(74, 390, 340, 24);
        jlPort.setText("Port ");
        jlPort.setForeground(Color.red);
        jlPort.setLayout(null);
        jPanelGlobal.add(jlPort);

        jtfPort = new JTextField(20);
        jtfPort.setBounds(170, 390, 120, 24);
        jPanelGlobal.add(jtfPort);

        jbRefresca = new JButton("Refresca");
        jbRefresca.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbRefresca.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbRefresca.setLayout(null);
        jbRefresca.setBounds(35, 450, 120, 24);
        jbRefresca.setIconTextGap(-260);
        jbRefresca.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbRefresca.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbRefresca.setHorizontalTextPosition(SwingConstants.CENTER);
        jPanelGlobal.add(jbRefresca);

        jbCrearUnir = new JButton("CREAR");
        jbCrearUnir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrearUnir.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrearUnir.setLayout(null);
        jbCrearUnir.setBounds(175, 450, 120, 24);
        jbCrearUnir.setIconTextGap(-260);
        jbCrearUnir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrearUnir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrearUnir.setHorizontalTextPosition(SwingConstants.CENTER);
        jPanelGlobal.add(jbCrearUnir);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setLayout(null);
        jbTornar.setBounds(100, 510, 120, 24);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jPanelGlobal.add(jbTornar);

        jlBarra = new JLabel("Menú Principal/Jugar/Partida LAN");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jPanelGlobal.add(jlBarra);
    }

    private void crearEscoltadors() {

        jbCrearUnir.addActionListener(new ActionListener() {

            private GuiTaulell taulell;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (jrbOnline.isSelected()) {
                    if (!jtfMAxJ.getText().isEmpty() && !jtfFInicials.getText().isEmpty()) {
                        if (Integer.parseInt(jtfMAxJ.getText()) > 1 && Integer.parseInt(jtfMAxJ.getText()) <= 9) {
                            ControladoraPartidaOnline cpo =
                                    new ControladoraPartidaOnline(Integer.parseInt(jtfMAxJ.getText()), gui,
                                    jtfIP.getText(), Integer.parseInt(jtfPort.getText()), (new Jugador("andres", 1000, 1, "avatar")));
                            gui.setCp(cpo);
                            gui.setCpo(cpo);
                            gui.getCpo().setServidor(new Servidor(jtfIP.getText(), Integer.parseInt(jtfPort.getText()), (new Jugador("andres", 1000, 1, "avatar"))));
                            gui.getCpo().getServidor().start();
                            dispose();
                            taulell = new GuiTaulell(gui);
                            taulell.getjFrame().setLocation(getLocation());
                            taulell.getjFrame().setVisible(true);

                        } else {
                            JOptionPane.showConfirmDialog(getContentPane(), "No pots introduir text en numero de jugadors", null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (jrbUnir.isSelected()) {
                        if (!jtfPort.getText().isEmpty() && !jtfIP.getText().isEmpty()) {
                            if (Integer.parseInt(jtfMAxJ.getText()) > 1 && Integer.parseInt(jtfMAxJ.getText()) <= 9) {
                                Jugador jugador = new Jugador("priva", Integer.parseInt(jtfFInicials.getText()), 2, "avatar");
                                Client client = new Client(jtfIP.getText(), Integer.parseInt(jtfPort.getText()), jugador);
                                client.start();
                                dispose();
                                taulell = new GuiTaulell(gui);
                                taulell.getjFrame().setLocation(getLocation());
                                taulell.getjFrame().setVisible(true);
                            } else {
                                JOptionPane.showConfirmDialog(getContentPane(), "Ha d'haver-hi entre 2 i 9 jugadors", null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            private GuiLoginJabberPartida menu;

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    dispose();
                    menu = new GuiLoginJabberPartida(gui);
                    menu.getjFrame().setLocation(getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jrbOnline.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                jtfMAxJ.setEnabled(true);
                jtfMAxJ.setBackground(Color.WHITE);
                jtfFInicials.setEnabled(true);
                jtfFInicials.setBackground(Color.WHITE);
                jbCrearUnir.setText("CREAR");
            }
        });

        jrbUnir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                jtfMAxJ.setEnabled(false);
                jtfMAxJ.setBackground(Color.DARK_GRAY);
                jtfFInicials.setEnabled(false);
                jtfFInicials.setBackground(Color.DARK_GRAY);
                jbCrearUnir.setText("UNIR-SE");
            }
        });
    }

    public ControladoraGui getGui() {
        return gui;
    }

    public void setGui(ControladoraGui gui) {
        this.gui = gui;
    }
}


