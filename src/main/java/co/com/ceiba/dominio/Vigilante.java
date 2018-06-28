package co.com.ceiba.dominio;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.excepcion.ServicioParqueoException;
import co.com.ceiba.reglas.IReglasIngresoParqueadero;
import co.com.ceiba.reglas.IReglasSalidaParqueadero;
import co.com.ceiba.reglas.ReglaCobro;
import co.com.ceiba.repositorio.IRepositorioRecibo;
import co.com.ceiba.repositorio.IRepositorioVehiculo;


@Service
public class Vigilante {

	private List<IReglasIngresoParqueadero> reglasingreso;
			List<IReglasSalidaParqueadero> reglasSalida;
	private Parqueadero parqueadero;
	IRepositorioRecibo repositorioRecibo;
	IRepositorioVehiculo repositorioVehiculo;

	@Autowired
	public Vigilante(List<IReglasIngresoParqueadero> reglasingreso, List<IReglasSalidaParqueadero> reglasSalida,
			Parqueadero parqueadero, IRepositorioVehiculo repositorioVehiculo, IRepositorioRecibo repositorioRecibo) {
		super();
		this.reglasingreso = reglasingreso;
		this.parqueadero = parqueadero;
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioRecibo = repositorioRecibo;
		this.reglasSalida =reglasSalida;

	}

	public ReciboParqueadero ingresarUnVehiculo(Vehiculo vehiculo) {
	 Calendar fechaactual = Calendar.getInstance();
		validarReglaIngreso(vehiculo);
		insertarVehiculo(vehiculo);
		ReciboParqueadero recibo = new ReciboParqueadero(vehiculo, fechaactual);
		if (repositorioRecibo.obtenerVehiculoEnParqueaderoPorPlaca(vehiculo.getPlaca()) == null) {
			repositorioRecibo.insertar(recibo);
			return recibo;
		} else
			throw new ServicioParqueoException("El Vehiculo Ya Se Encuetra En El Parqueadero");

	}

	public void insertarVehiculo(Vehiculo vehiculo) {
		if (repositorioVehiculo.obtenerVehiculoPorPlaca(vehiculo.getPlaca()) == null) {
			repositorioVehiculo.insertar(vehiculo);
		}
	}

	private void validarReglaIngreso(Vehiculo vehiculo) {
		for (IReglasIngresoParqueadero regla : reglasingreso) {
			regla.validar(vehiculo, parqueadero);
		}
	}
	
	public ReciboParqueadero darSalidaVehiculo( Vehiculo vehiculo){
		if(repositorioRecibo.obtenerVehiculoEnParqueaderoPorPlaca(vehiculo.getPlaca())!=null){
			Calendar fechaSalida = Calendar.getInstance();
			ReglaCobro reglaCobro = new ReglaCobro();
			ReciboParqueadero recibo = repositorioRecibo.obtenerRecibo(vehiculo.getPlaca());
			recibo.setFechasalida(fechaSalida);
			recibo.setValor(reglaCobro.calcular(recibo.getVehiculo(), recibo.getFechaingreso(), recibo.getFechasalida()));
			repositorioRecibo.actualizarRecibo(recibo);
			return recibo;
		}
		throw new ServicioParqueoException("El Vehiculo No Se Encuetra En El Parqueadero");
	}
	

	
}
