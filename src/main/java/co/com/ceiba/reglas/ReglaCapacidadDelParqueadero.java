package co.com.ceiba.reglas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.excepcion.ServicioParqueoException;
import co.com.ceiba.repositorio.IRepositorioRecibo;

@Component
@Order(value=2)
public class ReglaCapacidadDelParqueadero implements IReglasIngresoParqueadero {
	
	
	private static final int CANTIDAD_MINIMA = 0;
	IRepositorioRecibo repositorioRecibo; 
	
	@Autowired
	public ReglaCapacidadDelParqueadero(IRepositorioRecibo repositorioRecibo){
		this.repositorioRecibo = repositorioRecibo;
	}
	
	@Override
	public boolean validar(Vehiculo vehiculo, Parqueadero parqueadero) {
		boolean validarSiNoyHayCapacidad = validarSiHayCapacidad( vehiculo,parqueadero);
		if(validarSiNoyHayCapacidad){
			if (vehiculo instanceof Carro) {
				throw new ServicioParqueoException("No Hay Capacidad Para carros");
			} else if (vehiculo instanceof Moto) {
				throw new ServicioParqueoException("No hay Capacidad para Motos");
			}
		}
		return false;
	}
	
	
	private boolean validarSiHayCapacidad(Vehiculo vehiculo, Parqueadero parqueadero){
		if (vehiculo instanceof Moto) {
			int celdasocupadasMoto = obtenerEspacionOcupadosMoto();
			int celdasdisponiblesMoto = obtenerEspacionDisponiblesMoto(parqueadero, celdasocupadasMoto);
			return celdasdisponiblesMoto == CANTIDAD_MINIMA;
		} else if (vehiculo instanceof Carro) {
			int celdasocupadascarro = obtenerEspacionOcupadosCarro();
			int celdasdisponiblescarro = obtenerEspacionDisponiblesCarro(parqueadero, celdasocupadascarro);
			return celdasdisponiblescarro == CANTIDAD_MINIMA;
		}
		return false;
	}
	private int obtenerEspacionOcupadosCarro() {
		return  repositorioRecibo.obtenerEspacionOcupadosCarro().intValue() ;
	}
	
	private int obtenerEspacionDisponiblesCarro(Parqueadero parqueadero, int celdasocupadascarro) {
		return parqueadero.getCapacidadCarros()-celdasocupadascarro;
	}

	private int obtenerEspacionOcupadosMoto() {
		return  repositorioRecibo.obtenerEspacionOcupadosMoto().intValue() ;
	}
	
	private int obtenerEspacionDisponiblesMoto(Parqueadero parqueadero, int celdasocupadasMoto) {
		return parqueadero.getCapacidadMoto()-celdasocupadasMoto;
	}
	
}

