package co.com.ceiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"co.com.ceiba","co.com.ceiba.dominio","co.com.ceiba.persistencia.repositorio"})
@EntityScan("co.com.ceiba.persistencia.entidad")
public class ParqueaderoCeibaLhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoCeibaLhApplication.class, args);
	}
	
	
}
