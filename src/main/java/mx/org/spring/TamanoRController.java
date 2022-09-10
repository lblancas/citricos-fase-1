package mx.org.spring;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.ProductoresController;
import mx.org.citicos.controller.TrejaController;
import mx.org.citricos.entity.Productores;
import mx.org.citricos.entity.Tarima;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class  TamanoRController
{
	@RequestMapping("/tamanorejas")
    public List<Renglon> getTamanos()
    {
		TrejaController controller=new TrejaController ();
		List<Tarima> productos = controller.getAll();
		List<Renglon> lista= new ArrayList<Renglon>();
		for(Tarima productor: productos)
			lista.add(new Renglon(productor.getNombre()+"|"+productor.getId()));
        return lista;
    }
    @RequestMapping("/tamanorejas/{numero}")
    public Renglon getTamano(@PathVariable("numero") Integer numero)
    {
    	TrejaController controller=new TrejaController ();
    	Tarima productor = controller.getOne(numero);
        return new Renglon(productor.getNombre()+"|"+productor.getId());
    }
}
