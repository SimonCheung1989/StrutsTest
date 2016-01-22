package com.simon.test;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class WatermarkPdfTest {
	
	private static final String filename = "D:\\temp\\pdf\\ttt.pdf";

	public static void main(String[] args) throws Exception {
		Document document = new Document(PageSize.A4.rotate(), 25, 25, 60, 50);
		PdfWriter writer = PdfWriter.getInstance(
		document, new FileOutputStream(filename));
		Watermark event = new WatermarkPdfTest.Watermark();
		document.open();
		document.add(new Phrase("aaa"));
		document.newPage();
		document.add(new Phrase("aaa"));
		document.newPage();
		document.add(new Phrase("aaa"));
		writer.setPageEvent(event);
		writer.flush();
		document.close();
		
	}

	static class Watermark extends PdfPageEventHelper {
		Font FONT = new Font(FontFamily.HELVETICA, 52, Font.BOLD,
				new GrayColor(0.75f));
		
		public void onEndPage(PdfWriter writer, Document document) {
//			ColumnText.showTextAligned(writer.getDirectContentUnder(),
//					Element.ALIGN_CENTER, new Phrase("FOOBAR FILM FESTIVAL",
//							FONT), 297.5f, 421,
//					writer.getPageNumber() % 2 == 1 ? 45 : -45);
//			
			PdfContentByte under = writer.getDirectContentUnder();
			try {
				Image image = Image.getInstance("D:\\temp\\pdf\\aa.jpg");
				
				int total = writer.getPageNumber()+1;
				for(int i=0; i<total; i++){
					image.setAbsolutePosition(0, 300*i);
					under.addImage(image);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
