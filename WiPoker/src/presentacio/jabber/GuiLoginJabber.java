/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

import controladora.ControladoraGui;
import controladora.ControladoraJabber;
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

/**
 *
 * @author wida45787385
 */
@Deprecated
public class GuiLoginJabber {

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
    private JLabel jlCorreu;
    private JTextField jtfCorreu;
    private JButton jbTornar;
    private JButton jbLogin;
    private JLabel jlBarra;

    public GuiLoginJabber() throws InterruptedException {
        iniciarComponents();
    }

    public GuiLoginJabber(ControladoraGui gui) throws InterruptedException {
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

        jtfServidor = new JTextField();
        jtfServidor.setBounds(170, 280, 120, 24);
        jpFons.add(jtfServidor);

        jlCorreu = new JLabel();
        jlCorreu.setBounds(30, 310, 340, 104);
        jlCorreu.setText("Correu electrónic");
        jlCorreu.setForeground(Color.red);
        jpFons.add(jlCorreu);

        jtfCorreu = new JTextField();
        jtfCorreu.setBounds(170, 350, 120, 24);
        jpFons.add(jtfCorreu);

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
        jbLogin.setBounds(30, 450, 120, 24);
        jpFons.add(jbLogin);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setMnemonic('r');
        jbTornar.setCursor(cursor);
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbTornar.setBounds(30, 500, 120, 24);
        jpFons.add(jbTornar);

        jlBarra = new JLabel("Menú Principal/Jabber/Login");
        jlBarra.setForeground(Color.white);
        jlBarra.setBounds(2, 578, 340, 30);
        jpFons.add(jlBarra);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if (!jtfServidor.getText().equals("") && (!jtfNom.getText().equals("")) && (!jtfPassword.getText().equals(""))) {
                    if (!gui.isLogin()) {
                        try {
                            System.out.println(gui.getCjabber() == null);
                            gui.getCjabber().setConnexio(Connexio.crearConnexio(jtfServidor.getText()));
                            //GestioUsuaris.connectar(gui.getCjabber().getConnexio());
                            gui.getCjabber().prepararEscoltadorsConnexio();
                            GestioUsuaris.ferLogin(gui.getCjabber().getConnexio(), jtfNom.getText(), jtfPassword.getText());
                            gui.setLogin(true);
                            JOptionPane.showMessageDialog(jFrame, "Conectat i logat correctament");
                            jbTornar.doClick();
                        } catch (XMPPException ex) {
                            gui.setLogin(false);
                            Logger.getLogger(GuiLoginJabber.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

        jbTornar.addActionListener(new ActionListener() {

            private GuiMenuJabber menu;

            @Override
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

            @Override
            public void run() {
                try {
                    new GuiLoginJabber();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
