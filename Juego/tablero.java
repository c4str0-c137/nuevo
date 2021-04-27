package Juego;

import javax.swing.text.StyledEditorKit.BoldAction;

public class tablero {

    private jugador jugador1, jugador2;
    private int[][] tab;
    private static int barrera=-1;
    private peon peon1, peon2;
    private boolean turno;
    public tablero(String nombreJugador1, String nombreJugador2){
        jugador1 = new jugador(nombreJugador1);
        jugador2 = new jugador(nombreJugador2);
        peon1 = new peon(0, 8); // marcar como 2 para verificar su pos
        peon2 = new peon(16,8); // marcar como 4 
        tab = new int[17][17];
        generarTabla();
        turno = true;
    }
    private void generarTabla(){
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
        tab[0][8]=2;
        tab[16][8]=4;
    }

    public boolean movimientoPeon(int x, int y)
    {
        boolean res = false;
        if(turno)
            if(validarMovimientoDePeon(x, y))
            {
                int[] pos = peon1.posPeon();
                tab[pos[0]][pos[1]]= 0;
                tab[x][y]=2;
                turno = false;
                res = true;
                peon1.avanzar(x, y);
            }     
        else
            if(validarMovimientoDePeon(x, y))
            {
                int[] pos = peon2.posPeon();
                tab[pos[0]][pos[1]]= 0;
                tab[x][y]=4;
                res = true;
                turno = true;
                peon2.avanzar(x, y);
            }
        return res;    
    }

    private boolean validarMovimientoDePeon(int x, int y)
    {
        boolean res = false;
        if(validarMovimiento(x, y, 0))
            res = true;
        return res;
    }

    private boolean validarMovimiento(int x, int y, int obj)
    {
        boolean res = false;
        if(tab[x][y]==obj)
            res = true;       
        return res;
    }

    public boolean movimientoBarrera(int x, int y)
    {
        boolean res = true;
        if(validarMovimientoDeBarrera(x, y))
            tab[x][y]=-1;
        else 
            res = false;
        return res;
    }
    
    private boolean validarMovimientoDeBarrera(int x, int y)
    {
        boolean res = false;
        if(validarMovimiento(x, y, 1))
            res = true;
        return res;
    }

    public boolean avanzar(int x, int y)
    {
        boolean res = false;
        if(turno)
        {
            if(posPeon(x, y, peon1))
                res = movimientoPeon(x, y);
        } 
        else 
        {
            if(posPeon(x, y, peon2))
                res = movimientoPeon(x, y);
        }
        return res;
    }

    public Boolean posPeon(int x, int y, peon p)
    {
        boolean res = false;
        int[] pos = p.posPeon();
        if(pos[0]==x && pos[1]+2 == y)
            res = true;
        if(pos[0]==x && pos[1]-2==y)
            res = true;
        if(pos[0]+2==x && pos[1]==y)
            res = true;        
        return res;
    }
}
