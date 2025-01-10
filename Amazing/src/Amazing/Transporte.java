package Amazing;

import java.util.ArrayList;

public abstract class Transporte {
	private String patente;
	private int volumenMaximo;
	protected int volumenDisponible;
	protected int valorViaje;
	protected ArrayList<Paquete> paquetesCargados;
	private boolean cargado;
	protected String clase;
	
	
	public Transporte(String patente, int volumenMaximo, int valorViaje) {
		this.patente = patente;
		this.volumenMaximo = volumenMaximo;
		this.volumenDisponible = volumenMaximo;
		this.valorViaje = valorViaje;
		this.cargado = false;
		this.paquetesCargados = new ArrayList<Paquete>();
	}
	
	
	String devolverPatente() {
		return this.patente;
	}
	
	public abstract String devolverClase();
	
	public abstract void cargarPaquetes(Paquete paquete);
	
	
	public abstract double calcularCostoEntrega() ;
	
	void cambiarEstado() {
		this.cargado = true;
	}
	
	boolean estaCargado() {
		return this.cargado;
	}
	

	int consultarValorViaje() {
		return valorViaje;
	}
	
	public String toString() {
		StringBuilder st = new StringBuilder();
		if (paquetesCargados.isEmpty()) {
			return "";
		}else {
		for(Paquete paq : paquetesCargados) {
			st.append(paq.toString());
			st.append("\n");
		}
		return st.toString();
		}
	}
	
	Paquete devolverPaquete(int i) {
		Paquete paq = paquetesCargados.get(i) ;
		return paq;
	}
}
