package ar.edu.TPPOI;

public class Almacenar implements Accion{

	public void ejecutarLuegoDeLaBusqueda(BusquedaHecha unaBusqueda, Terminal unaTerminal){
		
		unaTerminal.agregarBusquedaHecha(unaBusqueda);
	}
	
}
