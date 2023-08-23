package articulos;

import abstracciones.Productos;

public class Limpieza extends Productos {

	
	private boolean importado;
	private static final String prefijo = "AZ";
	private static int ultimoNumero = 0;
	private EnumLimpieza enumLimpieza;
	
	

	public Limpieza( String descripcion, int stock, float precioUnidad, float costoUnidad,
			 boolean importado, EnumLimpieza enumLimpieza) {

		super( descripcion, stock, precioUnidad, costoUnidad);
		this.importado = importado;
		this.setId(generarID());
	    this.enumLimpieza = enumLimpieza;
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



	public boolean isImportado() {
		agregarImpuestoImportado();
		return importado;
	}

	private void agregarImpuestoImportado() {
		float nuevoPrecio = this.getPrecioUnidad();
		nuevoPrecio = (float) (nuevoPrecio * 1.10);
		this.setPrecioUnidad(nuevoPrecio);
	}

	public void setImportado(boolean importado) {
		this.importado = importado;
	}

	public void setId(String id) {

	}

	
	
	
}
