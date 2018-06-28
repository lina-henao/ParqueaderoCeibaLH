package co.com.ceiba.reglas;

import java.util.Calendar;

import co.com.ceiba.dominio.Vehiculo;


public interface IReglasSalidaParqueadero {
	
	public double calcular(Vehiculo vehiculo,Calendar fechaEntrada, Calendar fechaSalida);
		

}
