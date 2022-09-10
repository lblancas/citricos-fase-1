package mx.org.spring;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import mx.org.citicos.controller.FolioController;
import mx.org.citricos.entity.RepTarimaCabecera;
import mx.org.citricos.entity.RepTarimaDetalle;
import mx.org.citricos.entity.RepTarimaDetallePallet;
public class ReporteHilo  implements Runnable
{
	private static final FolioController controller=new FolioController();
    private final float[] columnWidths2_a  = new float[]{60f ,330f};
    private final float[] columnWidths3 = new float[]{50f ,390f, 50f};
    private final float[] columnWidths2_b = new float[]{70f,420f};
    private final float[] columnWidths08 = new float[]{50f,142f,24f,86f,22f,90f,19f,57f};
    private final float[] columnWidths02 = new float[]{50f,440f};
    private final float[] columnWidths05 = new float[]{55f,110f,110f,110f,55f};
    private RepTarimaCabecera reporte;
    private int  opcion;
    private int usuario;
    private Integer id;
    public ReporteHilo(RepTarimaCabecera reporte,int  opcion,int usuario,Integer id)
    {
    	this.reporte=reporte;
    	this.opcion=opcion;
    	this.usuario=usuario;
    	this.id = id;
    }
    @Override
    public void run()
    {
        System.out.println("imprimir>>>>>>>>>>>>>>>>>(corrida)>>>>>>>>>>>>>>>>>>>>> "+opcion );
        ByteArrayOutputStream documentoBytes= crearDocumentoiText(reporte,opcion,usuario,id);
        if(controller.impresionXusuario(usuario))
        {
			try {
				imprimir(documentoBytes,usuario);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    } 
	private ByteArrayOutputStream crearDocumentoiText(RepTarimaCabecera reporte,int  opcion,int usuario,int id) 
    {
        ByteArrayOutputStream documentoBytes = null;
        try 
        {
            documentoBytes = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(documentoBytes);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document documento = new Document(pdfDoc, PageSize.LETTER); 
            String realPath="";//C:\\android\\complete\\";
            String imageFiless = realPath+"citricos.png"; 
            List<RepTarimaDetalle> lista = reporte.getTarimas();
            String numeroTarima="";
            String proceso ="";
            if(opcion==2) // Tarimas
            {
            	if(lista!=null)
            		if(lista.size()>=1)
            			numeroTarima =  lista.get(0).getNumeroTarima();
            	proceso = "Tarima ["+id+"]";
            	
            }
            else // Transporte Tarimas
            {
            	
            	proceso = "Transporte-Tarima ["+id+"]";
            	numeroTarima = reporte.getNumero_transporte_tarima(); 
            }
            ImageData imagen2=ImageDataFactory.create(getImageQRImage(numeroTarima,"png",10000,10000));
            ImageData imagen1 = ImageDataFactory.create(imageFiless);
            Image img1=new Image(imagen1);
            img1.setHeight (35);
            img1.setWidth  (50);
            img1.setAutoScaleHeight(true);
            img1.setAutoScale(true);
            img1.setAutoScaleWidth(true);
            Image img2=new Image(imagen2);
            img2.setHeight (50);
            img2.setWidth  (50);
            img2.setAutoScaleHeight(true);
            img2.setAutoScale(true);
            img2.setAutoScaleWidth(true);
            Date date = new Date();
            int day =date.getDay();
            int dateX =  date.getDate();
            int hours =date.getHours();
            int minutes =date.getMinutes();
            int month =  date.getMonth() + 1;
            int year =  date.getYear() +1900;
            Paragraph par0 = new Paragraph(dia(day)+", "+dateX+" de "+mes(month)+" de "+year+" "+hours+":"+minutes);
            par0.setFontSize(7f);
            par0.setTextAlignment(TextAlignment.RIGHT);
            documento.add(par0);
            Paragraph par1 = new Paragraph(proceso);
            par1.setFontSize(9f);
            par1.setTextAlignment(TextAlignment.CENTER);
            documento.add(par1);
            documento.add(new Paragraph(" "));
            documento.add(creaTabla1(
                            img1,
                            img2,
                            reporte.getNumero_transporte_tarima() +  "  /  " +reporte.getPlacas() ,
                            reporte.getLote() +" / "+reporte.getNo_sello()+" / " +reporte.getNo_termo() ,
                            reporte.getNombre() ));
            documento.add(new Paragraph(" "));
            if(reporte.getTarimas()!=null)
            	if(reporte.getTarimas().size()>0)
            		documento=creaTabla_Tarima(reporte.getTarimas(), documento);
            documento.add(creaTable9("Luis Adrian Blancas Bahena"));
            documento.close();
        } 
        catch (MalformedURLException ex) 
        {
        	ex.printStackTrace();
        }
        return documentoBytes;
    }
	private Table creaTable9(String calibrador)
    {
        Table table1 = new Table(columnWidths2_b);
        table1.setBorder(Border.NO_BORDER);
        table1.setFontSize(8f);
        table1.addCell("Elaboró:");
        table1.addCell(calibrador);
        return table1;
    } 
	private Table creaTabla1(
            Image img1,
            Image img2,
            String transporte,
            String lote_sello_termo,
            String transportista) 
    { 
        Table table0 = new Table(columnWidths3);
        Table table1 = new Table(columnWidths2_a);
        Paragraph par1 = new Paragraph("Transporte / Placas");
        par1.setFontSize(5f);
        par1.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par2 = new Paragraph("Lote /Sello /Termo");
        par2.setFontSize(5f);
        par2.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par3 = new Paragraph("Transportista");
        par3.setFontSize(5f);
        par3.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par11 = new Paragraph(transporte);
        par11.setFontSize(7f);
        par11.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par22 = new Paragraph(lote_sello_termo);
        par22.setFontSize(7f);
        par22.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par33 = new Paragraph(transportista);
        par33.setFontSize(7f);
        par33.setTextAlignment(TextAlignment.LEFT);
        
        table1.addCell(par1);
        table1.addCell(par11);
        table1.addCell(par2);
        table1.addCell(par22);
        table1.addCell(par3);
        table1.addCell(par33);   
        table0.addCell(img1);
        table0.addCell(table1);
        table0.addCell(img2);
        
        return table0;
    }
	
	private Document creaTabla_Tarima(List<RepTarimaDetalle> tarimas,Document documento) 
    { 
		Paragraph par1 = new Paragraph("Num.Tarima");
        par1.setFontSize(5f);
        par1.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par2 = new Paragraph("Marca");
        par2.setFontSize(5f);
        par2.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par3 = new Paragraph("Tarima");
        par3.setFontSize(5f);
        par3.setTextAlignment(TextAlignment.LEFT);
        
        Paragraph par4 = new Paragraph("Cajas");
        par4.setFontSize(5f);
        par4.setTextAlignment(TextAlignment.LEFT);
        
    	Paragraph par001 = new Paragraph("Id");
        par001.setFontSize(5f);
        par001.setTextAlignment(TextAlignment.CENTER);
        
        Paragraph par002 = new Paragraph("Calibre");
        par002.setFontSize(5f);
        par002.setTextAlignment(TextAlignment.CENTER);
        
        Paragraph par003 = new Paragraph("Calidad");
        par003.setFontSize(5f);
        par003.setTextAlignment(TextAlignment.CENTER);
        
        Paragraph par004 = new Paragraph("CDI");
        par004.setFontSize(5f);
        par004.setTextAlignment(TextAlignment.CENTER);
    
        
        Paragraph par005 = new Paragraph("Cajas");
        par005.setFontSize(5f);
        par005.setTextAlignment(TextAlignment.CENTER);
        
        Table table1 = new Table(columnWidths08);
        Table table00 = new Table(columnWidths02);
        for(RepTarimaDetalle tarima:  tarimas)
        {
        	  
        	table1 = new Table(columnWidths08);
	        Paragraph par11 = new Paragraph(tarima.getNumeroTarima()+" ["+tarima.getId()+"]");
	        par11.setFontSize(6f);
	        par11.setTextAlignment(TextAlignment.RIGHT);
	        
	        Paragraph par22 = new Paragraph(tarima.getMarca_desc());
	        par22.setFontSize(6f);
	        par22.setTextAlignment(TextAlignment.RIGHT);
	        
	        Paragraph par33 = new Paragraph(tarima.getTarima_desc());
	        par33.setFontSize(6f);
	        par33.setTextAlignment(TextAlignment.RIGHT);
	        
	        Paragraph par44 = new Paragraph(""+tarima.getCajas());
	        par44.setFontSize(6f);
	        par44.setTextAlignment(TextAlignment.RIGHT);
	        
	        
	        table1.addCell(par1);
	        table1.addCell(par11);
	        table1.addCell(par2);
	        table1.addCell(par22);
	        table1.addCell(par3);
	        table1.addCell(par33);
	        table1.addCell(par4);
	        table1.addCell(par44);
	        documento.add(table1);
	        if(tarima.getPallets().size()>0)
	        { 
	        	Table table2 = new Table(columnWidths05);
		        table2.addCell(par001);
		        table2.addCell(par002);
		        table2.addCell(par003);
		        table2.addCell(par004);
		        table2.addCell(par005);
		        
		        for(RepTarimaDetallePallet pallet : tarima.getPallets() )
		        {
			        Paragraph par011 = new Paragraph(""+pallet.getId());
			        par011.setFontSize(6f);
			        par011.setTextAlignment(TextAlignment.RIGHT);
			        
			        Paragraph par022 = new Paragraph(pallet.getCalibre_desc());
			        par022.setFontSize(6f);
			        par022.setTextAlignment(TextAlignment.RIGHT);
			        
			        Paragraph par033 = new Paragraph(pallet.getCalidad_desc());
			        par033.setFontSize(6f);
			        par033.setTextAlignment(TextAlignment.RIGHT);
			        
			        Paragraph par044 = new Paragraph(""+pallet.getCdi());
			        par044.setFontSize(6f);
			        par044.setTextAlignment(TextAlignment.RIGHT);
			        
			        Paragraph par055 = new Paragraph(""+pallet.getCajas());
			        par055.setFontSize(6f);
			        par055.setTextAlignment(TextAlignment.RIGHT);

			        table2.addCell(par011);
			        table2.addCell(par022);
			        table2.addCell(par033);
			        table2.addCell(par044);
			        table2.addCell(par055);
		        }
		        table00 = new Table(columnWidths02);
		        ImageData imagen2=ImageDataFactory.create(getImageQRImage(tarima.getNumeroTarima(),"png",10000,10000));
	            Image img2=new Image(imagen2);
	            img2.setHeight (49);
	            img2.setWidth  (49);
	            img2.setAutoScaleHeight(true);
	            img2.setAutoScale(true);
	            img2.setAutoScaleWidth(true);
		        table00.addCell(img2);
		        table00.addCell(table2);
		        documento.add(table00);
	        }
        }
        return documento;
    }
	private boolean existenPallets(List<RepTarimaDetallePallet> pallets) {
		boolean existen=false;
		for(RepTarimaDetallePallet pallet : pallets )
		{
			if(pallet.getId()>0)
			{
				existen =true;
				break;
			}
		}
		return existen;
	}

	private static byte[] getImageQRImage(String text, String ext, int width, int height) 
    {
    	byte[] pngData = null;
        try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, ext, pngOutputStream);
            pngData = pngOutputStream.toByteArray();  
        } catch(WriterException wex) {
                System.out.println(wex.getMessage());
        } catch(IOException ioe) {
                System.out.println(ioe.getMessage());
       } 
         return pngData;
    }
	private  void imprimir(ByteArrayOutputStream documentoBytes,int usuario) throws IOException, PrinterException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(documentoBytes.toByteArray());
        PDDocument document = PDDocument.load(bais);
        PrintService myPrintService = findPrintService(firstImpresora(usuario));
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(new PDFPageable(document));
        printerJob.setPrintService(myPrintService);
        printerJob.print();
        //document.close();

    }
    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());

            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
    private static String  firstImpresora(int usuario) 
    {
        String imp = controller.getImpresora(usuario);
        if(imp!=null)
            if(imp.length()>1)
                return imp; 
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Lista de impresoras disponibles");
        int i=0;
        String primera ="";
        for (PrintService printService : printServices) 
        {
            String impresora = printService.getName();
            if(i==0)
                primera = impresora ;
            if(impresora.indexOf("L395 Series")>=0)
                return impresora ;
        }
        return primera; 
    }
    
    private String dia(int day) 
    {
        switch (day)
        {
            case 0: return "Domingo";
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miercoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sabado";
        }
        return "Domingo";
    }

    private String mes(int month) 
    {
        switch (month)
        {
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
        }
        return "Domingo";
    } 
}
