package co.com.ceiba.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.builder.ReciboBuilder;
import co.com.ceiba.builder.VehiculoBuilder;
import co.com.ceiba.dominio.ReciboParqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.excepcion.ServicioParqueoException;
import co.com.ceiba.persistencia.entidad.ReciboEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.repositorio.IRepositorioRecibo;
import co.com.ceiba.repositorio.IRepositorioVehiculo;


@Repository
public class RepositorioReciboPersistente implements IRepositorioRecibo {

	private static final String PLACA = "placa";
	private static final String TIPO_CARRO = "tipo_carro";
	private static final String TIPO_MOTO = "tipo_moto";
	private static final String RECIBO_BY_PLACA = "Recibo.findByPlaca";
	private static final String ESPACIOS_CARRO_DISPONIBLES = "Recibo.findCellsCars";
	private static final String ESPACIOS_MOTO_DISPONIBLES = "Recibo.findCellsMotos";
	private static final String USOPARQUEADERO = "Recibo.findAll";

	private EntityManager entityManager;
	@Autowired
	IRepositorioVehiculo repositorioVehiculo;

	public RepositorioReciboPersistente(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public ReciboParqueadero obtenerPorPlaca(String placa) {
		ReciboEntity reciboEntity = obtenerReciboEntityPorPlaca(placa);
		try {
			return new ReciboParqueadero(VehiculoBuilder.convertirVehiculoADominio(reciboEntity.getVehiculo()),
					reciboEntity.getFechaingreso(), reciboEntity.getFechasalida(), reciboEntity.getValor());

		} catch (Exception e) {
			
			throw new ServicioParqueoException("el vehiculo no se encuentra ingresado");
		}
	}

	@Override
	public Vehiculo obtenerVehiculoEnParqueaderoPorPlaca(String placa) {
		ReciboEntity reciboEntity = obtenerReciboEntityPorPlaca(placa);
		return VehiculoBuilder.convertirVehiculoADominio(reciboEntity != null ? reciboEntity.getVehiculo() : null);
	}

	public ReciboEntity obtenerReciboEntityPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(RECIBO_BY_PLACA);
		query.setParameter(PLACA, placa);
		@SuppressWarnings("rawtypes")
		List listarecibo = query.getResultList();
		query.setMaxResults(1);
		return !listarecibo.isEmpty() ? (ReciboEntity) listarecibo.get(0) : null;

	}

	@Override
	public void insertar(ReciboParqueadero recibo) {
		ReciboEntity reciboEntity = buildReciboEntity(recibo);
		entityManager.persist(reciboEntity);

	}

	private ReciboEntity buildReciboEntity(ReciboParqueadero recibo) {
		VehiculoEntity vehiculo = repositorioVehiculo.obtenerVehiculoEntityPorPlaca(recibo.getVehiculo().getPlaca());
		ReciboEntity reciboEntity = new ReciboEntity();
		reciboEntity.setVehiculo(vehiculo);
		reciboEntity.setFechaingreso(recibo.getFechaingreso());
		reciboEntity.setFechasalida(recibo.getFechasalida());
		reciboEntity.setValor(recibo.getValor());

		return reciboEntity;
	}

	@Override
	public Long obtenerEspacionOcupadosCarro() {
		Query query = entityManager.createNamedQuery(ESPACIOS_CARRO_DISPONIBLES);
		query.setParameter(TIPO_CARRO, "Carro");
		return (Long) (query.getSingleResult());
	}

	public Long obtenerEspacionOcupadosMoto() {
		Query query = entityManager.createNamedQuery(ESPACIOS_MOTO_DISPONIBLES);
		query.setParameter(TIPO_MOTO, "Moto");
		return (Long) (query.getSingleResult());
	}

	public ReciboParqueadero obtenerRecibo(String placa) {
		return ReciboBuilder.convertirADominioRecibo(obtenerReciboEntityPorPlaca(placa));
	}

	@Override
	public void actualizarRecibo(ReciboParqueadero recibo) {
		ReciboEntity reciboEntity = obtenerReciboEntityPorPlaca(recibo.getVehiculo().getPlaca());
		reciboEntity.setValor(recibo.getValor());
		reciboEntity.setFechasalida(recibo.getFechasalida());
	}

	@Override
	public List<ReciboParqueadero> obtenerListaVehiculosEnParqueadero() {
		List<ReciboEntity> listaEntity = listaRecibos();
		if (listaEntity==null){
			throw new ServicioParqueoException("No hay Vehiculos en el parqueadero");
		}
		else {
			List<ReciboParqueadero>listaRecibos= new ArrayList<>();
			for (int i= 0; i < listaEntity.size();++i){
			ReciboParqueadero recibo = ReciboBuilder.convertirADominioRecibo(listaEntity.get(i));
			listaRecibos.add(recibo);
			}
			return listaRecibos;
		}
					
	}

	private List<ReciboEntity> listaRecibos() {

		Query query = entityManager.createNamedQuery(USOPARQUEADERO);
		@SuppressWarnings("unchecked")
		List<ReciboEntity> resultList = query.getResultList();
		if (resultList == null) {
			throw new ServicioParqueoException("No hay Vehiculos en el parqueadero");
		}
		return !resultList.isEmpty() ? resultList : null;
	}

}
