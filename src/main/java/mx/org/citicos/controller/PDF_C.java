package mx.org.citicos.controller;
import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.lowagie.text.Font;

import mx.org.citricos.entity.Calidad_Empaque;
import mx.org.citricos.entity.Corrida;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Precios;
import mx.org.citricos.entity.Tarima;
public class PDF_C implements Runnable
{
	private int id=0;
	private int opcion=0;
	private int usuario=0;
	private static final FolioController controller=new FolioController();
	private static final UsuarioController usuarioController=new UsuarioController();
	private static final TrejaController trejaContoller=new TrejaController();
	private double promedio1=0.00d;
    private double promedio2=0.00d;
    private double promedio3=0.00d;
    private double promedio4=0.00d;
    private double subtotal =0.00d;
    private double subtotalresago=0.00d;
    private double verdes_cantidad=0;
    private double verdes_porcentaje=0;
    private double verdes_precio=0;
    private double verdes_total=0;
    
    private double empaques_cantidad=0;
    private double empaques_porcentaje=0;
    private double empaques_precio=0;
    private double empaques_total=0;
    
    private double desechos_cantidad=0;
    private double desechos_porcentaje=0;
    private double desechos_precio=0;
    private double desechos_total=0;
    private List<Calidad_Empaque> calidad_empaques;;
    private double _total=0;
    
    private final float[] columnWidths6 = new float[]{84f,84f,84f,84f,84f,84f};
    private final float[] columnWidths5 = new float[]{84f,84f,84f,84f,84f};
    private final float[] columnWidths15 = new float[]{84f,420f};
    private final float[] columnWidths4 = new float[]{99f,99f,99f,99f};
    private final float[] columnWidths24 = new float[]{108,396f};
    private final float[] columnWidths2 = new float[]{84f,84f};
    private final float[] columnWidths1 = new float[]{84f};
    private final float[] columnWidths004 = new float[]{84f,84f,84f,84f};
    private final float[] columnWidths024 = new float[]{168,336};
    private final float[] columnWidths003 = new float[]{168,168,168};
    
    private final float[] columnWidths007 = new float[]{84f,420f};
    private final float[] columnWidths008 = new float[]{396f};
    
    Font fontH_11 = new Font( Font.NORMAL, 11, Font.NORMAL);
    Font fontH_10 = new Font( Font.NORMAL, 10, Font.NORMAL);
    Font fontH_09 = new Font( Font.NORMAL,  9, Font.NORMAL);
    Font fontH_08 = new Font( Font.NORMAL,  8, Font.NORMAL);
    Font fontH_07 = new Font( Font.NORMAL,  7, Font.NORMAL);
    Font fontH_06 = new Font( Font.NORMAL,  6, Font.NORMAL);
	public PDF_C(int id, int opcion, int usuario) {
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
        System.out.println("imprimir>>>>>>>>>>>>>>>>>(corrida)>>>>>>>>>>>>>>>>>>>>> "+getId() );
        String fechaModificacion= controller.getFecha();
        Folios folio = controller.getOne(getId());
        controller.updateRecord(fechaModificacion,getOpcion(),folio.getId());
        CorridasController corridasController=new CorridasController();
        Corrida corrida  =  corridasController.getOne(getId());
        ProductoresController prodCon= new ProductoresController();
        String direccion = prodCon.getDireccion(folio.getId_productor());
        ByteArrayOutputStream documentoBytes=crearDocumentoiText(  folio,corrida, getUsuario(),direccion);
        String nombreArchivo="";
        nombreArchivo=nombreArchivo+"00"+getId();
        nombreArchivo=nombreArchivo+"00_"+getOpcion();
        nombreArchivo=nombreArchivo+"00_"+getUsuario();
        nombreArchivo=nombreArchivo+(new Date()).getTime();
        guardarArchivo(documentoBytes,"C:\\citricosCAD1\\salida\\"+nombreArchivo+".pdf");
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
	private String format(int cont, int valor)
    {
        if(cont> (""+valor).length()) {
            String caden="";
            for (int i=0;i<(cont-((""+valor).length()));i++)
            { caden =  caden +"0"; }
            return caden + valor;
        }
        return ""+valor;
    }
	private void guardarArchivo(ByteArrayOutputStream out,String file)
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
	private  void imprimir(ByteArrayOutputStream documentoBytes,int usuario) throws IOException, PrinterException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(documentoBytes.toByteArray());
        PDDocument document = PDDocument.load(bais);
        PrintService myPrintService = findPrintService(firstImpresora(usuario));
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(new PDFPageable(document));
        printerJob.setPrintService(myPrintService);
        printerJob.print();

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
        table1.addCell("Tama\u00f1o rejas");
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
    private ByteArrayOutputStream crearDocumentoiText( Folios  folio,Corrida corrida,int usuario,String direccion) 
    {
        ByteArrayOutputStream documentoBytes = null;
        try 
        {
            documentoBytes = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(documentoBytes);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document documento = new Document(pdfDoc, PageSize.LETTER); 
            String realPath="";//"C:\\android\\complete\\";
            String imageFiless = realPath+"citricos.png"; 
            ImageData imagen2=ImageDataFactory.create(getImageQRImage(folio.getFolio(),"png",130,130)); 
            String imagen_calidad_limon =realPath;
            if(folio.getTipos_limones()!=null)
            {
                if(folio.getTipos_limones().toUpperCase().indexOf("EMP")>=0) imagen_calidad_limon+="empaque.png";
                else if(folio.getTipos_limones().toUpperCase().indexOf("REV")>=0) imagen_calidad_limon+="revuelto.png";
                else if(folio.getTipos_limones().toUpperCase().indexOf("VER")>=0) imagen_calidad_limon+="verde.png";
                else if(folio.getTipos_limones().toUpperCase().indexOf("ORG")>=0) imagen_calidad_limon+="organico.png";
            }
            ImageData tipoLimonImagen =null;
            if(imagen_calidad_limon!=null)
                if(imagen_calidad_limon.length()> realPath.length())
                    tipoLimonImagen = ImageDataFactory.create(imagen_calidad_limon);
            Image imgLimon=null;
            if(tipoLimonImagen!=null)
            {
                imgLimon=new Image(tipoLimonImagen);
                imgLimon.setHeight(16);
                imgLimon.setWidth(100); 
            }
            ImageData imagen1 = ImageDataFactory.create(imageFiless);
            Image img1=new Image(imagen1);
            img1.setHeight(70);
            img1.setWidth(100);
            Image img2=new Image(imagen2);
            img2.setHeight(130);
            img2.setWidth(130);
            Date date = new Date();
            int day =date.getDay();
            int dateX =  date.getDate();
            int hours =date.getHours();
            int minutes =date.getMinutes();
            int month =  date.getMonth() + 1;
            int year =  date.getYear() +1900;
            double sumaEuropa = corrida.getVerde_japon()+corrida.getEur_110()+corrida.getEur_150()+corrida.getEur_175()+corrida.getEur_200()+corrida.getEur_230()+corrida.getEur_250();
            double sumaVerdes = corrida.getVerde_japon()+corrida.getVerde_110()+corrida.getVerde_150()+corrida.getVerde_175()+corrida.getVerde_200()+corrida.getVerde_230()+corrida.getVerde_250();
            double sumaEmpaques = corrida.getEmpaque_110()+corrida.getEmpaque_150()+corrida.getEmpaque_175()+corrida.getEmpaque_200()+corrida.getEmpaque_230()+corrida.getEmpaque_250();
            System.out.println("SUMA   :"+sumaEuropa+"\t"+(sumaVerdes+sumaEmpaques));
            Paragraph par0 = new Paragraph(dia(day)+", "+dateX+" de "+mes(month)+" de "+year+" "+hours+":"+minutes);
            par0.setFontSize(8f);
            par0.setTextAlignment(TextAlignment.RIGHT);
            documento.add(par0);
            Paragraph par1 = new Paragraph("REPORTE DE CORRIDA");
            par1.setFontSize(11f);
            par1.setTextAlignment(TextAlignment.CENTER);
            documento.add(par1);
            documento.add(new Paragraph(" "));
            documento.add(creaTabla1(
                            img1,
                            folio.getProductor(),
                            folio.getFolio(),
                            corrida.getComprador(),
                            obtieneCalidad(folio.getCalidad_empaque()),
                            corrida.getFacturar(),
                            ((folio.getAgronomo()==null)?" ":folio.getAgronomo()),
                            corrida.getTipo() ,
                           direccion,imgLimon,corrida.getAlbaran()));
        
            documento.add(new Paragraph(" "));
            
            
            documento.add(creaTabla2());
            documento.add(new Paragraph(" "));
            verdes_cantidad=format( corrida.getVerde_japon() +
                    corrida.getEur_110()+corrida.getEur_150()+corrida.getEur_175()+corrida.getEur_200()+corrida.getEur_230()+corrida.getEur_250());
            empaques_cantidad=format(corrida.getEmpaque_110()+corrida.getEmpaque_150()+corrida.getEmpaque_175()+corrida.getEmpaque_200()+corrida.getEmpaque_230()+corrida.getEmpaque_250()+
                     corrida.getVerde_110()+corrida.getVerde_150()+corrida.getVerde_175()+corrida.getVerde_200()+corrida.getVerde_230()+corrida.getVerde_250());
            
            double peso_segundas =26.00d;
            double peso_terceras =26.00d;
            double peso_torreon =26.00d;
            double peso_coleada =26.00d;

            double segunda =folio.getSegundas()*peso_segundas;
            segunda=segunda + corrida.getSegundas();

            double tercera =folio.getTerceras()*peso_terceras;

            double torreon =folio.getTorreon()*peso_torreon;

            double coleada =folio.getColeada()*peso_coleada;
            
            desechos_cantidad=format(segunda+tercera+torreon+coleada);
            
            _total = folio.getPeso_neto();
            
            double peso_bruto = folio.getPeso_bruto();
            double peso_tara = folio.getPeso_tara();
            int dejoRejas = folio.getDejo().intValue();
            Double numeroRejas=0d;
            Double rebajarNumeroRejasXtamano=0d;
            if(dejoRejas==1) {
            	numeroRejas= folio.getNo_rejas();
            	double kilosXreja= obtieneKilosxreja(folio.getIdRejas());
            	rebajarNumeroRejasXtamano = kilosXreja * numeroRejas;
            }
            double kilos = peso_bruto - peso_tara - rebajarNumeroRejasXtamano;
            _total =  kilos;
            if(_total<=0)
                _total =1;
            verdes_porcentaje=format(verdes_cantidad/_total);
            empaques_porcentaje=format(empaques_cantidad/_total);
            desechos_porcentaje=format(desechos_cantidad/_total);
            
            PreciosController controller=new PreciosController();
            Precios precios = null;
            
            if(opcion >=3)
            {
                precios = controller.getPrecioProProducto(folio.getId());
            }
            if(precios==null)
            {
                precios = controller.getMax();
            }
            //Verdes    
            documento.add(creaTabla3(corrida ,  precios));
            documento.add(new Paragraph(" "));
            //Empaques
            documento.add(creaTabla4(corrida ,  precios));
            documento.add(new Paragraph(" "));
            //Desechos
            documento.add(creaTabla5(corrida ,  precios,folio));
            documento.add(new Paragraph(" "));
            //Suma 1
            documento.add(creaTabla6_2(corrida,folio,precios));
            //suma2
            documento.add(creaTabla6_3(corrida,folio,precios,_total));
            
            documento.add(creaTable7(kilos,subtotalresago+subtotal));
            if(corrida.getPromedio1().doubleValue() !=  corrida.getPromedio2().doubleValue() )
            {
                documento.add(creaTabla7_1(corrida.getSubsuma1() +corrida.getSubsuma2(),  corrida.getPromedio2()));
            }
            documento.add(new Paragraph(" "));
            String nombreUsuario=usuarioController.getNameUsuarioById(usuario);
            documento.add(creaTable9(nombreUsuario));
            
            documento.close();
        } 
        catch (MalformedURLException ex) 
        {
        	ex.printStackTrace();
        }
        return documentoBytes;
    }
    private double obtieneKilosxreja(Integer id) {
    	Tarima tamanoReja =trejaContoller.getAll().stream().filter(m->m.getId().intValue()== id.intValue()).findFirst().get();
    	String nombre =  tamanoReja.getNombre();
    	if(nombre!=null) {
    		String[]  tokens =  nombre.split(" ");
    		if(tokens.length==3) {
    			try
    			{
    				double tamanoRejas = new Double(tokens[1]);
    				return tamanoRejas;
    			}
    			catch(Exception e) {
    				System.out.print("Error en casting");
    			}
    		}
    	}
		return 0d;
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

    private double format(double number) 
    {
        if(number>0)
        {
            BigDecimal bd = new BigDecimal(""+number);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        return 0.00d;
    }
    private String formato(double number) 
    {
        DecimalFormat formato = new DecimalFormat("#,###,##0.00");
        BigDecimal bd = new BigDecimal(""+number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return formato.format(bd.doubleValue());
    }
    protected Cell getCell(String cadena){
    	Cell cell = new Cell();
    	try
    	{
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        Paragraph paragraph = new Paragraph(cadena).setFont(font);
	        paragraph.setFixedLeading(0);
	        paragraph.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        paragraph.setMultipliedLeading(1);
	        cell.setVerticalAlignment(VerticalAlignment.TOP);
	        cell.setTextAlignment(TextAlignment.RIGHT);
	        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        cell.add(paragraph);
    	}
    	catch(Exception e) {
    		
    	}
        return cell;
    }
    protected Cell getCellBold(String cadena){
    	Cell cell = new Cell();
    	try
    	{
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	        Paragraph paragraph = new Paragraph(cadena).setFont(font);
	        paragraph.setFixedLeading(0);
	        paragraph.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        paragraph.setMultipliedLeading(1);
	        cell.setVerticalAlignment(VerticalAlignment.TOP);
	        cell.setTextAlignment(TextAlignment.RIGHT);
	        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        cell.add(paragraph);
    	}
    	catch(Exception e) {
    		
    	}
        return cell;
    }
    protected Cell getCellBoldAndColor(String cadena){
    	Cell cell = new Cell();
    	try
    	{
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	        Paragraph paragraph = new Paragraph(cadena).setFont(font);
	        paragraph.setFixedLeading(0);
	        paragraph.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        paragraph.setMultipliedLeading(1);
	        cell.setVerticalAlignment(VerticalAlignment.TOP);
	        cell.setTextAlignment(TextAlignment.RIGHT);
	        cell.setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY);
	        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	        cell.add(paragraph);
    	}
    	catch(Exception e) {
    		
    	}
        return cell;
    }
    private double formatoDouble(double number) 
    {
        DecimalFormat formato = new DecimalFormat("#,###,##0.00");
        BigDecimal bd = new BigDecimal(""+number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    
    public List<Calidad_Empaque> getCalidad_empaques() {
        return calidad_empaques;
    }

    public void setCalidad_empaques(List<Calidad_Empaque> calidad_empaques) {
        this.calidad_empaques = calidad_empaques;
    }

    private static String obtieneCalidad(Integer calidad_empaque)
    {
        if(calidad_empaque==1)
            return "Verde";
        if(calidad_empaque==2)
            return "Empaque";
        return "Revuelto";
    }
    private Table creaTabla1(
            Image img1,
            String productor,
            String folio,
            String comprador,
            String direccion,
            String facturar,
            String agronomo,
            String tipo,
            String direccionProductor,
            Image limon,
            Double albaran) 
        { 
            Table table0 = new Table(columnWidths24);
            
            Table table1 = new Table(columnWidths4);
            
            table1.addCell("Productor:");
            table1.addCell(productor);
            table1.addCell("Localizaci\u00f3n:");
            table1.addCell(direccionProductor);
            
            table1.addCell("Comprador:");
            table1.addCell(comprador);
            
            table1.addCell("Folio:"); 
            table1.addCell(folio);
            
            table1.addCell("Facturar a:");
            table1.addCell(facturar); 
            table1.addCell("Tipo : ");
            table1.addCell(limon);
            
            table1.addCell("Agr\u00f3nomo:");  
            table1.addCell(agronomo);
            
            table1.addCell("Albaran");
            table1.addCell(getCellBoldAndColor((""+(albaran==null?"":(""+albaran.intValue())))));
            table1.setFontSize(7f); 
            
            table0.addCell(img1);
            table0.addCell(table1);
            
            return table0;
        }
        private Table creaTabla2() 
        { 
            Table table1 = new Table(columnWidths6);
            table1.setFontSize(8f); 
            table1.addCell("");
            table1.addCell("CALIBRE");
            table1.addCell("CANTIDAD (KG"); 
            table1.addCell("PORCENTAJE (%)"); 
            table1.addCell("PRECIO ($)");
            table1.addCell("TOTAL ($)");
            return table1;
        }
        private Table creaTabla3(Corrida c ,  Precios precios) 
        { 
            Table table0 = new Table(columnWidths15);
            
            Table table1 = new Table(columnWidths5);
            table1.setFontSize(9f);
            table0.setFontSize(8f);
            
            //Japon
            table1.addCell("Jap\u00f3n");
            table1.addCell(getCell(formato(new Double(c.getVerde_japon()))));
            table1.addCell(getCell(formato(c.getVerde_japon()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_japon())));
            table1.addCell(getCell(formato(c.getVerde_japon() *  precios.getVerde_japon())));
            
            table1.addCell("110");
            table1.addCell(getCell(formato(new Double(c.getEur_110()))));
            table1.addCell(getCell(formato(c.getEur_110()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_110())));
            table1.addCell(getCell(formato(c.getEur_110() *  precios.getVerde_110())));
            
            table1.addCell("150");
            table1.addCell(getCell(formato(new Double(c.getEur_150()))));
            table1.addCell(getCell(formato(c.getEur_150()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_150())));
            table1.addCell(getCell(formato(c.getEur_150() *  precios.getVerde_150())));
            
            table1.addCell("175");
            table1.addCell(getCell(formato(new Double(c.getEur_175()))));
            table1.addCell(getCell(formato(c.getEur_175()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_175())));
            table1.addCell(getCell(formato(c.getEur_175() *  precios.getVerde_175())));
            
            table1.addCell("200");
            table1.addCell(getCell(formato(new Double(c.getEur_200()))));
            table1.addCell(getCell(formato(c.getEur_200()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_200())));
            table1.addCell(getCell(formato(c.getEur_200() *  precios.getVerde_200())));
            
            table1.addCell("230");
            table1.addCell(getCell(formato(new Double(c.getEur_230())).toString()));
            table1.addCell(getCell(formato(c.getEur_230()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_230())));
            table1.addCell(getCell(formato(c.getEur_230() *  precios.getVerde_230())));
            
            table1.addCell("250");
            table1.addCell(getCell(formato(new Double(c.getEur_250()))));
            table1.addCell(getCell(formato(c.getEur_250()/_total*100)));
            table1.addCell(getCell(formato(precios.getVerde_250())));
            table1.addCell(getCell(formato(c.getEur_250() *  precios.getVerde_250())));
            
            double suma_verdes =c.getEur_110()+c.getEur_150()+c.getEur_175()+c.getEur_200()+c.getEur_230()+c.getEur_250()+c.getVerde_japon();
            double porc_verdes = (c.getEur_110()/_total*100)+(c.getEur_150()/_total*100)+
                                 (c.getEur_175()/_total*100)+(c.getEur_200()/_total*100)+
                                 (c.getEur_230()/_total*100)+(c.getEur_250()/_total*100)+
                                 (c.getVerde_japon()/_total*100);
            table1.addCell("SUMA");
            table1.addCell(getCellBold(formato(suma_verdes)));
            table1.addCell(getCellBold(formato(porc_verdes)));
            
            verdes_total =
                    (c.getVerde_japon() *  precios.getVerde_japon())+
                    (c.getEur_110() *  precios.getVerde_110())+
                    (c.getEur_150() *  precios.getVerde_150())+
                    (c.getEur_175() *  precios.getVerde_175())+
                    (c.getEur_200() *  precios.getVerde_200())+
                    (c.getEur_230() *  precios.getVerde_230())+
                    (c.getEur_250() *  precios.getVerde_250());
            if(verdes_total>0 && suma_verdes>0)
            promedio1 = verdes_total/suma_verdes;
            table1.addCell(getCellBold(formato(promedio1)));
            table1.addCell(getCellBold(formato(verdes_total)));
            FoliosCorridaController fcc = new FoliosCorridaController();
            if(c.getPromedio1().doubleValue() ==  c.getPromedio2().doubleValue() )
            {
                fcc.updateVerde(c.getFolio(),""+formato(suma_verdes),""+
                    formato(porc_verdes),""+formato(promedio1),""+formato(verdes_total),precios.getId());
            }
            table0.addCell("VERDES");
            table0.addCell(table1);
            
            return table0;
        }
        
        private Table creaTabla4(Corrida c,Precios precios) 
        { 
            Table table0 = new Table(columnWidths15);
            Table table1 = new Table(columnWidths5);
            table1.setFontSize(9f);
            table0.setFontSize(8f);
            
            table1.addCell("110");
            table1.addCell(getCell(formato(c.getEmpaque_110()+c.getVerde_110())));
            table1.addCell(getCell(formato((c.getEmpaque_110()+c.getVerde_110())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_110())));
            table1.addCell(getCell(formato((c.getEmpaque_110()+c.getVerde_110()) *  precios.getEmpaque_110())));
            
            table1.addCell("150");
            table1.addCell(getCell(formato(c.getEmpaque_150()+c.getVerde_150())));
            table1.addCell(getCell(formato((c.getEmpaque_150()+c.getVerde_150())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_150())));
            table1.addCell(getCell(formato((c.getEmpaque_150()+c.getVerde_150() )*  precios.getEmpaque_150())));
            
            table1.addCell("175");
            table1.addCell(getCell(formato(c.getEmpaque_175()+c.getVerde_175())));
            table1.addCell(getCell(formato((c.getEmpaque_175()+c.getVerde_175())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_175())));
            table1.addCell(getCell(formato((c.getEmpaque_175()+c.getVerde_175()) *  precios.getEmpaque_175())));
            
            table1.addCell("200");
            table1.addCell(getCell(formato(c.getEmpaque_200()+c.getVerde_200())));
            table1.addCell(getCell(formato((c.getEmpaque_200()+c.getVerde_200())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_200())));
            table1.addCell(getCell(formato((c.getEmpaque_200()+c.getVerde_200()) *  precios.getEmpaque_200())));
            
            table1.addCell("230");
            table1.addCell(getCell(formato((c.getEmpaque_230()+c.getVerde_230()))));
            table1.addCell(getCell(formato((c.getVerde_230()+c.getEmpaque_230())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_230())));
            table1.addCell(getCell(formato((c.getEmpaque_230()+c.getVerde_230()) *  precios.getEmpaque_230())));
            
            table1.addCell("250");
            table1.addCell(getCell(formato(c.getEmpaque_250()+c.getVerde_250())));
            table1.addCell(getCell(formato((c.getEmpaque_250()+c.getVerde_250())/_total*100)));
            table1.addCell(getCell(formato(precios.getEmpaque_250())));
            table1.addCell(getCell(formato((c.getEmpaque_250()+c.getVerde_250()) *  precios.getEmpaque_250())));
            
            double suma_empaques =
                        (c.getEmpaque_110()+c.getVerde_110())+
                        (c.getEmpaque_150()+c.getVerde_150())+
                        (c.getEmpaque_175()+c.getVerde_175())+
                        (c.getEmpaque_200()+c.getVerde_200())+
                        (c.getEmpaque_230()+c.getVerde_230())+
                        (c.getEmpaque_250()+c.getVerde_250());
            double porc_empaques =
                        (c.getEmpaque_110()+c.getVerde_110())/_total*100+
                        (c.getEmpaque_150()+c.getVerde_150())/_total*100+
                        (c.getEmpaque_175()+c.getVerde_175())/_total*100+
                        (c.getEmpaque_200()+c.getVerde_200())/_total*100+
                        (c.getEmpaque_230()+c.getVerde_230())/_total*100+
                        (c.getEmpaque_250()+c.getVerde_250())/_total*100;
            
            table1.addCell("SUMA");
            table1.addCell(getCellBold(formato(suma_empaques)));
            table1.addCell(getCellBold(formato(porc_empaques)));
            
            
            empaques_total =
                    ((c.getEmpaque_110()+c.getVerde_110()) *  precios.getEmpaque_110())+
                    ((c.getEmpaque_150()+c.getVerde_150()) *  precios.getEmpaque_150())+
                    ((c.getEmpaque_175()+c.getVerde_175()) *  precios.getEmpaque_175())+
                    ((c.getEmpaque_200()+c.getVerde_200()) *  precios.getEmpaque_200())+
                    ((c.getEmpaque_230()+c.getVerde_230()) *  precios.getEmpaque_230())+
                    ((c.getEmpaque_250()+c.getVerde_250()) *  precios.getEmpaque_250());
            if(empaques_total>0 && suma_empaques>0)
            promedio2 = empaques_total/suma_empaques;
            table1.addCell(getCellBold(formato(promedio2)));
            table1.addCell(getCellBold(formato(empaques_total)));
            FoliosCorridaController fcc = new FoliosCorridaController();
            if(c.getPromedio1().doubleValue() ==  c.getPromedio2().doubleValue() )
            {
            	fcc.updateEmpaque(c.getFolio(),""+formato(suma_empaques),""+
                    formato(porc_empaques),""+formato(promedio2),""+formato(empaques_total));
            }
            table0.addCell("EMPAQUES");
            table0.addCell(table1);

            return table0;
        }
        
        private Table creaTabla5(Corrida c,Precios precios,Folios f) 
        { 
            Table table0 = new Table(columnWidths15);
            Table table1 = new Table(columnWidths5);
            table1.setFontSize(9f);
            table0.setFontSize(8f);
            
            double peso_segundas =26.00d;
            double peso_terceras =26.00d;
            double peso_torreon =26.00d;
            double peso_coleada =26.00d;
            table1.addCell("Segundas");
            
            double segunda =f.getSegundas()*peso_segundas;
            segunda=segunda + c.getSegundas();
            
            double tercera =f.getTerceras()*peso_terceras;
            //tercera=tercera + c.getTerceras();
            
            double torreon =f.getTorreon()*peso_torreon;
            //torreon = torreon + c.getTorreon();
            
            double coleada =f.getColeada()*peso_coleada;
            //coleada = coleada + c.getColeada();
            
			table1.addCell(getCell(formato(new Double(segunda))));
            table1.addCell(getCell(formato(segunda/_total*100)));
            table1.addCell(getCell(formato(precios.getSegundas())));
            table1.addCell(getCell(formato(segunda*precios.getSegundas())));
            
            table1.addCell("Terceras");
            table1.addCell(getCell(formato(new Double(tercera))));
            table1.addCell(getCell(formato(tercera/_total*100)));
            table1.addCell(getCell(formato(precios.getTerceras())));
            table1.addCell(getCell(formato(tercera*precios.getTerceras())));
            
            table1.addCell("Torreon");
            table1.addCell(getCell(formato(new Double(torreon))));
            table1.addCell(getCell(formato(torreon/_total*100)));
            table1.addCell(getCell(formato(precios.getTorreon())));
            table1.addCell(getCell(formato(torreon*precios.getTorreon())));
            
            table1.addCell("Coleada");
            table1.addCell(getCell(formato(new Double(coleada))));
            table1.addCell(getCell(formato(coleada/_total*100)));
            table1.addCell(getCell(formato(precios.getColeada())));
            table1.addCell(getCell(formato(coleada *  precios.getColeada())));
            
            table1.addCell("SUMA");
            
            double suma_desechos =
                    (segunda )+
                    (tercera )+
                    (torreon )+
                    (coleada );
            double porc_desechos =
                    (segunda /_total*100)+
                    (tercera /_total*100)+
                    (torreon /_total*100)+
                    (coleada /_total*100);
            table1.addCell(getCellBold(formato(suma_desechos)));
            table1.addCell(getCellBold(formato(porc_desechos)));
            
            desechos_total =
                    (segunda *  precios.getSegundas())+
                    (tercera *  precios.getTerceras())+
                    (torreon *  precios.getTorreon())+
                    (coleada *  precios.getColeada());
            if(desechos_total>0 && suma_desechos>0)
            {
            	promedio3 =desechos_total/suma_desechos;
            }
            table1.addCell(getCellBold(formato(promedio3)));
            table1.addCell(getCellBold(formato(desechos_total)));
            FoliosCorridaController fcc = new FoliosCorridaController();
            if(c.getPromedio1().doubleValue() ==  c.getPromedio2().doubleValue() )
            {
            	fcc.updateDesechos(c.getFolio(),""+formato(suma_desechos),""+
                    formato(porc_desechos),""+formato(promedio3),""+formato(desechos_total));
            }
            table0.addCell("DESECHOS");
            table0.addCell(table1);
            
            return table0;
        }
        private Table creaTabla6_1() 
        { 
            Table table1 = new Table(columnWidths2);
            table1.setBorder(Border.NO_BORDER);
            table1.setFontSize(9f);
            table1.addCell(" ");
            table1.addCell(" ");
            return table1;
        }
        private Table creaTabla6_2(
            Corrida c, Folios f,Precios precios) 
        { 
            double peso_segundas =26.00d;
            double peso_terceras =26.00d;
            double peso_torreon =26.00d;
            double peso_coleada =26.00d;
            
            double suma_verdes =c.getEur_110()+c.getEur_150()+c.getEur_175()+c.getEur_200()+c.getEur_230()+c.getEur_250()+c.getVerde_japon();
            double porc_verdes = (c.getEur_110()/_total*100)+(c.getEur_150()/_total*100)+
                                 (c.getEur_175()/_total*100)+(c.getEur_200()/_total*100)+
                                 (c.getEur_230()/_total*100)+(c.getEur_250()/_total*100)+
                                 (c.getVerde_japon()/_total*100);
            
            double total_verdes =
                    (c.getVerde_japon() *  precios.getVerde_japon())+
                    (c.getEur_110() *  precios.getVerde_110())+
                    (c.getEur_150() *  precios.getVerde_150())+
                    (c.getEur_175() *  precios.getVerde_175())+
                    (c.getEur_200() *  precios.getVerde_200())+
                    (c.getEur_230() *  precios.getVerde_230())+
                    (c.getEur_250() *  precios.getVerde_250());
            
            double suma__ =  suma_verdes;
            double porc__ =  porc_verdes;
            double tota__ =  total_verdes;
            
            double suma_empaques =
                        (c.getEmpaque_110()+c.getVerde_110())+
                        (c.getEmpaque_150()+c.getVerde_150())+
                        (c.getEmpaque_175()+c.getVerde_175())+
                        (c.getEmpaque_200()+c.getVerde_200())+
                        (c.getEmpaque_230()+c.getVerde_230())+
                        (c.getEmpaque_250()+c.getVerde_250());
            double porc_empaques =
                        (c.getEmpaque_110()+c.getVerde_110())/_total*100+
                        (c.getEmpaque_150()+c.getVerde_150())/_total*100+
                        (c.getEmpaque_175()+c.getVerde_175())/_total*100+
                        (c.getEmpaque_200()+c.getVerde_200())/_total*100+
                        (c.getEmpaque_230()+c.getVerde_230())/_total*100+
                        (c.getEmpaque_250()+c.getVerde_250())/_total*100;
            
            double total_empaque =
                    ((c.getEmpaque_110()+c.getVerde_110()) *  precios.getEmpaque_110())+
                    ((c.getEmpaque_150()+c.getVerde_150()) *  precios.getEmpaque_150())+
                    ((c.getEmpaque_175()+c.getVerde_175()) *  precios.getEmpaque_175())+
                    ((c.getEmpaque_200()+c.getVerde_200()) *  precios.getEmpaque_200())+
                    ((c.getEmpaque_230()+c.getVerde_230()) *  precios.getEmpaque_230())+
                    ((c.getEmpaque_250()+c.getVerde_250()) *  precios.getEmpaque_250());
            
            
            suma__ =  suma__ +suma_empaques;
            porc__ =  porc__ +porc_empaques;
            tota__ =  tota__ +total_empaque;
            
            double segunda =f.getSegundas()*peso_segundas;
            segunda=segunda + c.getSegundas();
            
            double tercera =f.getTerceras()*peso_terceras;
            //tercera=tercera + c.getTerceras();
            
            double torreon =f.getTorreon()*peso_torreon;
            //torreon = torreon + c.getTorreon();
            
            double coleada =f.getColeada()*peso_coleada;
            //coleada = coleada + c.getColeada();
            
            double suma_desechos =
                    (segunda )+
                    (tercera )+
                    (torreon )+
                    (coleada );
            double porc_desechos =
                    (segunda /_total*100)+
                    (tercera /_total*100)+
                    (torreon /_total*100)+
                    (coleada /_total*100);
            double total_desechos =
                    (segunda *  precios.getSegundas())+
                    (tercera *  precios.getTerceras())+
                    (torreon *  precios.getTorreon())+
                    (coleada *  precios.getColeada());
            
            suma__ =  suma__ +suma_desechos;
            porc__ =  porc__ +porc_desechos;
            tota__ =  tota__ +total_desechos;
            
            Table table1 = new Table(columnWidths004);
            Table table2 = new Table(columnWidths024);
            table2.setBorder(Border.NO_BORDER);
            table1.setFontSize(9f);
            table1.addCell(getCellBold(formato(suma__)));
            table1.addCell(getCellBold(formato(porc__)));
            promedio4=0.00d;
            if(c.getPromedio1().doubleValue() ==  c.getPromedio2().doubleValue() )
            {
	            if(tota__>0 && suma__>0 )
	                promedio4 = tota__ / suma__;
	            table1.addCell(getCellBold(formato(promedio4)));
	            subtotal = tota__;
	            table1.addCell(getCellBold(formato(subtotal)));
	            FoliosCorridaController fcc = new FoliosCorridaController();
	            fcc.updateSuma1(c.getFolio(),""+formato(suma__),""+formato(porc__),""+formato(subtotal));
	        }
            else
            {
            	promedio4 = c.getPromedio2().doubleValue();
	            table1.addCell(getCellBold(formato(promedio4)));
	            subtotal = tota__;
	            table1.addCell(getCellBoldAndColor(formato(subtotal)));
	            FoliosCorridaController fcc = new FoliosCorridaController();
	            fcc.updateSuma1(c.getFolio(),""+formato(suma__),""+formato(porc__),""+formato(subtotal));
	        }
            table2.addCell(" ");
            table2.addCell(table1);
            return table2;
        }
    
    private Table creaTabla7_1(double suma, double prom) 
    { 
        Table table1 = new Table(columnWidths004);
        Table table2 = new Table(columnWidths024);
        table2.setBorder(Border.NO_BORDER);
        table1.setFontSize(9f);
        table1.addCell(formato(suma));
        table1.addCell("");
        table1.addCell(formato(prom));
        table1.addCell(formato(suma * prom));
        table2.addCell(" ");
        table2.addCell(table1);
        return table2;
    }
    
    private Table creaTabla6_3(
        Corrida c, Folios f,Precios precios,double kilosNetos) 
    { 
        double peso_segundas =26.00d;
        double peso_terceras =26.00d;
        double peso_torreon =26.00d;
        double peso_coleada =26.00d;
        
        double suma_verdes =c.getEur_110()+c.getEur_150()+c.getEur_175()+c.getEur_200()+c.getEur_230()+c.getEur_250()+c.getVerde_japon();
        double porc_verdes = (c.getEur_110()/_total*100)+(c.getEur_150()/_total*100)+
                             (c.getEur_175()/_total*100)+(c.getEur_200()/_total*100)+
                             (c.getEur_230()/_total*100)+(c.getEur_250()/_total*100)+
                             (c.getVerde_japon()/_total*100);
        
        double total_verdes =
                (c.getVerde_japon() *  precios.getVerde_japon())+
                (c.getEur_110() *  precios.getVerde_110())+
                (c.getEur_150() *  precios.getVerde_150())+
                (c.getEur_175() *  precios.getVerde_175())+
                (c.getEur_200() *  precios.getVerde_200())+
                (c.getEur_230() *  precios.getVerde_230())+
                (c.getEur_250() *  precios.getVerde_250());
        
        double suma__ =  suma_verdes;
        double porc__ =  porc_verdes;
        double tota__ =  total_verdes;
        
        double suma_empaques =
                    (c.getEmpaque_110()+c.getVerde_110())+
                    (c.getEmpaque_150()+c.getVerde_150())+
                    (c.getEmpaque_175()+c.getVerde_175())+
                    (c.getEmpaque_200()+c.getVerde_200())+
                    (c.getEmpaque_230()+c.getVerde_230())+
                    (c.getEmpaque_250()+c.getVerde_250());
        double porc_empaques =
                    (c.getEmpaque_110()+c.getVerde_110())/_total*100+
                    (c.getEmpaque_150()+c.getVerde_150())/_total*100+
                    (c.getEmpaque_175()+c.getVerde_175())/_total*100+
                    (c.getEmpaque_200()+c.getVerde_200())/_total*100+
                    (c.getEmpaque_230()+c.getVerde_230())/_total*100+
                    (c.getEmpaque_250()+c.getVerde_250())/_total*100;
        
        double total_empaque =
                ((c.getEmpaque_110()+c.getVerde_110()) *  precios.getEmpaque_110())+
                ((c.getEmpaque_150()+c.getVerde_150()) *  precios.getEmpaque_150())+
                ((c.getEmpaque_175()+c.getVerde_175()) *  precios.getEmpaque_175())+
                ((c.getEmpaque_200()+c.getVerde_200()) *  precios.getEmpaque_200())+
                ((c.getEmpaque_230()+c.getVerde_230()) *  precios.getEmpaque_230())+
                ((c.getEmpaque_250()+c.getVerde_250()) *  precios.getEmpaque_250());
        
        
        suma__ =  suma__ +suma_empaques;
        porc__ =  porc__ +porc_empaques;
        tota__ =  tota__ +total_empaque;
        
        double segunda =f.getSegundas()*peso_segundas;
        segunda=segunda + c.getSegundas();
        
        double tercera =f.getTerceras()*peso_terceras;
        //tercera=tercera ;
        
        double torreon =f.getTorreon()*peso_torreon;
        //torreon = torreon;
        
        double coleada =f.getColeada()*peso_coleada;
        //coleada = coleada + c.getColeada();
        
        double suma_desechos =
                (segunda )+
                (tercera )+
                (torreon )+
                (coleada );
        double porc_desechos =
                (segunda /_total*100)+
                (tercera /_total*100)+
                (torreon /_total*100)+
                (coleada /_total*100);
        double total_desechos =
                (segunda *  precios.getSegundas())+
                (tercera *  precios.getTerceras())+
                (torreon *  precios.getTorreon())+
                (coleada *  precios.getColeada());
        
        suma__ =  suma__ +suma_desechos;
        porc__ =  porc__ +porc_desechos;
        tota__ =  tota__ +total_desechos;
        
        Table table1 = new Table(columnWidths004);
        Table table2 = new Table(columnWidths024);
        table2.setBorder(Border.NO_BORDER);
        table1.setFontSize(9f);
        table1.addCell(getCellBold(formato((Math.abs(suma__ - kilosNetos)))));
        table1.addCell(getCellBold(formato((Math.abs( porc__ - 100)))));
        table1.addCell(getCellBold(formato(promedio4)));
        double restante = (Math.abs(suma__ - kilosNetos));
        promedio4 = formatoDouble(promedio4);
        restante  = formatoDouble(restante);
        subtotalresago= promedio4 *restante;
        table1.addCell(getCellBoldAndColor(formato(subtotalresago)));
        FoliosCorridaController fcc = new FoliosCorridaController();
        if(c.getPromedio1().doubleValue() ==  c.getPromedio2().doubleValue() )
            
        fcc.updateSuma2(c.getFolio(),formato((Math.abs(suma__ - kilosNetos))),
                ""+formato(( Math.abs(porc__ - 100))),""+formato(subtotalresago));
        if(c.getPromedio1().doubleValue() <=0)
        {
        	fcc.updatePromedioFinal(c.getFolio(),""+promedio4);
        }
        fcc.deletePrecioCorrida(precios, f.getId());
        fcc.insertPrecioCorrida(precios, f.getId());
        table2.addCell(" ");
        table2.addCell(table1);
        return table2;
    }
     
    
    private Table creaTabla6_3(
        String etiqueta,
        double valor) 
    { 
        Table table1 = new Table(columnWidths1);
        table1.setBorder(Border.NO_BORDER);
        table1.setFontSize(9f);
        table1.addCell(etiqueta);
        
        Table table2 = new Table(columnWidths1);
        table2.setFontSize(9f);
        table2.addCell((new Double(subtotal +subtotalresago)).toString());
        
        Table table3 = new Table(columnWidths1);
        table3.addCell(table1);
        table3.addCell(table2);
        
        return table1;
    }
    private Table creaTable7(Table t1,Table t2,Table t3)
    {
        Table table1 = new Table(columnWidths003);
        table1.addCell(t1);
        table1.addCell(t2);
        table1.addCell(t3);
        return table1;
    }
    private Table creaTable7(
            double d1,
            double d2)
    {
        Table table1 = new Table(columnWidths6);
        table1.setFontSize(8f); 
        table1.addCell("Kilos Ticket :");
        table1.addCell(getCellBoldAndColor(formato(d1)));
        table1.addCell(""); 
        table1.addCell(""); 
        table1.addCell("Total :");
        table1.addCell(getCellBoldAndColor(formato(d2)));
        return table1;
    }
    private Table creaTable8(double d1)
    {
        Table table1 = new Table(columnWidths6);
        table1.setFontSize(8f); 
        table1.addCell("Folio Ticket:");
        table1.addCell(""+d1);
        table1.addCell(""); 
        table1.addCell(""); 
        table1.addCell(" ");
        table1.addCell("");
        return table1;
    }
    
    private Table creaTable9(String calibrador)
    {
        Table table1 = new Table(columnWidths007);
        table1.setBorder(Border.NO_BORDER);
        table1.setFontSize(9f);
        table1.addCell("Elabor\u00f3:");
        table1.addCell(calibrador);
        return table1;
    } 
}
