package co.com.ceiba.reglas;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.excepcion.ServicioParqueoException;

@Component
@Order(value=1)
public class ReglasCamposRequeridos implements IReglasIngresoParqueadero {


@Override
public boolean validar(Vehiculo vehiculo, Parqueadero parqueadero) {
		if (vehiculo.getPlaca() == null  || vehiculo.getPlaca().isEmpty() || vehiculo.getPlaca().length() < 6) {
			throw new ServicioParqueoException("PLACA debe de ser de 6 DÍGITOS");
		}

		if (vehiculo instanceof Moto && ((Moto) vehiculo).getCilindraje() == 0) {
			throw new ServicioParqueoException("CILINDRAJE no puede estar vacio");
				
	}
		validartipoplaca(vehiculo);
		return true;
}

	private void validartipoplaca(Vehiculo vehiculo){
	 String ultimo = vehiculo.getPlaca().substring(vehiculo.getPlaca().length()-1);
		if (vehiculo instanceof Moto && ultimo.matches("[0-9]") ) {
			throw new ServicioParqueoException(" La placa ingresada no pertenece a una moto");
		}
	
		if (vehiculo instanceof Carro && ultimo.matches("[a-zA-Z]") ) {
			throw new ServicioParqueoException(" La placa ingresada no pertenece a una Carro");
		}

}
}
