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
								税务文书送达回证
							</th>
						</tr>
						<tr>
							<td style="text-align:right;">
								回证编号：<xsl:value-of select="//ns2:hzbh" />
							</td>
						</tr>
					</table>
					<table class="table-grid">
							<tr style="height: 80px;">
								<td style="width:30%;">
									送达文书名称
								</td>
								<td>
									<xsl:value-of select="//ns2:dzbzdszlmc" />&#160;<xsl:value-of select="//ns2:wszg" />
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									受送达人
								</td>
								<td>
									<xsl:value-of select="//ns2:nsrmc" />
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									送达地点
								</td>
								<td>
									<xsl:value-of select="//ns2:nsrzz" />
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									受送达人签名或盖章
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									代收人代收理由、签名或盖章
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									受送达人拒收理由
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									见证人签名或盖章
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									送达人签名或盖章
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
							<tr style="height: 80px;">
								<td>
									填发税务机关
								</td>
								<td style="text-align:right; vertical-align: bottom;">
									（签章）
									&#160;
									&#160;
									年
									&#160;
									&#160;
									月
									&#160;
									&#160;
									日
									&#160;
									&#160;
									时
									&#160;
									&#160;
									分
								</td>
							</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
