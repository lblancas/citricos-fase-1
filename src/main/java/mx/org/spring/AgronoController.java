package mx.org.spring;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.AgronomoController;
import mx.org.citicos.controller.ProductoresController;
import mx.org.citicos.controller.TipoProductorController;
import mx.org.citricos.entity.Productores;
import mx.org.citricos.entity.Tarima;
import mx.org.citricos.entity.Tipop;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class AgronoController
{
	@RequestMapping("/agronomos")
	public List<Renglon> getAgronomos()
	
    {
		AgronomoController Controller=new AgronomoController();
		List<Tarima> productos = Controller.getAll();
		List<Renglon> lista= new ArrayList<Renglon>();
		for(Tarima producto: productos)
			lista.add(new Renglon(producto.getNombre()+"|"+producto.getId()));
        return lista;
    }
    @RequestMapping("/agronomos/{numero}")
    public Renglon getAgronomo(@PathVariable("numero") Integer numero)
    {
    	AgronomoController Controller=new AgronomoController();
    	Tarima producto = Controller.getOne(numero);
        return new Renglon(producto.getNombre()+"|"+producto.getId());
    }
}
