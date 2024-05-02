package com.alura.conversor.calculos;

import com.alura.conversor.connection.ConexionMonedas;
import com.alura.conversor.connection.Monedas;
import com.alura.conversor.menu.Colors;

import java.util.Scanner;

public class Conversor {
    private static String[] monedasBase = {"USD", "ARS", "BRL", "COP", "MXN", "EUR"};
    private static ConexionMonedas conexionMonedas = new ConexionMonedas();
    private static Scanner scanner = new Scanner(System.in);

    public static void conversorMonedas(int opcion) {
        boolean continuar = true;

        while (continuar) {
            String monedaBase = obtenerMonedaBase(opcion);
            String monedaObjetivo = ""; // Definir la variable aquí para que esté dentro del alcance correcto
            double montoConver;

            do {
                System.out.println("Ingrese la abreviación de la moneda objetivo:");
                monedaObjetivo = scanner.nextLine().toUpperCase(); // Convertir a mayúsculas

                // Verificar si la abreviación de la moneda objetivo es válida
                if (!esMonedaValida(monedaObjetivo)) {
                    System.out.println("La abreviación de la moneda objetivo no es válida.");
                }
            } while (!esMonedaValida(monedaObjetivo));

            // Obtener el monto a convertir
            montoConver = obtenerMonto();

            // Realizar la conversión
            Monedas monedas = conexionMonedas.monedaBase(monedaBase, monedaObjetivo, montoConver);
            if (monedas != null) {
                System.out.println("Tasa de cambio de " + monedaBase + " a " + monedaObjetivo + ":");
                System.out.println("Moneda base: " + monedas.base_code());
                System.out.println("Moneda objetivo: " + monedas.target_code());
                System.out.println("Tasa de cambio: " + monedas.conversion_rate());
                System.out.println("La conversión es: " + monedas.conversion_result());
            } else {
                System.out.println(Colors.NEGRITA + Colors.ROJO + "No se pudo obtener la tasa de cambio para las monedas ingresadas." + Colors.RESET);
            }

            // Preguntar al usuario si desea realizar otra conversión
            continuar = preguntarNuevaConversion();
        }
    }

    private static String obtenerMonedaBase(int opcion) {
        if (opcion >= 1 && opcion <= monedasBase.length) {
            return monedasBase[opcion - 1];
        } else {
            System.out.println(Colors.NEGRITA + Colors.ROJO + "Opción inválida. Seleccionando USD como moneda base por defecto." + Colors.RESET);
            return "USD"; // Si la opción es inválida, se usa USD por defecto
        }
    }

    private static boolean esMonedaValida(String moneda) {
        for (String monedaValida : monedasBase) {
            if (moneda.equals(monedaValida)) {
                return true;
            }
        }
        return false;
    }

    private static double obtenerMonto() {
        double montoConver;

        do {
            System.out.println("Ingrese la cantidad que desea convertir:");
            // Verificar si la entrada es un número
            while (!scanner.hasNextDouble()) {
                System.out.println("Por favor, ingrese un número válido:");
                scanner.next(); // Consumir la entrada inválida
            }
            // Si se llega aquí, la entrada es un número
            montoConver = scanner.nextDouble();

            // Verificar si el número es negativo
            if (montoConver < 0) {
                System.out.println("El número debe ser positivo. Por favor, ingrese un número válido:");
            }
        } while (montoConver < 0); // Repetir hasta que se ingrese un número válido

        scanner.nextLine(); // Consumir el carácter de nueva línea
        return montoConver;
    }

    private static boolean preguntarNuevaConversion() {
        while (true) {
            System.out.println("¿Desea realizar otra conversión? (s/n)");
            String respuesta = scanner.nextLine().toLowerCase(); // Convertir a minúsculas

            if (respuesta.equals("s")) {
                return true;
            } else if (respuesta.equals("n")) {
                return false;
            } else {
                System.out.println("Respuesta inválida. Por favor, ingrese 's' para continuar o 'n' para salir:");
            }
        }
    }
}
