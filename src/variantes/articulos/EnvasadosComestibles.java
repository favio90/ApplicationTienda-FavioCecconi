package variantes.articulos;

import java.time.LocalDate;

import articulos.EnumEnvasados;
import articulos.Envasados;

public class EnvasadosComestibles extends Envasados {

	
//por que si o si tengo que declarar constructor. 
	  public EnvasadosComestibles(String descripcion, int stock, float precioUnidad, float costoUnidad,
			EnumEnvasados tipoDeEnvase, boolean importado) {
		super(descripcion, stock, precioUnidad, costoUnidad, tipoDeEnvase, importado);
		// TODO Auto-generated constructor stub
	}

	private LocalDate fechaVencimiento;

	   


	    public LocalDate getFechaVencimiento() {
	        return fechaVencimiento;
	    }

	    public void setFechaVencimiento(LocalDate fechaVencimiento) {
	        this.fechaVencimiento = fechaVencimiento;
	    }
	}
	
	


