package com.simon.digitalsignatures;

import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;

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
 * @description 条形码/二维码工具类
 */
public class BarCodeTool {

	/**
	 * 获取一个二维码
	 * @param content
	 * @param width
	 * @param height
	 * @param hints
	 * @return
	 */
	public static Image getBarcodeQRCode(String content, int width, int height,
			Map<EncodeHintType, Object> hints) {
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(content, width, height,
				hints);
		Image image = null;
		try {
			image = barcodeQRCode.getImage();
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		return image;
	}
}
