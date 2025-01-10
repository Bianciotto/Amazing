package Amazing;

import java.util.ArrayList;

public class TransporteUtilitario extends Transporte {
	private double valorExtra;

	
	public TransporteUtilitario(String patente, int volumenMaximo, int valorViaje, int valorExtra) {
		super(patente, volumenMaximo,valorViaje);
		this.valorExtra = valorExtra;
		clase = "utilitario";
		volumenDisponible = volumenMaximo;
	}
	
	@Override
	public void cargarPaquetes( Paquete paquete) {
		if (volumenDisponible > paquete.consultarVolumen() && !paquete.consultarEntregado()) {
			paquetesCargados.add(paquete);
			paquete.cambiarEstado();
			volumenDisponible -= paquete.consultarVolumen();
			
		}
		
	}
	
	@Override
	public double calcularCostoEntrega() {
		if (paquetesCargados.size() > 3) {
			valorViaje += valorExtra;
			return valorViaje;
		}
		else {
			return valorViaje;
		}
	}
	@Override
	public String devolverClase() {
		return this.clase;
	}
}
