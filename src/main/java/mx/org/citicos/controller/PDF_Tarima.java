package mx.org.citicos.controller;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.text.StyleConstants.ColorConstants;

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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.BoxSizingPropertyValue;
import mx.org.citricos.entity.Folios;
import mx.org.citricos.entity.Pallet;
import mx.org.citricos.entity.Pallet_desc;

public class PDF_Tarima implements Runnable 
{
	private int id;
	private int usuario;
	 
	private static  FolioController controller=new FolioController();
	public PDF_Tarima(int id,int usuario)
	{
		this.id = id;
		this.usuario =  usuario;
	}
	@Override
	public void run()
    {
        System.out.println("imprimir tarima>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+this.getId() );
        String fechaModificacion= controller.getFecha();
        if(controller.impresionXusuario(getUsuario()))
        {
            imprimePallet(
                getId(),
                getUsuario(),
                fechaModificacion);
        }
    }

	private void imprimePallet(int id2, int usuario,String fecha) 
	{
        try 
        {
        	PalletController palletController=new PalletController();
    		Pallet pallet = palletController.getOne(getId());
    		PalletDescController palletDescController =new PalletDescController(id);
        	ArrayList<Pallet_desc> lista = palletDescController.getAll(id);
    		ByteArrayOutputStream documentoBytes=null;
            documentoBytes= crearDocumentoiText(pallet,lista,fecha);
			imprimir(documentoBytes,usuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static  void imprimir(ByteArrayOutputStream documentoBytes,int usuario) throws IOException, PrinterException
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
	private ByteArrayOutputStream crearDocumentoiText(Pallet pallet, ArrayList<Pallet_desc> lista,String fechaModificacion) 
	{
		ByteArrayOutputStream documentoBytes = null;
		try 
        {
            documentoBytes = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(documentoBytes);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document documento = new Document(pdfDoc, PageSize.LETTER); 
            String realPath="C:\\android\\complete\\";
            String imageFiless = realPath+"citricos.png"; 
            ImageData imagen2=ImageDataFactory.create(getImageQRImage(pallet.getNumero(),"png",130,130)); 
            fechaModificacion= changeMonthForKey(fechaModificacion);
            fechaModificacion= changeDayForKey(fechaModificacion);
            ImageData imagen1 = ImageDataFactory.create(imageFiless);
            Image img1=new Image(imagen1);
            img1.setHeight(90);
            img1.setWidth(130);
            Image img2=new Image(imagen2);
            img2.setHeight(115);
            img2.setWidth(115);
            String dato ="";
            String imagen_calidad_limon =realPath; 
            documento.add(creaTabla(img1,"Fecha["+fechaModificacion+"]",img2,pallet));
            documento.add(new Paragraph("   "));
            documento.add(creaTabla(lista));
            documento.close();
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return documentoBytes;
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
	private static Cell getCell(String cadena)
	{
		Cell cell = new Cell(); 
        cell.add(new Paragraph(cadena));
        cell.setProperty(Property.BOX_SIZING, BoxSizingPropertyValue.BORDER_BOX);
        cell.setBorder(Border.NO_BORDER); 
        cell.setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY);
        return cell;
	}
	private static Table creaTabla(
    Image img1,String fecha,Image img2,Pallet pallet) 
    { 
        Table table1 = new Table(3);
        table1.setBorder(Border.NO_BORDER);
        table1.setWidth(500F);
        table1.addCell("Citricos Cadillos SA de CV");
        table1.addCell("");
        table1.addCell(fecha);
        table1.addCell(img1);
        table1.addCell(""); 
        table1.addCell(img2); 
        table1.addCell(getCell("ID"));
        table1.addCell(getCell("Número de Pallet"));
        table1.addCell(getCell("Cajas"));
        table1.addCell(""+pallet.getId());
        table1.addCell(""+pallet.getNumero());
        
        table1.addCell(""+pallet.getCajas());

        table1.addCell(getCell("Marca"));
        table1.addCell(getCell("Tarima")); 
        table1.addCell(getCell("Transporte"));
        table1.addCell(""+pallet.getTarima());
        table1.addCell(""+pallet.getMarca());
        table1.addCell(""+pallet.getTransporte());
        return table1;
    }
	private static Table creaTabla(ArrayList<Pallet_desc> lista) 
		    { 
		        Table table1 = new Table(5);
		        table1.setBorder(Border.NO_BORDER);
		        table1.setWidth(500F); 
		    	table1.addCell(getCell("ID"));
	        	table1.addCell(getCell("Cajas"));
	        	table1.addCell(getCell("CDI"));
	        	table1.addCell(getCell("Calibre"));
	        	table1.addCell(getCell("Calidad"));
		        for(Pallet_desc desc:lista)
		        {
		        	table1.addCell("" +desc.getId());
		        	table1.addCell("" +desc.getCajas());
		        	table1.addCell("" +desc.getCdi());
		        	table1.addCell("" +desc.getCalibre());
		        	table1.addCell("" +desc.getCalidad());
		        }
		        return table1;
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

	
	public int getId() {
		return id;
	}
	
	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public void setId(int id) {
		this.id = id;
	}


}
