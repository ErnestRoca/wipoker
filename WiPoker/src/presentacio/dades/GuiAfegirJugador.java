
/*
 * GuiAfegirJugador.java
 */
package presentacio.dades;

import controladora.ControladoraGui;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.ButtonBorder;

/**
 * Gui que serveix per afegir un jugador a la base de dases d'estadístiques.
 * Modela una GuiAfegirJugador en el domini suggerit pels requeriments funcionals.
 *
 * @author wida47716705
 */
public class GuiAfegirJugador {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlNom;
    private JLabel jlAlies;
    private JLabel jlEdat;
    private JTextField jtfNom;
    private JTextField jtfAlies2;
    private JTextField jtfEdat;
    private JLabel jlDNI;
    private JTextField jtfDNI;
    private JLabel jlTelefon;
    private JTextField jtfTelefon;
    private JButton jbAfegir;
    private JButton jbTornar;
    private ControladoraGui gui;
    private GuiMenuDades menu;

    /**
     * 
     * Constructor d'instàncies de la classe.<br>
     *
     * pre: Cap.<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors dels paràmetres als atributs<br>
     * 
     */
    public GuiAfegirJugador() throws InterruptedException {
        iniciarComponents();
    }

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors dels paràmetres als atributs<br>
     * @param gui
     */
    public GuiAfegirJugador(ControladoraGui gui) throws InterruptedException {
        this.gui = gui;
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        //jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Afegir jugador");
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

        jlNom = new JLabel();
        jlNom.setBounds(60, 100, 340, 104);
        jlNom.setText("Nom Complet ");
        jlNom.setForeground(Color.red);
        jlNom.setLayout(null);
        jpFons.add(jlNom);

        jtfNom = new JTextField(20);
        jtfNom.setBounds(170, 140, 120, 24);
        jpFons.add(jtfNom);

        jlAlies = new JLabel();
        jlAlies.setBounds(115, 170, 340, 104);
        jlAlies.setText("Alies ");
        jlAlies.setForeground(Color.red);
        jlAlies.setLayout(null);
        jpFons.add(jlAlies);

        jtfAlies2 = new JTextField(20);
        jtfAlies2.setBounds(170, 210, 120, 24);
        jpFons.add(jtfAlies2);

        jlEdat = new JLabel();
        jlEdat.setBounds(115, 240, 340, 104);
        jlEdat.setText("Edat ");
        jlEdat.setForeground(Color.red);
        jlEdat.setLayout(null);
        jpFons.add(jlEdat);

        jtfEdat = new JTextField(20);
        jtfEdat.setBounds(170, 280, 120, 24);
        jpFons.add(jtfEdat);

        jlDNI = new JLabel();
        jlDNI.setBounds(120, 310, 340, 104);
        jlDNI.setText("DNI ");
        jlDNI.setForeground(Color.red);
        jlDNI.setLayout(null);
        jpFons.add(jlDNI);

        jtfDNI = new JTextField(20);
        jtfDNI.setBounds(170, 350, 120, 24);
        jpFons.add(jtfDNI);

        jlTelefon = new JLabel();
        jlTelefon.setBounds(100, 380, 340, 104);
        jlTelefon.setText("Telefon ");
        jlTelefon.setForeground(Color.red);
        jlTelefon.setLayout(null);
        jpFons.add(jlTelefon);

        jtfTelefon = new JTextField(20);
        jtfTelefon.setBounds(170, 420, 120, 24);
        jpFons.add(jtfTelefon);

        jbAfegir = new JButton("CREAR");
        jbAfegir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbAfegir.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbAfegir.setLayout(null);
        jbAfegir.setBounds(100, 490, 120, 24);
        jbAfegir.setIconTextGap(-260);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbAfegir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbAfegir.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbAfegir);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(new ButtonBorder(Color.black, Color.darkGray, Color.lightGray, Color.lightGray));
        jbTornar.setLayout(null);
        jbTornar.setBounds(100, 540, 120, 24);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);


        jbAfegir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jFrame, "Jugador afegit satisfactoriament");

            }
        });


        jbTornar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    menu = new GuiMenuDades(gui);
                    menu.getjFrame().setLocation(jFrame.getLocation());
                    menu.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * getter JFrame
     * @return JFrame: El jFrame de la gui
     */
    public JFrame getjFrame() {
        return jFrame;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new GuiAfegirJugador();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiAfegirJugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
