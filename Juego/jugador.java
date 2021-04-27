package Juego;

public class jugador
{
    private String nombre;
    private int puntaje;
    private int cantBarreras;
    public jugador(String nombre)
    {
        this.nombre = nombre;
        puntaje = 0;
        cantBarreras = 10;
    }

    public String getNombre()
    {
        return nombre;
    }
    
    public int getPuntaje()
    {
        return puntaje;
    }

    public int getCantBarreras()
    {
        return cantBarreras;
    }

    public void sumarPuntaje()
    {
        puntaje++;
    }

    public void restarBarreras()
    {
        cantBarreras--;
    }

}
