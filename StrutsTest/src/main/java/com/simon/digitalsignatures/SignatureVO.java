package com.simon.digitalsignatures;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;

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
 * @description 数字签名VO
 */
public class SignatureVO {

	private String certificatePath; 	// 证书路径
	private String privateKeyPassword; 	// 私钥密码
	private String tsaUrl; 				// TSA服务地址
	private String tsaUser; 			// TSA服务用户名
	private String tsaPassword; 		// TSA服务密码
	private String signatureReason;		// 签名原因
	private String signatureLocation; 	// 签名地址
	private Image signatureImage; 		// 签名图片
	private Rectangle signatureRectangle;	// 签名位置
	private String signatureField;		// 签名位置
	private int signaturePage;			//签名页
	private int certificationLevel = PdfSignatureAppearance.NOT_CERTIFIED;	//签名级别
	
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public String getPrivateKeyPassword() {
		return privateKeyPassword;
	}
	public void setPrivateKeyPassword(String privateKeyPassword) {
		this.privateKeyPassword = privateKeyPassword;
	}
	public String getTsaUrl() {
		return tsaUrl;
	}
	public void setTsaUrl(String tsaUrl) {
		this.tsaUrl = tsaUrl;
	}
	public String getTsaUser() {
		return tsaUser;
	}
	public void setTsaUser(String tsaUser) {
		this.tsaUser = tsaUser;
	}
	public String getTsaPassword() {
		return tsaPassword;
	}
	public void setTsaPassword(String tsaPassword) {
		this.tsaPassword = tsaPassword;
	}
	public String getSignatureReason() {
		return signatureReason;
	}
	public void setSignatureReason(String signatureReason) {
		this.signatureReason = signatureReason;
	}
	public String getSignatureLocation() {
		return signatureLocation;
	}
	public void setSignatureLocation(String signatureLocation) {
		this.signatureLocation = signatureLocation;
	}
	public Image getSignatureImage() {
		return signatureImage;
	}
	public void setSignatureImage(Image signatureImage) {
		this.signatureImage = signatureImage;
	}
	public Rectangle getSignatureRectangle() {
		return signatureRectangle;
	}
	public void setSignatureRectangle(Rectangle signatureRectangle) {
		this.signatureRectangle = signatureRectangle;
	}
	public String getSignatureField() {
		return signatureField;
	}
	public void setSignatureField(String signatureField) {
		this.signatureField = signatureField;
	}
	public int getSignaturePage() {
		return signaturePage;
	}
	public void setSignaturePage(int signaturePage) {
		this.signaturePage = signaturePage;
	}
	public int getCertificationLevel() {
		return certificationLevel;
	}
	public void setCertificationLevel(int certificationLevel) {
		this.certificationLevel = certificationLevel;
	}

}
