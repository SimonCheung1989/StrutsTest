$(function(){
	var obj = {
			key1:"val1",
			key2:"val2",
			key3:"val3",
			key4:"val4"
	}
	alert($.param(obj));
	
	alert(decodeURIComponent($.param(obj)));
});


var global_v1 = {
		"case" : 'ssss'
};

window.global_v2 = "123";