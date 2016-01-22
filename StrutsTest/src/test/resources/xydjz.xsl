<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:msxsl="urn:schemas-microsoft-com:xslt">
<msxsl:script language="JScript"><![CDATA[
function GetHTML()
{
return "a<hr>a<b>b<font color=red>bb</font></b>aa";//可以不遵守XML规范
}
]]></msxsl:script>

	<xsl:template match="/ROOT">		
		<html>
		<body>
		<div style="width:100%; height:100%;">
				<table style="width: 100%; height: 100%;">
						<tr style="height: 90px;">
							<td colspan="2" valign="bottom" style="font-family: 宋体; font-size:18px;">
								&#160;&#160;&#160;&#160;&#160;
								&#160;&#160;&#160;&#160;&#160;
								<label>证书编号：<xsl:value-of select="./ZSBH" /></label>
							</td>
						</tr>
						<tr style="height: 100px;">
							<td colspan="2" valign="bottom" align="center" style="font-family: 宋体; font-size:40px; color:#A4141D">
								<xsl:value-of select="./XYDJ" />级纳税信用等级证书
							</td>
						</tr>
						<tr style="height: 310px;">
							<td colspan="2">
								<table style="height:100%; width:100%;">
									<tr style="height: 100px;">
										<td style="width:140px;"></td>
										<td valign="bottom" align="left" style="font-family: 宋体; font-size:35px;">
											<label style="text-decoration:underline;"><xsl:value-of select="./NSRMC" /></label>:
										</td>
									</tr>
									<tr style="height: 210px;">
										<td></td>
										<td valign="center" style="font-family: 宋体; font-size:30px; line-height:20px;">
											&#160;&#160;<label>经<xsl:value-of select="./CSMC" />国家税务局、<xsl:value-of select="./CSMC" />地方税务局联合评定为</label>
											<br>&#160;</br>
											<br>&#160;</br>
											<label><xsl:value-of select="./ND" />年度纳税信用等级<xsl:value-of select="./XYDJ" />级纳税人。</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="height: 70px;">
							<td colspan="2" valign="bottom" style="font-family: 宋体; font-size:30px;">
								&#160;&#160;
								&#160;&#160;
								&#160;&#160;
								&#160;&#160;
								&#160;&#160;
								&#160;&#160;
								&#160;&#160;
								&#160;
								<label><xsl:value-of select="./CSMC" />国家税务局<!-- &#160;<xsl:value-of select="./CSMC" />地方税务局 --></label>
							</td>
						</tr>
						<tr style="height: 204px;">
							<td width="60px;"></td>
							<td valign="bottom" style="font-family: 宋体; font-size:12px;">纳税信用等级实行动态管理，评定等级如有变动，请以国、地税局官方网站公告或查验网站实时信息为准（扫描二维码进入查验页面）。</td>
						</tr>
				</table>
		</div>
		</body>
		</html>
	</xsl:template>


</xsl:stylesheet>