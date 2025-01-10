package Amazing;

import java.util.ArrayList;

public class TransporteCamion extends Transporte {
	private double adicXPaq;
	
	
	
	public TransporteCamion(String patente, int volumenMaximo, int valorViaje, int adicXPaq) {
		super(patente, volumenMaximo,valorViaje);
		this.adicXPaq = adicXPaq;
		clase = "camion";
		
	}
	
	@Override
	public void cargarPaquetes(Paquete paquete) {
		if (paquete.consultarTipo().equals("especial") && paquete.consultarVolumen() > 2000 && volumenDisponible > paquete.consultarVolumen() && !paquete.consultarEntregado()) {
			paquetesCargados.add(paquete);
			paquete.cambiarEstado();
			volumenDisponible -= paquete.consultarVolumen();
		}
		
	}
	
	@Override
	public double calcularCostoEntrega() {
		valorViaje += (paquetesCargados.size() * adicXPaq);
		return valorViaje;
	}
	@Override
	public String devolverClase() {
		return this.clase;
	}
}
