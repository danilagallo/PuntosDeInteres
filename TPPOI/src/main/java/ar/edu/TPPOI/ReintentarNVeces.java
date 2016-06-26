package ar.edu.TPPOI;

public class ReintentarNVeces extends ManejoDeResultado{

	private Integer veces;

	public Integer getVeces() {
		return veces;
	}

	public void setVeces(Integer veces) {
		this.veces = veces;
	}
	
	
	public void ejecutarEnCasoDeFalla(Proceso unProceso) {
		for (Integer i=0; i<this.veces; i++){
			unProceso.ejecutar();
		}
	}

}
