package Maquina_Expendedora;

public class TipoBillete {
    public int id;
    public String nombre;
    public double precio;
    public char tipoPapel;
    
    public TipoBillete(int id, String nombre, double precio, char tipoPapel) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipoPapel = tipoPapel;
    }
    
    public void mostrar() {
        System.out.println(id + ". " + nombre + " - " + precio + "€ (Papel: " + tipoPapel + ")");
    }
}