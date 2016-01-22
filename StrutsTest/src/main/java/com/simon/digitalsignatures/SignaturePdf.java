package com.simon.digitalsignatures;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.CrlClient;
import com.itextpdf.text.pdf.security.CrlClientOnline;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.OcspClient;
import com.itextpdf.text.pdf.security.OcspClientBouncyCastle;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import com.itextpdf.text.pdf.security.TSAClient;
import com.itextpdf.text.pdf.security.TSAClientBouncyCastle;

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
 * @description 给pdf文档签名
 */
public class SignaturePdf {
	public static String DIGEST_ALGORITHMS = DigestAlgorithms.SHA256;

	public static void signPdfWithCAert(String oriPdfPath, String signedPdfPath, SignatureVO signatureVO) throws Exception {
		String path = signatureVO.getCertificatePath();
		char[] pass = signatureVO.getPrivateKeyPassword().toCharArray();
		
		BouncyCastleProvider provider = new BouncyCastleProvider();
		Security.addProvider(provider);
		KeyStore ks = KeyStore.getInstance("pkcs12", provider.getName());
		ks.load(new FileInputStream(path), pass);
		String alias = (String) ks.aliases().nextElement();
		PrivateKey pk = (PrivateKey) ks.getKey(alias, pass);
		Certificate[] chain = ks.getCertificateChain(alias);
		// 吊销证书在线检查
		List<CrlClient> crlList = new ArrayList<CrlClient>();
		crlList.add(new CrlClientOnline(chain));

		// OCSP协议检查证书
		OcspClient ocspClient = new OcspClientBouncyCastle();

		// 创建时间戳客户端(TSA 服务)
		String tsaUrl = signatureVO.getTsaUrl();
		String tsaUser = signatureVO.getTsaUser();
		String tsaPassword = signatureVO.getTsaPassword();
		TSAClientBouncyCastle tsaClient = null;
		if(tsaUrl != null && !tsaUrl.trim().equals("")){
			if(tsaUser != null && !tsaUser.trim().equals("")){
				tsaClient = new TSAClientBouncyCastle(tsaUrl,
						tsaUser, tsaPassword);
			}else{
				tsaClient = new TSAClientBouncyCastle(tsaUrl);
			}
		}

		sign(oriPdfPath, signedPdfPath, chain, pk, DIGEST_ALGORITHMS,
				provider.getName(), CryptoStandard.CMS, signatureVO, crlList,
				ocspClient, tsaClient, 0);
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @param chain
	 * @param pk
	 * @param digestAlgorithm
	 * @param provider
	 * @param subfilter
	 * @param signatureVO
	 * @param crlList
	 * @param ocspClient
	 * @param tsaClient
	 * @param estimatedSize
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void sign(String src, String dest, Certificate[] chain,
			PrivateKey pk, String digestAlgorithm, String provider,
			CryptoStandard subfilter, SignatureVO signatureVO,
			Collection<CrlClient> crlList, OcspClient ocspClient,
			TSAClient tsaClient, int estimatedSize)
			throws GeneralSecurityException, IOException, DocumentException {
		// Creating the reader and the stamper
		PdfReader reader = new PdfReader(src);
		FileOutputStream os = new FileOutputStream(dest);
		PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
		// Creating the appearance
		PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
		appearance.setReason(signatureVO.getSignatureReason());
		appearance.setLocation(signatureVO.getSignatureLocation());
		Image image = signatureVO.getSignatureImage();
		appearance.setSignatureGraphic(image);
		appearance
				.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
		appearance.setCertificationLevel(signatureVO.getCertificationLevel());
		if(signatureVO.getSignatureField()!=null && !"".equals(signatureVO.getSignatureField())){
			appearance.setVisibleSignature(signatureVO.getSignatureField());
		}else{
			appearance.setVisibleSignature(signatureVO.getSignatureRectangle(), (signatureVO.getSignaturePage()>1)?signatureVO.getSignaturePage():1,
					null);
		}
		// Creating the signature
		ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm,
				provider);
		ExternalDigest digest = new BouncyCastleDigest();
		MakeSignature.signDetached(appearance, digest, pks, chain, crlList,
				ocspClient, tsaClient, estimatedSize, subfilter);
	}

}
