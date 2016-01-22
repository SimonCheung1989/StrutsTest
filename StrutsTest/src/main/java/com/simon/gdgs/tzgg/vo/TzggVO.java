package com.simon.gdgs.tzgg.vo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 */
public class TzggVO {
	@Field("id")
	private String uuid;
	@Field("tzgg_ggbt")
	private String ggbt;
	@Field("tzgg_ggrq")
	private String ggrq;
	@Field("tzgg_ggly")
	private String ggly;
	@Field("tzgg_ggzz")
	private String ggzz;
	@Field("tzgg_ggnr")
	private String ggnr;
	@Field("tzgg_ggfj")
	private String ggfj;
	@Field("tzgg_gglj")
	private String gglj;
	private String gghashcode;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getGgbt() {
		return ggbt;
	}
	public void setGgbt(String ggbt) {
		this.ggbt = ggbt;
	}
	public String getGgrq() {
		return ggrq;
	}
	public void setGgrq(String ggrq) {
		this.ggrq = ggrq;
	}
	public String getGgly() {
		return ggly;
	}
	public void setGgly(String ggly) {
		this.ggly = ggly;
	}
	public String getGgzz() {
		return ggzz;
	}
	public void setGgzz(String ggzz) {
		this.ggzz = ggzz;
	}
	public String getGgnr() {
		return ggnr;
	}
	public void setGgnr(String ggnr) {
		this.ggnr = ggnr;
	}
	public String getGgfj() {
		return ggfj;
	}
	public void setGgfj(String ggfj) {
		this.ggfj = ggfj;
	}
	public String getGglj() {
		return gglj;
	}
	public void setGglj(String gglj) {
		this.gglj = gglj;
	}
	public String getGghashcode() {
		return gghashcode;
	}
	public void setGghashcode(String gghashcode) {
		this.gghashcode = gghashcode;
	}
}
