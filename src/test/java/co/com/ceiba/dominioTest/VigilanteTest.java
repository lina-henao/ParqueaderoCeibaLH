package co.com.ceiba.dominioTest;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ParqueaderoCeibaLhApplication;
import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.ReciboParqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.repositorio.IRepositorioRecibo;



@RunWith(SpringRunner.class)
@SpringBootTest (classes = {ParqueaderoCeibaLhApplication.class})
@DataJpaTest
public class VigilanteTest {
	
	@Autowired
	IRepositorioRecibo repositorioRecibo;
	@Autowired
	Vigilante vigilante;
	private Vehiculo vehiculo;
	
	@Test
	public void ingresarCarroTest() {
		//Arrange
		vehiculo  = new Carro ("GMW303");
		vigilante.ingresarUnVehiculo(vehiculo);
		// assert
		Assert.assertNotNull(repositorioRecibo.obtenerPorPlaca(vehiculo.getPlaca()));
		
	}
	
	@Test
	
	public void  ingresarMotoTest (){
		//Arrange
		vehiculo = new Moto ("GMW303", 500);
		vigilante.ingresarUnVehiculo(vehiculo);
		//Assert
		Assert.assertNotNull(repositorioRecibo.obtenerPorPlaca(vehiculo.getPlaca()));
		
		
	}
	@Test
	   public void ingresarRetirarMoto(){
		//Arrange
		vehiculo = new Moto("GMW303",501);
		vigilante.ingresarUnVehiculo(vehiculo);
		ReciboParqueadero recibo = vigilante.darSalidaVehiculo(vehiculo);
		//Assert
		Assert.assertNotNull(recibo.getVehiculo().getPlaca(),"FCM30A");
	
	}
	
	  public void ingresarRetirarCarro(){
			//Arrange
			vehiculo = new Carro("GMW303");
			vigilante.ingresarUnVehiculo(vehiculo);
			ReciboParqueadero recibo = vigilante.darSalidaVehiculo(vehiculo);
			//Assert
			Assert.assertNotNull(recibo.getVehiculo().getPlaca(),"GMW303");
			
	

	  }
}
