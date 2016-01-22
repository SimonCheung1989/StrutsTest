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
				<div style="width:100%; height:100%;">
					<table class="table-head">
						<tr>
							<th>
								<xsl:value-of select="//ns2:slswjgmc" />
							</th>
						</tr>
						<tr>
							<th>税务事项通知书</th>
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
    							&#160;
    							&#160;
    							&#160;
    							&#160;
    							事由：发票真伪鉴定结果通知
							</td>
						</tr>
						<tr>
							<td>
    							&#160;
    							&#160;
    							&#160;
    							&#160;
    							依据：《中华人民共和国发票管理办法实施细则》（国家税务总局令第25号）第三十三条
							</td>
						</tr>
						<tr>
							<td>
    							&#160;
    							&#160;
    							&#160;
    							&#160;
								    通知内容：你（单位）于
								&#160;
								<xsl:value-of select="//ns2:slYear" />
								&#160;
								年
								&#160;
								<xsl:value-of select="//ns2:slMonth" />
								&#160;
								月
								&#160;
								<xsl:value-of select="//ns2:slDay" />
								&#160;
								日请了发票真伪鉴定事项，共提交待鉴定发票
								&#160;
								<xsl:value-of select="//ns2:slDay" />
								&#160;
								份。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								经鉴定，发票真伪鉴定结果如下：
							</td>
						</tr>
					</table>
					<table class="table-grid">
						<tr>
							<td width="20%">发票名称</td>
							<td width="10%">发票代码</td>
							<td width="5%">发票起始号码</td>
							<td width="5%">发票终止号码</td>
							<td width="5%">份数</td>
							<td width="10%">纳税人识别号</td>
							<td width="20%">纳税人名称</td>
							<td width="10%">日期</td>
							<td width="5%">金额</td>
							<td width="10%">鉴定结果</td>
						</tr>
						<xsl:for-each
							select="HXZGFPZWJDDY00081Request/ns2:ZwjdjgtzsGird/ns2:fpzwjdmxjgList/ns2:fpzwjdmxjgListlb">
							<tr>
								<td>
									<xsl:value-of select="ns2:fpzlmc" />
								</td>
								<td>
									<xsl:value-of select="ns2:fpDm" />
								</td>
								<td>
									<xsl:value-of select="ns2:fpqshm" />
								</td>
								<td>
									<xsl:value-of select="ns2:fpzzhm" />
								</td>
								<td>
									<xsl:value-of select="ns2:fpfs" />
								</td>
								<td>
									<xsl:value-of select="ns2:nsrsbh" />
								</td>
								<td>
									<xsl:value-of select="ns2:nsrmc" />
								</td>
								<td>
									<xsl:value-of select="ns2:kjrq" />
								</td>
								<td>
									<xsl:value-of select="ns2:kpje" />
								</td>
								<td>
									<xsl:value-of select="ns2:fpjdjgmc" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<table class="table-head" style="text-align:left;">
						<tr>
							<td colspan="2">
								&#160;
								&#160;
								&#160;
								&#160;
								特此通知。
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								鉴定机关：
								<xsl:value-of select="//ns2:jdswjgmc" />
							</td>
							<td>
								鉴定人：
								<xsl:value-of select="//ns2:jdrmc" />
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
