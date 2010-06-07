/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

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
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GuiCrearCompteJabber {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlNom;
    private JLabel jlPassword;
    private JLabel jlPassword2;
    private JTextField jtfNom;
    private JTextField jtfPassword;
    private JTextField jtfPassword2;
    private JLabel jlServidor;
    private JTextField jtfServidor;
    private JButton jbTornar;
    private JButton jbCrear;
    private JLabel jlBarra;
    private ControladoraGui gui;

    public GuiCrearCompteJabber() throws InterruptedException {
        iniciarComponents();
    }

    public GuiCrearCompteJabber(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Crear compte Jabber");
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

        jlNom = new JLabel();
        jlNom.setBounds(30, 100, 340, 104);
        jlNom.setText("Nom Complet ");
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

        jlPassword2 = new JLabel();
        jlPassword2.setBounds(30, 240, 340, 104);
        jlPassword2.setText("Repeteix contrasenya");
        jlPassword2.setForeground(Color.red);
        jlPassword2.setLayout(null);
        jpFons.add(jlPassword2);

        jtfPassword2 = new JPasswordField();
        jtfPassword2.setBounds(170, 280, 120, 24);
        jpFons.add(jtfPassword2);

        jlServidor = new JLabel();
        jlServidor.setBounds(30, 310, 340, 104);
        jlServidor.setText("Servidor");
        jlServidor.setForeground(Color.red);
        jlServidor.setLayout(null);
        jpFons.add(jlServidor);

        jtfServidor = new JTextField(20);
        jtfServidor.setBounds(170, 350, 120, 24);
        jpFons.add(jtfServidor);

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbCrear = new JButton("CREAR");
        jbCrear.setMnemonic('C');
        jbCrear.setCursor(cursor);
        jbCrear.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrear.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbCrear.setBounds(30, 450, 120, 24);
        jbCrear.setIconTextGap(-260);
        jbCrear.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrear.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrear.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrear);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setMnemonic('r');
        jbTornar.setCursor(cursor);
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setBounds(30, 500, 120, 24);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jlBarra = new JLabel("Menú Principal/Jabber/Crear Compte");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbCrear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (gui.getCjabber().getConnexio() == null) {
                    if (jtfServidor.getText() == null || jtfServidor.getText().equals("")) {
                        try {
                            String servidor = JOptionPane.showInputDialog("A quin servidor creem el compte?");
                            gui.getCjabber().setConnexio(Connexio.crearConnexio(servidor));
                            //GestioUsuaris.connectar(gui.getCjabber().getConnexio());
                        } catch (XMPPException ex) {
                            Logger.getLogger(GuiCrearCompteJabber.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //GestioUsuaris.connectar(gui.getCjabber().getConnexio());
                    } else {
                        try {
                            gui.getCjabber().setConnexio(Connexio.crearConnexio(jtfServidor.getText()));
                            //GestioUsuaris.connectar(gui.getCjabber().getConnexio());
                        } catch (XMPPException ex) {
                            Logger.getLogger(GuiCrearCompteJabber.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //GestioUsuaris.connectar(gui.getCjabber().getConnexio());
                    }
                } else {
                    if (gui.getCjabber().getConnexio().getAccountManager().supportsAccountCreation()) {
                        if (jtfPassword.getText().equals(jtfPassword2.getText())) {
                            try {
                                gui.getCjabber().getConnexio().getAccountManager().createAccount(jtfNom.getText(), jtfPassword.getText());
                            } catch (XMPPException ex) {
                                Logger.getLogger(GuiCrearCompteJabber.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(jFrame, "Les contrasenyes no coincideixen");
                        }
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "Aquest servidor no suporta la creació de comptes");
                    }
                }
            }
        });

        jbTornar.addActionListener(
                new ActionListener() {

                    private GuiMenuJabber menu;

                    public void actionPerformed(ActionEvent event) {
                        try {
                            jFrame.dispose();
                            menu = new GuiMenuJabber(gui);
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

            public void run() {
                try {
                    new GuiCrearCompteJabber();



                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });

    }
}
