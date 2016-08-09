package ar.edu.TPPOI;

import java.util.List;
import java.time.LocalDate;
import excepciones.NoSePuedeDesactivarException;
import excepciones.YaExisteUnaAccionDeEseTipoException;

import java.util.ArrayList;

public class Terminal {
	
	MapaPOI mapa;
	List<BusquedaHecha> busquedasHechas = new ArrayList<>();
	List<Accion> acciones = new ArrayList<>();
	GeneradorDeReportes reporte;
	
	//-------------------------------------------------------------
	
	public void setMapa(MapaPOI unMapa){
		this.mapa = unMapa;
	}
	
	public MapaPOI getMapa(){
		return this.mapa;
	}
	
	public void activarAccion(Accion unaAccion){
		if (this.yaExisteAccionDeEseTipo(unaAccion)){
			throw new YaExisteUnaAccionDeEseTipoException("Ya existe una accion de ese tipo") ;
		}
		else{
			this.acciones.add(unaAccion);
		}
	}
	
	public void desactivarAccion(Accion unaAccion){
		if (!acciones.contains(unaAccion)){
			throw new NoSePuedeDesactivarException("No se puede desactivar");
		}
		else{
			this.acciones.remove(unaAccion);
		}
	}
	
	public boolean yaExisteAccionDeEseTipo(Accion unaAccion){
		return acciones.stream().anyMatch(unaA -> unaA.getClass().equals(unaAccion.getClass()));
	}

	
	public void agregarBusquedaHecha(BusquedaHecha unaBusquedaHecha){
		this.busquedasHechas.add(unaBusquedaHecha);
	}
	
	public List<BusquedaHecha> getBusquedasHechas(){
		return this.busquedasHechas;
	}
	
	public List<Accion> getAcciones(){
		return this.acciones;
	}
	
	public GeneradorDeReportes getReporte() {
		return reporte;
	}

	public void setReporte(GeneradorDeReportes reporte) {
		this.reporte = reporte;
	}
	
	//--------------------------------------------------------------
	 
	public List<POI> buscar(String unTextoLibre){
		BusquedaHecha unaBusqueda = new BusquedaHecha();
		unaBusqueda.datosDeLaBusqueda(unTextoLibre,this);
		this.acciones.forEach(unaAccion -> unaAccion.luegoDeLaBusqueda(unaBusqueda, this));
		return this.getMapa().buscar(unTextoLibre);
	}

	public int obtenerReporte(LocalDate unaFecha){
		return this.getReporte().generarReportePorFecha(unaFecha, this.getBusquedasHechas());   
	}
	public List<Integer> generarReportePorBusqueda(){
		return this.getReporte().generarReportePorBusqueda(this.getBusquedasHechas());
	}

}
