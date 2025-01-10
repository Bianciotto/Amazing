package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class EmpresaAmazing {

	private String cuit;
	private  Map <String, Transporte> transportes;
	private static Map <Integer, Pedido> pedidos;
	private double facturacionPedidosCerrados;
	private int paquetes;
	
	public EmpresaAmazing() {
	}
	
	public EmpresaAmazing(String cuit) {
		this.cuit = cuit;
		this.paquetes = 0;
	    this.transportes = new HashMap<>();
	    this.pedidos=new HashMap<>();
	    this.facturacionPedidosCerrados = 0;
	       
	}

public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
	 
	if( transportes.containsKey(patente)) {
		throw new RuntimeException("Esta patente ya se encuentra registrada");
	}
	else {
		Transporte automovil = new TransporteAutomovil(patente, volMax, valorViaje,maxPaq);
		transportes.put(patente,automovil);
	}
	
	}
	
public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
	if( transportes.containsKey(patente)) {
		throw new RuntimeException("Esta patente ya se encuentra registrada");
	}
	else {
		Transporte utilitario = new TransporteUtilitario(patente, volMax, valorViaje, valorExtra);
		transportes.put(patente, utilitario);
	}
}

public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
	if( transportes.containsKey(patente)) {
		throw new RuntimeException("Esta patente ya se encuentra registrada");
	}
	else {
		Transporte camion = new TransporteCamion(patente,volMax,valorViaje,adicXPaq);
		transportes.put(patente,camion);
	}
}

public int registrarPedido(String cliente, String direccion, int dni) {
	Pedido nuevoPedido = new Pedido(cliente, direccion, dni);
	int nroPedido= generarCodigoDePedido();
	nuevoPedido.colocarNroPedido(nroPedido);
	pedidos.put(nroPedido, nuevoPedido);
	
	return nroPedido;
}

public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
	Pedido p = obtenerPedido(codPedido);
	if( p == null) {
		throw new RuntimeException("El pedido no se encuentra registrado");
	}
	else if( p.estaCerrado()) {
		throw new RuntimeException("No se pueden agregar paquetes, el pedido ya se encuentra cerrado");
	}
	else {
		Paquete paq = new PaqueteOrdinario(codPedido,volumen,precio,costoEnvio);
		paquetes +=1;
		p.agregarPaquete(paq);
		paq.colocarIDPaquete(paquetes);
		paq.direccionDeEntrega(p.devolverDireccion());
	}
	return paquetes;
}

public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
	Pedido p = obtenerPedido(codPedido);
	if( p == null) {
		throw new RuntimeException("El pedido no se encuentra registrado");
	}
	else if( p.estaCerrado()) {
		throw new RuntimeException("No se pueden agregar paquetes, el pedido ya se encuentra cerrado");
	}
	else {
		Paquete paq = new PaqueteEspecial(codPedido,volumen,precio,porcentaje, adicional);
		paquetes += 1;
		p.agregarPaquete(paq);
		paq.colocarIDPaquete(paquetes);
		paq.direccionDeEntrega(p.devolverDireccion());
	}
	return paquetes; 
}

public boolean quitarPaquete(int codPaquete) {
	boolean paqueteEliminado= false;
	for (Integer clave : pedidos.keySet()) {
		Pedido p = pedidos.get(clave);
		paqueteEliminado = paqueteEliminado || p.recorrerListaYEliminarPaq(codPaquete);
	}
	if (paqueteEliminado == false) {
		throw new RuntimeException("No se encontró el paquete");
	}
	return paqueteEliminado;
}
		
public double cerrarPedido(int codPedido) {
	Pedido p = obtenerPedido(codPedido);
	if( p == null) {
		throw new RuntimeException("El pedido no se encuentra registrado");
	}
	else if (p.estaCerrado()) {
		throw new RuntimeException("El pedido ya fue finalizado");
	}
	else {
		p.cerrarPedido();
		double Total= p.calcularTotalPedido();
		facturacionPedidosCerrados += Total;
		return Total;
	}
}

public String cargarTransporte(String patente) {
	Transporte T = obtenerTransporte(patente);
	if (T==null) {
		throw new RuntimeException("La patente no se encuentra registrada");
	}
	else {
		cargarPaquetesAlTransporte(T);
		return T.toString();

	}
	
}


public double costoEntrega(String patente) {          
	Transporte T = obtenerTransporte(patente);
	if(T == null) {
		throw new RuntimeException("La patente no se encuentra registrada");
	}
	if (!T.estaCargado()) {
		throw new RuntimeException("El transporte no está cargado");
	}
	return T.calcularCostoEntrega();
}


public double facturacionTotalPedidosCerrados() {
	
	return this.facturacionPedidosCerrados;
}

public Map<Integer,String> pedidosNoEntregados(){
	Map <Integer,String> pedidosNoEntregados = new HashMap<>();
	for ( Integer clave : pedidos.keySet()) {
		Pedido p = pedidos.get(clave);
		if(p.estaCerrado() && !p.pedidoEntregado()) {
			pedidosNoEntregados.put(p.getNroPedido(),p.devolverNombreCliente());
		}
	}
	return pedidosNoEntregados;
}


public boolean hayTransportesIdenticos() {
	boolean sonIdenticos = false;
	for ( String patente : transportes.keySet()) {
		Transporte T = transportes.get(patente);
		for (String patente2 : transportes.keySet()) {
			Transporte T2 = transportes.get(patente2);
			if (T.estaCargado() && T2.estaCargado()) {
				if (compararTransportes(T,T2)) {
				sonIdenticos = sonIdenticos || compararCargaDeTransportes(T,T2);
				}
			}
			
		}
	}
	return sonIdenticos;
}

////////////////////////////////////////////Metodos Auxiliares///////////////////////////////////////////

public static int generarCodigoDePedido() {
    return pedidos.size();
  }

public static Pedido obtenerPedido(int nroPedido) {
	Pedido p = pedidos.get(nroPedido);
	return p;
}


Transporte obtenerTransporte(String patente) {
	Transporte T= transportes.get(patente);
	return T;
}

 void cargarPaquetesAlTransporte(Transporte transporte) {
		for (Integer clave : pedidos.keySet()) {
			Pedido p = pedidos.get(clave);
			if (p.estaCerrado() && !p.pedidoEntregado()) {
				for(int i= 0; i < p.cantidadDePaquetes() ; i++) {
					transporte.cargarPaquetes(p.devolverPaquete(i));
				}
				
			}
		}
	transporte.cambiarEstado();	
	 }

 boolean compararTransportes(Transporte T1, Transporte T2) {
	if (T1.devolverClase().equals(T2.devolverClase()) && (T1.devolverPatente().equals(T2.devolverPatente() ) ) == false ) {
		return true;
	}
	else {
		return false;
	}
}

 boolean compararCargaDeTransportes(Transporte T1, Transporte T2) {
	 boolean mismaCarga = true;
	if( T1.paquetesCargados.size() == T2.paquetesCargados.size() ) {
		for(int i= 0; i < T1.paquetesCargados.size(); i++) {
			Paquete paqT1= T1.devolverPaquete(i);
			mismaCarga = mismaCarga && compararPaqueteEnArray(paqT1, T2.paquetesCargados);
		}
	}
	return mismaCarga;
 }

boolean compararPaqueteEnArray(Paquete paq1, ArrayList<Paquete> paquetes) {
	boolean estaElPaquete= false;
	for (int i= 0 ; i < paquetes.size() ; i ++) {
		estaElPaquete = estaElPaquete || compararPaquetes(paq1, paquetes.get(i));
	}
	return estaElPaquete;
}

 boolean compararPaquetes(Paquete paq1, Paquete paq2) {
	 if(paq1.precio == paq2.precio && paq1.volumen == paq2.volumen && paq1.consultarTipo().equals(paq2.consultarTipo())) {
		 return true;
		}
	 else {
		 return false;
	 }
 }


@Override
public String toString() {
	 StringBuilder st= new StringBuilder();
	 st.append("EmpresaAmazing - CUIT [ ");
	 st.append(cuit);
	 st.append(" ] ");
	 return st.toString();
 }
}