package co.com.ceiba.dominio;

import java.io.Serializable;



@SuppressWarnings("serial")
public class Carro extends Vehiculo implements Serializable {

	public Carro(String placa) {
		super(placa);
	}

	public Carro(){
		
	}

	
}
