<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ns2="http://www.chinatax.gov.cn/dataspec/">
	<xsl:template match="/">
		<html>
			<body>
				<div style="width:100%;">
					<hr>&#160;</hr>
					<br>&#160;</br>
					<br>&#160;</br>
					<table class="table-head">
						<tr>
							<th>
								<xsl:value-of select="//zgswjgmc" />
							</th>
						</tr>
						<tr>
							<th>责令限期改正通知书</th>
						</tr>
						<tr>
							<td>
								<xsl:value-of select="//jznw" />
							</td>
						</tr>
					</table>
					<br>&#160;</br>
					<table class="table-content">
						<tr>
							<td>
							<u><xsl:value-of select="//nsrmc" /></u>：（纳税人识别号：<xsl:value-of select="//nsrsbh" />）
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								你（单位）<xsl:value-of select="//wfxwlx" />。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								根据<xsl:value-of select="//flyj" />，限你（单位）于<xsl:value-of select="//zlxqgzqx" />前<xsl:value-of select="//zlxqgznr" />。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								如对本通知不服，可自收到本通知之日起六十日内依法向<xsl:value-of select="//fyjgmc" />申请行政复议，或者自收到本通知之日起，三个月内依法向人民法院起诉。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
							</td>
						</tr>
						<tr>
							<td>
								&#160;
							</td>
						</tr>
						<tr>
							<td style="text-align:right;">
								<xsl:value-of select="//wszzrq" />
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
