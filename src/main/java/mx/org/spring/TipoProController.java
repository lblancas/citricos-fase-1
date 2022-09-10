package mx.org.spring;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.org.citicos.controller.TlimonController;
import mx.org.citricos.entity.Tarima;
@RestController
public class TipoProController
{
	@RequestMapping("/tipoproducto")
    public List<Renglon> getTamanos()
    {
		TlimonController  Controller=new TlimonController ();
		List<Tarima> productos = Controller.getAll();
		List<Renglon> lista= new ArrayList<Renglon>();
		for(Tarima producto: productos)
			lista.add(new Renglon(producto.getNombre()+"|"+producto.getId()));
        return lista;
    }
    @RequestMapping("/tipoproducto/{numero}")
    public Renglon getTamano(@PathVariable("numero") Integer numero)
    {
    	TlimonController  Controller=new TlimonController ();
    	Tarima producto = Controller.getOne(numero);
        return new Renglon(producto.getNombre()+"|"+producto.getId());
    }
}
