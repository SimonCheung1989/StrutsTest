<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ns2="http://www.chinatax.gov.cn/dataspec/">
	<xsl:output method="html" encoding="UTF-8"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
	<xsl:template match="/">
		<html>
			<body>
			<div style="width:100%;">
				<table class="table-head">
					<tr>
						<th>
							<xsl:value-of select="//ns2:swjgmc" />
						</th>
					</tr>
					<tr>
						<th>
							税务事项通知书（核定定额通知）
						</th>
					</tr>
					<tr>
						<td>
							<xsl:value-of select="//ns2:wszg" />
						</td>
					</tr>
				</table>

				<table class="table-content">
					<tr>
						<td>
							<u><xsl:value-of select="//ns2:nsrmc" /></u>：（纳税人识别号：<xsl:value-of select="//ns2:nsrsbh" />）
						</td>
					</tr>
					<tr>
						<td>
							&#160;
							&#160;
							&#160;
							&#160;
							根据《中华人民共和国税收征收管理法》、《中华人民共和国税收征收管理法实施细则》以及相关规定，经审核你户月应纳税额为<xsl:value-of select="//ns2:zse" />元（分税种税额见下表）。请按规定的期限申报缴纳应纳税款。本通知自<xsl:value-of select="//ns2:hdqnd" />年<xsl:value-of select="//ns2:hdqyf" />月<xsl:value-of select="//ns2:hdqrq" />日起至<xsl:value-of select="//ns2:hdznd" />年<xsl:value-of select="//ns2:hdzyf" />月<xsl:value-of select="//ns2:hdzrq" />日止执行。
						</td>
					</tr>
				</table>
				<table class="table-grid">
					<tr>
						<td colspan="5">
							应纳税额明细表
						</td>
					</tr>
					<tr>
						<td>税种</td>
						<td>应纳税经营（所得）额</td>
						<td>税率（征收率）</td>
						<td>速算扣除数</td>
						<td>月应纳税额（元）</td>
					</tr>
						<xsl:for-each
								select="ZsHddeTzsbdxxVO/ns2:wsdyGrid/ns2:wsdyGridlb">
								<tr>
									<td>
										<xsl:value-of select="ns2:zsxmmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:ynsjye" />
									</td>
									<td>
										<xsl:value-of select="ns2:sl1" />
									</td>
									<td>
										--
									</td>
									<td>
										<xsl:value-of select="ns2:hdse" />
									</td>
								</tr>
							</xsl:for-each>
						<tr>
							<td colspan="5" style="text-align:left;">
								税额合计（大写）：<xsl:value-of select="//ns2:sehj" />
							</td>
						</tr>
				</table>
				<table class="table-content">
					<tr>
						<td>&#160;</td>
					</tr>
					<tr>
						<td>&#160;</td>
					</tr>
					<tr>
						<td style="text-align:right;"> 税务机关（公章）</td>
					</tr>
					<tr>
						<td style="text-align:right;">
							<xsl:value-of select="//ns2:zznd" />年<xsl:value-of select="//ns2:zzyf" />月<xsl:value-of select="//ns2:zzrq" />日
						</td>
					</tr>
				</table>
				<p>
					<stampPosition defaultX="19">+CTAIS_SIGNATURE-</stampPosition>
				</p>
				<table class="table-content" style="text-align:left;">
					<tr>
						<td>
							告知事项：
						</td>
					</tr>
					<tr>
						<td>
							1、定额有效期间内，如你户月应纳税经营（所得）额超过税务机关的核定定额百分之<xsl:value-of select="//ns2:tzscs1" />的，应当在法律、行政法规定的申报期限内，向主管税务机关进行申报并缴纳税款。
						</td>
					</tr>
					<tr>
						<td>
							2、定期定额户应当在定额有效期满后<xsl:value-of select="//ns2:tzscs2" />日内，以该期每月实际发生的经营额、所得额向主管税务机关进行汇总申报。
						</td>
					</tr>
					<tr>
						<td>
							3、不按规定的期限进行申报或缴纳应纳税款的，税务机关将依法予以处罚。
						</td>
					</tr>
					<tr>
						<td>
							4、对税务机关核定的定额有争议的，可以在接到《核定定额通知书》之日起30日内向主管税务机关提出重新核定定额申请。也可以按照法律、行政法规的规定提起行政复议或诉讼。
						</td>
					</tr>
				</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
