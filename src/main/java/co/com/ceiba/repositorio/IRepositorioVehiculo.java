package co.com.ceiba.repositorio;


import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;



public interface IRepositorioVehiculo {
	
	Vehiculo obtenerVehiculoPorPlaca(String placa);
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);	
	void insertar(Vehiculo vehiculo);
	

}
