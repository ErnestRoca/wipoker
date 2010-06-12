/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import controladora.ControladoraGui;
import controladora.jabber.Connexio;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import org.jivesoftware.smack.XMPPException;
import presentacio.GuiMenu;
import presentacio.jabber.BuscarSala;
import presentacio.jabber.GuiLoginJabber;

/**
 *
 * @author wida45787385
 */
public class GuiLoginJabberPartida {

    private ControladoraGui gui;
    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlNom;
    private JLabel jlPassword;
    private JLabel jlServidor;
    private JTextField jtfNom;
    private JTextField jtfPassword;
    private JTextField jtfServidor;
    private JButton jbTornar;
    private JButton jbLogin;
    private JLabel jlBarra;
    private JButton jbOffline;
    private JLabel jlOnline;
    private JLabel jlOffline;
    private JPanel jpBarra;
    private JPanel jpBarra2;

    public GuiLoginJabberPartida() throws InterruptedException {
        iniciarComponents();
    }

    public GuiLoginJabberPartida(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("login Jabber");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setBounds(0, 0, 340, 950);
        jpFons.setLayout(null);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jlTitol.setBounds(0, 0, 340, 104);
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setBounds(0, 104, 340, 499);

        jlOnline = new JLabel();
        jlOnline.setBounds(100, 65, 140, 104);
        jlOnline.setText("Si vols jugar Online: ");
        jlOnline.setForeground(Color.red);
        jpFons.add(jlOnline);

        jlOffline = new JLabel();
        jlOffline.setBounds(100, 410, 140, 104);
        jlOffline.setText("Si vols jugar Offline: ");
        jlOffline.setForeground(Color.red);
        jpFons.add(jlOffline);

        jpBarra = new JPanel();
        jpBarra.setBounds(20, 440, 300, 2);
        jpBarra.setBackground(Color.red);
        jpBarra.setLayout(null);
        jpFons.add(jpBarra);

        jpBarra2 = new JPanel();
        jpBarra2.setBounds(20, 530, 300, 2);
        jpBarra2.setBackground(Color.red);
        jpBarra2.setLayout(null);
        jpFons.add(jpBarra2);

        jlNom = new JLabel();
        jlNom.setBounds(30, 100, 340, 104);
        jlNom.setText("Usuari");
        jlNom.setForeground(Color.red);
        jpFons.add(jlNom);

        jtfNom = new JTextField(20);
        jtfNom.setBounds(170, 140, 120, 24);
        jpFons.add(jtfNom);

        jlPassword = new JLabel();
        jlPassword.setBounds(30, 170, 340, 104);
        jlPassword.setText("Contrasenya");
        jlPassword.setForeground(Color.red);
        jpFons.add(jlPassword);

        jtfPassword = new JPasswordField();
        jtfPassword.setBounds(170, 210, 120, 24);
        jpFons.add(jtfPassword);

        jlServidor = new JLabel();
        jlServidor.setBounds(30, 240, 340, 104);
        jlServidor.setText("Servidor");
        jlServidor.setForeground(Color.red);
        jpFons.add(jlServidor);

        jtfServidor = new JTextField("jabberes.org");
        jtfServidor.setBounds(170, 280, 120, 24);
        jpFons.add(jtfServidor);

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbLogin = new JButton("LOGIN");
        jbLogin.setMnemonic('L');
        jbLogin.setCursor(cursor);
        jbLogin.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbLogin.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbLogin.setIconTextGap(-260);
        jbLogin.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbLogin.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbLogin.setHorizontalTextPosition(SwingConstants.CENTER);
        jbLogin.setBounds(100, 400, 120, 24);
        jpFons.add(jbLogin);

        jbOffline = new JButton("OFFLINE");
        jbOffline.setMnemonic('L');
        jbOffline.setCursor(cursor);
        jbOffline.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbOffline.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbOffline.setIconTextGap(-260);
        jbOffline.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbOffline.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbOffline.setHorizontalTextPosition(SwingConstants.CENTER);
        jbOffline.setBounds(100, 490, 120, 24);
        jpFons.add(jbOffline);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setMnemonic('r');
        jbTornar.setCursor(cursor);
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbTornar.setBounds(100, 545, 120, 24);
        jpFons.add(jbTornar);

        jlBarra = new JLabel("Menú Principal/Jugar");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbLogin.addActionListener(new ActionListener() {

            private GuiNovaPartidaOnline2 guiNovaPartidaOline2;

            @Override
            public void actionPerformed(ActionEvent event) {
                if (!jtfServidor.getText().isEmpty() && !jtfNom.getText().isEmpty() && !jtfPassword.getText().isEmpty()) {
                    if (!gui.isLogin()) {
                        try {
                            gui.getCjabber().setConnexio(Connexio.crearConnexio(jtfServidor.getText()));
                            GestioUsuaris.ferLogin(gui.getCjabber().getConnexio(), jtfNom.getText(), jtfPassword.getText());
                            gui.setLogin(true);
                            JOptionPane.showMessageDialog(jFrame, "Conectat i logat correctament");
                            jFrame.dispose();
                            try {
                                guiNovaPartidaOline2 = new GuiNovaPartidaOnline2(gui, jFrame, true);
                                guiNovaPartidaOline2.setLocation(jFrame.getLocation());
                                guiNovaPartidaOline2.setVisible(true);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (XMPPException ex) {
                            gui.setLogin(false);
                            Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(jFrame, "Introdueix valors vàlids", null, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        jbOffline.addActionListener(new ActionListener() {

            private GuiNovaPartidaOffline novaPartidaOffline;

            @Override
            public void actionPerformed(ActionEvent event) {
                if (gui.isLogin()) {
                    GestioUsuaris.desconnectar(gui.getCjabber().getConnexio());
                    gui.setLogin(false);
                }
                try {
                    jFrame.dispose();
                    novaPartidaOffline = new GuiNovaPartidaOffline(gui);
                    novaPartidaOffline.getjFrame().setLocation(jFrame.getLocation());
                    novaPartidaOffline.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(GuiLoginJabber.class.getName()).log(Level.SEVERE, null, ex);
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
                    new GuiLoginJabberPartida();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiLoginJabberPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
