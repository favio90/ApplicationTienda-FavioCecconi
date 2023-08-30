package variantes.articulos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PartidasComestibles {

private String id;
private String descripcion;
private int stock;
LocalDate fechaVencimiento;



public PartidasComestibles(String id, String descripcion, int stock) {
	
	this.id = id;
	this.descripcion = descripcion;
	this.stock = stock;
	
}


public PartidasComestibles(String id, String descripcion, int stock, LocalDate fechaVencimiento) {
	
	this.id = id;
	this.descripcion = descripcion;
	this.stock = stock;
	this.fechaVencimiento = fechaVencimiento;
}



public String getId() {
	return id;
}



public void setId(String id) {
	this.id = id;
}



public String getDescripcion() {
	return descripcion;
}



public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}



public int getStock() {
	return stock;
}



public void setStock(int stock) {
	this.stock = stock;
}



public LocalDate getFechaVencimiento() {
	return fechaVencimiento;
}



public void setFechaVencimiento( ) {
	System.out.println("Ingrese la fecha en formato yyyy-MM-dd:");
	Scanner scanner = new Scanner(System.in);
	
	String fechaIngresada = scanner.nextLine();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	try { 
	fechaVencimiento = LocalDate.parse(fechaIngresada, formatter);
	    LocalDate fechaHoy = LocalDate.now();
	    if (fechaVencimiento.isBefore(fechaHoy)) {
	        System.out.println("La fecha de vencimiento no puede ser anterior al día de hoy.");
	       setFechaVencimiento();
	   
	       return;
	    } else {
	        System.out.println("Fecha ingresada: " + fechaVencimiento);
	
	    }}
	 catch (Exception e) {
	    System.out.println("Fecha ingresada no válida. Asegúrese de usar el formato yyyy-MM-dd.");
        setFechaVencimiento();
        return;
	    
	 } 
}
	
}
