package co.com.ceiba.dominio;



public class Parqueadero {
	
	/**
	 * 
	 */
	
	private int capacidadCarros ;
	private int capacidadMotos  ;
	
	
	public int getCapacidadMoto() {
		return capacidadMotos;
	}

	public void setCapacidadMoto(int capacidadMoto) {
		this.capacidadMotos = capacidadMoto;
	}
	
	public int getCapacidadCarros() {
		return capacidadCarros;
	}

	public void setCapacidadCarros(int capacidadCarros) {
		this.capacidadCarros = capacidadCarros;
	}
	
	public Parqueadero(int capacidadCarros, int capacidadMotos) {
		super();
		this.setCapacidadCarros(capacidadCarros);
		this.setCapacidadMoto(capacidadMotos);
			
	}

	
	
}
