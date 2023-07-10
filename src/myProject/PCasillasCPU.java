package myProject;

import javax.swing.*;
import java.awt.*;

public class PCasillasCPU extends JPanel {
    private BackgroundPane panelTableroPosicion;
    private JLabel nombreTableroPosicion;
    private myProject.Casillas tableroPosicionOponente, tableroPrincipalOponente;
    private String abecedario[];

    /**
     * Constructor de la clase PCasillasCPU
     */
    public PCasillasCPU(){
        GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);
        this.setBackground(Color.decode("#67595A"));
        tableroPosicionOponente = new myProject.Casillas();
        tableroPrincipalOponente = new myProject.Casillas();
        abecedario = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        iniciar();
        modelTableroOponente();
    }

    /**
     * Establece la configuración inicial del JComponent
     */
    public void iniciar(){
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel tablero posición
        nombreTableroPosicion = new JLabel("T A B L E R O   P O S I C I O N");
        nombreTableroPosicion.setForeground(new Color(0, 0, 0, 230));
        nombreTableroPosicion.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        this.add(nombreTableroPosicion, gbc);

        panelTableroPosicion = new BackgroundPane();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0,15,0,15);
        this.add(panelTableroPosicion, gbc);
    }

    /**
     * JPanel con imagen para agregar las matrices
     */
    public class BackgroundPane extends JPanel{
        private Image img;

        public BackgroundPane(){
            img = new ImageIcon(getClass().getResource("/rsc/mar.jpg")).getImage();
            this.setLayout(new GridLayout(11, 11));
            this.setPreferredSize(new Dimension(400, 400));
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this);
            revalidate();
            repaint();
        }
    }

    /**
     * Crea los tableros posición y principal
     */
    public void modelTableroOponente(){
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                if(row == 0 && col == 0){
                    tableroPosicionOponente.getMatriz()[row][col] = new JLabel();
                    tableroPosicionOponente.getMatriz()[row][col].setOpaque(true);
                    tableroPosicionOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                    tableroPosicionOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    tableroPrincipalOponente.getMatriz()[row][col] = new JLabel();
                    tableroPrincipalOponente.getMatriz()[row][col].setOpaque(true);
                    tableroPrincipalOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                    tableroPrincipalOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }else{
                    if(row == 0 && col > 0){
                        tableroPosicionOponente.getMatriz()[row][col] = new JLabel(abecedario[col-1], SwingConstants.CENTER);
                        tableroPosicionOponente.getMatriz()[row][col].setOpaque(true);
                        tableroPosicionOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                        tableroPosicionOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        tableroPrincipalOponente.getMatriz()[row][col] = new JLabel(abecedario[col-1], SwingConstants.CENTER);
                        tableroPrincipalOponente.getMatriz()[row][col].setOpaque(true);
                        tableroPrincipalOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                        tableroPrincipalOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }else{
                        if(row > 0 && col == 0){
                            tableroPosicionOponente.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            tableroPosicionOponente.getMatriz()[row][col].setOpaque(true);
                            tableroPosicionOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                            tableroPosicionOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            tableroPrincipalOponente.getMatriz()[row][col] = new JLabel(String.valueOf(row), SwingConstants.CENTER);
                            tableroPrincipalOponente.getMatriz()[row][col].setOpaque(true);
                            tableroPrincipalOponente.getMatriz()[row][col].setBackground(Color.WHITE);
                            tableroPrincipalOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }else{
                            tableroPosicionOponente.getMatriz()[row][col] = new JLabel();
                            tableroPosicionOponente.getMatriz()[row][col].setOpaque(false);
                            tableroPosicionOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                            tableroPrincipalOponente.getMatriz()[row][col] = new JLabel();
                            tableroPrincipalOponente.getMatriz()[row][col].setOpaque(false);
                            tableroPrincipalOponente.getMatriz()[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                panelTableroPosicion.add(tableroPosicionOponente.getMatriz()[row][col]);
            }
        }
    }

    /**
     * Retorna el tablero ingresado
     * @param _tablero
     * @return Casillas
     */
    public myProject.Casillas getTableroOponente(String _tablero){
        myProject.Casillas tablero = new myProject.Casillas();
        if(_tablero.equals("posicion")){
            tablero = tableroPosicionOponente;
        }else{
            if(_tablero.equals("principal")){
                tablero = tableroPrincipalOponente;
            }
        }
        return tablero;
    }
}
