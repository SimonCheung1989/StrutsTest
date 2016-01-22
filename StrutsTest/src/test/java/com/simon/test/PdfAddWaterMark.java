package com.simon.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 【功能描述：给PDF 加水印功能，（文字水印和图片水印）】 【功能详细描述：逐条详细描述功能】
 * 
 * @author 【lfssay】
 * @see 【相关类/方法】
 * @version 【类版本号, 2013-8-20 上午11:22:21】
 * @since 【产品/模块版本】
 */
public class PdfAddWaterMark {
	static Log log = LogFactory.getLog(PdfAddWaterMark.class);

	public static void main(String[] args) throws DocumentException,
			IOException {
		new PdfAddWaterMark().addWaterMark("D:\\temp\\pdf\\ttt.pdf",
				"D:\\temp\\pdf\\tttt.pdf", "D:\\temp\\pdf\\aa.jpg", PageSize.A4.rotate().getWidth(), PageSize.A4.rotate().getHeight());
	}

	/**
	 * 
	 * 【功能描述：添加图片和文字水印】 【功能详细描述：功能详细描述】
	 * 
	 * @see 【类、类#方法、类#成员】
	 * @param srcFile
	 *            待加水印文件
	 * @param destFile
	 *            加水印后存放地址
	 * @param imgFile
	 *            加水印图片文件
	 * @param imgWidth
	 *            图片横坐标
	 * @param imgHeight
	 *            图片纵坐标
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void addWaterMark(String srcFile, String destFile,
			String imgFile, float imgWidth,
			float imgHeight) throws IOException, DocumentException {
		// 待加水印的文件
		PdfReader reader = new PdfReader(srcFile);
		// 加完水印的文件
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
				destFile));

		int total = reader.getNumberOfPages() + 1;
		PdfContentByte content;
		float height = PageSize.A4.rotate().getHeight();// 高度
		// 循环对每页插入水印
		HashSet set = new HashSet();
		for (int i = 1; i < total; i++) {
			// 水印的起始
			// 水印在之前文本之上
			Image image = Image.getInstance(imgFile);
			image.setAbsolutePosition(0, 0);
			content = stamper.getUnderContent(i);
			set.add(content);
			if (image != null) {
				content.addImage(image);
			}
		}
		log.info("set size:"+set.size());
		stamper.close();
		log.info("===" + srcFile + "===添加水印到==" + destFile + "==成功=====");
	}
}
