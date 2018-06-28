package co.com.ceiba.persistencia.entidad;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity(name="Recibo")
@NamedQueries({
	@NamedQuery(name = "Recibo.findByPlaca",query = "SELECT recibo FROM Recibo recibo WHERE recibo.vehiculo.placa = :placa AND recibo.fechasalida IS NULL" ), 
	@NamedQuery(name = "Recibo.findCellsCars", query = "SELECT COUNT(*) FROM Recibo recibo WHERE recibo.vehiculo.tipo = :tipo_carro AND recibo.fechasalida IS NULL" ),
	@NamedQuery(name = "Recibo.findCellsMotos", query = "SELECT COUNT(*) from Recibo recibo WHERE recibo.vehiculo.tipo = :tipo_moto AND recibo.fechasalida IS NULL" ),
	@NamedQuery(name = "Recibo.findAll", query ="SELECT recibo FROM Recibo recibo WHERE recibo.fechasalida IS NULL")

			
})
public class ReciboEntity {

	@Id                                                   
	@GeneratedValue (strategy = GenerationType.AUTO) 
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idVehiculoRecibo", referencedColumnName ="id")
	private VehiculoEntity vehiculo; 
	
	@Column
	private Calendar fechaingreso;
	
	@Column
	private Calendar fechasalida;
	
	@Column
	private double valor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
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
