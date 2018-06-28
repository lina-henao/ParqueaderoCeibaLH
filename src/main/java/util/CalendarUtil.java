package util;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

 public class  CalendarUtil {
	 
	

	private static final double SEGUNDOS_EN_UNA_HORA = 3600.0;

	public double calcularDiferenciaHoras(Calendar fechaIngreso, Calendar fechaSalida) {		
		long cantidadhoras= (ChronoUnit.SECONDS.between(fechaIngreso.toInstant(), fechaSalida.toInstant()));
		double cantidadhorasdecimal = (double)cantidadhoras/SEGUNDOS_EN_UNA_HORA;				
		double totalhorasaproximadasconminutos =cantidadhorasdecimal;
		totalhorasaproximadasconminutos = Math.ceil(totalhorasaproximadasconminutos);		
		return totalhorasaproximadasconminutos;
				
	}
	


}
