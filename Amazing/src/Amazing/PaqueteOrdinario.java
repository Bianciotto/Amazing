package Amazing;

public class PaqueteOrdinario extends Paquete {
	private int costoDeEnvio;
	
	public PaqueteOrdinario(int pedidoRelacionado, int volumen, int precio, int costoEnvio) {
		super(pedidoRelacionado, volumen, precio);
		this.costoDeEnvio = costoEnvio;
		tipo = "ordinario";
	}
	
	@Override
	public double calcularCosto() {  
		return precio + costoDeEnvio;
	}
	
	@Override
	public String consultarTipo() {
		return this.tipo;
	}
	
}
