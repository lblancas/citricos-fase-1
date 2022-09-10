package mx.org.citicos.controller;
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

import mx.org.citricos.entity.Folios;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class PDF  implements Runnable{
	private int id=0;
	private int opcion=0;
	private int usuario=0;
	private static  FolioController controller=new FolioController();
	public PDF(int id, int opcion, int usuario) {
		super();
		this.id = id;
		this.opcion = opcion;
		this.usuario = usuario;
	} 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOpcion() {
		return opcion;
	}


	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}


	public int getUsuario() {
		return usuario;
	}


	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public void run()
    {
		findPrintService("",true); 
        System.out.println("imprimir>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+getId() );
        String fechaModificacion= controller.getFecha();
        Folios folio = controller.getOne(getId());
        controller.updateRecord(fechaModificacion,getOpcion(),folio.getId());
        if(controller.impresionXusuario(getUsuario()))
        {
            imprimePallet(
                folio.getId(),
                folio.getFolio(),
                folio.getCodigo(),
                folio.getFecha(),
                folio.getProductor(),
                folio.getPeso_bruto(),
                folio.getPeso_tara(),
                folio.getPeso_neto(),
                folio.getNo_rejas(),
                folio.getTipos_rejas(),
                folio.getTipos_limones(),
                (folio.getAgronomo()!=null?folio.getAgronomo():""),
                folio.getObservaciones(),
                folio.getDejo(),
                folio.getJapon(),
                folio.getSegundas(),
                folio.getTerceras(),
                folio.getTorreon(),
                folio.getColeada(),
                fechaModificacion,
                folio.getTicket(),
                getOpcion(),
                getUsuario(), folio.getId());
        }

    }
	private  static void guardarPallet(
            Integer Id,
            String Folio,
            String Codigo,
            String Fecha,
            String Productor,
            Double bruto,
            Double tara,
            Double neto,
            Double rejas,
            String  trejas,
            String  tlimon,
            String  agronomo,
            String  observaciones,
            int     dejo,
            Double Japon,
            Double Segundas,
            Double Terceras,
            Double Torreon,
            Double Coleada,
            String fechaModificacion,
            String ticket,
            int opcion,
            int usuario,
            int idTicket,
            String fileName)
    {
        try 
        {
            ByteArrayOutputStream documentoBytes=null;
            System.out.print("!");
            documentoBytes= crearDocumentoiText(Folio,Codigo,Fecha,Productor,bruto,tara,neto,
                            rejas,trejas,tlimon,agronomo,observaciones,dejo,
                            Japon,Segundas,Terceras,Torreon,Coleada,fechaModificacion,ticket,opcion,idTicket);
            System.out.print("*");
            guardarArchivo(documentoBytes,fileName);
            System.out.print("!");
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
    }
	private static void guardarArchivo(ByteArrayOutputStream out,String file)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(out.toByteArray());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
	}
	private  static void imprimePallet(
            Integer Id,
            String Folio,
            String Codigo,
            String Fecha,
            String Productor,
            Double bruto,
            Double tara,
            Double neto,
            Double rejas,
            String  trejas,
            String  tlimon,
            String  agronomo,
            String  observaciones,
            int     dejo,
            Double Japon,
            Double Segundas,
            Double Terceras,
            Double Torreon,
            Double Coleada,
            String fechaModificacion,
            String ticket,
            int opcion,int usuario,int idTicket)
    {
        try 
        {
            ByteArrayOutputStream documentoBytes=null;
            System.out.print("!");
            documentoBytes= crearDocumentoiText(Folio,Codigo,Fecha,Productor,bruto,tara,neto,
                            rejas,trejas,tlimon,agronomo,observaciones,dejo,
                            Japon,Segundas,Terceras,Torreon,Coleada,fechaModificacion,ticket,opcion,idTicket);
            System.out.print("*");
            imprimir(documentoBytes,usuario);
            System.out.print("!");
            guardarArchivo(documentoBytes,"/Users/out/bascula"+(new Date()).getTime()+".pdf");
        }
        catch (IOException | PrinterException ex) 
        {
            ex.printStackTrace();
        }
        
    }
	private static  void imprimir(ByteArrayOutputStream documentoBytes,int usuario) throws IOException, PrinterException
    {
		System.out.print(" Busca <Impresoras>");
        ByteArrayInputStream bais = new ByteArrayInputStream(documentoBytes.toByteArray());
        PDDocument document = PDDocument.load(bais);
        PrintService myPrintService = findPrintService(firstImpresora(usuario),false);
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(new PDFPageable(document));
        printerJob.setPrintService(myPrintService);
        printerJob.print();

    }
    private static PrintService findPrintService(String printerName,boolean imp) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) 
        {
        	if(imp)
        	{
        		System.out.println(printService.getName());
        	}
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
            return printService.getName();
        }
        return primera; 
    }
    private static Table creaTabla(Double rejas,
            String  trejas,
            String  tlimon,
            String  agronomo,
            String  observaciones,
            Image dejo,
            String ticket)
    {
        Table table0 = new Table(1);
        Table table1 = new Table(4);
        table1.setBorder(Border.NO_BORDER);
        table1.setWidth(495F);
        
        table1.addCell("No. Rejas");
        table1.addCell("Tamano rejas");
        table1.addCell("Dejo rejas");
        table1.addCell("Ticket");
        
        table1.addCell(""+rejas);
        table1.addCell(""+trejas);
        table1.addCell(dejo);
        table1.addCell(ticket);
        table0.setBorder(Border.NO_BORDER);
        table0.setWidth(500F);
        table0.addCell(table1);
        if(observaciones!=null)
            if(observaciones.length()>0)
                table0.addCell(creaTablaObservacione(observaciones));
        return table0;
    }
    private static Table creaTablaObservacione(String  observaciones)
    {
        Table table1 = new Table(1);
        table1.setBorder(Border.NO_BORDER);
        table1.setWidth(495F);
        table1.addCell(observaciones);
        return table1;
    }
    private static Table creaTabla(
            Double Japon,
            Double Segundas,
            Double Terceras,
            Double Torreon,
            Double Coleada)
    {
        Table table1 = new Table(3);
        table1.setBorder(Border.NO_BORDER);
        table1.setWidth(500F);

        table1.addCell("Japon");
        table1.addCell("Segundas");
        table1.addCell("Terceras");
        table1.addCell(""+Japon);
        table1.addCell(""+Segundas);
        table1.addCell(""+Terceras);

        table1.addCell("Torreon");
        table1.addCell("Coleada");
        table1.addCell(" ");
        table1.addCell(""+Torreon);
        table1.addCell(""+Coleada);
        table1.addCell(" ");  
        
        return table1; 
    }
    private static Table creaTabla(String c1,String c2,String c3,
            String c4,String c5,String c6,
    String c7,String c8,String c9,
    String c10,String c11,String c12,
    Image img1,String fecha,Image img2,String dato,Image limon) 
    { 
        Table table1 = new Table(3);
        table1.setBorder(Border.NO_BORDER);
        table1.setWidth(500F); 
        table1.addCell(dato);
        table1.addCell("Citricos Cadillos SA de CV");
        table1.addCell(fecha);
        table1.addCell(img1);
        if(limon!=null)
            table1.addCell(limon); 
        else
            table1.addCell(""); 
        table1.addCell(img2); 
        table1.addCell(c1);
        table1.addCell(c2);
        table1.addCell(c3); 
        table1.addCell(c4);
        table1.addCell(c5);
        table1.addCell(c6);  /*
        table1.addCell(c7);
        table1.addCell(c8);
        table1.addCell(c9);
        table1.addCell(c10);
        table1.addCell(c11);
        table1.addCell(c12);
        */
        
        return table1;
    }
    private static String changeDayForKey(String cadena)
    {
        Map md=new HashMap();
        md.put("Mon","Lunes");
        md.put("Tue","Martes");
        md.put("Wed","Miercoles");
        md.put("Thu","Jueves");
        md.put("Fri","Viernes");
        md.put("Sat","Sabado");
        md.put("Sun","Domingo");
        int size= md.size();
        Iterator itKey =  md.keySet().iterator();
        for(int i=0;itKey.hasNext() && i<size;i++)
        {
            String key = (String)itKey.next();
            int container =  cadena.indexOf(key);
            if(container >= 0)
            {
                String value=  (String)md.get(key);
                cadena=cadena.replaceAll(key,value);
                return cadena;
            }
        }
        return cadena;
    }
    private static String changeMonthForKey(String cadena)
    {
        Map md=new HashMap();
        md.put("January","Enero");
        md.put("February","Febrero");
        md.put("March","Marzo");
        md.put("April","Abril");
        md.put("May","Mayo");
        md.put("June","Junio");
        md.put("July","Julio");
        md.put("August","Agosto");
        md.put("September","Septiembre");
        md.put("October","Octubre");
        md.put("November","Noviembre");
        md.put("December","Diciembre");
        int size= md.size();
        Iterator itKey =  md.keySet().iterator();
        for(int i=0;itKey.hasNext() && i<size;i++)
        {
            String key = (String)itKey.next();
            int container =  cadena.indexOf(key);
            if(container >= 0)
            {
                String value=  (String)md.get(key);
                cadena = cadena.replaceAll(key,value);
                return cadena;
            }
        }
        return cadena;
    }
    private static ByteArrayOutputStream crearDocumentoiText( 
            String Folio,
            String Codigo,
            String Fecha,
            String Productor,
            Double bruto,
            Double tara,
            Double neto,
            Double rejas,
            String  trejas,
            String  tlimon,
            String  agronomo,
            String  observaciones,
            int     dejo,
            Double Japon,
            Double Segundas,
            Double Terceras,
            Double Torreon,
            Double Coleada,
            String fechaModificacion,
            String ticket,
            int     opcion,
            int idTicket) 
    {
        ByteArrayOutputStream documentoBytes = null;
        try 
        {
        	System.out.print("Crea");
            documentoBytes = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(documentoBytes);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document documento = new Document(pdfDoc, PageSize.LETTER); 
            String realPath="";//"C:\\android\\complete\\";
            String imageFiless = realPath+"citricos.png"; 
            ImageData imagen2=ImageDataFactory.create(getImageQRImage(Folio,"png",130,130)); 
            System.out.print("Lee <"+imageFiless+">");
            ImageData imagen1 = ImageDataFactory.create(imageFiless);
            Image img1=new Image(imagen1);
            img1.setHeight(90);
            img1.setWidth(130);
            Image img2=new Image(imagen2);
            img2.setHeight(115);
            img2.setWidth(115);
            String dato ="";
            String imagen_calidad_limon =realPath;
            if(tlimon!=null)
            {
                if(tlimon.toUpperCase().indexOf("EMP")>=0) imagen_calidad_limon+="empaque.png";
                else if(tlimon.toUpperCase().indexOf("REV")>=0) imagen_calidad_limon+="revuelto.png";
                else if(tlimon.toUpperCase().indexOf("VER")>=0) imagen_calidad_limon+="verde.png";
                else if(tlimon.toUpperCase().indexOf("ORG")>=0) imagen_calidad_limon+="organico.png";
            }
            ImageData tipoLimonImagen =null;
            if(imagen_calidad_limon!=null)
                if(imagen_calidad_limon.length()> realPath.length())
                    tipoLimonImagen = ImageDataFactory.create(imagen_calidad_limon);
            Image imgLimon=null;
            if(tipoLimonImagen!=null)
            {
                imgLimon=new Image(tipoLimonImagen);
                imgLimon.setHeight(26);
                imgLimon.setWidth(120); 
            }
            if(opcion == 0) dato  =  "Bascula"; 
            if(opcion == 1) dato  =  "Recepci\u00f3n"; 
            if(opcion == 2) dato  =  "Confirmaci\u00f3n"; 
            fechaModificacion= changeMonthForKey(fechaModificacion);
            fechaModificacion= changeDayForKey(fechaModificacion);
            documento.add(new Paragraph("Fecha de Impresi\u00f3n: "+fechaModificacion+ "  // Ticket ["+idTicket+"]"));
            documento.add(creaTabla("Folio",Folio,"       "+Codigo,
                    "Productor",Productor,"       *",
                    "Peso Bruto","Peso Tara","Peso neto",
                    ""+bruto,""+tara,""+neto,
                    img1,"Fecha["+Fecha+"]",img2,dato,imgLimon));
            if(opcion == 2 || opcion == 3)
            { 
                String checkin_png = realPath+"checkIn.png"; 
                String checkout_png = realPath+"checkOut.png";
                
                System.out.println("realPath>>"+realPath);
                ImageData checkIn_img = ImageDataFactory.create(checkin_png);
                ImageData checkOut_img = ImageDataFactory.create(checkout_png);
                Image checkIn_imagen=new Image(checkIn_img);
                Image checkOut_imagen=new Image(checkOut_img);
                checkIn_imagen.setHeight(20);
                checkIn_imagen.setWidth(20);
                checkOut_imagen.setHeight(20);
                checkOut_imagen.setWidth(20);
                Image imagen = (dejo==1)? checkIn_imagen : checkOut_imagen ; 
                documento.add(new Paragraph("   "));
                documento.add(creaTabla(
                        rejas,trejas,tlimon,
                        agronomo,observaciones,imagen,ticket));
            }
            if(opcion == 3)
            { 
                documento.add(new Paragraph("   "));
                documento.add(creaTabla(Japon,Segundas,Terceras,Torreon,Coleada));
            }
            documento.close();
        } 
        catch (MalformedURLException ex) 
        {
            ex.printStackTrace();
        }
        return documentoBytes;
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
}