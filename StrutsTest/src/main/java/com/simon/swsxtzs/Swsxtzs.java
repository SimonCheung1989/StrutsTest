package com.simon.swsxtzs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDiv;
import com.simon.digitalsignatures.SignatureVO;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	Administrator
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 * 税务事项通知书抽象类
 */
public abstract class Swsxtzs {
	private String charset = "UTF-8";
	
	private String xslBasePath = Swsxtzs.class.getResource("/").getPath()+"swsx";
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	public String getXslBasePath() {
		return xslBasePath;
	}

	public void setXslBasePath(String xslBasePath) {
		this.xslBasePath = xslBasePath;
	}
	
	/**
	 * 获取签名Y坐标位置
	 * @param document	pdf文档
	 * @param documentBottomY pdf bottom
	 * @param sigOffsetY	签名位置距离pdf最后一行内容偏移量
	 * @param sigHeight	签名图片高度
	 * @return
	 */
	protected float getSigBottomY(Document document, float documentBottomY,
			float sigOffsetY, float sigHeight) {
		float sigBottomY = (documentBottomY + sigOffsetY) > 0 ? (documentBottomY + sigOffsetY)
				: document.bottomMargin();
		float sigTopY = sigBottomY + sigHeight;
		if (sigTopY > document.getPageSize().getHeight()) {
			sigTopY = document.getPageSize().getHeight()
					- document.topMargin();
			sigBottomY = sigTopY - sigHeight;
		}
		return sigBottomY;
	}
	
	/**
	 * 给pdf添加空白div
	 * @param document
	 * @return
	 */
	protected PdfDiv addBottomDiv(Document document){
		PdfDiv div = new PdfDiv();
		div.setPercentageHeight(100f);
		div.setPercentageWidth(100f);
		try {
			document.add(div);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return div;
	}
	
	/**
	 * 获取XSL模板文件
	 * @return
	 * @throws FileNotFoundException
	 */
	protected InputStream getXslInputStream() throws FileNotFoundException{
		return new FileInputStream(this.getXslBasePath()+"/"+this.getClass().getSimpleName()+".xsl");
	}
	
	/**
	 * 获取CSS样式文件内容
	 * @param cssPath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	protected String getCssContent() throws Exception{
		String cssPath = this.getXslBasePath()+"/swsx.css";
		InputStreamReader cssReader = new InputStreamReader(new FileInputStream(cssPath), "UTF-8");
		BufferedReader cssBfReader = new BufferedReader(cssReader);
		StringBuilder cssSb = new StringBuilder();
		String str = "";
		while((str=cssBfReader.readLine())!=null){
			cssSb.append(str);
		}
		return cssSb.toString();
	}
	/**
	 * 创建pdf文件
	 * @param inputXml
	 *            xml数据流
	 * @param pdfPath
	 *            生成pdf文件路径
	 * @param signedPdfPath
	 *            签名后的pdf路径
	 * @param signatureVO
	 *            签名VO
	 * @throws Exception 
	 */
	public abstract void createPdf(InputStream inputXml, String pdfPath,
			String signedPdfPath, SignatureVO signatureVO) throws Exception;

}
