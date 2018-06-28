package co.com.ceiba.testdatabuilder;

import co.com.ceiba.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	static private final String PLACA ="GMW303";
	String placa;
	
	public VehiculoTestDataBuilder() {
	this.placa=PLACA;
		
	}
	
	public VehiculoTestDataBuilder conPlca(String placa){
		this.placa = placa;
		return this;
		
	}
	
	public Vehiculo build(){
		return new Vehiculo(this.placa);
		
		
	}

}
