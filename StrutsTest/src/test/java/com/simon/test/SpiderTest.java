package com.simon.test;

import org.junit.Test;
import com.simon.spider.IHtmlParser;
import com.simon.spider.ILinkFilter;
import com.simon.spider.Spider;
import com.simon.spider.UrlVO;

public class SpiderTest {
	@Test
	public void test(){
		final String seedsArray[] = {"http://servyou.yunxuetang.cn/exam/examquestionpreview.htm?UserExamID=3c7573cf-6b3b-4419-83ab-9d85c01c8377&ExamArrangeID=c12748cc-6818-4565-97bc-688585e5c205"};
		final Spider spider = new Spider();
		final int maxDeepth = 2;
		//url过滤器
		final ILinkFilter filter = new ILinkFilter() {
			@Override
			public boolean accept(UrlVO urlVO) {
				//只爬行该站点，并且爬行深度不超过最大值
				if (urlVO.getUrl().equals(
						"http://servyou.yunxuetang.cn/exam/examquestionpreview.htm?UserExamID=3c7573cf-6b3b-4419-83ab-9d85c01c8377&ExamArrangeID=c12748cc-6818-4565-97bc-688585e5c205")
						&& urlVO.getLevel() < maxDeepth) {
					return true;
				}
				return false;
			}
		};
		//html解析实现类（具体业务具体实现）
		final IHtmlParser htmlParser = new IHtmlParser(){
			@Override
			public void parseHtmlContent(String url, String htmlContent) {
				System.out.println(htmlContent);
			}
		};
		spider.crawling(seedsArray, filter, htmlParser);
	}

}
