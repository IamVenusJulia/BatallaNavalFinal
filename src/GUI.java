package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Clase principal
 * @autor Daniel Arias Castrillón - 2222205
 * @autor Venus Juliana Paipilla 2177134-3744
 */
public class GUI extends JFrame {
    public static final String PATH = "/rsc/";
    private Header header;
    private JButton start, reiniciar;
    private Listener listener;
    private ImageIcon play, reinicio;
    private JPanel northPanel, southPanel, eastPanel, centralPanel;
    private PCasillas PCasillas;
    private colocarBarcos colocarBarcos;
    private PBarcos PBarcos;
    private Enemigo opponentwindow;
    private int faseJuego; // 1 seleccionar barco, 2 seleccionar orientacion del barco, 3 seleccionar sentido del barco, 4 colocar barco en el tablero, 5 pelea, 6 turno del oponente
    private Pelea pelea;
    private int hundidosCount; // Contador de barcos hundidos
    private Timer time; // establece el tiempo que tarde el oponente en escoger casilla
    private Image image;

    /**
     * Constructor de la clase GUI
     */
    public GUI() {
        initGUI();

        // Configuración del JFrame
        this.setTitle("Batalla Naval");
        this.setIconImage(image);
        this.setUndecorated(false);
        this.setSize(1500, 750);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Método inicial para la configuracion del JComponent,
     * crea objetos tipo listener y control empleados en la clase GUI
     */
    private void initGUI() {
        // ventana del oponente
        opponentwindow = new Enemigo(this);

        // Icono del JFrame
        image = new ImageIcon(getClass().getResource(PATH + "barcoIcono.png")).getImage();

        // Set up JFrame Container's Layout
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel(); // Cambio de "panelEste" a "panelOeste"
        centralPanel = new JPanel();

        // Creación de paneles para el JFrame
        northPanel.setBackground(Color.decode("#514C73"));
        southPanel.setBackground(Color.decode("#514C73"));
        eastPanel.setBackground(Color.decode("#514C73")); // Cambio de "panelEste" a "panelOeste"
        centralPanel.setBackground(Color.decode("#514C73"));

        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 5));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 5));
        eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 60)); // Cambio de "panelEste" a "panelOeste"
        centralPanel.setLayout(new GridLayout(1, 1, 0, 100));

        southPanel.setPreferredSize(new Dimension(100, 60));
        northPanel.setPreferredSize(new Dimension(100, 60));
        eastPanel.setPreferredSize(new Dimension(1000, 60)); // Cambio de "panelEste" a "panelOeste"
        centralPanel.setPreferredSize(new Dimension(600, 100));

        // Se agregan los paneles al JFrame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.WEST); // Cambio de "panelEste" a "panelOeste"
        this.add(centralPanel, BorderLayout.CENTER);

        // Estado del juego
        faseJuego = 1;

        // evento listener
        listener = new Listener();

        // panel de Barcos
        PBarcos = new PBarcos();

        // Set up JComponents
        // Imágenes
        play = new ImageIcon(getClass().getResource(PATH + "play.png"));
        reinicio = new ImageIcon(getClass().getResource(PATH + "reiniciarIcon.png"));

        // Título
        header = new Header("B A T A L L A   N A V A L", Color.decode("#514C73"));
        northPanel.add(header, FlowLayout.LEFT);

        // Casillas
        PCasillas = new PCasillas();
        colocarBarcos = new colocarBarcos(PCasillas, PBarcos);
        eastPanel.add(PCasillas); // Cambio de "panelEste" a "panelOeste"

        // Flota
        centralPanel.add(PBarcos);


        // Inicio de partida
        start = new JButton("Iniciar juego", play);
        start.addActionListener(listener);
        start.setBackground(Color.decode("#5274FF"));
        start.setForeground(Color.WHITE);
        start.setFocusable(false);
        start.setBorder(null);
        southPanel.add(start, FlowLayout.LEFT);

        // botón de reinicio
        reiniciar = new JButton("Reiniciar", reinicio);
        reiniciar.addActionListener(listener);
        reiniciar.setBackground(Color.decode("#5274FF"));
        reiniciar.setForeground(Color.WHITE);
        reiniciar.setFocusable(false);
        reiniciar.setBorder(null);
        southPanel.add(reiniciar, FlowLayout.CENTER);

        // Se agrega él escucha a todos los botones de todas las clases
        setEscuchaBotones("remover");
        setVerticalHorizontal("remover");
        setOrientacionSentidoVertical("remover");
        setOrientacionSentidoHorizontal("remover");

        // barco del oponente
        while (opponentwindow.getPintarFlotaOponente().cantidadTotalNaves() > 0) {
            opponentwindow.distribucionFlotaOponente();
        }

        // funciones de pelea
        pelea = new Pelea(PCasillas, opponentwindow.getPanelTableroOponente());

        hundidosCount = 0;
        // Timer para el turno del oponente
        time = new Timer(2000, listener);
    }

    /**
     * Reinicia el juego
     */
    public void reset(){
        this.dispose();
        GUI gui = new GUI();
    }

    /**
     * Proceso principal del programa Java
     * @param args Objeto utilizado para enviar datos de entrada desde la línea de comando cuando
     * el programa se ejecuta por consola.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * Agrega o remueve él escucha al botón de cada barco
     * @param evento
     */
    public void setEscuchaBotones(String evento){
        if(evento == "agregar"){
            PBarcos.getBotonBarco("portavion").addActionListener(listener);
            PBarcos.getBotonBarco("destructor").addActionListener(listener);
            PBarcos.getBotonBarco("fragata").addActionListener(listener);
            PBarcos.getBotonBarco("submarino").addActionListener(listener);
            PBarcos.getBotonBarco("portavion").setEnabled(true);
            PBarcos.getBotonBarco("destructor").setEnabled(true);
            PBarcos.getBotonBarco("fragata").setEnabled(true);
            PBarcos.getBotonBarco("submarino").setEnabled(true);
        }else{
            PBarcos.getBotonBarco("portavion").removeActionListener(listener);
            PBarcos.getBotonBarco("destructor").removeActionListener(listener);
            PBarcos.getBotonBarco("fragata").removeActionListener(listener);
            PBarcos.getBotonBarco("submarino").removeActionListener(listener);
            PBarcos.getBotonBarco("portavion").setEnabled(false);
            PBarcos.getBotonBarco("destructor").setEnabled(false);
            PBarcos.getBotonBarco("fragata").setEnabled(false);
            PBarcos.getBotonBarco("submarino").setEnabled(false);
        }
    }

    /**
     *Agrega o remueve él escucha a los botones Vertical y Horizontal
     * @param evento
     */
    public void setVerticalHorizontal(String evento){
        if(evento == "agregar"){
            PBarcos.getBotonOrientacion("vertical").addActionListener(listener);
            PBarcos.getBotonOrientacion("horizontal").addActionListener(listener);
            PBarcos.getBotonOrientacion("vertical").setEnabled(true);
            PBarcos.getBotonOrientacion("horizontal").setEnabled(true);
        }else{
            PBarcos.getBotonOrientacion("vertical").removeActionListener(listener);
            PBarcos.getBotonOrientacion("horizontal").removeActionListener(listener);
            PBarcos.getBotonOrientacion("vertical").setEnabled(false);
            PBarcos.getBotonOrientacion("horizontal").setEnabled(false);
        }
    }

    /**
     * Agrega o remueve él escucha de los botones verticales
     * @param evento
     */
    public void setOrientacionSentidoVertical(String evento){
        if(evento == "agregar"){
            PBarcos.getBotonSentidoOrientacion("sup_inf").addActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("inf_sup").addActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("sup_inf").setEnabled(true);
            PBarcos.getBotonSentidoOrientacion("inf_sup").setEnabled(true);
        }else{
            PBarcos.getBotonSentidoOrientacion("sup_inf").removeActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("inf_sup").removeActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("sup_inf").setEnabled(false);
            PBarcos.getBotonSentidoOrientacion("inf_sup").setEnabled(false);
        }
    }

    /**
     * Agrega o remueve él escucha de los botones horizontales
     * @param evento
     */
    public void setOrientacionSentidoHorizontal(String evento){
        if(evento == "agregar"){
            PBarcos.getBotonSentidoOrientacion("der_izq").addActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("izq_der").addActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("der_izq").setEnabled(true);
            PBarcos.getBotonSentidoOrientacion("izq_der").setEnabled(true);
        }else{
            PBarcos.getBotonSentidoOrientacion("der_izq").removeActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("izq_der").removeActionListener(listener);
            PBarcos.getBotonSentidoOrientacion("der_izq").setEnabled(false);
            PBarcos.getBotonSentidoOrientacion("izq_der").setEnabled(false);
        }
    }

    /**
     * Agrega o remueve él escucha a cada uno de los JLabel de la matriz posición de PintarTablero
     * @param evento
     */
    public void setEscuchaCasillas(String evento){
        if(evento == "agregar"){
            for (int row = 0; row < PCasillas.getTablero("posicion").getMatriz().length; row++) {
                for (int col = 0; col < PCasillas.getTablero("posicion").getMatriz()[row].length; col++) {
                    PCasillas.getTablero("posicion").getMatriz()[row][col].addMouseListener(listener);
                }
            }
        }else{
            for (int row = 0; row < PCasillas.getTablero("posicion").getMatriz().length; row++) {
                for (int col = 0; col < PCasillas.getTablero("posicion").getMatriz()[row].length; col++) {
                    PCasillas.getTablero("posicion").getMatriz()[row][col].removeMouseListener(listener);
                }
            }
        }
    }

    /**
     * Agrega o remueve él escucha a cada uno de los JLabel de la matriz principal de PintarTablero
     * @param evento
     */
    public void setEscuchaCasillasPrincipal(String evento){
        if(evento == "agregar"){
            for (int row = 0; row < PCasillas.getTablero("principal").getMatriz().length; row++) {
                for (int col = 0; col < PCasillas.getTablero("principal").getMatriz()[row].length; col++) {
                    PCasillas.getTablero("principal").getMatriz()[row][col].addMouseListener(listener);
                }
            }
        }else{
            for (int row = 0; row < PCasillas.getTablero("principal").getMatriz().length; row++) {
                for (int col = 0; col < PCasillas.getTablero("principal").getMatriz()[row].length; col++) {
                    PCasillas.getTablero("principal").getMatriz()[row][col].removeMouseListener(listener);
                }
            }
        }
    }

    /**
     * Identifica si hay un barco en la casilla del tablero principal para hundirlo
     * @param row
     * @param col
     * @param barco
     */
    public void funcionesCombate(int row, int col, String barco){
        // Establece una imagen a la casilla seleccionada del tablero principal del usuario y del tablero posicion del oponente si un barco fue tocado
        opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/rsc/tocado.png")));
        PCasillas.getTablero("principal").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/rsc/tocado.png")));
        PCasillas.getTablero("principal").getCasillasOcupadas().replace(PCasillas.getTablero("principal").getMatriz()[row][col], Integer.valueOf(2));

        // Reduce las casillas ocupadas del barco tocado para poder ser hundido
        opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").reducirCasillasUsadas(barco);

        // Si no hay mas casillas ocupadas, el barco se hunde y se establecen las imagenes respectivas
        if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]) == Integer.valueOf(0)){
            PBarcos.getInformacionJuego().setText("Barco hundido, selecciona otra casilla");
            faseJuego = 5;
            hundidosCount++;
            for (int fil = 1; fil < 11; fil++) {
                for (int colu = 1; colu < 11; colu++) {
                    if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[fil][colu]) != null){
                        if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[fil][colu]).equals(barco)){
                            opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[fil][colu].setIcon(new ImageIcon(getClass().getResource("/rsc/hundido.png")));
                            PCasillas.getTablero("principal").getMatriz()[fil][colu].setIcon(new ImageIcon(getClass().getResource("/rsc/hundido.png")));
                        }
                    }else{
                        continue;
                    }
                }
            }
        }else{
            PBarcos.getInformacionJuego().setText("Tocaste una nave, selecciona otra casilla");
            faseJuego = 5;
        }

        if(hundidosCount == 10){
            PBarcos.getInformacionJuego().setText("Todos los barcos enemigos han sido hundidos, ganaste el juego");
            setEscuchaCasillasPrincipal("remover");
        }
    }

    /**
     * Retorna el objeto de clase PCasillas
     * @return PCasillas
     */
    public PCasillas getPanelTablero(){
        return PCasillas;
    }

    /**
     * clase interna que extiende una clase de adaptador o implementa oyentes utilizados por la clase GUI
     */
    private class Listener implements ActionListener, MouseListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == reiniciar){
                reset();
            }else{
                if(e.getSource() == start){
                    start.removeActionListener(this);
                    setEscuchaBotones("agregar");
                    setVerticalHorizontal("remover");
                    setOrientacionSentidoVertical("remover");
                    setOrientacionSentidoHorizontal("remover");
                    PBarcos.getAsignarTurno().setText("¡Tu turno!");
                    PBarcos.getInformacionJuego().setText("Selecciona la nave que quieres desplegar");
                }else{
                    if(faseJuego == 6){
                        if(e.getSource() == time){
                            opponentwindow.oponenteVsUsuario();
                            if(opponentwindow.getEstado() == 0){
                                time.stop();
                                faseJuego = 5;
                                PBarcos.getAsignarTurno().setText("Tu turno");
                                PBarcos.getInformacionJuego().setText("Selecciona otra casilla del tablero principal");
                            }else{
                                if(opponentwindow.getEstado() == 2){
                                    time.stop();
                                    PBarcos.getInformacionJuego().setText("Tus barcos han sido hundidos, perdiste el juego");
                                }
                            }
                        }
                    }else{
                        switch(faseJuego){
                            case 1:
                                if(e.getSource() == PBarcos.getBotonBarco("portavion")){
                                    if(PBarcos.getCantidadBarco("portavion") > 0){
                                        PBarcos.setCantidadBarco("portavion");
                                        setEscuchaBotones("remover");
                                        PBarcos.getInformacionJuego().setText("Escoge si quieres ubicarlo vertical u horizontal");
                                        setVerticalHorizontal("agregar");
                                        PBarcos.setNombreBoton("portavion");
                                        faseJuego = 2;
                                    }else{
                                        PBarcos.getInformacionJuego().setText("No hay mas portaviones disponibles");
                                    }
                                }else{
                                    if(e.getSource() == PBarcos.getBotonBarco("destructor")){
                                        if(PBarcos.getCantidadBarco("destructor") > 0){
                                            PBarcos.setCantidadBarco("destructor");
                                            setEscuchaBotones("remover");
                                            PBarcos.getInformacionJuego().setText("Escoge si quieres ubicarlo vertical u horizontal");
                                            setVerticalHorizontal("agregar");
                                            PBarcos.setNombreBoton("destructor");
                                            faseJuego = 2;
                                        }else{
                                            PBarcos.getInformacionJuego().setText("No hay mas destructores disponibles");
                                        }
                                    }else{
                                        if(e.getSource() == PBarcos.getBotonBarco("fragata")){
                                            if(PBarcos.getCantidadBarco("fragata") > 0){
                                                PBarcos.setCantidadBarco("fragata");
                                                setEscuchaBotones("remover");
                                                PBarcos.getInformacionJuego().setText("Escoge si quieres ubicarlo vertical u horizontal");
                                                setVerticalHorizontal("agregar");
                                                PBarcos.setNombreBoton("fragata");
                                                faseJuego = 2;
                                            }else{
                                                PBarcos.getInformacionJuego().setText("No hay mas fragatas disponibles");
                                            }
                                        }else{
                                            if(e.getSource() == PBarcos.getBotonBarco("submarino")){
                                                if(PBarcos.getCantidadBarco("submarino") > 0){
                                                    PBarcos.setCantidadBarco("submarino");
                                                    setEscuchaBotones("remover");
                                                    PBarcos.getInformacionJuego().setText("Escoge si quieres ubicarlo vertical u horizontal");
                                                    setVerticalHorizontal("agregar");
                                                    PBarcos.setNombreBoton("submarino");
                                                    faseJuego = 2;
                                                }else{
                                                    PBarcos.getInformacionJuego().setText("No hay mas submarinos disponibles");
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case 2:
                                if(e.getSource() == PBarcos.getBotonOrientacion("vertical")){
                                    setVerticalHorizontal("remover");
                                    PBarcos.getInformacionJuego().setText("Escoge cual sentido quieres usar");
                                    setOrientacionSentidoVertical("agregar");
                                    PBarcos.setOrientacion(0);
                                    faseJuego = 3;
                                }else{
                                    if(e.getSource() == PBarcos.getBotonOrientacion("horizontal")){
                                        setVerticalHorizontal("remover");
                                        PBarcos.getInformacionJuego().setText("Escoge cual sentido quieres usar");
                                        setOrientacionSentidoHorizontal("agregar");
                                        PBarcos.setOrientacion(1);
                                        faseJuego = 3;
                                    }
                                }
                                break;
                            case 3:
                                if(e.getSource() == PBarcos.getBotonSentidoOrientacion("sup_inf")){
                                    setOrientacionSentidoVertical("remover");
                                    PBarcos.getInformacionJuego().setText("Selecciona la casilla en la que quieres ubicar la nave");
                                    setEscuchaCasillas("agregar");
                                    PBarcos.setSentidoOrientacion(1);
                                    faseJuego = 4;
                                }else{
                                    if(e.getSource() == PBarcos.getBotonSentidoOrientacion("inf_sup")){
                                        setOrientacionSentidoVertical("remover");
                                        PBarcos.getInformacionJuego().setText("Selecciona la casilla en la que quieres ubicar la nave");
                                        setEscuchaCasillas("agregar");
                                        PBarcos.setSentidoOrientacion(2);
                                        faseJuego = 4;
                                    }else{
                                        if(e.getSource() == PBarcos.getBotonSentidoOrientacion("izq_der")){
                                            setOrientacionSentidoHorizontal("remover");
                                            PBarcos.getInformacionJuego().setText("Selecciona la casilla en la que quieres ubicar la nave");
                                            setEscuchaCasillas("agregar");
                                            PBarcos.setSentidoOrientacion(3);
                                            faseJuego = 4;
                                        }else{
                                            if(e.getSource() == PBarcos.getBotonSentidoOrientacion("der_izq")){
                                                setOrientacionSentidoHorizontal("remover");
                                                PBarcos.getInformacionJuego().setText("Selecciona la casilla en la que quieres ubicar la nave");
                                                setEscuchaCasillas("agregar");
                                                PBarcos.setSentidoOrientacion(4);
                                                faseJuego = 4;
                                            }
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }


        @Override
        public void mouseClicked(MouseEvent e) {
            int auxiliar = 0; // Variable para indicar cuando se debe terminar el primer ciclo
            switch(faseJuego){
                case 4:
                    for (int row = 1; row < 11; row++) {
                        for (int col = 1; col < 11; col++) {
                            if(e.getSource() == PCasillas.getTablero("posicion").getMatriz()[row][col]) {
                                // Condicional para saber si el usuario pudo colocar el barco
                                if(colocarBarcos.funcionesFlota(PBarcos.getNombreBoton(), PBarcos.getOrientacion(), PBarcos.getSentidoOrientacion(), col, row)){
                                    if(PBarcos.cantidadTotalNaves() > 0){
                                        setEscuchaCasillas("remover");
                                        PBarcos.getInformacionJuego().setText("Escoge otro barco");
                                        setEscuchaBotones("agregar");
                                        faseJuego = 1;
                                    }else{
                                        setEscuchaCasillas("remover");
                                        PBarcos.getInformacionJuego().setText("El pelea comienza, selecciona una casilla del tablero principal");
                                        pelea.usuarioVsOponente();
                                        pelea.oponenteVsUsuario();
                                        setEscuchaCasillasPrincipal("agregar");
                                        faseJuego = 5;
                                    }
                                }
                                auxiliar = 1;
                                break;
                            }
                        }
                        if(auxiliar == 1){
                            break;
                        }
                    }
                    break;
                case 5:
                    for (int row = 1; row < 11; row++) {
                        for (int col = 1; col < 11; col++) {
                            if(e.getSource() == PCasillas.getTablero("principal").getMatriz()[row][col]) {
                                // Verifica si la casilla seleccionada hay un barco oponente
                                if(PCasillas.getTablero("principal").getCasillasOcupadas().get(PCasillas.getTablero("principal").getMatriz()[row][col]) == Integer.valueOf(1)){
                                    // Verifica si todas las casillas del barco fueron seleccionadas
                                    if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]) != Integer.valueOf(0)){
                                        for(int num=1; num < 11; num++){
                                            if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]).equals("portavion" + String.valueOf(num))){
                                                funcionesCombate(row, col, "portavion" + String.valueOf(num));
                                                break;
                                            }else{
                                                if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]).equals("submarino" + String.valueOf(num))){
                                                    funcionesCombate(row, col, "submarino" + String.valueOf(num));
                                                    break;
                                                }else{
                                                    if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]).equals("destructor" + String.valueOf(num))){
                                                        funcionesCombate(row, col, "destructor" + String.valueOf(num));
                                                        break;
                                                    }else{
                                                        if(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getCasillaNombreBarco().get(opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col]).equals("fragata" + String.valueOf(num))){
                                                            funcionesCombate(row, col, "fragata" + String.valueOf(num));
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    if(PCasillas.getTablero("principal").getCasillasOcupadas().get(PCasillas.getTablero("principal").getMatriz()[row][col]) == Integer.valueOf(2)){
                                        PBarcos.getInformacionJuego().setText("Casilla usada, presiona otra");
                                        faseJuego = 5;
                                    }else{
                                        PBarcos.getInformacionJuego().setText("Le diste al agua, espera el turno del oponente");
                                        PCasillas.getTablero("principal").getCasillasOcupadas().put(PCasillas.getTablero("principal").getMatriz()[row][col], Integer.valueOf(2));
                                        opponentwindow.getPanelTableroOponente().getTableroOponente("posicion").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/rsc/agua.png")));
                                        PCasillas.getTablero("principal").getMatriz()[row][col].setIcon(new ImageIcon(getClass().getResource("/rsc/agua.png")));
                                        faseJuego = 6;
                                        PBarcos.getAsignarTurno().setText("¡Turno del oponente!");
                                        time.start();
                                    }
                                }
                                auxiliar = 1;
                                break;
                            }
                        }
                        if(auxiliar == 1){
                            break;
                        }
                    }
                    break;
            }
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
    }
}