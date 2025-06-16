package com.akihabara.market.view;

import java.util.Scanner;

public class InterfazConsola {
    private Scanner scanner; // Scanner para entrada por consola

    public InterfazConsola() {
        scanner = new Scanner(System.in); // Inicializa el Scanner
    }

    // Muestra el menú principal en consola
    public void mostrarMenu() {
        System.out.println("\n========= MENÚ PRINCIPAL =========");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Listar productos por nombre");
        System.out.println("5. Listar productos por categoría");
        System.out.println("6. Actualizar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Sugerir nombre con IA");
        System.out.println("9. Salir");
        System.out.print("Elige una opción: ");
    }

    // Lee la opción elegida por el usuario, devuelve -1 si no es válida
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;  // opción no válida
        }
    }

    // Lee un texto con mensaje personalizado
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    // Lee un número decimal con validación, repite hasta entrada correcta
    public double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: introduce un número decimal válido.");
            }
        }
    }

    // Lee un número entero con validación, repite hasta entrada correcta
    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: introduce un número entero válido.");
            }
        }
    }

    // Muestra un mensaje general en consola
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra un mensaje de error en consola
    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    // Muestra la representación en String de un producto
    public void mostrarProducto(String productoStr) {
        System.out.println(productoStr);
    }
}
