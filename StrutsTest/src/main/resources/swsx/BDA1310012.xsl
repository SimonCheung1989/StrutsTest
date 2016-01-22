<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="http://www.chinatax.gov.cn/dataspec/">
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
								准予税务行政许可决定书
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
								<u><xsl:value-of select="//ns2:sqrxm" /></u>：
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								你（单位）于<xsl:value-of select="//ns2:sqrqY" />年<xsl:value-of select="//ns2:sqrqM" />月<xsl:value-of select="//ns2:sqrqD" />日提出的<xsl:value-of select="//ns2:swxzxkxmmc" />申请，我机关已经于<xsl:value-of select="//ns2:slrqY" />年<xsl:value-of select="//ns2:slrqM" />月<xsl:value-of select="//ns2:slrqD" />日受理。经审查，决定准予你（你单位）取得该项许可。
							</td>
						</tr>
						<tr>
							<td>&#160;</td>
						</tr>
						<tr>
							<td>&#160;</td>
						</tr>
						<tr>
							<td style="text-align:right;"> （税务机关印章或许可专用章）</td>
						</tr>
						<tr>
							<td style="text-align:right;">
								<xsl:value-of select="//ns2:nowY" />年<xsl:value-of select="//ns2:nowM" />月<xsl:value-of select="//ns2:nowD" />日
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
