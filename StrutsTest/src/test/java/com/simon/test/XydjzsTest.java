package com.simon.test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.simon.digitalsignatures.BarCodeTool;
import com.simon.digitalsignatures.Html2Pdf;
import com.simon.digitalsignatures.SignaturePdf;
import com.simon.digitalsignatures.SignatureVO;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月1日
 * @version 1.0
 * @description
 * 测试类
 */
public class XydjzsTest {
	private static final String XSL_PATH           = XydjzsTest.class.getResource("/").getPath()+"xydjz.xsl";
	private static final String BG_IMG_PATH        = XydjzsTest.class.getResource("/").getPath()+"xydjz_bg.jpg";
	private static final String ZHGS_IMG_PATH      = XydjzsTest.class.getResource("/").getPath()+"zhgs_yz.png";
	private static final String ZHDS_IMG_PATH      = XydjzsTest.class.getResource("/").getPath()+"zhds_yz.png";
	private static final String PDF_PATH           = "D:/temp/pdf/test_src.pdf";
	private static final String SIGNED_PDF_PATH    = "D:/temp/pdf/signed_test_dest.pdf";
	private static final String ZHGS_PFX_PATH      = XydjzsTest.class.getResource("/").getPath()+"ZHGS.pfx";
	private static final String ZHDS_PFX_PATH      = XydjzsTest.class.getResource("/").getPath()+"ZHGS.pfx";
	
	@Test
	public void test() throws Exception {
		StringBuffer xml = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"gbk\"?>");
		xml.append("<ROOT>");
		xml.append(" 	<ZSBH>144011111111111111111</ZSBH>");
		xml.append(" 	<XYDJ>A</XYDJ>                     ");
		xml.append(" 	<NSRMC>珠海市格力集团</NSRMC>    ");
		xml.append(" 	<ND>2015</ND>                      ");
		xml.append(" 	<CSMC>珠海市</CSMC>                      ");
		xml.append(" </ROOT>");

		String htmlContent = Html2Pdf.getHtmlByXmlXsl(new ByteArrayInputStream(xml.toString()
				.getBytes("GBK")), new FileInputStream(XSL_PATH));
		System.out.println(htmlContent);
		
		Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
		Html2Pdf.createPdfWithHtml(document, htmlContent, PDF_PATH);
		Image bgImage = Image.getInstance(BG_IMG_PATH);
		bgImage.setAbsolutePosition(0, 0);
		
		System.out.println(PageSize.A4.getHeight() +" "+PageSize.A4.getWidth());
		bgImage.scaleAbsolute(PageSize.A4.getHeight(), PageSize.A4.getWidth());
		document.add(bgImage);
		
		String param = java.net.URLEncoder.encode("中国","utf-8");  
		Image qrImage = BarCodeTool.getBarcodeQRCode("http://localhost:8080/WebTest/getDzzs.do?nsrsbh=1255555555555555454Ewe345678&nsrmc="+param, 0, 0, null);
		qrImage.setAbsolutePosition(100, 35);
		qrImage.scaleAbsolute(80, 80);
		document.add(qrImage);
		document.close();
		
		SignatureVO signatureVO = new SignatureVO();
		signatureVO.setCertificatePath(ZHGS_PFX_PATH);
		signatureVO.setPrivateKeyPassword("123456");
		signatureVO.setSignatureImage(Image.getInstance(ZHGS_IMG_PATH));
		signatureVO.setSignatureRectangle(new Rectangle(490, 120, 640, 270));
		signatureVO.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
		SignaturePdf.signPdfWithCAert(PDF_PATH, SIGNED_PDF_PATH, signatureVO);
		
//		signatureVO = new SignatureVO();
//		signatureVO.setCertificatePath(ZHDS_PFX_PATH);
//		signatureVO.setPrivateKeyPassword("123456");
//		signatureVO.setSignatureImage(Image.getInstance(ZHDS_IMG_PATH));
//		signatureVO.setSignatureRectangle(new Rectangle(590, 120, 740, 270));
//		signatureVO.setSignatureLocation("珠海国税局");
//		signatureVO.setSignatureReason("电子信用证书");
//		signatureVO.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
//		SignaturePdf.signPdfWithCAert(SIGNED_PDF_PATH, PDF_PATH, signatureVO);
//		
//		signatureVO = new SignatureVO();
//		signatureVO.setCertificatePath(PFX_SIMON_PATH);
//		signatureVO.setPrivateKeyPassword("123456");
//		signatureVO.setSignatureImage(Image.getInstance(IMG_PATH));
//		signatureVO.setSignatureRectangle(new Rectangle(100, 100, 220, 220));
//		signatureVO.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
//		SignaturePdf.signPdfWithCAert(PDF_PATH, SIGNED_PDF_PATH, signatureVO);
	}
	
}
