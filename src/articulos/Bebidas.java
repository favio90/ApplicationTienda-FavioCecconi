package articulos;

import abstracciones.Productos;

public class Bebidas extends Productos {

	
	private boolean importado;
	private static final String prefijo = "AC";
	private static int ultimoNumero = 0;
    private boolean alcoholica;
    private float graduacionAlcoholica;
	
	
	

	public Bebidas( String descripcion, int stock, float precioUnidad, float costoUnidad,
			boolean alcoholica, boolean importado, float graduacionAlcoholica) {

		super( descripcion, stock, precioUnidad, costoUnidad);
		this.alcoholica = alcoholica;
		this.graduacionAlcoholica = graduacionAlcoholica;
		this.importado = importado;
		this.setId(generarID());
	}

	
	
	
//TODO PROBAR QUE ESTO ANDA	
	public String generarID() {
		ultimoNumero++;
		String numeroFormateado = String.format("%03d", ultimoNumero);
		return prefijo + numeroFormateado;
	}


 
	public boolean isAlcoholica() {
		return alcoholica;
	}


	public float getGraduacionAlcoholica() {
		return graduacionAlcoholica;
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
