package com.simon.swsxtzs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfReader;
import com.simon.digitalsignatures.Html2Pdf;
import com.simon.digitalsignatures.SignaturePdf;
import com.simon.digitalsignatures.SignatureVO;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 */
public class BDA0420115 extends Swsxtzs{
	private static Log log = LogFactory.getLog(BDA0420115.class);
	
	@Override
	public void createPdf(InputStream inputXml, String pdfPath, String signedPdfPath, SignatureVO signatureVO) {
		try {
			List<XmlNodeTransformer> list = new ArrayList<XmlNodeTransformer>();
			XmlNodeTransformer transformer = new XmlNodeTransformer() {
				@Override
				public void transformNode(org.dom4j.Document document) {
					// TODO Auto-generated method stub
					
				}
			};
			list.add(transformer);
			String xmlStr = XmlNodeDeal.dealXml(inputXml, list);
			log.debug("----------------swsx.xml(处理后)---------------------");
			log.debug(xmlStr);

			String htmlContent = Html2Pdf.getHtmlByXmlXsl(new ByteArrayInputStream(xmlStr.getBytes(this.getCharset())), this.getXslInputStream());
			log.debug("----------------swsx.html---------------------");
			log.debug(htmlContent);
			Document document = new Document(PageSize.A4.rotate(), 25, 25, 60, 50);
			Html2Pdf.createPdfWithHtml(document, htmlContent, this.getCssContent(), pdfPath);
			
			PdfDiv div = this.addBottomDiv(document);
			
			document.close();
			
			int totalPage = new PdfReader(pdfPath).getNumberOfPages();
			if(signatureVO != null){
				float sigBottomY = this.getSigBottomY(document, div.getYLine(), -50, 110);
				signatureVO.setSignatureRectangle(new Rectangle(580, sigBottomY, 690, sigBottomY+110));
				signatureVO.setSignaturePage(totalPage);
				SignaturePdf.signPdfWithCAert(pdfPath, signedPdfPath, signatureVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
