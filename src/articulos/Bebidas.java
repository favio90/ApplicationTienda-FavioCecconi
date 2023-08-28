package articulos;

import abstracciones.Productos;

public class Bebidas extends Productos {

	
	
	private static final String prefijo = "AC";
	private static int ultimoNumero = 0;
    private boolean alcoholica;
    private float graduacionAlcoholica;
	
	
	

	public Bebidas( String descripcion, int stock, float precioUnidad, float costoUnidad,
			boolean alcoholica, boolean importado, float graduacionAlcoholica) {

		super( descripcion, stock, precioUnidad, costoUnidad, importado);
		this.alcoholica = alcoholica;
		this.graduacionAlcoholica = graduacionAlcoholica;
		this.setId(generarID());
		 setDisponible(true);
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



	private void agregarImpuestoImportado() {
		float nuevoPrecio = this.getPrecioUnidad();
		nuevoPrecio = (float) (nuevoPrecio * 1.10);
		this.setPrecioUnidad(nuevoPrecio);
	}



	public void setId(String id) {

		this.id = id;
	}

	
	
	
	
	
}
