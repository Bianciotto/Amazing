package Amazing;

public abstract class Paquete {
	
	private int IDPaquete;
	protected int volumen;
	protected int precio;
	private boolean fueEntregado;
	protected String tipo;
	protected String direccion;
	protected int pedidoRelacionado;
	
	public Paquete( int pedidoRelacionado, int volumen, int precio) {
		this.pedidoRelacionado = pedidoRelacionado;
		this.volumen = volumen;
		this.precio = precio;
		this.fueEntregado = false;
	}
	

	void colocarIDPaquete(int ID) {
		this.IDPaquete = ID;
	}
	
	int consultarIDPaquete() {
		return this.IDPaquete;
	}
	
	boolean consultarEntregado() {
		return fueEntregado;
	}
	
	int consultarVolumen() {
		return this.volumen;
	}
	
	public abstract String consultarTipo();
	
	void cambiarEstado() {
		this.fueEntregado = true;
	}
	
	void direccionDeEntrega(String direccion) {
		this.direccion = direccion;
	}
	
	public abstract double calcularCosto();

	public String toString() {
		StringBuilder st = new StringBuilder();
		st.append(" + [ ");
		st.append(pedidoRelacionado);
		st.append(" - ");
		st.append(IDPaquete);
		st.append(" ] ");
		st.append(direccion);
		return st.toString();
	}
}
