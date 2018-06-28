package co.com.ceiba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.dominio.Parqueadero;


@Configuration
public class ParqueaderoConfig {
	
	private static final int MAXIMO_CARROS = 20;
	private static final int MAXIMO_MOTOS = 10;
	
	@Bean
	public Parqueadero parqueadero(){		
		return new Parqueadero(MAXIMO_CARROS,MAXIMO_MOTOS);
	}
	

}
