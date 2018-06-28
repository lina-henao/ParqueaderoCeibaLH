package co.com.ceiba.reglas;

import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;

public interface  IReglasIngresoParqueadero {
	
	public boolean validar(Vehiculo vehiculo, Parqueadero parqueadero);

}
