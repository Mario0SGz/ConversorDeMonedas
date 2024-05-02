package com.alura.conversor.menu;

public class UI {

    public static void menu(){
        System.out.println(Colors.NEGRITA+Colors.VERDE+ "----------------------------------------------------");
        System.out.println("\t\t Bienvenido al Conversor de Monedas");
        System.out.println("\t 1) USD ==> Dólar estadounidense");
        System.out.println("\t 2) ARS ==> Peso argentino");
        System.out.println("\t 3) BRL ==> Real brasileño");
        System.out.println("\t 4) COP ==> Peso colombiano");
        System.out.println("\t 5) MXN ==> Peso mexicano");
        System.out.println("\t 6) EUR ==> Euro");
        System.out.println("\t 7) SALIR");
        System.out.println("\t Elija una moneda para iniciar:");
        System.out.println("----------------------------------------------------" + Colors.RESET);
    }
}
