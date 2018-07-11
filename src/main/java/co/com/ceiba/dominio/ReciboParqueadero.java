package co.com.ceiba.dominio;

import java.util.Calendar;


public class ReciboParqueadero {
	private Vehiculo vehiculo;
	private Calendar fechaingreso;
	private Calendar fechasalida; 
	private double valor;
	
	
    public ReciboParqueadero (Vehiculo vehiculo, Calendar fechaingreso){
    	this.vehiculo = vehiculo;
    	this.fechaingreso =fechaingreso;
    	this.fechasalida = null;
    	this.valor=0;
    	
 	
    }
    
    public ReciboParqueadero (Vehiculo vehiculo, Calendar fechaingreso,Calendar fechasalida, double valor ){
    	this.vehiculo =vehiculo;
    	this.fechaingreso =fechaingreso;
    	this.fechasalida = fechasalida;
    	this.valor=valor;
    	
    }
    
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Calendar fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Calendar getFechasalida() {
		return fechasalida;
	}

	public void setFechasalida(Calendar fechasalida) {
		this.fechasalida = fechasalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	

}
