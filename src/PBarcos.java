package myProject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class PBarcos extends JPanel{
    public static final String PATH = "/rsc/";
    private JButton portavion, destuctor, fragata, submarino, vertical, horizontal,sup_inf,inf_sup,izq_der,der_izq,explicacionBotones;
    private JPanel panelFlota, infojuego, panelBotones, subpanelBotones, subpanelBotones2;
    private JLabel asignarTurno;
    private JTextPane informacionJuego;
    private ImageIcon imageDestructor, imagePortavion, imageFragata, imageSubmarino;
    private TitledBorder tituloFlota,tituloInfo, titulo_Orientacion;
    private Border blackline;
    private String nombreBoton; // Guarda el texto del boton
    private int orientacion; // 0 si es vertical, 1 si es horizontal
    private int sentidoOrientacion; // 1 superior-inferior, 2 inferior-superior, 3 izquierda-derecha, 4 derecha-izquierda
    private int cantidadPortavion;
    private int cantidadSubmarino;
    private int cantidadDestructor;
    private int cantidadFragata;

    /**
     * Constructor de clase PBarcos
     */
    public PBarcos(){
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        this.setBackground(Color.decode("#514C73"));
        this.setPreferredSize(new Dimension(600,100));
        barcos();
    }

    /**
     * Creación de panel con los barcos y los botones de sentido y orientación
     */
    public void barcos(){
        GridBagConstraints gbc = new GridBagConstraints();

        nombreBoton = "";
        orientacion = 0;
        sentidoOrientacion = 0;

        // Cantidad inicial de barcos
        cantidadPortavion = 1;
        cantidadSubmarino = 2;
        cantidadDestructor = 3;
        cantidadFragata = 4;

        // Imágenes
        imageDestructor = new ImageIcon(getClass().getResource(PATH + "destructor.png"));
        imagePortavion = new  ImageIcon(getClass().getResource(PATH+"portavion.png"));
        imageFragata = new ImageIcon(getClass().getResource(PATH+"fragata.png"));
        imageSubmarino = new ImageIcon(getClass().getResource(PATH+"submarino.png"));

        // Botones de portaviones
        portavion = new JButton();
        portavion.setText("PORTAVION");
        portavion.setIcon(imagePortavion);
        portavion.setBackground(Color.decode("#67595A"));
        portavion.setHorizontalTextPosition( SwingConstants.CENTER );
        portavion.setVerticalTextPosition( SwingConstants.BOTTOM );
        portavion.setFocusable(false);
        portavion.setBorder(null);

        destuctor = new JButton();
        destuctor.setText("DESTRUCTOR");
        destuctor.setIcon(imageDestructor);
        destuctor.setBackground(Color.decode("#67595A"));
        destuctor.setHorizontalTextPosition( SwingConstants.CENTER );
        destuctor.setVerticalTextPosition( SwingConstants.BOTTOM );
        destuctor.setFocusable(false);
        destuctor.setBorder(null);

        fragata = new JButton();
        fragata.setText("FRAGATA");
        fragata.setIcon(imageFragata);
        fragata.setBackground(Color.decode("#67595A"));
        fragata.setHorizontalTextPosition( SwingConstants.CENTER );
        fragata.setVerticalTextPosition( SwingConstants.BOTTOM );
        fragata.setFocusable(false);
        fragata.setBorder(null);

        submarino = new JButton();
        submarino.setText("SUBMARINO");
        submarino.setIcon(imageSubmarino);
        submarino.setBackground(Color.decode("#67595A"));
        submarino.setHorizontalTextPosition( SwingConstants.CENTER );
        submarino.setVerticalTextPosition( SwingConstants.BOTTOM );
        submarino.setFocusable(false);
        submarino.setBorder(null);

        // Botones de orientación
        vertical = new JButton("Vertical");
        vertical.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        vertical.setBackground(Color.decode("#5274FF"));
        vertical.setForeground(Color.white);
        vertical.setFocusable(false);
        vertical.setBorder(null);

        horizontal = new JButton("Horizontal");
        horizontal.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        horizontal.setBackground(Color.decode("#5274FF"));
        horizontal.setForeground(Color.white);
        horizontal.setFocusable(false);
        horizontal.setBorder(null);

        // Botones de sentido
        sup_inf = new JButton("Superior-Inferior");
        sup_inf.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        sup_inf.setBackground(Color.decode("#5274FF"));
        sup_inf.setForeground(Color.white);
        sup_inf.setFocusable(false);
        sup_inf.setBorder(null);

        inf_sup = new JButton("Inferior-Superior");
        inf_sup.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        inf_sup.setBackground(Color.decode("#5274FF"));
        inf_sup.setForeground(Color.white);
        inf_sup.setFocusable(false);
        inf_sup.setBorder(null);

        izq_der = new JButton("Izquierda-Derecha");
        izq_der.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        izq_der.setBackground(Color.decode("#5274FF"));
        izq_der.setForeground(Color.white);
        izq_der.setFocusable(false);
        izq_der.setBorder(null);

        der_izq = new JButton("Derecha-Izquierda");
        der_izq.setFont(new Font(Font.SERIF,Font.ROMAN_BASELINE,15));
        der_izq.setBackground(Color.decode("#5274FF"));
        der_izq.setForeground(Color.white);
        der_izq.setFocusable(false);
        der_izq.setBorder(null);

        // Flota
        blackline = BorderFactory.createLineBorder(Color.WHITE);
        panelFlota = new JPanel();
        panelFlota.setLayout(new GridLayout(2,2,10,10));
        panelFlota.setPreferredSize(new Dimension(350,300));
        panelFlota.setBackground(Color.decode("#67595A"));
        tituloFlota = BorderFactory.createTitledBorder(blackline, "Tus barcos");
        tituloFlota.setTitleJustification(TitledBorder.CENTER);
        panelFlota.setBorder(tituloFlota);
        panelFlota.add(portavion);
        panelFlota.add(destuctor);
        panelFlota.add(fragata);
        panelFlota.add(submarino);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelFlota, gbc);

        // Texto bajo flota
        asignarTurno = new JLabel();
        asignarTurno.setHorizontalAlignment(SwingConstants.CENTER);

        informacionJuego = new JTextPane();
        informacionJuego.setEditable(false);
        informacionJuego.setBackground(Color.decode("#67595A"));
        StyledDocument documentStyle = informacionJuego.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        infojuego = new JPanel(new GridLayout(2,0,0,0));
        infojuego.setPreferredSize(new Dimension(350,100));
        infojuego.setBackground(Color.decode("#67595A"));
        tituloInfo = BorderFactory.createTitledBorder(blackline, "Información del juego");
        tituloInfo.setTitleJustification(TitledBorder.CENTER);
        infojuego.setBorder(tituloInfo);
        infojuego.add(asignarTurno);
        infojuego.add(informacionJuego);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(infojuego, gbc);

        // Panel botones dentro de panel flota
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0,2,5,10));
        panelBotones.setBackground(Color.decode("#67595A"));
        panelBotones.setPreferredSize(new Dimension(350,120));
        titulo_Orientacion = BorderFactory.createTitledBorder(blackline, "¿Como quieres acomodar tu flota?");
        titulo_Orientacion.setTitleJustification(TitledBorder.CENTER);
        panelBotones.setBorder(titulo_Orientacion);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(panelBotones,gbc);

        // panel dentro de panel botones para los botones de horizontal y vertical
        subpanelBotones = new JPanel(new GridLayout());
        subpanelBotones.setPreferredSize(new Dimension(175,120));
        subpanelBotones.setBackground(Color.decode("#67595A"));
        subpanelBotones.setLayout(new GridLayout(2,0,5,5));
        subpanelBotones.add(vertical);
        subpanelBotones.add(horizontal);
        panelBotones.add(subpanelBotones,BorderLayout.WEST);

        // panel dentro de panel botones para los botones de sup_inf, inf_sup, der_izq, izq_der
        subpanelBotones2 = new JPanel();
        subpanelBotones2.setPreferredSize(new Dimension(175,120));
        subpanelBotones2.setBackground(Color.decode("#67595A"));
        subpanelBotones2.setLayout(new GridLayout(4,0,5,5));
        subpanelBotones2.add(sup_inf);
        subpanelBotones2.add(inf_sup);
        subpanelBotones2.add(izq_der);
        subpanelBotones2.add(der_izq);
        panelBotones.add(subpanelBotones2,BorderLayout.EAST);

    }

    /**
     * Retorna el botón del barco especificado
     * @param barco
     * @return JButton
     */
    public JButton getBotonBarco(String barco){
        JButton boton = new JButton();
        if(barco.equals("portavion")){
            boton = portavion;
        }else{
            if(barco.equals("submarino")){
                boton = submarino;
            }else{
                if(barco.equals("destructor")){
                    boton = destuctor;
                }else{
                    if(barco.equals("fragata")){
                        boton = fragata;
                    }
                }
            }
        }
        return boton;
    }

    /**
     * Retorna el botón de orientación especificado
     * @param orientacion
     * @return JButton
     */
    public JButton getBotonOrientacion(String orientacion){
        JButton boton = new JButton();
        if(orientacion.equals("vertical")){
            boton = vertical;
        }else{
            if(orientacion.equals("horizontal")){
                boton = horizontal;
            }
        }
        return boton;
    }


    /**
     * Retorna el botón del sentido de la orientación especificado
     * @param sentido
     * @return JButton
     */
    public JButton getBotonSentidoOrientacion(String sentido){
        JButton boton = new JButton();
        if(sentido.equals("sup_inf")){
            boton = sup_inf;
        }else{
            if(sentido.equals("inf_sup")){
                boton = inf_sup;
            }else{
                if(sentido.equals("izq_der")){
                    boton = izq_der;
                }else{
                    if(sentido.equals("der_izq")){
                        boton = der_izq;
                    }
                }
            }
        }
        return boton;
    }

    /**
     * Guarda el nombre del botón presionado en un string
     * @param _nombreBoton
     */
    public void setNombreBoton(String _nombreBoton){
        nombreBoton = _nombreBoton;
    }

    /**
     * Retorna el nombre del barco que se presionó
     * @return String
     */
    public String getNombreBoton(){
        return nombreBoton;
    }

    /**
     * Asigna el estado de orientación
     * @param _orientacion
     */
    public void setOrientacion(int _orientacion){
        orientacion = _orientacion;
    }

    /**
     * Asigna el estado de sentidoOrientacion
     * @param _sentidoOrientacion
     */
    public void setSentidoOrientacion(int _sentidoOrientacion){
        sentidoOrientacion = _sentidoOrientacion;
    }

    /**
     * Retorna el estado de orientación
     * @return int
     */
    public int getOrientacion(){
        return orientacion;
    }

    /**
     * Retorna el estado de sentidoOrientacion
     * @return int
     */
    public int getSentidoOrientacion(){
        return sentidoOrientacion;
    }

    /**
     * Reduce la cantidad disponible del barco ingresado
     * @param barco
     */
    public void setCantidadBarco(String barco){
        if(barco.equals("portavion")){
            cantidadPortavion--;
        }else{
            if(barco.equals("submarino")){
                cantidadSubmarino--;
            }else{
                if(barco.equals("destructor")){
                    cantidadDestructor--;
                }else{
                    if(barco.equals("fragata")){
                        cantidadFragata--;
                    }
                }
            }
        }
    }

    /**
     * Retorna la cantidad disponible del barco ingresado
     * @param barco
     * @return int
     */
    public int getCantidadBarco(String barco){
        int cantidad = 0;
        if(barco.equals("portavion")){
            cantidad = cantidadPortavion;
        }else{
            if(barco.equals("submarino")){
                cantidad = cantidadSubmarino;
            }else{
                if(barco.equals("destructor")){
                    cantidad = cantidadDestructor;
                }else{
                    if(barco.equals("fragata")){
                        cantidad = cantidadFragata;
                    }
                }
            }
        }
        return cantidad;
    }

    /**
     * Retorna la cantidad total de naves disponibles
     * @return int
     */
    public int cantidadTotalNaves(){
        return cantidadPortavion + cantidadSubmarino + cantidadDestructor + cantidadFragata;
    }

    /**
     * Retorna el JTextPane para editar la información del juego
     * @return JTextPane
     */
    public JTextPane getInformacionJuego(){
        return informacionJuego;
    }

    /**
     * Retorna el JLabel que edita el turno
     * @return JLabel
     */
    public JLabel getAsignarTurno(){
        return asignarTurno;
    }

}