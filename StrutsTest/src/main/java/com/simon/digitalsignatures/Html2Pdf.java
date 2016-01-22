package com.simon.digitalsignatures;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * 
 * <p>
 * Company: 税友软件集团有限公司
 * </p>
 * <p>
 * Copyright: Copyright(c) 2009-2018
 * </p>
 * 
 * @author zhangww
 * @date 2015年8月1日
 * @version 1.0
 * @description
 */
public class Html2Pdf {

	/**
	 * 将html信息填充到document
	 * 
	 * @param document
	 * @param htmlContent
	 * @param pdfPath
	 * @return
	 * @throws Exception
	 */
	public static Document createPdfWithHtml(Document document,
			String htmlContent, String pdfPath) throws Exception {
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(pdfPath));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new StringReader(htmlContent));
		return document;
	}

	/**
	 * 将html按照css样式填充到document
	 * @param document
	 * @param htmlContent
	 * @param cssContent
	 * @param pdfPath
	 * @return
	 * @throws Exception
	 */
	public static Document createPdfWithHtml(Document document,
			String htmlContent, String cssContent, String pdfPath)
			throws Exception {
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(pdfPath));
		document.open();
		ByteArrayInputStream bis = new ByteArrayInputStream(htmlContent
				.toString().getBytes());
		ByteArrayInputStream cis = new ByteArrayInputStream(cssContent
				.toString().getBytes());
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, bis, cis);
		return document;
	}

	/**
	 * 通过xml、xsl获取html字符串
	 * 
	 * @param xml
	 * @param xsl
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlByXmlXsl(InputStream xml, InputStream xsl)
			throws Exception {
		StringWriter writer = new StringWriter();
		Source xmlSource = new StreamSource(xml);
		Source xsltSource = new StreamSource(xsl);

		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer trans = transFact.newTransformer(xsltSource);
		trans.transform(xmlSource, new StreamResult(writer));
		return writer.getBuffer().toString();
	}
}
