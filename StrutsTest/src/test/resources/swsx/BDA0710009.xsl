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
							税务事项通知书（未达起征点通知）
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
							经审核，你户月应纳税经营额为<xsl:value-of select="//ns2:ynsjye" />元，未达<xsl:value-of select="//ns2:sz" />起征点，从<xsl:value-of select="//ns2:qnd" />年<xsl:value-of select="//ns2:qyf" />月<xsl:value-of select="//ns2:qrq" />日起至<xsl:value-of select="//ns2:znd" />年<xsl:value-of select="//ns2:zyf" />月<xsl:value-of select="//ns2:zrq" />日止暂不缴纳<xsl:value-of select="//ns2:sz" />。在此期间，如你户月应纳税经营额达到增值税起征点（<xsl:value-of select="//ns2:hdse" />元/月），应于次月15日前向主管税务机关如实申报缴纳税款，连续三个月达到起征点，应当向主管税务机关提请重新核定定额。否则，税务机关将依法进行处理。
						</td>
					</tr>
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
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
