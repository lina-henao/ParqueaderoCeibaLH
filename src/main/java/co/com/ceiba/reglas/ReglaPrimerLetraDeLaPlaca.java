package co.com.ceiba.reglas;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.excepcion.ServicioParqueoException;

@Component
public class ReglaPrimerLetraDeLaPlaca implements IReglasIngresoParqueadero {

	@Override
	public boolean validar(Vehiculo vehiculo, Parqueadero parqueadero) {
		String placa = vehiculo.getPlaca();
		Calendar fechaActual = Calendar.getInstance();
		if (placaIniciaporA(placa)&&noEsUnDiaHabil(fechaActual))
			throw new  ServicioParqueoException("Las Placas iniciadas con A no pueden ingresar");
		return true;
	}
	private boolean noEsUnDiaHabil(Calendar fechaActual ){
		return (fechaActual.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY||
				fechaActual.get(Calendar.DAY_OF_WEEK)!= Calendar.MONDAY) ;
    }
	
	private boolean placaIniciaporA(String placa){
		return placa.startsWith("a") || placa.startsWith("A");
	}
	
}
