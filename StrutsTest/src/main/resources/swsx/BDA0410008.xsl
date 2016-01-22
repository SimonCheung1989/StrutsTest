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
							<th>税务事项通知书</th>
						</tr>
						<tr>
							<td>
								<xsl:value-of select="//ns2:djh" />
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
								事由：<xsl:value-of select="//ns2:sy" />
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								依据：<xsl:value-of select="//ns2:yj" />。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								通知内容：<xsl:value-of select="//ns2:tznr" />
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
								<xsl:value-of select="//ns2:qsrq" />
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
