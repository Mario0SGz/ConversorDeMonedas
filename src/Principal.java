import com.alura.conversor.calculos.Conversor;
import com.alura.conversor.menu.Colors;
import com.alura.conversor.menu.UI;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.iniciarConversor();
    }

    private void iniciarConversor() {
        Scanner scanner = new Scanner(System.in);

        // Se inicia el menú
        while (true) {
            UI.menu();
            var numeroOpcion = scanner.nextLine();
            try {
                int opcion = Integer.parseInt(numeroOpcion);
                switch (opcion) {
                    case 1, 2, 3, 4, 5, 6 -> {
                        Conversor conversor = new Conversor();
                        conversor.conversorMonedas(opcion);
                    }
                    case 7 -> {
                        System.out.println(Colors.AZUL + "Saliendo del programa. ¡Hasta luego!" + Colors.RESET);
                        return; // Termina el programa
                    }
                    default -> System.out.println(Colors.NEGRITA + Colors.ROJO + "Por favor, introduce un número del 1 al 7." + Colors.RESET);
                }
            } catch (NumberFormatException e) {
                // Si se ingresa algo que no es un número, se muestra un mensaje de error
                System.out.println(Colors.NEGRITA + Colors.ROJO + "Por favor, introduce únicamente números." + Colors.RESET);
            }
        }
    }
}
