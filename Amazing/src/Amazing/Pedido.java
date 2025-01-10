package Amazing;

import java.util.ArrayList;
import java.util.Iterator;


public class Pedido {
	private int codPedido;
	private String nombreCliente;
	private String direccion;
	private int DNI;
	private ArrayList<Paquete> paquetes;
	private boolean pedidoCerrado;
	private boolean entregado;
	
	public Pedido( String cliente, String direccion, int dni) {
		this.nombreCliente = cliente;
		this.direccion = direccion;
		this.DNI = dni;
		this.entregado = false;
		this.pedidoCerrado = false;
		this.paquetes = new ArrayList<Paquete>();
	}
	
	void colocarNroPedido( int codPedido) {
		this.codPedido = codPedido;
	}
	
	double calcularTotalPedido() {
		double TotalPedido = 0;
		for ( Paquete Paq : paquetes) {
			TotalPedido += Paq.calcularCosto();
		}
	return TotalPedido;
	}
	
	void cerrarPedido() {
		this.pedidoCerrado = true;
	}
	
	void agregarPaquete(Paquete paquete) {
		paquetes.add(paquete);
	}
	
	public int getNroPedido() {
		return this.codPedido;
	}
	
	boolean estaCerrado() {
		return this.pedidoCerrado;
	}
	
	String devolverDireccion() {
		return this.direccion;
	}

	
	boolean recorrerListaYEliminarPaq(int codPaquete) {
		boolean paqueteEncontrado = false;
		Iterator<Paquete> iterador = paquetes.iterator();
		while(iterador.hasNext()) {
			Paquete paq = iterador.next();
			if (paq.consultarIDPaquete() == codPaquete) {
				iterador.remove();
				paqueteEncontrado = true; ///Si encuentra el paquete, lo elimina y cambia a TRUE, sino devuelve FALSE.
			}
		}
		return paqueteEncontrado;
	}
	boolean pedidoEntregado() {
		boolean pedidoCompleto = true;
		for(Paquete Paq : paquetes) {
			pedidoCompleto = pedidoCompleto && Paq.consultarEntregado();
		}
		return pedidoCompleto;
	}
	
	void esEntregado() {
		this.entregado = true;
	}
	String devolverNombreCliente() {
		return this.nombreCliente;
	}
	
	
	Paquete devolverPaquete(int i) {
		Paquete paq = paquetes.get(i) ;
		return paq;
	}
	
	int cantidadDePaquetes() {
		return paquetes.size();
	}
}
