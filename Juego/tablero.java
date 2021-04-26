package Juego;

public class tablero {

    private jugador jugador1, jugador2;
    private int[][] tab;
    private peon peon1, peon2;
    public tablero(String nombreJugador1, String nombreJugador2){
        jugador1 = new jugador(nombreJugador1);
        jugador2 = new jugador(nombreJugador2);
        peon1 = new peon(0, 8);
        peon2 = new peon(16,8);
        tab = new int[17][17];
    }
    public int[][] generarTabla(){
        for(int i = 0; i<tab.length;i++)
        {
            for(int j = 0; j<tab[0].length;j++)
            {
                if(i%2==0)
                    if(j%2==0)
                        tab[i][j]=0;
                    else
                        tab[i][j]=1;   
                else
                    tab[i][j]=1;
            }
        }
        return tab;
    }


}
