package online.xiaohei.utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Test;

import java.io.*;


public final class ReadPdf {

    private ReadPdf() {

    }

    public static String[] getLines(File file) {
        String[] lines = null;
        try {
            PDDocument document = PDDocument.load(file);
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper textStripper = new PDFTextStripper();
                String pdfFileInText = textStripper.getText(document);
                lines = pdfFileInText.split("\\r?\\n");
                return lines;
            }
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Test
    public static void testGetLines() {
        String[] lines = ReadPdf.getLines(new File("F:/2-2/排队论论文/Queueing Analysis of Unicast IPTV With Adaptive Modulation and Coding in Wireless Cellular Networks.pdf"));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s);
        }
        File file = new File("C:/Users/zy/Desktop/test2.txt");
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ps.print(stringBuilder.toString());
    }
}
