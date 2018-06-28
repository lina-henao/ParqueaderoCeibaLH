package co.com.ceiba.persistencia.repositorio;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import co.com.ceiba.builder.VehiculoBuilder;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.repositorio.IRepositorioVehiculo;

@Repository
public class RepositorioVehiculoPersistente  implements IRepositorioVehiculo{
	private static final String  VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	private static final String  PLACA = "placa";
	
	// @Autowired
	 Parqueadero parqueadero;
	 private EntityManager entityManager;
	 
	 public RepositorioVehiculoPersistente(EntityManager entityManager){
	 super();
	 this.entityManager=entityManager;
	
	 
	 }
	
	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		VehiculoEntity vehiculoEntity = obtenerVehiculoEntityPorPlaca(placa);
		return vehiculoEntity != null ? VehiculoBuilder.convertirVehiculoADominio(vehiculoEntity) : null ;
		
	}
	
	
	@Override
	public void insertar(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity=buildVehiculoEntity(vehiculo);
		entityManager.persist(vehiculoEntity);
	}
	
	private  VehiculoEntity buildVehiculoEntity(Vehiculo vehiculo){
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		if (vehiculo instanceof Moto){
			vehiculoEntity.setCilindraje(((Moto)vehiculo).getCilindraje());
			vehiculoEntity.setTipo("Moto");
		}else {
			vehiculoEntity.setCilindraje(0);
			vehiculoEntity.setTipo("Carro");
			}
				return vehiculoEntity;
		
	}
	
	@Override
	public  VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa) {
		Query query=entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);
		@SuppressWarnings("rawtypes")
		List listacarros=query.getResultList();
		return listacarros.isEmpty() ? null : (VehiculoEntity)listacarros.get(0) ;
	

		
	}

}
