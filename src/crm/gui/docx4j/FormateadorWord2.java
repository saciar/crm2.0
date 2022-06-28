package crm.gui.docx4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.xml.bind.JAXBElement;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.Color;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcMar;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.docx4j.wml.TcPrInner.VMerge;

import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.util.MessageUtil;

public class FormateadorWord2 {
	private WordprocessingMLPackage wordMLPackage; 
	private String nroPpto;
	private ObjectFactory factory;
	private String color;
	
	public String getNroPpto() {
		return nroPpto;
	}

	public void setNroPpto(String nroPpto) {
		this.nroPpto = nroPpto;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public FormateadorWord2() {
		try {
			/*URL url =Main.class.getResource("imagenes/Presupuesto.docx"); 
			System.out.println(url.getPath());
			System.out.println(url.toURI());
			System.out.println(url.toURI().getPath());
			wordMLPackage = getTemplate(url.toURI().getPath());*/
			
			//wordMLPackage = getTemplate(getClass().getResourceAsStream("/imagenes/Presupuesto.docx"));
			wordMLPackage = WordprocessingMLPackage.createPackage();
			factory = Context.getWmlObjectFactory();
			} catch (Docx4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
	}
	
	public void addNropPpto(){
		replacePlaceholder(wordMLPackage, "Presupuesto "+nroPpto, "nroppto");
		//wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Titulo", "Presupuesto Nro "+nroPpto);
	}
	
	public void addFecha(){
		DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL);
		replacePlaceholder(wordMLPackage, "Buenos Aires, "+df4.format(new Date()), "lugarFecha");
	}
	
	
	public void guardarDoc(){
		/*try {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			
			chooser.setDialogTitle("Seleccione el directorio para guardar el presupuesto nro "+nroPpto);
			
			//Indicamos lo que podemos seleccionar
			//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						
			int seleccion=chooser.showSaveDialog(new JWindow());
			 
			//Si el usuario, pincha en aceptar
			if(seleccion==JFileChooser.APPROVE_OPTION){
				
				writeDocxToStream(wordMLPackage, chooser.getSelectedFile().getPath()+"/Presupuesto_"+nroPpto+".docx");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Util.errMsg(Main.getVentana(), e.getMessage(), e);
			e.printStackTrace();
			
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			Util.errMsg(Main.getVentana(), e.getMessage(), e);
			e.printStackTrace();
		}*/
		
		
		try {			
			String direccion=getPath(System.getProperty("user.home"));
			String sSistemaOperativo = System.getProperty("os.name");
			if(sSistemaOperativo.startsWith("Win")){
				writeDocxToStream(wordMLPackage, direccion+"/Mis documentos/presupuestosCRM/Presupuesto_"+nroPpto+".docx");
				MessageUtil.showMessage("Se guardo el presupuesto en la carpeta: /Mis documentos/presupuestosCRM");
			}
			else if(sSistemaOperativo.startsWith("Mac")){
				writeDocxToStream(wordMLPackage, direccion+"/Documents/presupuestosCRM/Presupuesto_"+nroPpto+".docx");
				MessageUtil.showMessage("Se guardo el presupuesto en la carpeta: /Documents/presupuestosCRM");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MessageUtil.showErrorMessage("No se encontro el directorio. \n"+e.getMessage());
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			MessageUtil.showErrorMessage("No se pudo armar el reporte en DOCX. "+e.getMessage());
		}
	}
	
	public String getPath(String p)
    {
        String dir="";
        char d=((char)92);
        for(int x=0;x<p.length();x++)
        {
            char l=p.charAt(x);
            if(d==l)
            {
                dir+="\\";
            }
            else
            {
                dir+=l+"";
            }
        }
        return dir;
    }
	
	//**********************************************PARRAFOS*****************************************************************************************
	
	public void agregarParrafo(String p){
		wordMLPackage.getMainDocumentPart().addParagraphOfText(p);
	}
	
	public void agregarParrafoMultilinea(String[] renglones){
		P  p = factory.createP();
		R  run = factory.createR();
		p.getContent().add(run);        
           
		
        for(String renglon: renglones){
        	Text  text1 = factory.createText();
            text1.setValue(renglon);

            run.getContent().add(text1 );           
            
            run.getContent().add(factory.createBr() );
        }
        wordMLPackage.getMainDocumentPart().addObject(p);
	}
	
	public void agregarCabeceraSalas(String sala,String fecha,String tam){
		P paragraph = factory.createP();
		
		Text text = factory.createText();
		text.setValue(sala);		
		Text text2 = factory.createText();
		text.setValue(fecha);
		
		R  run = factory.createR();		
		run.getContent().add(text);	
		run.getContent().add(factory.createBr());		
		//run.getContent().add(text2);
		paragraph.getContent().add(run);
		paragraph.getContent().add(text2);
		RPr runProperties = factory.createRPr();
		
		
		HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(tam));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        
        Color c = new Color();
        c.setVal(color);
        runProperties.setColor(c);
        
        run.setRPr(runProperties);
		
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}
	
	public void agregarParrafoColor(String p,String s){
		P paragraph = factory.createP();
		Text text = factory.createText();
		text.setValue(p);
		
		R  run = factory.createR();
		run.getContent().add(text);	
		
		//***************************+
		PPr paragraphProperties = factory.createPPr();
		Jc justification = factory.createJc();
		justification.setVal(JcEnumeration.RIGHT);
		paragraphProperties.setJc(justification);
		paragraph.setPPr(paragraphProperties);
		//***********************
		
		paragraph.getContent().add(run);
		
		RPr runProperties = factory.createRPr();
		
		
		HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(s));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        
        Color c = new Color();
        c.setVal(color);
        runProperties.setColor(c);
        
        run.setRPr(runProperties);
		
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}
	
	public void agregarParrafoFecha(String p,String s){
		P paragraph = factory.createP();
		Text text = factory.createText();
		text.setValue(p);
		
		R  run = factory.createR();
		run.getContent().add(text);	
		paragraph.getContent().add(run);
		
		RPr runProperties = factory.createRPr();
		
		
		HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(s));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        
        Color c = new Color();
        c.setVal("7e7e7e");
        runProperties.setColor(c);
        
        run.setRPr(runProperties);
		
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}
	
	public void agregarParrafoNegrita(String p){
		P paragraph = factory.createP();
		Text text = factory.createText();
		text.setValue(p);
		
		R  run = factory.createR();
		run.getContent().add(text);	
		paragraph.getContent().add(run);
		
		RPr runProperties = factory.createRPr();
		BooleanDefaultTrue b = new org.docx4j.wml.BooleanDefaultTrue();
	    b.setVal(true);	    
	    runProperties.setB(b);
	    
		run.setRPr(runProperties);
		
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}
	
	public void agregarParrafoGrande(String p,String s){
		P paragraph = factory.createP();
		Text text = factory.createText();
		text.setValue(p);
		
		R  run = factory.createR();
		run.getContent().add(text);	
		paragraph.getContent().add(run);
		
		RPr runProperties = factory.createRPr();
		
		
		HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger(s));
        runProperties.setSz(size);
        runProperties.setSzCs(size);
        
        JcEnumeration hAlign = JcEnumeration.RIGHT;
	    
	           PPr pprop = new PPr();
	           Jc align = new Jc();
	           align.setVal(hAlign);
	           pprop.setJc(align);
	           paragraph.setPPr(pprop);
        
        run.setRPr(runProperties);
		
		wordMLPackage.getMainDocumentPart().addObject(paragraph);
	}	
		
	//guarda el .doc donde dice el TARGET
	private void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
		File f = new File(target);
		
			template.save(f);
	}
	
	// guarda todos los elementos del documento, por clase
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();
 
		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			} 
		}
		return result;
	}
	
	//modifica el texto contenido en el PLACEHOLDER por el que dice NOMBRE
	private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
 
		for (Object text : texts) {
			Text textElement = (Text) text;
			if (textElement.getValue().equals(placeholder)) {
				textElement.setValue(name);
			}
		}
	}

	
	//Carga el template del documento .doc*
	private WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		return template;
	}
	
	private WordprocessingMLPackage getTemplate(InputStream name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(name);
		return template;
	}
	
	//**********************************************TABLA*****************************************************************************************
		
	private ServicioManager servManager= ServicioManager.instance();
	private ServicioIdiomaManager servIdiomaManager = ServicioIdiomaManager.instance();
	private Double total = 0.0;
	
	public Double getTotal(){
		return total;
	}
	
	public void crearTabla(Set<Ppto_Sala> salas){		
		 
        Tbl table = factory.createTbl();
        addBorders(table);       
		
        addTitles(table,new String[]{"Sala","Cant.","Servicio","Precio"});

        crearTablaSalas(salas, table);
        
        wordMLPackage.getMainDocumentPart().addObject(table);
	}
	
	public void crearTablaSalas(Set<Ppto_Sala> salas, Tbl table){
		for(Ppto_Sala sala: salas){
			
			crearTablaServicios(sala.getServiciosArray(),sala.getSala().getDescripcion(),table);
		}

	}
	
	private void crearTablaServicios(Object[] servicios, String sala, Tbl table){
		
		for (int i = 0; i < servicios.length; i++) {
			Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) servicios[i];
			String nombreServ="";
			if(serv.getDetalle()!=null){
				nombreServ=serv.getDetalle();
			} else{
				try {
					nombreServ=servIdiomaManager.getDescripcionServicio(serv.getServicio().getCodigo(),"1");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(i==0){
				addTableRowWithMergedCells(sala, serv.getCantidad(),nombreServ,serv.getPrecioDescuento(), table);
			}
			else{
				addTableRowWithMergedCells(null, serv.getCantidad(),nombreServ,serv.getPrecioDescuento(), table);
			}
			total=total+Double.parseDouble(serv.getPrecioDescuento());
		}
	}
	
	private void addTitles(Tbl table, String[] titles){
		Tr tableRow1 = factory.createTr();
        for(String cab: titles){
        	addTitleCell(tableRow1, cab);
        }
 
        table.getContent().add(tableRow1);
	}
	
	private void addTitleCell(Tr tr, String content){
    	Tc tc1 = factory.createTc();
    	
		DocxStyle style = new DocxStyle();
		style.setVerticalAlignment(STVerticalJc.CENTER);
		style.setBold(true);
        style.setItalic(true);
        style.setUnderline(false);
		style.setFontSize("24");
        style.setFontColor("FFFFFF");
        style.setFontFamily("Arial");
        style.setBackground(color);

        style.setHorizAlignment(JcEnumeration.CENTER);
        
        if(content.equals("Precio") || content.equals("Sala")){
        	setCellWidth(tc1,400);
        }
        else if(content.equals("Cant.")){
        	setCellWidth(tc1,150);
        }

		addCellStyle(tc1, content, style);
		
		
		//if (content != null) {
		//	tc1.getContent().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
		//}

		tr.getContent().add(tc1);  
    }
	
	private void addCellStyle(Tc tableCell, String content, DocxStyle style) {
        if (style != null) {
                                   
            P paragraph = factory.createP();
    
            Text text = factory.createText();
            text.setValue(content);
    
            R run = factory.createR();
            run.getContent().add(text);
    
            paragraph.getContent().add(run);
            
            setHorizontalAlignment(paragraph, style.getHorizAlignment());
                    
            RPr runProperties = factory.createRPr();
            
            if (style.isBold()) {
                addBoldStyle(runProperties);
            }
            if (style.isItalic()) {
                addItalicStyle(runProperties);
            }
            if (style.isUnderline()) {
                addUnderlineStyle(runProperties);
            }
            
            setFontSize(runProperties, style.getFontSize());                
            setFontColor(runProperties, style.getFontColor());                
            setFontFamily(runProperties, style.getFontFamily());
                    
            setCellColor(tableCell, style.getBackground());
            setVerticalAlignment(tableCell, style.getVerticalAlignment());

            run.setRPr(runProperties);
    
            tableCell.getContent().add(paragraph);
        }
    }
	
	private void setCellWidth(Tc tableCell, int width) {
        if (width > 0) {
            TcPr tableCellProperties = tableCell.getTcPr();
            if (tableCellProperties == null) {
                tableCellProperties = new TcPr();
                tableCell.setTcPr(tableCellProperties);
            }
            TblWidth tableWidth = new TblWidth();
            tableWidth.setType("dxa");
            tableWidth.setW(BigInteger.valueOf(width));
            tableCellProperties.setTcW(tableWidth);
        }
    }
	
	private void setVerticalAlignment(Tc tableCell, STVerticalJc align) {
        if (align != null) {
            TcPr tableCellProperties = tableCell.getTcPr();
            if (tableCellProperties == null) {
                tableCellProperties = new TcPr();
                tableCell.setTcPr(tableCellProperties);
            }
    
            CTVerticalJc valign = new CTVerticalJc();
            valign.setVal(align);
    
            tableCellProperties.setVAlign(valign);
        }
    } 
	
	private void setFontSize(RPr runProperties, String fontSize) {
	       if (fontSize != null && !fontSize.isEmpty()) {
	          HpsMeasure size = new HpsMeasure();
	          size.setVal(new BigInteger(fontSize));
	          runProperties.setSz(size);
	          runProperties.setSzCs(size);
	       }
	    }
	 
	    private void setFontFamily(RPr runProperties, String fontFamily) {
	       if (fontFamily != null) {
	          RFonts rf = runProperties.getRFonts();
	          if (rf == null) {
	             rf = new RFonts();
	             runProperties.setRFonts(rf);
	          }
	          rf.setAscii(fontFamily);
	       }
	    }
	 
	    private void setFontColor(RPr runProperties, String color) {
	       if (color != null) {
	          Color c = new Color();
	          c.setVal(color);
	          runProperties.setColor(c);
	       } 
	    }
	    	    
	    private void setHorizontalAlignment(P paragraph, JcEnumeration hAlign) {
	        if (hAlign != null) {
	           PPr pprop = new PPr();
	           Jc align = new Jc();
	           align.setVal(hAlign);
	           pprop.setJc(align);
	           paragraph.setPPr(pprop);
	        }
	     }
	    
	    private void setCellColor(Tc tableCell, String color) {
	        if (color != null) {
	            TcPr tableCellProperties = tableCell.getTcPr();
	            if (tableCellProperties == null) {
	                tableCellProperties = new TcPr();
	                tableCell.setTcPr(tableCellProperties);
	            }
	            CTShd shd = new CTShd();
	            shd.setFill(color);
	            tableCellProperties.setShd(shd);
	        }
	    }
	    
	    private void addBoldStyle(RPr runProperties) {
	        BooleanDefaultTrue b = new BooleanDefaultTrue();
	        b.setVal(true);
	        runProperties.setB(b);
	     }

	     private void addItalicStyle(RPr runProperties) {
	        BooleanDefaultTrue b = new BooleanDefaultTrue();
	        b.setVal(true);
	        runProperties.setI(b);
	     }

	     private void addUnderlineStyle(RPr runProperties) {
	        U val = new U();
	        val.setVal(UnderlineEnumeration.SINGLE);
	        runProperties.setU(val);
	     }

	
	/**
     *  En este método se crea una fila, agrega la columna fusionada a ella, y luego
	 *  agregar dos celdas regulares a la misma. Luego añadimos la fila a la tabla.
     */
    private void addTableRowWithMergedCells(String mergedContent, String field3Content,
            String field1Content, String field2Content, Tbl table) {
        Tr tableRow1 = factory.createTr();
        
        addMergedColumn(tableRow1, mergedContent);
 
        addTableCell(tableRow1, field3Content);
        addTableCell(tableRow1, field1Content);
        addTableCell(tableRow1, field2Content);
 
        table.getContent().add(tableRow1);
    }
 
    /**
     *  En este método se añade una celda de columna que se fusionó con celdas de otra
		filas. Si content dado es nulo, se pasa en el contenido de vacío y una fusión
		valor de null
     */
    private void addMergedColumn(Tr row, String content) {
        if (content == null) {
            addMergedCell(row, "", null);
        } else {
            addMergedCell(row, content, "restart");
        }
    }
 
    /**
     *  Creamos una celda de la tabla y luego una celda de tabla objeto de propiedades.
     *  También creamos un objeto de mezcla vertical. Si el valor de combinación no es nulo, 
     *  Lo ponemos en el objeto. Luego añadimos el objeto de fusión para la celda de tabla
     *  Propiedades y agregar las propiedades de la celda de tabla. Por último ponemos el
     *  Contenido en la celda de tabla y añadir la celda de la fila
     *
     *  Si el valor de combinación es 'reinicio', se inicia una nueva fila. Si es nulo,  
     *  Continuaremos con la fila anterior, fusionando las celdas.
     */
    private void addMergedCell(Tr row, String content, String vMergeVal) {
        Tc tableCell = factory.createTc();
        TcPr tableCellProperties = new TcPr();
 
        VMerge merge = new VMerge();
        if(vMergeVal != null){
            merge.setVal(vMergeVal);
        }
        tableCellProperties.setVMerge(merge);       
        
        tableCell.setTcPr(tableCellProperties);
         
        DocxStyle style = new DocxStyle();
		style.setHorizAlignment(JcEnumeration.CENTER);
		style.setBold(false);
        style.setItalic(true);
        style.setUnderline(true);
		style.setFontSize("20");
        style.setFontColor("000000");
        style.setFontFamily("Arial");
        
        addCellStyle(tableCell, content, style);
        
        /*if(content != null) {
                tableCell.getContent().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        }*/
 
        row.getContent().add(tableCell);
    }
 
    /**
     * En este método se añade una celda de tabla a la fila dada con el dado
      * Párrafo como contenido.
     */
    private void addTableCell(Tr tr, String content) {
        Tc tc1 = factory.createTc();
        tc1.getContent().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tr.getContent().add(tc1);
    }
     
    /**
     *  In this method we'll add the borders to the table.
     */
    private void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);
 
        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }
	
}
