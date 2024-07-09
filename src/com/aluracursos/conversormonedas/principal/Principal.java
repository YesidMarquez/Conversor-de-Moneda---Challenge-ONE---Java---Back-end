package com.aluracursos.conversormonedas.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.aluracursos.conversormonedas.modelos.Moneda;
import com.google.gson.Gson;


public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String conversor ="";

        while (true){
            System.out.println(" ");
            System.out.println("               Bienvenido al conversor de moneda                ");
            System.out.println(" ");
            System.out.println("********* Elija una Opción del Menu *********");
            System.out.println("1 - Dolar =>> Peso Colombiano");
            System.out.println("2 - Peso Colombiano =>> Dolar");
            System.out.println("3 - Dolar =>> Real Brasileño");
            System.out.println("4 - Real Brasileño =>> Dolar");
            System.out.println("5 - Dolar =>> Peso Argentino");
            System.out.println("6 - Peso Argentino =>> Dolar");
            System.out.println("7 - Salir");

            var tipoConversion = input.nextLine();

            if (tipoConversion.equalsIgnoreCase("1")){
                conversor="USD/COP";
            }
            if (tipoConversion.equalsIgnoreCase("2")){
                conversor="COP/USD";
            }
            if (tipoConversion.equalsIgnoreCase("3")){
                conversor="USD/BRL";
            }
            if (tipoConversion.equalsIgnoreCase("4")){
                conversor="BRL/USD";
            }
            if (tipoConversion.equalsIgnoreCase("5")){
                conversor="USD/ARS";
            }
            if (tipoConversion.equalsIgnoreCase("6")){
                conversor="ARS/USD";
            }




            //System.out.println(result);

            if (tipoConversion.equalsIgnoreCase("7")){
                System.out.println("Hasta Pronto...");
                break;
            };

            System.out.println("Ingrese un valor a convertir ==>" +conversor);
            var valorConversion = input.nextLine();

            if (tipoConversion.equalsIgnoreCase("1")){
                conversor=conversor+"/"+valorConversion;
            };

            if (tipoConversion.equalsIgnoreCase("2")){
                conversor=conversor+"/"+valorConversion;
            };

            if (tipoConversion.equalsIgnoreCase("3")){
                conversor=conversor+"/"+valorConversion;
            };
            if (tipoConversion.equalsIgnoreCase("4")){
                conversor=conversor+"/"+valorConversion;
            };
            if (tipoConversion.equalsIgnoreCase("5")){
                conversor=conversor+"/"+valorConversion;
            };
            if (tipoConversion.equalsIgnoreCase("6")){
                conversor=conversor+"/"+valorConversion;
            };

            String direccion="https://v6.exchangerate-api.com/v6/5549eaef4e6a70ae02696262/pair/";
            String result = direccion.concat(conversor);
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(result))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
               // System.out.println(json);
                Gson gson = new Gson();

                Moneda moneda = gson.fromJson(json, Moneda.class);

                // El valor de 24 [USD] correspoden al valor final  de ==>> 22 [ARS]
                System.out.println("El valor de "+valorConversion+" ["+moneda.base_code+"] correspode al valor final  de ==>> "+moneda.conversion_result+" ["+moneda.target_code+"]");



            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Erro e la URi verifique la direccion: ");
                System.out.println(e.getMessage());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
