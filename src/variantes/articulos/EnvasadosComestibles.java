package variantes.articulos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import abstracciones.Descuento;
import abstracciones.VencimientoYCalorias;
import articulos.EnumEnvasados;
import articulos.Envasados;
import articulos.TiposDescuentos;
import tinda.Tienda;

public class EnvasadosComestibles extends Envasados implements VencimientoYCalorias  {

public 	PriorityQueue<PartidasComestibles> partidasQueue =  new PriorityQueue<>(Comparator.comparing(PartidasComestibles::getFechaVencimiento));
	private short calorias;
	private LocalDate fechaVencimiento;
	public TiposDescuentos tipoDescuento = TiposDescuentos.NINGUNO;
	public float descuento;
	
	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public EnvasadosComestibles(String descripcion, int stock, float precioUnidad, float costoUnidad,
			EnumEnvasados tipoDeEnvase, boolean importado) {
		super(descripcion, stock, precioUnidad, costoUnidad, tipoDeEnvase, importado);
		// TODO Auto-generated constructor stub
    setDisponible(true);
	}

//TODO ver bien como hacer esto
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

//TODO ver bien como hacer esto
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
	

	public void setTiposDescuento (TiposDescuentos tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}
	
	
	public short getCalorias() {
		return calorias;
	}
	
	public void setCalorias(short calorias) {
		
		this.calorias = calorias;
	}

	public TiposDescuentos getTiposDescuentos () {
		
		return tipoDescuento;
	}


}
	 
	


