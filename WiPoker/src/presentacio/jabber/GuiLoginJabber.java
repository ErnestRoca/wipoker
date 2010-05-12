/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;
import presentacio.GuiMenu;

/**
 *
 * @author wida45787385
 */
public class GuiLoginJabber {

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
    private JLabel jlCorreu;
    private JTextField jtfCorreu;
    private JButton jbTornar;
    private JButton jbLogin;
    private GuiMenuJabber menu;
    private JLabel jlBarra;

    public GuiLoginJabber() throws InterruptedException {
        iniciarComponents();

    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("login Jabber");
        //jFrame.setLayout(null);
        //jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setBounds(0, 0, 340, 950);
        jpFons.setLayout(null);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setLayout(null);
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setOpaque(false);

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

        jlPassword2 = new JLabel();
        jlPassword2.setBounds(30, 240, 340, 104);
        jlPassword2.setText("Repeteix contrasenya");
        jlPassword2.setForeground(Color.red);
        jpFons.add(jlPassword2);

        jtfPassword2 = new JPasswordField();
        jtfPassword2.setBounds(170, 280, 120, 24);
        jpFons.add(jtfPassword2);

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

        jbTornar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    menu = new GuiMenuJabber();
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
                    new GuiLoginJabber();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}

