package Juego;

public class peon
{
    private posicion pos;
    
    public peon(int x, int y)
    {
        pos = new posicion(x, y);
    }
    public int[] posPeon()
    {
        int[] res = new int[2];
        res[0]=pos.getX();
        res[1]=pos.getY();
        return res;
    }
    public void avanzar(int x, int y)
    {
        pos.setX(x);
        pos.setY(y);
    }
}
