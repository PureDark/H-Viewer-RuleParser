
$(document).ready(function(e) {
	
	$("#btnParseRule").click(function(e) {
		var site = $("#site").val();
		try { 
			$("#site").val(JSON.stringify(JSON.parse(site), null, 4));
		} catch (e) {
			alert(e.message);
		} 
    });
	
	$("#btnParseUrl").click(function(e) {
		var paramUrl = $("#paramUrl").val();
        $.ajax({
		     type: "POST",
		     url: "servlet/RuleTester",
			 data: {
				 	action: 'getGeneratedIndexUrl',
				 	paramUrl: paramUrl
			 },
		     dataType: "text",
		     success: function(result){
		 		$("#targetUrl").val(result);
		    },
			error:function(xhr){
				alert("网址生成失败")
			}
		});
    });
	
	$("#btnGenerateQrCode").click(function(e) {
		var site = $("#site").val();
        $.ajax({
		     type: "POST",
		     url: "servlet/RuleTester",
			 data: {
				 	action: 'generateQrCode',
				 	site: site
			 },
		     dataType: "text",
		     success: function(result){
				 $("#qrcode").attr("src", "data:image/png;base64," + result);
		    },
			error:function(xhr){
				alert("二维码生成失败")
			}
		});
    });
	
	$("#btnTestRule").click(function(e) {
		var getHtml = ($(this).attr("getHtml")=="true");
		var action = (getHtml==true)?"getHtml":"getList";
		var site = $("#site").val();
		var targetUrl = $("#targetUrl").val();
	    $.ajax({
		     type: "POST",
		     url: "servlet/RuleTester",
			 data: {
				 	action: action,
				 	site: site,
					targetUrl: targetUrl
			 },
		     dataType: "text",
		     success: function(result){
		 		try { 
					$("#display").val(JSON.stringify(JSON.parse(result), null, 4));
		 		} catch (e) {
		 			$("#display").val(result);
		 		} 
		    },
			error:function(xhr){
				 $("#display").val(xhr);
			}
		});
    });
	
});