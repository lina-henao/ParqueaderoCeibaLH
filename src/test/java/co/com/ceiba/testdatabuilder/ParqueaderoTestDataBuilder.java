package co.com.ceiba.testdatabuilder;

import co.com.ceiba.dominio.Parqueadero;

public class ParqueaderoTestDataBuilder {
	
	private final int CAPACIDAD_PARA_CARROS = 20 ;
	private final int CAPACIDAD_PARA_MOROS = 10;
	private int capacidadCarro;
	private int capacidadMotos;
	
	public ParqueaderoTestDataBuilder() {
		
		this.capacidadCarro=CAPACIDAD_PARA_CARROS;
		this.capacidadMotos=CAPACIDAD_PARA_MOROS;
				
	}
	
	public ParqueaderoTestDataBuilder concapacidadparacarro (int capacidadCarro){
		this.capacidadCarro=capacidadCarro;
		return this;
			
	}
	
	public ParqueaderoTestDataBuilder concapacidadparaMoto (int capacidadMoto){
		this.capacidadMotos=capacidadMoto;
		return this;		
	}
	
	public Parqueadero build (){
		  return new Parqueadero(this.capacidadCarro, this.capacidadMotos) ;
		
		
	}
		

}
