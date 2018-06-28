package co.com.ceiba.restService;

import java.rmi.RemoteException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Carro;
import co.com.ceiba.dominio.Moto;
import co.com.ceiba.dominio.ReciboParqueadero;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.repositorio.IRepositorioRecibo;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;





@RequestMapping(value="/parqueadero")
@RestController
@Transactional


public class RestVigilante {
	
	@Autowired
	Vigilante vigilante;
	@Autowired
	IRepositorioRecibo repositorioRecibo;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	@RequestMapping(value = "ingreso/carro", method = RequestMethod.POST)
	public ReciboParqueadero ingresoCarro (@RequestBody Carro carro){
		Vehiculo vehiculo=carro;
		vehiculo.setTipo("CARRO");
		return vigilante.ingresarUnVehiculo(vehiculo);
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	@RequestMapping(value = "ingreso/moto", method = RequestMethod.POST)
	public ReciboParqueadero ingresoMoto (@RequestBody Moto moto){
		Vehiculo vehiculo=moto;
		vehiculo.setTipo("MOTO");
		return vigilante.ingresarUnVehiculo(vehiculo);
			
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/salida/vehiculo", method = RequestMethod.POST)
	@ResponseBody
	public ReciboParqueadero salidaCarro(@RequestBody Vehiculo vehiculo) {
			return vigilante.darSalidaVehiculo(vehiculo);		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/vehiculosparquedero", method = RequestMethod.GET )
	@ResponseBody
	public List<ReciboParqueadero>listarVehiculosEnParqueadero() {
		return repositorioRecibo.obtenerListaVehiculosEnParqueadero();

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/trm", method = RequestMethod.GET )
	@ResponseBody
	public  float  trm()  {
		TCRMServicesInterface proxy = new TCRMServicesInterfaceProxy("https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL");
        TcrmResponse tcrmResponse;
        try {
            tcrmResponse = proxy.queryTCRM(null);
            if (tcrmResponse != null) {
                return tcrmResponse.getValue();
            }
        } catch (RemoteException e) {
        }
        
        return 0.0f;

	}
	

}
