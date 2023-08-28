package articulos;

import abstracciones.Productos;

public class Limpieza extends Productos {

	
	private static final String prefijo = "AZ";
	private static int ultimoNumero = 0;
	private EnumLimpieza enumLimpieza;
	
	
	

	public Limpieza( String descripcion, int stock, float precioUnidad, float costoUnidad,
			 boolean importado, EnumLimpieza enumLimpieza) {
		super( descripcion, stock, precioUnidad, costoUnidad, importado);
		this.setId(generarID());
	    this.enumLimpieza = enumLimpieza;
	    setDisponible(true);
	}

	
	
	
//TODO PROBAR QUE ESTO ANDA	
	public String generarID() {
		ultimoNumero++;
		String numeroFormateado = String.format("%03d", ultimoNumero);
		return prefijo + numeroFormateado;
	}


	
	
	public EnumLimpieza getEnumLimpieza() {
		return enumLimpieza;
	}





	private void agregarImpuestoImportado() {
		float nuevoPrecio = this.getPrecioUnidad();
		nuevoPrecio = (float) (nuevoPrecio * 1.10);
		this.setPrecioUnidad(nuevoPrecio);
	}



	public void setId(String id) {

		this.id = id;
	}

	
	
	
}
