
$(document).ready(function(e) {
	
	$("#btnParseRule").click(function(e) {
		var site = $("#site").val();
		try { 
			$("#site").val(JSON.stringify(JSON.parse(site), null, 4));
		} catch (e) {
			alert(e.message);
		} 
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
		     dataType: "json",
		     success: function(result){
		 		try { 
					$("#display").val(JSON.stringify(result, null, 4));
		 		} catch (e) {
		 			$("#display").val(e.message);
		 		} 
		    },
			error:function(xhr){
				 $("#display").val(xhr);
			}
		});
    });
	
});