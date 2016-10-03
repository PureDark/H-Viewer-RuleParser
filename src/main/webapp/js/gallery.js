
$(document).ready(function(e) {
	
	$("#btnParseRule").click(function(e) {
		var site = $("#site").val();
		$("#site").val(JSON.stringify(JSON.parse(site), null, 4));
		var collection = $("#collection").val();
		$("#collection").val(JSON.stringify(JSON.parse(collection), null, 4));
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
		var site = $("#site").val();
		var collection = $("#collection").val();
        $.ajax({
		     type: "POST",
		     url: "servlet/RuleTester",
			 data: {
				 	action: 'getDetail',
				 	site: site,
					collection: collection
				 	},
		     dataType: "json",
		     success: function(result){
				 $("#display").val(JSON.stringify(result, null, 4));
		    },
			error:function(xhr){
				 $("#display").val(xhr);
			}
		});
    });
	
});