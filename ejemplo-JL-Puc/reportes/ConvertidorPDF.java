package reportes;

import java.awt.print.PrinterJob;
import java.io.FileReader;

import com.qoppa.pdfWriter.PDFPrinterJob;

public class ConvertidorPDF {

    public void generarReporte(){

        try
        {	
			GenerarTxt txt = new GenerarTxt();
			txt.crearTxt();
    	    // Load a text file into a string
    	    FileReader inputFile = new FileReader ("ejemplo-JL-Puc/reportes/txtTemporal/Reporte_Clientes.txt");
    	    StringBuffer fileContent = new StringBuffer ();
    	    char [] buffer = new char [256];
    	    int charsRead = inputFile.read(buffer);
    	    while (charsRead > 0)
    	    {
    	        fileContent.append(buffer, 0, charsRead);
    	        charsRead = inputFile.read (buffer);
    	    }
    	    
    	    // Create object to print the text
    		TextPrinter textPrinter = new TextPrinter (fileContent);
    		
    		// Create a PDF printer job and print the text to it
    		PrinterJob printerJob = PDFPrinterJob.getPrinterJob();
    		printerJob.setPrintable(textPrinter);
			printerJob.print();

		}
		catch (Throwable t)
		{
		    t.printStackTrace();
		}
        // Exit
		System.exit(0);
    }
    
		
		
}
