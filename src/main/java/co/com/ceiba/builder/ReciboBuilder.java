package co.com.ceiba.builder;

import co.com.ceiba.dominio.ReciboParqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.ReciboEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;


public class ReciboBuilder {


	public static ReciboParqueadero convertirADominioRecibo(ReciboEntity reciboEntity) {
		ReciboParqueadero recibo = null;
		if(reciboEntity != null){
			Vehiculo vehiculo =VehiculoBuilder.convertirVehiculoADominio(reciboEntity.getVehiculo());
			recibo = new ReciboParqueadero(vehiculo, reciboEntity.getFechaingreso(), reciboEntity.getFechasalida(),reciboEntity.getValor());
		}
			return recibo;
	}
	
		public static ReciboEntity convertirAEntityRecibo(ReciboParqueadero recibo){
			VehiculoEntity vehiculo = VehiculoBuilder.convertirVehiculoEntidad(recibo.getVehiculo());
			ReciboEntity reciboEntity = new ReciboEntity();
			reciboEntity.setVehiculo(vehiculo);
			reciboEntity.setFechaingreso(recibo.getFechaingreso());
			reciboEntity.setFechasalida(recibo.getFechasalida());
			reciboEntity.setValor(recibo.getValor());
			return reciboEntity;
			
			
		}

}

	


  