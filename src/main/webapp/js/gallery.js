
$(document).ready(function(e) {
	
	$("#btnParseRule").click(function(e) {
		var site = $("#site").val();
		$("#site").val(JSON.stringify(JSON.parse(site), null, 4));
		var collection = $("#collection").val();
		$("#collection").val(JSON.stringify(JSON.parse(collection), null, 4));
    });
	
	$("#btnTestRule").click(function(e) {
		var site = $("#site").val();
		var collection = $("#collection").val();
        $.ajax({
		     type: "GET",
		     url: "servlet/SiteTester",
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