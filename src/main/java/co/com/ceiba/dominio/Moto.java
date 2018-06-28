package co.com.ceiba.dominio;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Moto extends Vehiculo implements Serializable {
	
	private int cilindraje;
	
	
	public Moto(){
		
	}

	public Moto(String placa, int cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}


	public int getCilindraje() {
		return cilindraje;
	}


	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

}
