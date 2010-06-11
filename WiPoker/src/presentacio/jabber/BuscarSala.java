/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

import controladora.ControladoraGui;
import controladora.jabber.JID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import presentacio.GuiTaulell;
import presentacio.partida.GuiNovaPartidaOnline;

/**
 *
 * @author wida53312560
 */
public class BuscarSala extends javax.swing.JDialog {

    private ControladoraGui gui;
    private final static String[] servidors = {"conf.jabberes.org",
        "muc.jabber.upc.es", "conferencia.jabber-hispano.org",
        "conference.seunet.org", "conference.jabber.org"};
    public JID room = new JID();
    public boolean func = false;
    private JPanel jPanelGlobal;
    private JList jlLlista;
    private JComboBox jcbServidors;
    private JButton jbRefresca;
    private JButton jbCancelar;
    private JButton jbEntrar;
    private JLabel jlAlias;
    private JTextField jtfAlias;

    public BuscarSala(ControladoraGui gui, Frame owner, boolean modal) {
        super(owner, "Buscar sala", modal);
        this.gui = gui;
        iniciarComponents();
        crearEscoltadors();
        llistarServidors();
    }

    /** Crea objectes crear els components de la UI. */
    private void iniciarComponents() {
        crearJFrame();
        crearJPanels();
        crearControls();
        //iniciarJFrame();
    }

    private void crearJFrame() {
        final int w = 300;
        final int h = 450;
        setSize(w, h);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    //Mètode per introduir una imatge de fons.
    public class JPanelGlobal extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg"));
            g.drawImage(imatgeFons.getImage(), 0, 0, 333, 499, null);
            setOpaque(true);
        }
    }

    private void crearJPanels() {
        jPanelGlobal = new JPanelGlobal();
        jPanelGlobal.setBackground(Color.GREEN);
        jPanelGlobal.setLayout(null);
        jPanelGlobal.setBounds(0, 0, 300, 450);
        getContentPane().add(jPanelGlobal);
    }

    private void crearControls() {
        jcbServidors = new JComboBox();
        jcbServidors.setModel(new javax.swing.DefaultComboBoxModel(servidors));
        final int x1 = 10;
        final int y1 = 20;
        final int w1 = 265;
        final int h1 = 27;
        jcbServidors.setBounds(x1, y1, w1, h1);
        jPanelGlobal.add(jcbServidors);


        jlLlista = new JList();
        jlLlista.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jlLlista.setBounds(10, 60, 265, 250);
        jPanelGlobal.add(jlLlista);

        jbRefresca = new JButton("Refresca");
        jbRefresca.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
        jbRefresca.setHorizontalTextPosition(SwingConstants.RIGHT);
        jbRefresca.setBounds(10, 320, 85, 25);
        jPanelGlobal.add(jbRefresca);

        jlAlias = new JLabel("Alias");
        jlAlias.setForeground(Color.WHITE);
        jlAlias.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
        jlAlias.setBounds(125, 320, 27, 17);
        jPanelGlobal.add(jlAlias);

        jtfAlias = new JTextField();
        jtfAlias.setBounds(170, 320, 105, 27);
        jPanelGlobal.add(jtfAlias);

        jbEntrar = new JButton("Entrar");
        jbEntrar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
        jbEntrar.setBounds(90, 390, 90, 25);
        jPanelGlobal.add(jbEntrar);

        jbCancelar = new JButton("Cancel·lar");
        jbCancelar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 12));
        jbCancelar.setBounds(190, 390, 95, 25);
        jPanelGlobal.add(jbCancelar);
    }

    private void crearEscoltadors() {

        jbEntrar.addActionListener(new ActionListener() {
            private GuiNovaPartidaOnline guiNovaPartidaOnline;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    carregar_sala();
                    if (room.getNick() != null && room.getNick().length() == 0) {
                        jtfAlias.setText("usuari" + (new Random()).nextInt(1000));
                        return;
                    }
                    if (room.getName() != null && room.getName().length() == 0) {
                        jlLlista.setSelectedIndex(1);
                        carregar_sala();
                        if (jlLlista.getSelectedIndex() == 0) {
                            String jop = JOptionPane.showInputDialog(this, "Nom de la nova taula?");
                            if (jop instanceof String) {
                                room.setName(jop);
                            }
                        } else {
                            room.setName(jlLlista.getSelectedValue().toString());
                        }
                        refrescar_sala();
                        return;
                    }
                    func = true;
                    guiNovaPartidaOnline = new GuiNovaPartidaOnline(gui);
                    dispose();
                    guiNovaPartidaOnline.getjFrame().setLocation(guiNovaPartidaOnline.getjFrame().getLocation());
                    guiNovaPartidaOnline.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BuscarSala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        jbRefresca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carregar_sala();
                refrescar_sala();
                llistarServidors();
            }
        });

        jlLlista.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                carregar_sala();
                /*
                 * room.setName(lista.getSelectedValue().toString().substring(0,
                 * lista.getSelectedValue().toString().indexOf(" ")));
                 */
                if (jlLlista.getSelectedIndex() == 0) {
                    String jop = JOptionPane.showInputDialog(this,
                            "Nom de la nova taula?");
                    if (jop instanceof String) {
                        room.setName(jop);
                    }
                } else {
                    room.setName(jlLlista.getSelectedValue().toString());
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
    }

    /** Crea l'objecte controlador del cas d'ús. */
    @Deprecated
    private void iniciarJFrame() {
        setVisible(true);
    }

    public void llistarServidors() {
        int i;
        Vector v = new Vector();
        v.add("Afegir...");
        for (i = 1; i < 14; i++) {
            v.add("WiPoker_Taula" + i);
        }
        jlLlista.setListData(v);
    }

    private void carregar_sala() {
        room.setJID(jcbServidors.getModel().getSelectedItem().toString());
        room.setNick(jtfAlias.getText());
    }

    private void refrescar_sala() {
        jcbServidors.getModel().setSelectedItem(room.getJID());
    }
}
