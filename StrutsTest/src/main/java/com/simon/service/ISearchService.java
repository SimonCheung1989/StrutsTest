package com.simon.service;

import java.util.List;

import com.simon.gdgs.tzgg.vo.TzggVO;

/**
 * 网页抓取服务接口
 * @author Administrator
 *
 */
public interface ISearchService {

	public List search(String keywords);

	public TzggVO getDetail(String uuid);
}
