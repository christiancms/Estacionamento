package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author christian
 */
public class ImprimirRecibo extends JFrame {

    public void imprime(String nomeArq) {

        PrintService[] printService = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();

        try {
            FileInputStream fileInputStream = new FileInputStream(nomeArq);
            Doc doc = new SimpleDoc(fileInputStream, docFlavor, hashDocAttributeSet);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            PrintService printServico = ServiceUI.printDialog(null, 300, 300, printService,
                    impressoraPadrao, docFlavor, printRequestAttributeSet);

            if (printServico != null) {

                DocPrintJob docPrintJob = printServico.createPrintJob();
                try {
                    docPrintJob.print(doc, printRequestAttributeSet);
                } catch (PrintException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
}
