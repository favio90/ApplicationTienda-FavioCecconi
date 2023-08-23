package abstracciones;

public abstract class Productos {

	private String id;
	private String descripcion;
	private int stock;
	private float precioUnidad;
	private float costoUnidad;
	private boolean disponible;

	
	
	
	
	
	
	
	
	
public Productos(String descripcion, int stock, float precioUnidad, float costoUnidad) {

		this.descripcion = descripcion;
		this.stock = stock;
		this.precioUnidad = precioUnidad;
		this.costoUnidad = costoUnidad;

	}



	//el setId debe validarse de formas distintas según la clase que herede (por eso es abstracto)
	public abstract void setId(String id);


	
	
//	Setters comunes 
	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public void setCostoUnidad(float costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	
	
//getters para que las sub clases puedan usar los atributos privados
	public String getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getStock() {
		return stock;
	}

	public float getPrecioUnidad() {
		return precioUnidad;
	}

	public float getCostoUnidad() {
		return costoUnidad;
	}

	public boolean isDisponible() {
		return disponible;
	}

}
