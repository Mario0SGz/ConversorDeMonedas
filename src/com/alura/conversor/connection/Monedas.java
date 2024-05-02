package com.alura.conversor.connection;


public record Monedas(String base_code,
                      String target_code,
                      double conversion_rate,
                      double conversion_result) {

}

