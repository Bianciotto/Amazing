package Amazing;

public class PaqueteEspecial extends Paquete {
	private int valorAdicionalXVolumen;
	private int porcentajeAdicional;
	
	public PaqueteEspecial(int pedidoRelacionado, int volumen,int precio, int porcentaje,int adicional) {
		super(pedidoRelacionado, volumen,precio);
		this.porcentajeAdicional= porcentaje;
		this.valorAdicionalXVolumen= adicional;
		tipo = "especial";
	}
	@Override
	public double calcularCosto() {
		if (volumen < 3000) {
			return precio + ((porcentajeAdicional * precio)/100);
		}
		else if (volumen >= 3000 && volumen <= 5000  ){
			return precio +  ((porcentajeAdicional * precio)/100) + valorAdicionalXVolumen;
		}
		else {
			return precio +  ((porcentajeAdicional * precio)/100) + (valorAdicionalXVolumen * 2) ;
		}
	}
	@Override
	public String consultarTipo() {
		return this.tipo;
	}
	
}
