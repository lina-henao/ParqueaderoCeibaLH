package co.com.ceiba.reglas;

import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;

public class ReglaMotoDeAltoCilindraje {
	
	private static final int LIMITE_CILINDRAJE = 500;
	
	public boolean validar(Vehiculo vehiculo,Parqueadero parqueadero){
		Moto moto = (Moto)vehiculo;
		int limitedecilingraje =LIMITE_CILINDRAJE;
		return (moto.getCilindraje()>limitedecilingraje);
		
		
	}
}
