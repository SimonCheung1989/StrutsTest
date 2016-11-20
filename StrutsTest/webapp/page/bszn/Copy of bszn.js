$(function(){
//	$(".bszn_title").html("标题");
	initTitle();
	
	$(".bszn_title>p").click(function(){
//		$(this).next("div.title_1").slideToggle().siblings("div.title_1").slideUp();
		if($(this).next("div.title_1").is(":visible")){
			$(this).next("div.title_1").find("div.title_2").slideUp();
		}
		$(this).next("div.title_1").slideToggle(500);
	});
	
	$(".title_1>p").click(function(){
//		$(this).next("div.title_2").slideToggle().siblings("div.title_2").slideUp();
		$(this).next("div.title_2").slideToggle(500);
	});
	
	$(".title_1 a").click(function(){
		$(".title_1 a").removeClass("current");
		$(this).addClass("current");
	});
	
});


function showContent(cdid, cdmc){
	_$currentCdid = cdid;
	$.ajax({
		url: "/bszn/bszn_queryBsznByCdid.do",
		data: {
			cdid: cdid
		},
		type: "post",
		success: function(res){
			var result=mini.decode(res);
			if(result.success){
				var content = result.data["nr"];
				if(content==null || content==""){
					content = "<p style='text-align: center;'><strong><span style='font-size:24px'>"+cdmc+"</span></strong></p>";
				}
				$(".bszn_content").html(content);
			}
		}
	});
}

function initTitle(){
	$.ajax({
		url: "/bszn/bszn_queryBszncd.do",
		data: {},
		type: "post",
		async: false,
		success: function(res){
			var result=mini.decode(res);
			if(result.success){
				var title1Json = result.data;
				if(title1Json!=null){
					var htmlCode = "";
					for(var i=0, len=title1Json.length; i<len; i++){
					    var title2Json = title1Json[i]["children"];
					    var title2HtmlCode = "";
					    if(title2Json!=null && title2Json!=''){
					    	for(var j=0, jLen=title2Json.length; j<jLen; j++){
					    		var title3HtmlCode = "";
					    		var title3Json = title2Json[j]["children"];
					    		if(title3Json!=null && title3Json.length>0){
					    			for(var k=0, kLen=title3Json.length; k<kLen; k++){
					    				title3HtmlCode += "<a href='javascript:showContent(\""+title3Json[k]["cdid"]+"\", \""+title3Json[k]["cdmc"]+"\")'>"+title3Json[k]["cdmc"]+"</a>";
					    			}
					    		}
					    		title2HtmlCode += "<p>"+title2Json[j]["cdmc"]+"</p>"
					    			          + "<div class='title_2'>"
					    			          + title3HtmlCode
					    			          + "</div>";
					    	}
					    }
						htmlCode += "<p>"+title1Json[i]["cdmc"]+"</p>"
						         + "<div class='title_1'>"
						         + title2HtmlCode
						         + "</div>";
					}
					
					htmlCode += "<p onclick=\"javascript:openWinPageByURL('/ZhBsfwtWeb/pages/bsxz/bsxz.html','办税下载','600','500')\" style='background-color:#209B63;'>办税下载</p>";
					$(".bszn_title").html(htmlCode);
				}
			}
		}
	});
}

function downloadFile(bdxh){
	window.open("/bdxz/bdxz_downloadBd.do?bdxh="+bdxh);
}

function openWinPageByURL(url,title,wid,hei) {
	mini.open( {
		title : title,
		showMaxButton : true,
		url : url,
		showModal : true,
		width : wid||1010,
		height : hei||680
	});
}