package com.alura.conversor.connection;

import com.alura.conversor.menu.Colors;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionMonedas {
    private static final String API_KEY = "daab7d065c968a518fa8cd3d"; // mi API_KEY

    public Monedas monedaBase(String monedaBase, String monedaObjetivo, double montoConver) {
        try {
            // Formar la URL de la solicitud
            String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + monedaBase + "/" + monedaObjetivo + "/" + montoConver;
            URI direccion = URI.create(url);

            // Realizar la solicitud HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la solicitud fue exitosa
            if (response.statusCode() == 200) {
                // Convertir la respuesta JSON en un objeto Monedas
                return new Gson().fromJson(response.body(), Monedas.class);
            } else {
                // Si la solicitud no fue exitosa, imprimir el código de estado
                System.out.println(Colors.NEGRITA+Colors.ROJO+"Error al realizar la solicitud: " + response.statusCode()+ Colors.RESET);
                throw new RuntimeException(Colors.NEGRITA+Colors.ROJO+"No se pudo obtener la tasa de cambio. Código de estado: " + response.statusCode()+ Colors.RESET);
            }
        } catch (java.io.IOException e) {
            // Si ocurre un error de E/S, imprimirlo y lanzar una excepción
            System.out.println(Colors.NEGRITA+Colors.ROJO+ "Error de E/S al realizar la solicitud: " + e.getMessage()+ Colors.RESET);
            throw new RuntimeException(Colors.NEGRITA+Colors.ROJO+ "No se pudo obtener la tasa de cambio debido a un error de E/S."+ Colors.RESET);
        } catch (java.lang.InterruptedException e) {
            // Si la solicitud es interrumpida, imprimirlo y lanzar una excepción
            System.out.println(Colors.NEGRITA+Colors.ROJO+"Solicitud interrumpida: " + e.getMessage()+ Colors.RESET);
            throw new RuntimeException(Colors.NEGRITA+Colors.ROJO+"La solicitud fue interrumpida."+ Colors.RESET);
        }
    }
}
