package com.co.ceiba.reglasTestUnitarias;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.Parqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.excepcion.ServicioParqueoException;
import co.com.ceiba.reglas.ReglaCapacidadDelParqueadero;
import co.com.ceiba.reglas.ReglaMotoDeAltoCilindraje;
import co.com.ceiba.reglas.ReglaPrimerLetraDeLaPlaca;
import co.com.ceiba.repositorio.IRepositorioRecibo;
import co.com.ceiba.testdatabuilder.ParqueaderoTestDataBuilder;


public class ReglasParqueaderoTest {
	
	
IRepositorioRecibo  repositorioRecibo;
Parqueadero parqueadero = new ParqueaderoTestDataBuilder().build();


@Test 
 public void laPrimeraLetraEsATest(){
	//Arrange=
	ReglaPrimerLetraDeLaPlaca reglaPrimerLetraDeLaPlaca = new  ReglaPrimerLetraDeLaPlaca();
	//Act
	Vehiculo vehiculo = new Vehiculo("AVD125");
	////Assert
	try{
		reglaPrimerLetraDeLaPlaca.validar(vehiculo, parqueadero);
		fail();	
	}catch(ServicioParqueoException e){
		Assert.assertEquals("Las Placas iniciadas con A no pueden ingresar",e.getMessage());
		
	}
	
}

@Test
public void nohayCapacidadEnelParqueaderoCarro(){
	
	//Arrange
	Parqueadero parqueadero = new ParqueaderoTestDataBuilder().concapacidadparacarro(0).build();
	repositorioRecibo = Mockito.mock(IRepositorioRecibo.class);
	ReglaCapacidadDelParqueadero reglaCapacidadDelParqueadero = new ReglaCapacidadDelParqueadero(repositorioRecibo);
	//Act
	Vehiculo carro 	= new Carro("SDF103");
	//Assert
	try {
		reglaCapacidadDelParqueadero.validar(carro, parqueadero);
		fail();
	} catch (ServicioParqueoException e) {
		Assert.assertEquals("No Hay Capacidad Para carros",e.getMessage());
	}
		
}

@Test
public void nohayCapacidadEnelParqueaderoMoto(){
	
	//Arrange
	Parqueadero parqueadero = new ParqueaderoTestDataBuilder().concapacidadparaMoto(0).build();
	repositorioRecibo = Mockito.mock(IRepositorioRecibo.class);
	ReglaCapacidadDelParqueadero reglaCapacidadDelParqueadero = new ReglaCapacidadDelParqueadero(repositorioRecibo);
	//Act
	Vehiculo Moto 	= new Moto("SDF103", 0);
	//Assert
	try {
		reglaCapacidadDelParqueadero.validar(Moto, parqueadero);
		fail();
	} catch (ServicioParqueoException e) {
		Assert.assertEquals("No hay Capacidad para Motos",e.getMessage());
	}
		
}
@Test 
public void altocCilindrajeMoto (){
	//Arrange 
	ReglaMotoDeAltoCilindraje reglaMotoDeAltoCilindraje = new ReglaMotoDeAltoCilindraje();
	//Act
	Vehiculo moto = new Moto("CFG125", 501);
	//Assert
	assertTrue(reglaMotoDeAltoCilindraje.validar(moto, parqueadero));
	
 }

}
