package Maquina_Expendedora;

public class Impresora {

    public static boolean tieneTinta() {
        return true;
    }
    
    public static boolean tienePapel(char tipo) {
        return true;
    }
    
    public static void imprimir(int tipoBillete) {
        System.out.println("🖨️ IMPRESORA: Imprimiendo, grabando y expulsando billete tipo " + tipoBillete);
    }
}