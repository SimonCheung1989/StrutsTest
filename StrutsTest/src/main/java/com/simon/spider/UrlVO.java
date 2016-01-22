package com.simon.spider;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * URLVO
 */
public class UrlVO {
	
	private String url;	//url
	private int level;	//相对于种子url的层级，种子url为0
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UrlVO){
			UrlVO urlVO = (UrlVO) obj;
			if(this.getUrl()!=null && this.getUrl().equals(urlVO.getUrl())){
				return true;
			}
			return false;
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return this.getUrl().hashCode();
	}
	
	
}
