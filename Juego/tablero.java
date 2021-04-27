package Juego;

public class tablero {

    private jugador jugador1, jugador2;
    private int[][] tab;
    private static int barrera=3;
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
    
    public void imprimir()
    {
        for(int i = 0; i<tab.length;i++)
        {
            for(int j = 0; j<tab[0].length;j++)
            {
                System.out.print(tab[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public boolean movimientoPeon(int x, int y)
    {
        boolean res = false;
        if(turno)
        {
            if(validarMovimientoDePeon(x, y))
            {
                int[] pos = peon1.posPeon();
                tab[pos[0]][pos[1]]= 0;
                tab[x][y]=2;
                turno = false;
                res = true;
                peon1.avanzar(x, y);
            }     
        }else{
            if(validarMovimientoDePeon(x, y))
            {
                int[] pos = peon2.posPeon();
                tab[pos[0]][pos[1]]= 0;
                tab[x][y]=4;
                res = true;
                turno = true;
                peon2.avanzar(x, y);
            }
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
        boolean res = false;
        if(x%2==0 && valMovDeBar(x+1,y) && valMovDeBar(x+2,y))
        {
            res = true;
            tab[x+2][y]=barrera;
            tab[x+1][y]=barrera;
            tab[x][y] =barrera;
        }
        if(x%2==1 && valMovDeBar(x,y+1) && valMovDeBar(x,y+2))
        {
            res = true;   
            tab[x][y]=barrera;
            tab[x][y+1]=barrera;
            tab[x][y+2]=barrera;
        }
        return res;
    }
    
    private boolean valMovDeBar(int x, int y)
    {
        boolean res = false;
        if(x<17 && y <17)
            if(validarMovimiento(x, y, 1))
            {
                res = true;
            }
        return res;
    }

    public boolean avanzar(int x, int y)
    {
        boolean res = false;
        if(turno)
        {
            if(posPeon(x, y, peon1,getTab()))
                res = movimientoPeon(x, y);
        } 
        else 
        {
            if(posPeon(x, y, peon2,getTab()))
                res = movimientoPeon(x, y);
        }
        return res;
    }

    public Boolean posPeon(int x, int y, peon p, int[][] tabAux)
    {
        boolean res = false;
        int[] pos = p.posPeon();
        int posX = pos[0];
        int posY = pos[1];
        if(posX  == x && posY+2 == y)
            if(tabAux[x][(Math.abs(posY+y))/2]==1)
            {
                res = true;
            }
        if(posX  == x && posY-2 == y)
            if(tabAux[x][(Math.abs(posY-y))/2]==1)
            {
                res = true;
            }
        if(posX+2== x && posY   == y)
            if(tabAux[Math.abs((posX+x))/2][y]==1)
            {
                res = true;         
            }
        if(posX-2== x && posY   == y)
            if(tabAux[Math.abs((posX-x))/2][y]==1)
            {
                res = true;       
            }
        return res;
    }

    public int[][] getTab()
    {
        return tab;
    }
 }