package mx.org.spring;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.org.citicos.controller.CorridasController;
import mx.org.citicos.controller.FolioController;
import mx.org.citicos.controller.PDF_C;
import mx.org.citicos.controller.ProductoresController;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Precios;
import mx.org.citricos.entity.Productores;

import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class ProductController
{
	@RequestMapping("/productores")
    public List<Renglon> getProductores()
    {
		ProductoresController productoresController=new ProductoresController();
		List<Productores> productoresFind = productoresController.getAll();
		List<Renglon> lista= new ArrayList<Renglon>();
		for(Productores productor: productoresFind)
			lista.add(new Renglon(((productor.getNombre()+" "+productor.getPaterno()).trim()+" "+productor.getMaterno()).trim()+"|"+productor.getId()));
        return lista;
    }
	
    @RequestMapping("/productores/{numero}")
    public Renglon getProductore(@PathVariable("numero") Integer numero)
    {
    	ProductoresController productoresController=new ProductoresController();
		Productores productor = productoresController.getOne(numero);
        return new Renglon(((productor.getNombre()+" "+productor.getPaterno()).trim()+" "+productor.getMaterno()).trim()+"|"+productor.getId());
    }
}
