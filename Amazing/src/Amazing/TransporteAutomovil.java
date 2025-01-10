package Amazing;

import java.util.ArrayList;

public class TransporteAutomovil extends Transporte {
	private int maxPaquetes;
	
	
	
	
	public TransporteAutomovil(String patente, int volumenMaximo, int valorViaje, int maxPaquetes) {
		super(patente, volumenMaximo,valorViaje);
		this.maxPaquetes = maxPaquetes;
		clase = "automovil";
		
	}
	
	@Override
	public void cargarPaquetes(Paquete paquete) {
		if(paquete.consultarTipo().equals("ordinario") && paquetesCargados.size() <= maxPaquetes && paquete.consultarVolumen() < 2000 && !paquete.consultarEntregado()) {
			paquetesCargados.add(paquete);
			paquete.cambiarEstado();
		}			
	}
	
	@Override
	public double calcularCostoEntrega() {
		return valorViaje;
	}
	
	@Override
	public String devolverClase() {
		return this.clase;
	}
}