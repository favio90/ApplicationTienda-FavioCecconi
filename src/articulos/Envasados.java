package articulos;

import abstracciones.Productos;

public class Envasados extends Productos {

	private EnumEnvasados tipoDeEnvase;
	private static final String prefijo = "AB";
	private static int ultimoNumero = 0;

	
	
	
	public Envasados( String descripcion, int stock, float precioUnidad, float costoUnidad,
			 EnumEnvasados tipoDeEnvase, boolean importado) {

		super( descripcion, stock, precioUnidad, costoUnidad, importado);
		this.tipoDeEnvase = tipoDeEnvase;
		 setDisponible(true);
		this.setId(generarID());
 //   System.out.println("este es el constructor . boolean da " + this.ge);
	
	}

	
	
	
//TODO PROBAR QUE ESTO ANDA	
	public String generarID() {
		ultimoNumero++;
		String numeroFormateado = String.format("%03d", ultimoNumero);
		return prefijo + numeroFormateado;
	}

	public EnumEnvasados getTipoDeEnvase() {
		return tipoDeEnvase;
	}

	public void setTipoDeEnvase(EnumEnvasados tipoDeEnvase) {
		this.tipoDeEnvase = tipoDeEnvase;

	}



//	public boolean esImportado() {
//	
//	
//		
//	}
	
//	private void agregarImpuestoImportado() {
//		float nuevoPrecio = this.getPrecioUnidad();
//		nuevoPrecio = (float) (nuevoPrecio * 1.10);
//		this.setPrecioUnidad(nuevoPrecio);
//	}



	public void setId(String id) {
		this.id = id;
	}

}
