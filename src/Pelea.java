package myProject;
public class Pelea {
    private myProject.PCasillas PCasillas;
    private PCasillasCPU PCasillasCPU;

    /**
     * Constructor de la clase Pelea
     */
    public Pelea(myProject.PCasillas _PCasillas, PCasillasCPU _PCasillasCPU){
        this.PCasillas = _PCasillas;
        this.PCasillasCPU = _PCasillasCPU;
    }

    /**
     * Busca las casillas ocupadas por naves del tablero posición del oponente y las marca en el tablero principal del usuario
     */
    public void usuarioVsOponente(){
        for(int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if(PCasillasCPU.getTableroOponente("posicion").getCasillasOcupadas().get(PCasillasCPU.getTableroOponente("posicion").getMatriz()[row][col]) == Integer.valueOf(1)){
                    PCasillas.getTablero("principal").getCasillasOcupadas().put(PCasillas.getTablero("principal").getMatriz()[row][col], 1);
                }
            }
        }
    }

    /**
     * Busca las casillas ocupadas por naves del tablero posición del usuario y las marca en el tablero principal del oponente
     */
    public void oponenteVsUsuario(){
        for(int row = 1; row < 11; row++) {
            for (int col = 1; col < 11; col++) {
                if(PCasillas.getTablero("posicion").getCasillasOcupadas().get(PCasillas.getTablero("posicion").getMatriz()[row][col]) == Integer.valueOf(1)){
                    PCasillasCPU.getTableroOponente("principal").getCasillasOcupadas().put(PCasillasCPU.getTableroOponente("principal").getMatriz()[row][col], 1);
                }
            }
        }
    }
}
