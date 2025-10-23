package Maquina_Expendedora;

import java.util.*;

public class MaquinaExpendedora {
    private ArrayList<TipoBillete> billetes;
    private Scanner scanner;
    

    public MaquinaExpendedora() {
        billetes = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        billetes.add(new TipoBillete(1, "Simple", 1.50, 'a'));
        billetes.add(new TipoBillete(2, "Ida y Vuelta", 2.80, 'a'));
        billetes.add(new TipoBillete(3, "Diario", 4.50, 'b'));
    }
    
    public void iniciar() {
        System.out.println("=== MÁQUINA EXPENDEDORA INICIADA ===");
        
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. 🎫 Comprar billete");
            System.out.println("2. 🔧 Modo mantenimiento");
            System.out.println("3. ❌ Salir");
            System.out.print("Elige opción: ");
            
            int opcion = scanner.nextInt();
            
            if (opcion == 1) comprarBillete();
            else if (opcion == 2) modoMantenimiento();
            else if (opcion == 3) break;
        }
    }
    
    private void comprarBillete() {
        // Mostrar billetes
        System.out.println("\n--- BILLETES DISPONIBLES ---");
        for (TipoBillete billete : billetes) {
            billete.mostrar();
        }
        
        System.out.print("Elige el número de billete: ");
        int idElegido = scanner.nextInt();
        
        // Buscar billete
        TipoBillete billeteElegido = null;
        for (TipoBillete b : billetes) {
            if (b.id == idElegido) {
                billeteElegido = b;
                break;
            }
        }
        
        if (billeteElegido == null) {
            System.out.println("❌ Billete no encontrado");
            return;
        }
        
        System.out.println("✅ Elegiste: " + billeteElegido.nombre);
        System.out.println("💰 Precio: " + billeteElegido.precio + "€");
        
        int dineroIntroducido = Monedero.cantidadIntroducida();
        System.out.println("💵 Dinero detectado por el monedero: " + dineroIntroducido + "€");
        
        if (dineroIntroducido < billeteElegido.precio) {
            System.out.println("❌ Dinero insuficiente");
            Monedero.devolverCantidadIntroducida();
            return;
        }
        
        if (!Impresora.tieneTinta()) {
            System.out.println("❌ No hay tinta en la impresora");
            Monedero.devolverCantidadIntroducida();
            return;
        }
        
        if (!Impresora.tienePapel(billeteElegido.tipoPapel)) {
            System.out.println("❌ No hay papel tipo " + billeteElegido.tipoPapel);
            Monedero.devolverCantidadIntroducida();
            return;
        }
        
        double cambio = dineroIntroducido - billeteElegido.precio;
        if (cambio > 0 && !Monedero.esPosibleSuministrar((int) cambio)) {
            System.out.println("❌ No hay cambio disponible");
            Monedero.devolverCantidadIntroducida();
            return;
        }
        
        System.out.println("🖨️ Enviando comando a la impresora...");
        Impresora.imprimir(billeteElegido.id);
        
        if (cambio > 0) {
            System.out.println("🪙 Solicitando cambio...");
            Monedero.suministrar((int) cambio);
        }
        
        System.out.println("🎉 ¡Compra exitosa! Gracias por su compra.");
    }
    
    private void modoMantenimiento() {
        while (true) {
            System.out.println("\n--- MODO MANTENIMIENTO ---");
            System.out.println("1. 👀 Ver billetes");
            System.out.println("2. ➕ Agregar billete");
            System.out.println("3. 🖨️ Estado impresora");
            System.out.println("0. ↩️ Volver");
            System.out.print("Elige opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion == 0) break;
            
            switch (opcion) {
                case 1:
                    System.out.println("\n--- BILLETES CONFIGURADOS ---");
                    for (TipoBillete b : billetes) b.mostrar();
                    break;
                    
                case 2:
                    agregarBillete();
                    break;
                    
                case 3:
                    System.out.println("Estado Impresora:");
                    System.out.println("Tinta: " + (Impresora.tieneTinta() ? "✅" : "❌"));
                    System.out.println("Papel A: " + (Impresora.tienePapel('a') ? "✅" : "❌"));
                    System.out.println("Papel B: " + (Impresora.tienePapel('b') ? "✅" : "❌"));
                    System.out.println("Papel C: " + (Impresora.tienePapel('c') ? "✅" : "❌"));
                    break;
                    
                default:
                    System.out.println("❌ Opción no válida");
            }
        }
    }
    
    private void agregarBillete() {
        System.out.print("ID del billete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Tipo de papel (a/b/c): ");
        char papel = scanner.nextLine().charAt(0);
        
        billetes.add(new TipoBillete(id, nombre, precio, papel));
        System.out.println("✅ Billete agregado correctamente");
    }
}