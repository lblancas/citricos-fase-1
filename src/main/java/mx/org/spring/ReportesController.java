package mx.org.spring;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.org.citicos.controller.ReporteController;
import mx.org.citricos.entity.RepTarimaCabecera;
@RestController
public class ReportesController 
{
	
    @RequestMapping("/reporte/pdf/tarima/{idtarima}")
	public int getTarimasPDF(@PathVariable("idtarima") Integer idtarima)
    {
		ReporteController controller=new ReporteController();
		RepTarimaCabecera reporte=controller.getTarima(idtarima);
		ReporteHilo reporteHilo = new ReporteHilo(reporte,2,9,idtarima);
		reporteHilo.run();
		return 1;
    }
	
	@RequestMapping("/reporte/pdf/transporteTarima/{idtransporteTarima}")
	public int  getTransporteTarimaPDF(@PathVariable("idtransporteTarima") Integer idtransporteTarima)
    {
		ReporteController controller=new ReporteController();
		RepTarimaCabecera reporte=controller.getTransporteTarima(idtransporteTarima);
		ReporteHilo reporteHilo = new ReporteHilo(reporte,1,9,idtransporteTarima);
		reporteHilo.run();
		return 1;
    }
	
	@RequestMapping("/reporte/tarima/{idtarima}")
	public RepTarimaCabecera getTarimas(@PathVariable("idtarima") Integer idtarima)
    {
		ReporteController controller=new ReporteController();
		RepTarimaCabecera reporte=controller.getTarima(idtarima);
		return reporte;
    }
	
	@RequestMapping("/reporte/transporteTarima/{idtransporteTarima}")
	public RepTarimaCabecera  getTransporteTarima(@PathVariable("idtransporteTarima") Integer idtransporteTarima)
    {
		ReporteController controller=new ReporteController();
		RepTarimaCabecera reporte=controller.getTransporteTarima(idtransporteTarima);
		return reporte;
    }
	
	
}
