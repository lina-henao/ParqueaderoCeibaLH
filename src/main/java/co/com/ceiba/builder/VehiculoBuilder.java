package co.com.ceiba.builder;


import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;



public class VehiculoBuilder {
	
	private VehiculoBuilder() {
	}
	
	public static VehiculoEntity convertirVehiculoEntidad(Vehiculo vehiculo){
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		if (vehiculo instanceof Carro){
			vehiculoEntity.setTipo("Carro");		
		}
		if (vehiculo instanceof Moto){
			vehiculoEntity.setTipo("Moto");
		}
		
		return vehiculoEntity;
				
	}
		
	public static Vehiculo convertirVehiculoADominio(VehiculoEntity vehiculoEntity){
		if (vehiculoEntity == null){
			return null;
		}
		Vehiculo vehiculo = null;
			if (vehiculoEntity.getTipo().equals("Carro")){
				vehiculo = new Carro(vehiculoEntity.getPlaca());
				vehiculo.setTipo(vehiculoEntity.getTipo());
			 }else {
				 vehiculo = new Moto(vehiculoEntity.getPlaca(), vehiculoEntity.getCilindraje());
				 vehiculo.setTipo(vehiculoEntity.getTipo());
			 }
		return vehiculo;
	}



}
