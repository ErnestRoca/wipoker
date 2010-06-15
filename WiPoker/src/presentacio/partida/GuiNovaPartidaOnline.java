/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import controladora.ControladoraPartidaOnline;
import controladora.jabber.JID;
import domini.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
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

/**
 *
 * @author ula
 */
public class GuiNovaPartidaOnline extends javax.swing.JDialog {

    private ControladoraGui gui;
    //
    private final static String[] servidors = {"conf.jabberes.org",
        "muc.jabber.upc.es", "conferencia.jabber-hispano.org",
        "conference.seunet.org", "conference.jabber.org"};
    public JID room = new JID();
    public boolean func = false;
    //
    private JLabel jlTitol;
    //
    private JLabel jlNom;
    private JTextField jtfNom;
    //
    private JLabel jlMaxJ;
    private JTextField jtfMAxJ;
    //
    private JLabel jlFInicials;
    private JTextField jtfFInicials;
    //
    private JLabel jlAlias;
    private JTextField jtfAlias;
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

    public GuiNovaPartidaOnline(ControladoraGui gui, Frame owner, boolean modal) throws InterruptedException {
        super(owner, "Partida Online", modal);
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
        setTitle("Partida Online");
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

        jcbServidors = new JComboBox();
        jcbServidors.setModel(new javax.swing.DefaultComboBoxModel(servidors));
        final int x1 = 30;
        final int y1 = 165;
        final int w1 = 265;
        final int h1 = 27;
        jcbServidors.setBounds(x1, y1, w1, h1);
        jPanelGlobal.add(jcbServidors);

        jlNom = new JLabel();
        jlNom.setBounds(80, 220, 340, 24);
        jlNom.setText("Nom Partida ");
        jlNom.setForeground(Color.red);
        jlNom.setLayout(null);
        jPanelGlobal.add(jlNom);

        jtfNom = new JTextField("WiPoker_Taula1");
        jtfNom.setBounds(170, 220, 120, 24);
        jPanelGlobal.add(jtfNom);

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

        jlAlias = new JLabel();
        jlAlias.setBounds(74, 390, 340, 24);
        jlAlias.setText("Alias jugador ");
        jlAlias.setForeground(Color.red);
        jlAlias.setLayout(null);
        jPanelGlobal.add(jlAlias);

        jtfAlias = new JTextField(20);
        jtfAlias.setBounds(170, 390, 120, 24);
        jPanelGlobal.add(jtfAlias);

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

        jlBarra = new JLabel("Menú Principal/Jugar/Partida Online");
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
                        try {
                            carregar_sala();
                            if (room.getNick() != null && room.getNick().length() == 0) {
                                jtfAlias.setText("usuari" + (new Random()).nextInt(1000));
                            } else if (room.getName() != null && room.getName().length() == 0) {
                                if (jtfNom.getText().isEmpty()) {
                                    String jop = JOptionPane.showInputDialog(getContentPane(), "Nom de la nova taula?");
                                    if (jop instanceof String) {
                                        room.setName(jop);
                                        jtfNom.setText(jop);
                                    }
                                } else {
                                    room.setName(jtfNom.getText());
                                }
                            }
                            carregar_sala();
                            refrescar_sala();
                            func = true;
                            
                            ControladoraPartidaOnline cpo = new ControladoraPartidaOnline(Integer.parseInt(jtfMAxJ.getText()),
                                    gui, "", 0, null);
                            gui.setCp(cpo);
                            cpo.afegirJugador(new Jugador(jtfAlias.getText(),6, 1, "avatar"));
                            dispose();

                        } catch (NumberFormatException exception) {
                            JOptionPane.showConfirmDialog(getContentPane(), "No pots introduir text en un lloc de dades numèriques", null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showConfirmDialog(getContentPane(), "Introdueix valors vàlids", null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                } else if (jrbUnir.isSelected()) {
                    if (room.getNick() != null && room.getNick().length() == 0) {
                        jtfAlias.setText("usuari" + (new Random()).nextInt(1000));
                    }

                    ControladoraPartidaOnline cpo = new ControladoraPartidaOnline(gui);
                    gui.setCp(cpo);                    
                    if (room.getName() != null && room.getName().length() == 0) {
                        carregar_sala();
                        if (jtfNom.getText().isEmpty()) {
                            String jop = JOptionPane.showInputDialog(getContentPane(), "Nom de la nova taula?");
                            if (jop instanceof String) {
                                room.setName(jop);
                                jtfNom.setText(jop);
                            }
                        } else {
                            room.setName(jtfNom.getText());
                        }
                        refrescar_sala();
                    }
                    func = true;
                    dispose();
                    cpo.afegirJugador(new Jugador(jtfAlias.getText(), cpo.taula.getPlaces(), 1, "avatar"));
                }
            }
        });

        jbRefresca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carregar_sala();
                refrescar_sala();

            }
        });

        jtfNom.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                carregar_sala();
                /*
                 * room.setName(lista.getSelectedValue().toString().substring(0,
                 * lista.getSelectedValue().toString().indexOf(" ")));
                 */
                if (jtfNom.getText().isEmpty()) {
                    String jop = JOptionPane.showInputDialog(getContentPane(), "Nom de la nova taula?");
                    if (jop instanceof String) {
                        room.setName(jop);
                        jtfNom.setText(jop);
                    }

                } else {
                    room.setName(jtfNom.getText());
                }

                refrescar_sala();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            private GuiLoginJabberPartida menu;

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    dispose();
                    menu =
                            new GuiLoginJabberPartida(gui);
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

    private void carregar_sala() {
        String cad = jtfNom.getText() + "@" + jcbServidors.getSelectedItem().toString() + "/" + jtfAlias.getText();
        room.setServer(jcbServidors.getModel().getSelectedItem().toString());
        room.setName(jtfNom.getText());
        room.setNick(jtfAlias.getText());
        //room.setJID(cad);
    }

    private void refrescar_sala() {
        jcbServidors.getModel().setSelectedItem(room.getServer());
    }

    public boolean isFunc() {
        return func;
    }

    public void setFunc(boolean func) {
        this.func = func;
    }

    public ControladoraGui getGui() {
        return gui;
    }

    public void setGui(ControladoraGui gui) {
        this.gui = gui;
    }

    public JID getRoom() {
        return room;
    }

    public void setRoom(JID room) {
        this.room = room;
    }
}
