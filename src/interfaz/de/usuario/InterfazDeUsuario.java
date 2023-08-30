package interfaz.de.usuario;

import java.time.LocalDate;
import java.util.Scanner;

import abstracciones.Productos;
import articulos.Bebidas;
import articulos.EnumEnvasados;
import articulos.EnumLimpieza;
import articulos.Envasados;
import articulos.Limpieza;
import tinda.Tienda;
import tinda.TiposDeProductos;
import variantes.articulos.EnvasadosComestibles;
import variantes.articulos.PartidasComestibles;


public class InterfazDeUsuario {

	private static Scanner scanner = new Scanner(System.in);
	String descripcion;
	TiposDeProductos tipoDeProducto;
	EnumEnvasados tipoDeEnvasado;
	EnumLimpieza tipoDeLimpieza;
	float costo;
	float precioVenta;
	int cantidad;
	float costoTotal;
    boolean importado; 
    boolean alcoholica;
   public final static LocalDate fechaHoy = LocalDate.now();
    
	public static void mostrarMenu() {
		System.out.println("===== Menú =====");
		System.out.println("1. Comprar y cargar nuevo producto");
		System.out.println("2. Comprar stock de producto ya cargado");
		System.out.println("3. Vender producto");
		System.out.println("4. Mostrar todos los productos cargados (CON O SIN STOCK)");
		System.out.println("5. Mostrar sólo los productos que tengan Stock");
		System.out.println("6. Mostrar Partidas de Productos con fechas de vencimiento ");
		System.out.println("7. Filtrar productos con porcentajes de ganancia menor al introducido (API STREAM)");
		System.out.println("0. Salir");
		System.out.print("Elija una opción: ");
		System.out.println("Recordá que el saldo de la caja es: " + Tienda.saldoCaja);
		System.out.println("Recordá que el Espacio que queda en la tienda es de : " + Tienda.maximoStock + "   articulos");
		System.out.println("Hoy es " + fechaHoy);
	}
    
    
    
	public void menu() {
		int opcion;
		do {
			mostrarMenu();
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				comprarNuevoProducto();
				break;
			case 2:
				comprarProductoExistente();
				break;
			case 3:
				seleccionarVenta();
				break;
			case 4:
				Tienda.mostrarProductosStock();
				break;
			case 5:
			Tienda.mostrarProductosSoloStock();
				break;
			case 6:
			Tienda.mostrarVencimientos();
				break;
			case 7:
				filtrarProductos();
					break;
			case 0:
				System.out.println("Saliendo del programa.");
				break;
			default:
				System.out.println("Opción inválida. Por favor, elija nuevamente.");
			}
		} while (opcion != 0);
		scanner.close();
	}

	
	
	private void filtrarProductos() {
		
System.out.println("Ingresá el porcentaje de ganancias que los productos no deben superar ");
System.out.println("nota: la misma se calculará en base al precio de venta por unidad y al costo por unidad");
float porcentaje = scanner.nextFloat();
scanner.nextLine();
Tienda.listarProductosConUtilidadesInferiores(porcentaje);

		
	}



	public void comprarNuevoProducto() {
		System.out.println("Ingresá el nombre del producto");
		descripcion = scanner.nextLine();
		if (Tienda.validarDescripcion(descripcion)) {
			elegirProducto();
		} else {
			System.out.println("YA EXISTE UN PRODUCTO CON ESE NOMBRE");
			comprarNuevoProducto();
		}
	}

	
	
	
	private void elegirProducto() {
		System.out.println("Seleccione el tipo de producto:");
		System.out.println("1 = Envasados");
		System.out.println("2 = Envasados Comestibles");
		System.out.println("3 = Bebidas");
		System.out.println("4 = Limpieza");
		int opcion = scanner.nextInt();
		// scanner.nextLine(); // Consume la nueva línea dejada por nextInt()
		switch (opcion) {
////////////////////////////////////////  ENVASADOS  /////////////////////////////////////
		case 1:
			elegirTipoEnvasado();
			setCostoYPrecio();
			validarMontosEnvasado();		  
			esImportado();
			cargarEnvasado(cantidad, costo, precioVenta, costoTotal );
			break;
	/////////////////////////////////  COMESTIBLES  /////////////////////////////////////
		case 2:
			elegirTipoEnvasado();
			setCostoYPrecio();
			validarMontosEnvasado();			
			esImportado();
		int calorias =	valorEnergetico();
		cargarComestible( cantidad, costo, precioVenta, calorias, costoTotal  );
			break;
////////////////////////////////////////  BEBIDAS  /////////////////////////////////////
		case 3:
			setCostoYPrecio();
	    	validarMontosBebidas();
			esImportado();	   
			float graduacion = esAlcoholica();
			cargarBebidas(cantidad, costo, precioVenta, alcoholica, graduacion);   
		    break;
////////////////////////////////////////  LIMPIEZA  /////////////////////////////////////
		case 4:
			eligioLimpieza();
			setCostoYPrecio();
			esImportado();
			cargaPrimeraLimpieza(cantidad, costo, precioVenta, tipoDeLimpieza);
		    break;
			
		default:
			System.out.println("Opción inválida. ingresá nuevamente.");
			elegirProducto();
			break;
		}
		menu();
	}

	
	
	private void cargarComestible(int cantidad, float costo, float precioVenta, int calorias, float costoTotal ) {
	
		
	EnvasadosComestibles comestible = new EnvasadosComestibles ( descripcion,  cantidad,  precioVenta,  costo,
	 tipoDeEnvasado,  importado);
	comestible.setCalorias((short)calorias);
	comestible.setFechaVencimiento();
    PartidasComestibles partida = new PartidasComestibles (comestible.getId(), comestible.getDescripcion(), 
    		                          comestible.getStock(), comestible.getFechaVencimiento()) ; 
    comestible.partidasQueue.add(partida);
    Tienda.cargaPrimera(comestible);
	Tienda.setDescuento(comestible, partida);
		}



		private void cargarBebidas(int cantidad, float costo, float precioVenta, boolean alcoholica, float graduacion ) {
			
        Bebidas bebida = new Bebidas ( descripcion, cantidad, precioVenta,  costo,
    			 alcoholica, importado, graduacion);
			Tienda.cargaPrimera(bebida);
		}
	
	
	private void cargaPrimeraLimpieza(int cantidad, float costo, float precioVenta, EnumLimpieza tipoDeLimpieza) {
		Limpieza nuevo = new Limpieza(descripcion, cantidad, precioVenta, costo,  importado, tipoDeLimpieza );
	    Tienda.cargaPrimera(nuevo);
	    System.out.println("=======La compra fue realizada=======");
	    
	}

	
	
	
	private void cargarEnvasado(int cantidad, float costo, float precioVenta, float costoTotal) {

		Envasados envasado = new Envasados(  descripcion, cantidad,  precioVenta, costo,
	    tipoDeEnvasado,  importado);
	   	Tienda.cargaPrimera(envasado);
		
	}

	private void eligioLimpieza() {
		tipoDeProducto = TiposDeProductos.LIMPIEZA;
		System.out.println("¿Que tipo de Producto de Limpieza?");
		System.out.println("1= Ropa");
		System.out.println("2= Multiusos");
		System.out.println("3= Cocina");
		System.out.println("4= Pisos");
		int tipo = scanner.nextInt();
		switch (tipo) {
		case 1:
			tipoDeLimpieza = EnumLimpieza.ROPA;
			break;
		case 2:
			tipoDeLimpieza = EnumLimpieza.MULTIUSOS;
			break;
		case 3:
			tipoDeLimpieza = EnumLimpieza.COCINA;
			break;
		case 4:
			tipoDeLimpieza = EnumLimpieza.PISOS;
			break;
		default:
			System.out.println("Opción inválida. Tipo de limpieza no definido.");
		}
	}

	
	
	
 private float esAlcoholica() {
		
	float graduacion = 0;
	int eleccion;
	
		 System.out.println("¿ Es Alcoholica? ");
		    System.out.println("1 = si /  2 = no");
		    eleccion = scanner.nextInt();
		   if(eleccion == 1) {
         alcoholica = true;  				   
		   System.out.println("¿que graduación alcoholica tiene?");
		   graduacion = scanner.nextInt();
		   return graduacion;
		   }else if (eleccion == 2) {
			    alcoholica = false;
		  graduacion = 0 ;
		  return graduacion;
		   } else {
			   
			   System.out.println("elegiste mal la opcion");
			 esAlcoholica();
		   }
		return eleccion;
	}
	
 
 
 
 private int valorEnergetico() {
	 int calorias;
	 System.out.println("¿cuantas calorías tiene?");
	   calorias = scanner.nextInt();
	   return calorias;
 }
	
	private void esImportado() {
		System.out.println("¿ Es importado? ");
	    System.out.println("1 = si /  2 = no");
	   int eleccion = scanner.nextInt();
	   if(eleccion == 1) {
      importado = true;  				   
	   }else {
		   importado = false;
	   }
	System.out.println(importado);
	
	}

	
	private void elegirTipoEnvasado() {
		tipoDeProducto = TiposDeProductos.ENVASADOS;
		System.out.println("¿Qué tipo de Producto Envasado?");
		System.out.println("1 = Plástico");
		System.out.println("2 = Vidrio");
		System.out.println("3 = Lata");
		int opcion1 = scanner.nextInt();
		switch (opcion1) {
		case 1:
			tipoDeEnvasado = EnumEnvasados.plastico;
			break;
		case 2:
			tipoDeEnvasado = EnumEnvasados.vidrio;
			break;
		case 3:
			tipoDeEnvasado = EnumEnvasados.lata;
			break;
		default:
			System.out.println("Opción inválida. Tipo de envasado no definido.");
			menu();
			return;
		}
	}

	
	
	
	private void setCostoYPrecio() {
		costo = cargarEntero("Ingrese el costo: ");
		precioVenta = cargarEntero("Ingrese el precio: ");
		cantidad = (int)cargarEntero("Ingrese la cantidad: ");
		costoTotal = costo * cantidad;
	}



//solicita ingreso de código para renovar stock producto ya cargado. Chequea que exista
	public  void comprarProductoExistente() {
		System.out.println("Ingresá el código del producto al que querés agregar stock");
		String codigo = scanner.nextLine();
		Productos producto = Tienda.buscarProducto(codigo);
		if(producto != null) {
		averiguarClase(producto);	
		}else {
	
			menu();
		}
		
	}

	
	public void seleccionarVenta() {
		
	
		System.out.println("Ingresá el código del producto  que querés vender");
		String codigo = scanner.nextLine();
		Productos producto = Tienda.buscarProducto(codigo);
		
		if(producto != null) {
	    
			if(producto.isDisponible() == false) {
				System.out.println("Este producto está fuera de venta");
				menu();
			}
			System.out.println("recordá que el precio de venta es " + producto.getPrecioUnidad());
	        System.out.println("y además cuenta con el siguiente Stock: " + producto.getStock());
	       System.out.println("Cuantas unidades querés vender? El máximo es 10");
	       int unidades = scanner.nextInt();
	      scanner.nextLine();   
			if(unidades>10) {
	 System.out.println("No se pueden exceder las 10 unidades por prodoucto");
	 seleccionarVenta();
				return;
			}
	      Tienda.venderProducto(producto, unidades);	
	      return;
		}else {
			menu();
		}
	}
	
	
	private void averiguarClase(Productos producto) {
	
		 if (producto instanceof EnvasadosComestibles) {
	        agregarEnvasadosComestibles((EnvasadosComestibles) producto);
	        } else if (producto instanceof Envasados) {
	            agregarStockEnvasados((Envasados) producto);
	        } else if (producto instanceof Limpieza) {
	    	agregarLimpieza(producto);
	        } else if (producto instanceof Bebidas) {
	        	agregarBebidas(producto);
	        } else {
	            System.out.println("La instancia no pertenece a ninguna clase conocida");
	        }
		
	}



	private void agregarLimpieza(Productos producto) {
		setCostoYPrecio();
		validarMontosLimpieza();
		Tienda.aumentarStock(producto, cantidad, costo, precioVenta);
	}


	private void agregarBebidas(Productos producto) {
		setCostoYPrecio();
		validarMontosBebidas();
		Tienda.aumentarStock(producto, cantidad, costo, precioVenta);
	}
	

	private void validarMontosLimpieza() {
	
		Object resultado[] = Tienda.setCostoYPrecioLimpieza(cantidad, costo, precioVenta, tipoDeLimpieza,
				costoTotal);
		if ((boolean) resultado[0] == false) {
			System.out.println((String) resultado[1]);
			elegirProducto();
		    setCostoYPrecio();
		}
		
	}



	private void agregarEnvasadosComestibles(EnvasadosComestibles comestible) {
		System.out.println();
		setCostoYPrecio();
		validarMontosEnvasado();
	PartidasComestibles nuevaPartida = new PartidasComestibles(comestible.getId(),
    comestible.getDescripcion(), cantidad);
	nuevaPartida.setFechaVencimiento();
	comestible.partidasQueue.add(nuevaPartida);
	Tienda.aumentarStock(comestible, cantidad, costo, precioVenta);
    Tienda.actualizarDescuentos();
	}



	private void agregarStockEnvasados(Envasados producto) {
		setCostoYPrecio();
		validarMontosEnvasado();
	 Tienda.aumentarStock(producto, cantidad, costo, precioVenta);
	}


	public void validarMontosEnvasado() {
		Object resultado1[] = Tienda.setCostoYPrecioEnvasados(cantidad, precioVenta, costo, costoTotal);
		if ((boolean) resultado1[0] == false) {
			System.out.println((String) resultado1[1]);
		  menu();
			return;
		} 
	}

	
	
	public void validarMontosBebidas() {
	Object beber[]  = Tienda.setCostoYPrecioBebidas(cantidad, precioVenta, costo, costoTotal);
	if ((boolean) beber [0] == false) {
		System.out.println((String) beber [1]);
		menu();
		return;}
	}
	
	
	public void validarMontosEnvasadoComestible() {
		Object resultado1[] = Tienda.setCostoYPrecioEnvasados(cantidad, precioVenta, costo, costoTotal);
		if ((boolean) resultado1[0] == false) {
			System.out.println((String) resultado1[1]);
			elegirProducto();
			return;
		} else {
			esImportado();
	cargarEnvasado(cantidad, costo, precioVenta, costoTotal );
		}
	}
	
	
	
	public static float cargarEntero(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextFloat();
	}

}
