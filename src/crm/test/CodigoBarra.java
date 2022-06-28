package crm.test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.swing.JPanel;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class CodigoBarra{
	
	public JPanel usingBarbecueAsSwingComponent(String code) {
		JPanel panel = new JPanel();

		// Always get a Barcode from the BarcodeFactory
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode128C(code);
        } catch (BarcodeException e) {
            // Error handling
        }

        /* Because Barcode extends Component, you can use it just like any other
		 * Swing component. In this case, we can add it straight into a panel
		 * and it will be drawn and layed out according to the layout of the panel.
		 */
		panel.add(barcode);
		
		return panel;
	}

	public void drawingBarcodeDirectToGraphics() throws BarcodeException, OutputException {
		// Always get a Barcode from the BarcodeFactory
		Barcode barcode = BarcodeFactory.createCode128B("My Barcode");

		// We are created an image from scratch here, but for printing in Java, your
		// print renderer should have a Graphics internally anyway
		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_BYTE_GRAY);
		// We need to cast the Graphics from the Image to a Graphics2D - this is OK
		Graphics2D g = (Graphics2D) image.getGraphics();

		// Barcode supports a direct draw method to Graphics2D that lets you position the
		// barcode on the canvas
		barcode.draw(g, 10, 56);
	}

    public void outputtingBarcodeAsPNG(String code) throws BarcodeException {
        // get a Barcode from the BarcodeFactory
		Barcode barcode = BarcodeFactory.createCode128B(code);

        try {
            File f = new File("C:\\Documents and Settings\\saciar\\Mis documentos\\Mi música\\mybarcode.jpeg");

            // Let the barcode image handler do the hard work
            BarcodeImageHandler.saveJPEG(barcode, f);
        } catch (Exception e) {
            // Error handling here
        }
    }
    
    public void imprimir(String code){
    	try
    	{
    		Barcode b = BarcodeFactory.createCode128(code);
    		PrinterJob job = PrinterJob.getPrinterJob();
    		job.setPrintable(b);
    		if (job.printDialog())
    		{
    			job.print();
    		}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}  

