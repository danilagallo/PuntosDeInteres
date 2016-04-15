package ar.edu.TPPOI;

public class ParadaDeColectivo extends POI {
	private String linea;
	
	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
		this.addPalabraClave(linea);
	}

	public boolean estasCercaDe (Punto unaCoordenada){
		return this.estasAMenosDeXMetrosDe (100,unaCoordenada);
	}

}
