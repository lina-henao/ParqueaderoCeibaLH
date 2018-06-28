package co.com.ceiba.repositorio;

import java.util.List;

import co.com.ceiba.dominio.ReciboParqueadero;
import co.com.ceiba.dominio.Vehiculo;



public interface IRepositorioRecibo {

	Vehiculo obtenerVehiculoEnParqueaderoPorPlaca(String placa);
	ReciboParqueadero obtenerPorPlaca(String placa);
	void insertar(ReciboParqueadero recibo);
	Long obtenerEspacionOcupadosCarro();
	Long obtenerEspacionOcupadosMoto();
	ReciboParqueadero obtenerRecibo(String placa);
	void actualizarRecibo(ReciboParqueadero recibo);
	List<ReciboParqueadero> obtenerListaVehiculosEnParqueadero();	
	
	 

}
