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
								<xsl:value-of select="//ns2:slswjgmc" />
							</th>
						</tr>
						<tr>
							<th>
								税务行政处罚决定书（<xsl:value-of select="//ns2:zg" />）
							</th>
						</tr>
						<tr>
							<td>
								<xsl:value-of select="//ns2:swjgjg" />
							</td>
						</tr>
					</table>
					<table class="table-grid" style="text-align:left;">
							<tr>
								<td colspan="2">被处罚人名称</td>
								<td colspan="3"><xsl:value-of select="//ns2:bcfrmc" /></td>
							</tr>
							<tr>
								<td colspan="2" style="width:20%;">被处罚人证件名称</td>
								<td style="width:30%;"><xsl:value-of select="//ns2:bcfrzjlxDm" /></td>
								<td style="width:20%;">证件号码</td>
								<td><xsl:value-of select="//ns2:zjhm" /></td>
							</tr>
							<tr>
								<td colspan="2">处罚地点</td>
								<td><xsl:value-of select="//ns2:cfdd" /></td>
								<td>处罚时间</td>
								<td><xsl:value-of select="//ns2:cfrq" /></td>
							</tr>
							<tr style="height:400px;">
								<td colspan="2">违法事实及处罚依据</td>
								<td colspan="3" valign="top"><xsl:value-of select="//ns2:wfssjcfyj" /></td>
							</tr>
							<tr>
								<td colspan="2">缴纳方式</td>
								<td colspan="3"><xsl:value-of select="//ns2:jnfs" /></td>
							</tr>
							<tr>
								<td colspan="2">罚款金额</td>
								<td colspan="3"><xsl:value-of select="//ns2:fk1" /></td>
							</tr>
							<tr>
								<td>告知事项</td>
								<td colspan="4">
									1.当事人应终止违法行为并予以纠正；
									<br>&#160;</br>
									2.如对本决定不服，可以自收到本决定书之日起60日内可以依法向珠海市国家税务局申请行政复议，或者自收到本决议书之日起6个月内依法向人民法院起诉；
									<br>&#160;</br>
									3.到期不缴纳罚款的，税务机关可自缴款期限届满次日起每日按罚款数额的3%加处罚款；
									<br>&#160;</br>
									4.对处罚决定逾期不申请行政复议也不向人民法院起诉、又不履行的，税务机关将依法采取强制执行措施或者申请人民法院强制执行。
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<table class="table-content">
										<tr>
											<td style="width:25%;">
												经办人：
											</td>
											<td style="width:25%;">
												负责人：
											</td>
											<td>
												税务机关（签章）
											</td>
										</tr>
										<tr>
											<td><xsl:value-of select="//ns2:jbrblrq" /></td>
											<td><xsl:value-of select="//ns2:fzrsprq" /></td>
											<td><xsl:value-of select="//ns2:wszzrq" /></td>
										</tr>
									</table>
								</td>
							</tr>
					</table>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
