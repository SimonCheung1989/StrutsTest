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
								依据：<xsl:value-of select="//ns2:yj" />
							</td>
						</tr>
						<tr>
							<td>
								&#160;
								&#160;
								&#160;
								&#160;
								通知内容：税务机关根据你（单位）办理相关登记时所申报的行业、经营范围和应税行为（财产）等信息，以及在税收征管工作中依法取得的其他相关信息，对你（单位）具有申报纳税（扣缴或代征）义务的税（费）种进行了认定。具体信息如下：
							</td>
						</tr>
					</table>
					<table class="table-grid">
							<tr>
								<td width="8%">征收项目</td>
								<td width="8%">征收品目</td>
								<td width="8%">征收子目</td>
								<td width="8%">行业</td>
								<td width="8%">征收代理方式</td>
								<td width="8%">认定有效期起</td>
								<td width="8%">认定有效期止</td>
								<td width="8%">税率</td>
								<td width="8%">申报期限</td>
								<td width="8%">纳税期限</td>
								<td width="8%">缴款期限</td>
								<td width="8%">预算科目</td>
								<td>预算分配比例</td>
							</tr>
							<xsl:for-each select="SfzrdtzsxxForm/ns2:szxxGrid/ns2:szxxGridlb">
								<tr>
									<td>
										<xsl:value-of select="ns2:zsxmmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:zspmmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:zszmmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:hymc" />
									</td>
									<td>
										<xsl:value-of select="ns2:zsdlfsmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:rdyxqq" />
									</td>
									<td>
										<xsl:value-of select="ns2:rdyxqz" />
									</td>
									<td>
										<xsl:value-of select="ns2:zsl" />
									</td>
									<td>
										<xsl:value-of select="ns2:sbqxmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:nsqxmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:jkqxmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:yskmmc" />
									</td>
									<td>
										<xsl:value-of select="ns2:ysfpblmc" />
									</td>
								</tr>
							</xsl:for-each>
					</table>
					<table class="table-content">
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
							</td>
						</tr>
						<tr>
							<td>
								&#160;
							</td>
						</tr>
						<tr>
							<td style="width:50%;">
								&#160;
							</td>
							<td>
								税务机关：<xsl:value-of select="//ns2:jdswjgmc" />
							</td>
						</tr>
						<tr>
							<td>
								&#160;
							</td>
							<td>
								<xsl:value-of select="//ns2:year" />
								年
								<xsl:value-of select="//ns2:month" />
								月
								<xsl:value-of select="//ns2:day" />
								日
							</td>
						</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
