package Maquina_Expendedora;

public class Monedero {
    
    public static boolean esPosibleSuministrar(int cantidad) {
        return cantidad <= 50;
    }
    
    public static void suministrar(int cantidad) {
        System.out.println("MONEDERO: Entregando " + cantidad + "€");
    }
    
    public static void devolverCantidadIntroducida() {
        System.out.println("MONEDERO: Devolviendo todo el dinero introducido");
    }
    
    public static int cantidadIntroducida() {
        return 10;
    }
}