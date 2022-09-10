package mx.org.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.TarimaController;
import mx.org.citicos.controller.TarimaTransporteController;
import mx.org.citicos.controller.TransporteController;
import mx.org.citicos.controller.TransportistaController;
import mx.org.citricos.entity.TarimaTransporte;
import mx.org.citricos.entity.Transporte;
import mx.org.citricos.entity.Transportista;

@RestController
public class TarimasController {
	@RequestMapping("/tarima/transportes")
	public List<Renglon> getTransportes() {
		List<Transporte> cat_transporte;
		TransporteController ctra = new TransporteController();
		cat_transporte = ctra.getAll();

		List<Renglon> lista = new ArrayList<Renglon>();
		for (Transporte rec : cat_transporte)
			lista.add(new Renglon(
					""+rec.getNumero() + " " + getTransportista(rec.getIdtransportista()) + " | " + rec.getId()));
		return lista;
	}

	@RequestMapping("/tarima/transportes/{id}")
	public Transporte getTransporte(@PathVariable("id") int id) {
		Transporte cat_transporte;
		TransporteController ctra = new TransporteController();
		cat_transporte = ctra.getOne(id);
		cat_transporte.setTransportista(
				cat_transporte.getIdtransportista() + "-" + getTransportista(cat_transporte.getIdtransportista()));
		return cat_transporte;
	}

	private String getTransportista(int id) {
		TransportistaController controller = new TransportistaController();
		Transportista transportista = controller.getOne(id);
		return (transportista.getNombre() != null ? transportista.getNombre() : "");
	}

	@RequestMapping("/tarima/setTransporte/{id}/{numerotermo}/{numerosello}/{lector_temp}/{lote}/{idtransportista}/{usuario}/{opcion}")
	public Transporte setTransporte(@PathVariable("id") int id, @PathVariable("numerotermo") String numerotermo,
			@PathVariable("numerosello") String numerosello, @PathVariable("lector_temp") String lector_temp,
			@PathVariable("lote") String lote, @PathVariable("idtransportista") int idtransportista,
			@PathVariable("usuario") int usuario, @PathVariable("opcion") int opcion) {
		Transporte transporte = null;
		int mod = 0;
		Date date = new Date();
		String fecha = date.getYear() + "-" + (date.getMonth() >= 10 ? ("" + date.getMonth()) : ("0" + date.getMonth()))
				+ "-" + (date.getDay() >= 10 ? ("" + date.getDay()) : ("0" + date.getDay()));

		TransporteController transporteController = new TransporteController();
		if (opcion == 1) // alta
			transporte = transporteController.insertRecord(numerotermo, numerosello, lector_temp, lote, fecha, 0,
					usuario, idtransportista);
		if (opcion == 2) // modificar
		{
			mod = transporteController.updateRecord(numerotermo, numerosello, lector_temp, lote, fecha, 0,
					idtransportista, usuario, id);
			transporte = new Transporte();
			transporte.setId(id);
			transporte.setFecha(fecha);
			transporte.setIdactivo(1);
			transporte.setIdcliente(0);
			transporte.setIdtransportista(idtransportista);
			transporte.setLector_temp(lector_temp);
			transporte.setLote(lote);
			transporte.setNumerosello(numerosello);
			transporte.setNumerotermo(numerotermo);
		}
		if (opcion == 3) // borrar
		{
			mod = transporteController.deleteRecord(id, usuario);
			transporte = new Transporte();
			transporte.setId(id);
			transporte.setFecha(fecha);
			transporte.setIdactivo(1);
			transporte.setIdcliente(0);
			transporte.setIdtransportista(idtransportista);
			transporte.setLector_temp(lector_temp);
			transporte.setLote(lote);
			transporte.setNumerosello(numerosello);
			transporte.setNumerotermo(numerotermo);
		}
		return transporte;
	}

	@RequestMapping("/tarima/transportista")
	public List<Renglon> getTransportistas() {
		List<Transportista> cat_transportistas;
		TransportistaController ctra = new TransportistaController();
		cat_transportistas = ctra.getAll();
		List<Renglon> lista = new ArrayList<Renglon>();
		for (Transportista rec : cat_transportistas)
			lista.add(new Renglon("" + rec.getNumero() + "-" + rec.getNombre() + " " + rec.getPlacas() + " "
					+ rec.getNumero_economico() + "|" + rec.getId()));
		return lista;
	}

	@RequestMapping("/tarima/setTransportista/{id}/{nombre}/{placas}/{numero_economico}/{activo}/{usuario}/{opcion}")
	public Transportista setTransportista(@PathVariable("id") int id, @PathVariable("nombre") String nombre,
			@PathVariable("placas") String placas, @PathVariable("numero_economico") String numero_economico,
			@PathVariable("activo") int activo, @PathVariable("usuario") int usuario,
			@PathVariable("opcion") int opcion) {
		Transportista transportista = null;
		int mod = 0;
		TransportistaController transportistaController = new TransportistaController();
		if (opcion == 1) // alta
			transportista = transportistaController.insertRecord(nombre, placas, numero_economico, usuario);
		if (opcion == 2) // modificar
		{
			mod = transportistaController.updateRecord(nombre, placas, numero_economico, usuario, id);
			transportista = new Transportista();
			transportista.setId(id);
			transportista.setNombre(nombre);
			transportista.setPlacas(placas);
			transportista.setNumero_economico(numero_economico);
			transportista.setIdactivo(1);
		}
		if (opcion == 3) // borrar
		{
			mod = transportistaController.deleteRecord(id, usuario);
			transportista = new Transportista();
			transportista.setId(id);
			transportista.setNombre(nombre);
			transportista.setPlacas(placas);
			transportista.setNumero_economico(numero_economico);
			transportista.setIdactivo(0);
		}
		return transportista;
	}
	@RequestMapping("/getNumeroTarima/{num}")
	public Renglon getNumeroTarima(@PathVariable("num") String num) 
	{
		TarimaController ctrc = new TarimaController();
		return new Renglon(""+ctrc.getId(num));
	}
	@RequestMapping("/tarimasNumTransporte/{num}")
	public TarimaTransporte getTransportistaOne(@PathVariable("num") String num) 
	{
		TarimaController ctrc = new TarimaController();
		TarimaTransporte ctt;
		TarimaTransporteController ctra = new TarimaTransporteController();
		ctt = ctra.getOne(num);
		TransporteController ctTrans = new TransporteController();
		if (ctt.getId() != null) 
		{
			Transporte cat_transporte;
			cat_transporte = ctTrans.getOne(ctt.getIdTransporte());
			ctt.setTransporte(""+ cat_transporte.getNumero()+" "+getTransportista(cat_transporte.getIdtransportista())+" | "+ cat_transporte.getId());
			ctt.setCajas(ctrc.getCajas(ctt.getId()));
		} else {
			Transporte cat_transporte;
			ctt.setId(0);
			cat_transporte = (ctTrans.getAll()).get(0);
			ctt.setIdTransporte(cat_transporte.getId());
			ctt.setIdEstatus(0);
			ctt.setNumero("");
			ctt.setTransporte(""+ cat_transporte.getNumero()+" "+getTransportista(cat_transporte.getIdtransportista())+" | "+ cat_transporte.getId());
			ctt.setCajas(ctrc.getCajas(ctt.getId()));

		}
		return ctt;
	}
	@RequestMapping("/tarimasTransporte/{id}")
	public TarimaTransporte getTransportistaOne(@PathVariable("id") int id) 
	{
		TarimaController ctrc = new TarimaController();
		TarimaTransporte ctt;
		TarimaTransporteController ctra = new TarimaTransporteController();
		ctt = ctra.getOne(id);
		TransporteController ctTrans = new TransporteController();
		if (ctt.getId() != null) {
			Transporte cat_transporte;
			cat_transporte = ctTrans.getOne(ctt.getIdTransporte());
			ctt.setTransporte(""+ cat_transporte.getNumero()+" "+getTransportista(cat_transporte.getIdtransportista())+" | "+ cat_transporte.getId());
			ctt.setCajas(ctrc.getCajas(id));
		} else {
			Transporte cat_transporte;
			ctt.setId(0);
			cat_transporte = (ctTrans.getAll()).get(0);
			ctt.setIdTransporte(cat_transporte.getId());
			ctt.setIdEstatus(0);
			ctt.setNumero("");
			ctt.setTransporte(""+ cat_transporte.getNumero()+" "+getTransportista(cat_transporte.getIdtransportista())+" | "+ cat_transporte.getId());
			ctt.setCajas(ctrc.getCajas(id));

		}
		return ctt;
	}

	@RequestMapping("/tarima/transportista/{id}")
	public Transportista getTransportistas(@PathVariable("id") int id) {
		Transportista transportista;
		TransportistaController ctra = new TransportistaController();
		transportista = ctra.getOne(id);
		return transportista;
	}

	@RequestMapping("/tarimasTransporte")
	public List<Renglon> getTarimaTransportes() {
		List<TarimaTransporte> cat_transporte;
		TarimaTransporteController ctra = new TarimaTransporteController();
		cat_transporte = ctra.getAll();
		List<Renglon> lista = new ArrayList<Renglon>();
		for (TarimaTransporte rec : cat_transporte)
			lista.add(new Renglon("" + rec.getNumero() + "|" + rec.getId()));
		return lista;
	}
	
	@RequestMapping("/setIdTarimasTransporte/{idTransporteTarima}/{idTarima}")
	public int getTransportistas(@PathVariable("idTransporteTarima") int idTransporteTarima,@PathVariable("idTarima") int idTarima) {
		TransportistaController ctra = new TransportistaController();
		ctra.updateAsignaTransporteTarimaATarima(idTransporteTarima,idTarima);
		return 1;
	}
	@RequestMapping("/setTarimasTransporte/{id}/{idtransporte}/{idTarima}/{usuario}/{opcion}")
	public TarimaTransporte setTransporte(@PathVariable("id") int id, @PathVariable("idtransporte") int idtransporte,
			@PathVariable("usuario") int usuario, @PathVariable("idTarima") int idTarima,
			@PathVariable("opcion") int opcion) {
		TarimaTransporte transporte = null;
		TarimaController ctrc = new TarimaController();
		TarimaTransporteController trtController = new TarimaTransporteController();
		if (opcion == 1) // alta
		{
			transporte = trtController.insertRecord(idtransporte, usuario);
		} else if (opcion == 2) // modificar
		{
			transporte = trtController.modificar(id, idtransporte, usuario);
		} else if (opcion == 3) // borrar
		{
			trtController.deleteRecord(id, usuario);
			transporte = trtController.getOne(id);
		}
		int cajas = ctrc.getCajas(id);
		transporte.setCajas(cajas);
		return transporte;
	}
}
