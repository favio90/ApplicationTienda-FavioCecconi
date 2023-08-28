package tinda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import abstracciones.Descuento;
import abstracciones.Productos;
import articulos.Bebidas;
import articulos.EnumLimpieza;
import articulos.Envasados;
import articulos.Limpieza;
import articulos.TiposDescuentos;
import variantes.articulos.EnvasadosComestibles;
import variantes.articulos.PartidasComestibles;

public class Tienda implements Descuento {

	String codigo;



	static Map<String, Productos> productosEnStock = new HashMap<>();
	public static Map<Integer, PartidasComestibles> partidasConVencimiento = new HashMap<>();
	private final String Tienda = "Tienda Favio Cecconi";
	public static int maximoStock = 10000;
	public static float saldoCaja = 50000;
	private static Integer numeroDePartidasVencimiento = 1;
	private static int claveParaEliminar;

//VALIDA QUE LA DESCRIPCI�N INGRESADA NO SEA IGUAL A UNA QUE YA EXISTE
	static public boolean validarDescripcion(String nombreBuscado) {
		if (productosEnStock.isEmpty()) {
			return true;
		} else {
			for (Productos producto : productosEnStock.values()) {
				if (producto.getDescripcion().equals(nombreBuscado)) {
					System.out.println(
							"ESA DESCRIPCI�N YA EXISTE POR FAVOR INGRES� OTRA O SELECCION� PRODUCTO YA EXISTENTE");
					return false;
				}
			}
			return true;
		}
	}

	static public Object[] setCostoYPrecioLimpieza(int cantidad, float costo, float precio, Enum tipoArticulo,
			float costoTotal) {
		Object[] datos = new Object[3];

		if (cantidad > maximoStock) {
			datos[0] = false;
			datos[1] = "No hay espacio en la tienda. Selecciona menos cantidad";
			return datos;
		}

		if (costoTotal > saldoCaja) {
			datos[0] = false;
			datos[1] = "El saldo de la caja es menor al costo de la compra";
			return datos;
		}
		if (tipoArticulo != EnumLimpieza.ROPA && tipoArticulo != EnumLimpieza.MULTIUSOS) {
			double porcentajeGanancia = ((double) (precio - costo) / costo) * 100;
			if (porcentajeGanancia < 10 || porcentajeGanancia > 25) {
				datos[0] = false;
				datos[1] = "la ganancia de los productos de limpieza de cocina y pisos no puede ser menor del 10% ni mayor al 25%";
				return datos;
			}
		}
		datos[0] = true;
		return datos;
	}

	static public Object[] setCostoYPrecioBebidas(int cantidad, float costo, float precio, float costoTotal) {
		Object[] datos = new Object[3];
		if (cantidad > maximoStock) {
			datos[0] = false;
			datos[1] = "No hay espacio en la tienda. Selecciona menos cantidad";
			return datos;
		}
		if (costoTotal > saldoCaja) {
			datos[0] = false;
			datos[1] = "El saldo de la caja es menor al costo de la compra";
			return datos;
		}
		datos[0] = true;
		return datos;
	}

	static public Object[] setCostoYPrecioEnvasados(int cantidad, float costo, float precio, float costoTotal) {

		Object[] datos = new Object[3];
		if (cantidad > maximoStock) {
			datos[0] = false;
			datos[1] = "No hay espacio en la tienda. Selecciona menos cantidad";
			return datos;
		}
		if (costoTotal > saldoCaja) {
			datos[0] = false;
			datos[1] = "El saldo de la caja es menor al costo de la compra";
			return datos;
		}
		datos[0] = true;
		return datos;
	}

	public static void cargaPrimera(Productos a) {

		if (a.isImportado() == true) {
			a.setPrecioUnidad((float) (a.getPrecioUnidad() + (a.getPrecioUnidad() * 0.10)));
		}

		productosEnStock.put(a.getId(), a);
		maximoStock = maximoStock - a.getStock();

		System.out.println("el costo por unidad es " + a.getCostoUnidad());
		System.out.println("el stock que cargaste es " + a.getStock());

		saldoCaja = saldoCaja - (a.getCostoUnidad() * a.getStock());

	}

	public static void comprarEnvasado(Envasados nuevoProducto) {

		productosEnStock.put(nuevoProducto.getId(), nuevoProducto);

	}

	public static void mostrarProductosStock() {

		for (Map.Entry<String, Productos> entry : productosEnStock.entrySet()) {
			String idProducto = entry.getKey();
			Productos producto = entry.getValue();
			System.out.println("ID: " + idProducto);
			System.out.println("Descripci�n: " + producto.getDescripcion());
			System.out.println("Stock: " + producto.getStock());
			System.out.println("Precio por Unidad: " + producto.getPrecioUnidad());
			System.out.println("Costo por Unidad: " + producto.getCostoUnidad());
			System.out.println("Est� disponible?: " + producto.isDisponible());
			System.out.println("Es Importado? : " + producto.isImportado());
			if(producto instanceof Limpieza) {
		Limpieza	limpieza =(Limpieza)producto;
		System.out.println("Es un art�culo de limpieza");
		System.out.println("del tipo:  " + limpieza.getEnumLimpieza());
			}
			if(producto instanceof Bebidas) {
				Bebidas	bebida =(Bebidas)producto;
				System.out.println("Es una bebida");
				System.out.println("Es alcoholica? :  " + bebida.isAlcoholica());
			    System.out.println("que graduaci�n tiene? " + bebida.getGraduacionAlcoholica());		
			}
			
			if(producto instanceof Envasados) {
				Envasados	envase =(Envasados)producto;
				System.out.println("Es un Envasado");
				System.out.println("El envase es de tipo :  " + envase.getTipoDeEnvase());
					
			}
			
			if (producto instanceof EnvasadosComestibles) {
				EnvasadosComestibles comestible = (EnvasadosComestibles) producto;
				System.out.println( "Es un comestible");
				System.out.println("El tipo de envase es: " + comestible.getTipoDeEnvase());
				System.out.println("Las calor�as que tiene son: " + comestible.getCalorias());
				System.out.println("para averiguar las distintas fechas de vencimiento ingres� 4 en el men�");
			}
			
			
			System.out.println("---------------------------------------------------------");

		}
	}
	
	
	public static void mostrarProductosSoloStock() {

		for (Map.Entry<String, Productos> entry : productosEnStock.entrySet()) {
		
			String idProducto = entry.getKey();
			Productos producto = entry.getValue();
		if(producto.isDisponible() == false)
			continue;
			System.out.println("ID: " + idProducto);
			System.out.println("Descripci�n: " + producto.getDescripcion());
			System.out.println("Stock: " + producto.getStock());
			System.out.println("Precio por Unidad: " + producto.getPrecioUnidad());
			System.out.println("Costo por Unidad: " + producto.getCostoUnidad());
			System.out.println("Est� disponible?: " + producto.isDisponible());
			System.out.println("Es Importado? : " + producto.isImportado());
			if(producto instanceof Limpieza) {
		Limpieza	limpieza =(Limpieza)producto;
		System.out.println("Es un art�culo de limpieza");
		System.out.println("del tipo:  " + limpieza.getEnumLimpieza());
			}
			if(producto instanceof Bebidas) {
				Bebidas	bebida =(Bebidas)producto;
				System.out.println("Es una bebida");
				System.out.println("Es alcoholica? :  " + bebida.isAlcoholica());
			    System.out.println("que graduaci�n tiene? " + bebida.getGraduacionAlcoholica());		
			}
			
			if(producto instanceof Envasados) {
				Envasados	envase =(Envasados)producto;
				System.out.println("Es un Envasado");
				System.out.println("El envase es de tipo :  " + envase.getTipoDeEnvase());
					
			}
			
			if (producto instanceof EnvasadosComestibles) {
				EnvasadosComestibles comestible = (EnvasadosComestibles) producto;
				System.out.println( "Es un comestible");
				System.out.println("El tipo de envase es: " + comestible.getTipoDeEnvase());
				System.out.println("para averiguar las distintas fechas de vencimiento ingres� 4 en el men�");
			}
			
			
			System.out.println("---------------------------------------------------------");

		}
	}

	public static Object[] setCostoYPrecioComestible(int cantidad, float costo, float precioVenta, float costoTotal) {

		Object[] datos = new Object[3];
		if (cantidad > maximoStock) {
			datos[0] = false;
			datos[1] = "No hay espacio en la tienda. Selecciona menos cantidad";
			return datos;
		}
		if (costoTotal > saldoCaja) {
			datos[0] = false;
			datos[1] = "El saldo de la caja es menor al costo de la compra";
			return datos;
		}

		double porcentajeGanancia = ((double) (precioVenta - costo) / costo) * 100;
		if (porcentajeGanancia > 20) {
			datos[0] = false;
			datos[1] = "La ganancia de los productos comestibles no debe superar el 20%";
			return datos;
		}
		datos[0] = true;
		return datos;

	}

	public static Productos buscarProducto(String codigo) {

		if (productosEnStock.containsKey(codigo)) {
			Productos productoEncontrado = productosEnStock.get(codigo);
			System.out.println("Producto encontrado:");
			System.out.println(productoEncontrado);
			return productoEncontrado;
		} else {
			System.out.println("Producto no encontrado para el identificador: " + codigo);
			return null;
		}
	}

	public static void aumentarStock(Productos producto, int cantidad, float costo, float precioVenta) {

		producto.setStock(producto.getStock() + cantidad);
		producto.setCostoUnidad(costo);
		producto.setPrecioUnidad(precioVenta);
		maximoStock = maximoStock - cantidad;
		float costoTotal = costo * cantidad;
		saldoCaja = saldoCaja - costoTotal;
		System.out.println("se ha aumentado el STOCK del producto seleccionado");
		System.out.println("Ahora tiene " + producto.getStock() + "   unidades");

	}

	public static void mostrarVencimientos() {

		for (Map.Entry<Integer, PartidasComestibles> entry : partidasConVencimiento.entrySet()) {
			Integer numero = entry.getKey();
			PartidasComestibles producto = entry.getValue();
			System.out.println("ID: " + producto.getId());
			System.out.println("Descripci�n: " + producto.getDescripcion());
			System.out.println("Stock: " + producto.getStock());
			System.out.println("Esta partida Vence el +  " + producto.getFechaVencimiento());

			System.out.println("---------------------------------------------------------");

		}

	}

	public static void cargarVencimiento(PartidasComestibles comestible) {
		partidasConVencimiento.put(numeroDePartidasVencimiento, comestible);
		numeroDePartidasVencimiento++;
	}

	public static void venderProducto(Productos producto, int unidades) {

		if (producto instanceof EnvasadosComestibles) {
			venderProductoVencimiento((EnvasadosComestibles) producto, unidades);
			return;
		}
		int stock = producto.getStock();
		if (unidades > stock) {
			ventaSuperaStock(producto, stock);
			return;
		}

		producto.setStock(producto.getStock() - unidades);
		System.out.println("Se han vendido unidades: " + unidades);
		float ganancia = unidades * producto.getPrecioUnidad();
		System.out.println("la ganancia es de : " + ganancia);

		saldoCaja = saldoCaja + ganancia;
		maximoStock = maximoStock - unidades;
		if (producto.getStock() == 0) {
			producto.setDisponible(false);
			System.out.println("el producto ya no tiene stock y se ha retirado de la venta");
		}

	}

	private static void ventaSuperaStock(Productos producto, int stock) {

		System.out.println("Hay productos con Stock menor al solicitado. Se vender�n s�lo" + stock + " unidades");
		System.out.println("El producto quedar� fuera de venta");
		producto.setStock(0);
		producto.setDisponible(false);
		float ganancia = stock * producto.getPrecioUnidad();
		saldoCaja = saldoCaja + ganancia;
		maximoStock = maximoStock - stock;
	
	}

	
	//TODO ANDA MAL ARREGLAR
	public static void venderProductoVencimiento(EnvasadosComestibles producto, int unidades) {

		PartidasComestibles productoPorVencer = vencimientoProximo(producto);
		float ganancia = unidades * producto.getPrecioUnidad();

		if (productoPorVencer.getStock() == unidades && producto.getStock() == unidades) {

			ventaExitosaVencimiento(producto, unidades, productoPorVencer);
			partidasConVencimiento.remove(claveParaEliminar);
			// arreglar la linea siguiente. est� mal. no supera stock iguala al stock.
			ventaSuperaStock(producto, producto.getStock());
		}

		if (productoPorVencer.getStock() == unidades && producto.getStock() > unidades) {
			saldoCaja = saldoCaja + ganancia;
			maximoStock = maximoStock + unidades;
			ventaExitosaVencimiento(producto, unidades, productoPorVencer);
			partidasConVencimiento.remove(claveParaEliminar);
			System.out.println("Se vendi� una partida entera de vencimientos");
			System.out.println("el programa revisar� si es necesario actualizar descuentos");
			setDescuento(producto, productoPorVencer);
		}

		if (productoPorVencer.getStock() > unidades) {

			saldoCaja = saldoCaja + ganancia;
			maximoStock = maximoStock + unidades;
			ventaExitosaVencimiento(producto, unidades, productoPorVencer);
		}

		// falta hacer el de unidades mayor que producto. no confiar en GANANCIA para
		// este caso. hacerlo de nuevo.

		if (productoPorVencer.getStock() < unidades && producto.getStock() > unidades) {

			if (unidades < 0)
				System.out.println("hay error qued� en negativo");

			System.out.println("ENTRO AC�");
			int partidaStockEliminado = productoPorVencer.getStock();

			unidades = unidades - partidaStockEliminado;
			System.out.println("Se vendieron " + unidades + "unidades de la partida de vencimiento mas pr�xima");
			partidasConVencimiento.remove(claveParaEliminar);
			ganancia = unidades * producto.getPrecioUnidad();
			producto.setStock(producto.getStock() - unidades);
			saldoCaja = saldoCaja + ganancia;
			maximoStock = maximoStock + unidades;
			venderProductoVencimiento(producto, unidades);
		}

	}

	private static void ventaExitosaVencimiento(EnvasadosComestibles producto, int unidades,
			PartidasComestibles productoPorVencer) {

		int stock = producto.getStock();
		productoPorVencer.setStock(productoPorVencer.getStock() - unidades);
		float ganancia = unidades * producto.getPrecioUnidad();
		System.out.println("la venta fue exitosa");
		System.out.println(producto.getId() + producto.getDescripcion() + "  x  " + unidades);
		System.out.println("El precio por unidad es : " + producto.getPrecioUnidad());
		System.out.println("ganaste $" + ganancia);
		System.out.println("el nuevo stock del producto es: " + producto.getStock());
		System.out.println("el programa revisar� si es necesario actualizar descuentos");
		setDescuento(producto, productoPorVencer);

	}

	public static PartidasComestibles vencimientoProximo(EnvasadosComestibles productoAVender) {
		LocalDate fechaActual = LocalDate.now();
		PartidasComestibles productoMasProximo = null;

		for (Entry<Integer, PartidasComestibles> entry : partidasConVencimiento.entrySet()) {
			PartidasComestibles producto = entry.getValue();

			if (productoAVender.getId().equals(producto.getId())) {
				LocalDate fechaVencimiento = producto.getFechaVencimiento();

				if (productoMasProximo == null || fechaVencimiento.isBefore(productoMasProximo.getFechaVencimiento())) {
					productoMasProximo = producto;
					claveParaEliminar = entry.getKey();
				}
			}
		}

		return productoMasProximo;
	}

	private static PartidasComestibles buscarVencimientoProximo(EnvasadosComestibles producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	// TODO ANDA MAL HAY QUE ARREGLAR
	public static void setDescuento(EnvasadosComestibles producto, PartidasComestibles partida) {

		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaVencimiento = partida.getFechaVencimiento();
		long diasRestantes = ChronoUnit.DAYS.between(fechaHoy, fechaVencimiento);

		if (diasRestantes <= 15) {

			float precioConDescuento = (float) (producto.getPrecioUnidad() * 0.9); // Descuento del 5%
			if (precioConDescuento < producto.getCostoUnidad()) {
				System.out.println(
						" Aunque el vencimiento es inminente No puede aplicarse el descuento debido a que el producto dar�a perdida");
				return;
			}
			producto.setPrecioUnidad(precioConDescuento);
			System.out.println("Se aplic� un descuento del 10%  ya que vence dentro de menos de 15 d�as. Producto:  "
					+ producto.getId());
			System.out.println("El precio ahora es de:  " + producto.getPrecioUnidad());

		} else if (diasRestantes <= 30) {
			// Descuento del 10%
			float precioConDescuento = (float) (producto.getPrecioUnidad() * 0.95); // Descuento del 5%
			if (precioConDescuento < producto.getCostoUnidad()) {
				System.out.println(
						" Aunque el vencimiento es inminente No puede aplicarse el descuento debido a que el producto dar�a perdida");
				return;
			}
			producto.setPrecioUnidad(precioConDescuento);
			System.out.println(
					"Se aplic� un descuento del 5% al producto ya que vence dentro de menos de 30 d�as. Producto:   "
							+ producto.getId());
		} else {
			System.out.println(
					"No se aplic� ning�n descuento al producto ya que faltan mas de 30 d�as para el vencimiento "
							+ producto.getId());
		}

	}

	
	
	// TODO NO ANDA HAY QUE ARREGLAR
	public static void actualizarDescuentos() {
	    for (PartidasComestibles partida : partidasConVencimiento.values()) {
	        String productoId = partida.getId();  
	        EnvasadosComestibles producto = (EnvasadosComestibles) productosEnStock.get(productoId);
	        
	        if (producto != null) {
	            LocalDate fechaHoy = LocalDate.now();
	            LocalDate fechaVencimiento = partida.getFechaVencimiento();
	            long diasRestantes = ChronoUnit.DAYS.between(fechaHoy, fechaVencimiento);
                producto.setTiposDescuento(TiposDescuentos.NINGUNO);
	            if (diasRestantes <= 15 ) { //creo que est� mal la condicion habr�a que agregar q no de perdida
	                producto.setTiposDescuento(TiposDescuentos.DIEZ);
	            	float precioConDescuento = (float) (producto.getPrecioUnidad() * 0.9); // Descuento del 10%
	                if (precioConDescuento >= producto.getCostoUnidad()) {
	                    producto.setPrecioUnidad(precioConDescuento);
	                    System.out.println("Se aplic� un descuento del 10% al producto " + producto.getId());
	                    System.out.println("El precio ahora es de: " + producto.getPrecioUnidad());
	                } else {
	                    System.out.println("No se aplic� el descuento al producto " + producto.getId() + "   "  + 
	                producto.getDescripcion() +  " debido a que el precio con descuento dar�a p�rdida.");
	                }
	            } else if (diasRestantes <= 30) {
	                if(producto.getTiposDescuentos() == TiposDescuentos.DIEZ)
	                	continue;
	            	float precioConDescuento = (float) (producto.getPrecioUnidad() * 0.95); // Descuento del 5%
	                if (precioConDescuento >= producto.getCostoUnidad()) {
	                    producto.setPrecioUnidad(precioConDescuento);
	                    System.out.println("Se aplic� un descuento del 5% al producto " + producto.getId());
	                    System.out.println("El precio ahora es de: " + producto.getPrecioUnidad());
	                } else {
	                    System.out.println("No se aplic� el descuento al producto " + producto.getId() +
	                            " debido a que el precio con descuento dar�a p�rdida.");
	                }
	            }
	        }
	    }
	}

	
	public static void listarProductosConUtilidadesInferiores(float porcentaje_utilidad) {
	    productosEnStock.values().stream()
	        .filter(producto -> {
	            float gananciaPorcentaje = (calcularGanancia(producto) / producto.getPrecioUnidad()) * 100;
	            return gananciaPorcentaje < porcentaje_utilidad;
	        })
	        .forEach(producto -> {
	            System.out.println("C�digo: " + producto.getId());
	            System.out.println("Descripci�n: " + producto.getDescripcion());
	            System.out.println("Cantidad en stock: " + producto.getStock());
	            System.out.println("Ganancia (%): " + (calcularGanancia(producto) / producto.getPrecioUnidad()) * 100);
	            System.out.println("-------------------------");
	        });
	}

	private static float calcularGanancia(Productos producto) {
	    return producto.getPrecioUnidad() - producto.getCostoUnidad();
	}
	






	
	

}