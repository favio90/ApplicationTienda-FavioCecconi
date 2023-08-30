package articulos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import interfaz.de.usuario.InterfazDeUsuario;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Prueba {
    public static void main(String[] args) {
  
    	
    	InterfazDeUsuario interfaz = new InterfazDeUsuario(); 
    	interfaz.menu();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	 LocalDate fechaHoy = LocalDate.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//         System.out.println("Fecha de hoy: " + fechaHoy.format(formatter));
//    	
    	
//    	try {
//            URL url = new URL("http://worldtimeapi.org/api/ip");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            StringBuilder response = new StringBuilder();
//
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//
//            reader.close();
//
//            // Procesar la respuesta JSON para obtener la hora actual
//            String jsonResponse = response.toString();
//            // ...
//
//            System.out.println("Respuesta JSON: " + jsonResponse);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}



