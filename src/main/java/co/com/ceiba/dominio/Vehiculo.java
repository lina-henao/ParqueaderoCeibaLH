package co.com.ceiba.dominio;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehiculo implements Serializable {
	
	private String placa;
	private String tipo; 
	
	
	public Vehiculo(){
		
	}
	
	public Vehiculo (String placa){
		super();
		this.placa =placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
