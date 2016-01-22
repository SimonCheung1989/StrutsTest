package com.simon.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.simon.digitalsignatures.SignatureVO;
import com.simon.swsxtzs.Swsxtzs;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 * 测试类
 */
public class SwsxtzsTest extends Thread{

	private static final String ZHGS_IMG_PATH = SwsxtzsTest.class.getResource("/").getPath()+"xz.png";
	private static final String ZHGS_PFX_PATH = SwsxtzsTest.class.getResource("/").getPath()+"ZHGS.pfx";
	private static Logger log = Logger.getLogger(SwsxtzsTest.class);

	private int no;
	
	public SwsxtzsTest(){
	}

	@Override
	public void run() {
		try {
			for(int i=0; i<1; i++){
					
				String[] array = {"BDA0210092", "BDA0410008", "BDA0410009", "BDA0420115", "BDA0710009", "BDA0710010", "BDA1220074", "BDA1220079", "BDA1310012", "BDA1320012"};
				
//				String[] array = {"BDA1220074"};
				String xmlPath = "";
				String pdfPath = "";
				String signedPdfPath = "";
				
				for(String dm : array){
					Swsxtzs swsxtzs = (Swsxtzs) Class.forName("com.simon.swsxtzs."+dm).newInstance();
					xmlPath = SwsxtzsTest.class.getResource("/").getPath()+"swsx/"+ dm + "/" + dm + ".xml";
					pdfPath = "D:/temp/pdf/swsx/" + dm +"-"+no+ i+ ".pdf";
					signedPdfPath = "D:/temp/pdf/swsx/signed_" + dm +"-"+no+ i+ ".pdf";
					
					
					SignatureVO signatureVO = new SignatureVO();
					signatureVO.setCertificatePath(ZHGS_PFX_PATH);
					signatureVO.setPrivateKeyPassword("123456");
					signatureVO.setSignatureImage(Image.getInstance(ZHGS_IMG_PATH));

					
					swsxtzs.createPdf(new FileInputStream(xmlPath), pdfPath,
							signedPdfPath, signatureVO);
					log.debug("----------------------");
				}
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test() throws Exception{
		String[] array = {"BDA0210092", "BDA0410008", "BDA0410009", "BDA0420115", "BDA0710009", "BDA0710010", "BDA1220074", "BDA1220079", "BDA1310012", "BDA1320012"};
//		String[] array = {"BDA1220074"};
		String xmlPath = "";
		String pdfPath = "";
		String signedPdfPath = "";
		
		for(String dm : array){
			Swsxtzs swsxtzs = (Swsxtzs) Class.forName("com.simon.swsxtzs."+dm).newInstance();
			xmlPath = SwsxtzsTest.class.getResource("/").getPath()+"swsx/"+ dm + "/" + dm + ".xml";
			pdfPath = "D:/temp/pdf/swsx/" + dm +"-"+no+ ".pdf";
			signedPdfPath = "D:/temp/pdf/swsx/signed_" + dm +"-"+no+ ".pdf";
			
			
			SignatureVO signatureVO = new SignatureVO();
			signatureVO.setCertificatePath(ZHGS_PFX_PATH);
			signatureVO.setPrivateKeyPassword("123456");
			signatureVO.setSignatureImage(Image.getInstance(ZHGS_IMG_PATH));

			
			swsxtzs.createPdf(new FileInputStream(xmlPath), pdfPath,
					signedPdfPath, signatureVO);
			log.debug("----------------------");
		}
	}

}
